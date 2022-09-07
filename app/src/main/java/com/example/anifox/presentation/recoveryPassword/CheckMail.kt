package com.example.anifox.presentation.recoveryPassword

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.anifox.R
import com.example.anifox.databinding.FragmentCheckMailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import java.util.*


@AndroidEntryPoint
class CheckMail : Fragment() {
    private val args: CheckMailArgs by navArgs()

    private var _binding: FragmentCheckMailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ResetPasswordViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCheckMailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
    }

    private fun observeOnState(){
        viewModel.state.onEach { state ->

            if(state.passwordConfirmationState.data?.isNotEmpty() == true) {
                findNavController().navigate(R.id.action_checkMail_to_changePassword)
            }

        }.launchWhenStarted()
    }

    private fun initListeners(){
        val handler = Handler()
        val timer = Timer()

        val task: TimerTask = object : TimerTask() {
            override fun run() {
                handler.post {
                    viewModel.confirmationPassword(args.email)
                    observeOnState()
                    println("WTF?")
                }
            }
        }

        timer.scheduleAtFixedRate(task, 0, "10000".toLong()) // Executes the task every 50 seconds.


        binding.abOpenMail.setOnClickListener {
            try {

                val emailAppLauncherIntents: MutableList<Intent?> = ArrayList()

                val emailAppIntent = Intent(Intent.ACTION_SENDTO)
                emailAppIntent.data = Uri.parse("mailto:")
                val packageManager = requireActivity().packageManager

                val emailApps = packageManager.queryIntentActivities(
                    emailAppIntent,
                    PackageManager.MATCH_ALL
                )

                for (resolveInfo in emailApps) {
                    val packageName = resolveInfo.activityInfo.packageName
                    val launchIntent = packageManager.getLaunchIntentForPackage(packageName)
                    emailAppLauncherIntents.add(launchIntent)
                }

                val chooserIntent = Intent.createChooser(Intent(), getString(R.string.choose_email))
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, emailAppLauncherIntents.toTypedArray())
                startActivity(chooserIntent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.error_mail_not_found),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.tvBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun <T> Flow<T>.launchWhenStarted() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted { collect () }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}