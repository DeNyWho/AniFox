package com.example.anifox.presentation.reader

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.anifox.common.adapters.reader.MangaReaderViewTarget
import com.example.anifox.common.dialogs.reader.ContentsDialogFragment
import com.example.anifox.common.listeners.ItemClickListenerReaderChapters
import com.example.anifox.databinding.FragmentReaderBinding
import com.example.anifox.domain.model.support.ChapterSupport
import com.example.anifox.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup


class ReaderFragment : Fragment() {

    private val args: ReaderFragmentArgs by navArgs()
    private var _binding: FragmentReaderBinding? = null
    private val binding get() = _binding!!

    private var url = ""
    private var page = 1
    private var chapter: ChapterSupport = ChapterSupport()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReaderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        url = args.url
        jsoup(args.url)
        observeOnState()
        initListeners()
    }

    private fun readerListener(link: String){
        chapter = normalizeUrls(link)
        page = 1
        url = "${chapter.tempUrl}/vol${chapter.vol}/${chapter.chapter}?page=$page"

        jsoup(url)
    }

    private fun initListeners (){

        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.ivChapters.setOnClickListener {
            ContentsDialogFragment(
                manga = args.manga,
                onClick = object : ItemClickListenerReaderChapters {
                    override fun navigationToReader(url: String) {
                        readerListener(url)
                    }
                }
            ).show(parentFragmentManager, "contentsDialog")
        }

        binding.ivRead.setOnTouchListener (
            ViewOnTouchListener()
        )

        binding.ivNextChapter.setOnClickListener {
            nextChapter()
        }

        binding.ivBackChapter.setOnClickListener {
            prevChapter()
        }
    }

    private fun nextChapter(){
        chapter = normalizeUrls(url)
        page++
        url = "${chapter.tempUrl}/vol${chapter.vol}/${chapter.chapter}?page=$page"

        jsoup(url)
    }

    private fun prevChapter(){
        chapter = normalizeUrls(url)
        if(page > 1 ) page--
        url = "${chapter.tempUrl}/vol${chapter.vol}/${chapter.chapter}?page=$page"
        if(page > 0) {
            jsoup(url)
        }
    }

    inner class ViewOnTouchListener : View.OnTouchListener {
        private var gestureDetector =
            GestureDetector(requireContext(), object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    val x = e.x
                    if(x > view!!.width/ 2) {
//                      Right
                        nextChapter()
                    } else {
//                      Left
                        prevChapter()
                    }
                    return super.onSingleTapUp(e)
                }
            })

        @SuppressLint("ClickableViewAccessibility")
        override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
            gestureDetector.onTouchEvent(motionEvent)
            return false
        }
    }

    private fun normalizeUrls(url: String): ChapterSupport {
        val temp = mutableListOf<String>()
        val number = try {
            url.takeLast(3).toInt()
        } catch (e: Exception){
            try {
                url.takeLast(2).toInt()
            } catch (e: Exception){
                url.takeLast(1).toInt()
            }
        }

        temp.addAll(url.split("/vol").filter { it.contains("page") })
        val tempUrl = url.split("/vol").filter { !it.contains("page") }[0]
        val x = mutableListOf<String>()
        temp.forEach { x.addAll(it.split("?page=$number").filter { it.isNotEmpty() }) }
        val z = mutableListOf<String>()
        x.forEach {
            z.addAll(it.split("/").filter { it.isNotEmpty() })
        }

        var v = ChapterSupport()
            try {
                v = ChapterSupport(
                    vol = z[0].toInt(),
                    chapter = z[1].toInt(),
                    tempUrl = tempUrl,
                )
            } catch (e: Exception) {
                println("URL ERROR = ${e.message}")
            }
        return v
    }

    private fun jsoup(url: String) {
        var tempUrl = ""
        runBlocking {
            val job = launch {
                val connection =
                    Jsoup.connect(url)
                connection.userAgent(Constants.USER_AGENT)
                connection.referrer(url.take(19))
                connection.timeout(0)

                withContext(Dispatchers.IO) {
                    val document = connection.get()
                    tempUrl =
                        document.select("img.reader-viewer-img").first()!!.absUrl("src")
                }
            }

            job.start()
            job.join()
            val imageUrl = GlideUrl(
                tempUrl, LazyHeaders.Builder()
                    .addHeader(
                        "User-Agent",
                        Constants.USER_AGENT
                    )
                    .addHeader("Referer", url.take(19))
                    .build()
            )
            Glide
                .with(requireContext())
                .download(imageUrl)
                .timeout(15000)
                .into(MangaReaderViewTarget(binding))
        }
    }

    private fun observeOnState (){

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}