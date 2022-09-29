package com.example.anifox.presentation.reader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.anifox.common.adapters.reader.MangaReaderViewTarget
import com.example.anifox.databinding.FragmentReaderBinding
import com.example.anifox.util.Constants.USER_AGENT
import org.jsoup.Jsoup

class ReaderFragment : Fragment() {

    private val args: ReaderFragmentArgs by navArgs()
    private var _binding: FragmentReaderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReaderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeOnState()
        initListeners()
    }

    private fun initListeners (){
        var tempUrl = ""
        val thread = Thread {
            val connection =
                Jsoup.connect(args.url)
            connection.userAgent(USER_AGENT)
            connection.referrer(args.url.take(19))
            connection.timeout(0)

            val document = connection.get()
            println("DOC = ${document.body()}")

            tempUrl = document.select("img.reader-viewer-img").first()!!.absUrl("src")
        }

        thread.start()
        thread.join()

        if(!thread.isAlive){
            val url = GlideUrl(
                tempUrl, LazyHeaders.Builder()
                    .addHeader(
                        "User-Agent",
                        USER_AGENT
                    )
                    .addHeader("Referer", args.url.take(19))
                    .build()
            )
            Glide
                .with(requireContext())
                .download(url)
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