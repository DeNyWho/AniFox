package com.example.anifox.common.dialogs

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

open class MaterialDialogFragment : DialogFragment() {

    private var dialogView: View? = null

    @SuppressLint("UseGetLayoutInflater")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext(), theme).apply {
            dialogView = onCreateView(LayoutInflater.from(requireContext()), null, savedInstanceState)

            dialogView?.let { onViewCreated(it, savedInstanceState) }
            setView(dialogView)
        }.create()
    }

    override fun getView(): View? {
        return dialogView
    }
}