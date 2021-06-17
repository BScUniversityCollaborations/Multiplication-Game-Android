package com.unipi.p17172p17168p17164.multiplicationgame.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.unipi.p17172p17168p17164.multiplicationgame.R
import com.unipi.p17172p17168p17164.multiplicationgame.databinding.SnackbarSuccessBinding
import com.unipi.p17172p17168p17164.multiplicationgame.extension.findSuitableParent

class SnackBarSuccessClass(
    parent: ViewGroup,
    content: SnackBarSuccessView
) : BaseTransientBottomBar<SnackBarSuccessClass>(parent, content, content) {
    private val binding: SnackbarSuccessBinding = SnackbarSuccessBinding.inflate(
        LayoutInflater.from(view.context)
    )

    init {
        getView().setBackgroundColor(ContextCompat.getColor(view.context, android.R.color.transparent))
        getView().setPadding(0, 0, 0, 0)

        binding.btnDismiss.setOnClickListener { dismiss() }
        animationMode = ANIMATION_MODE_SLIDE
    }

    companion object {

        fun make(view: View, title: String, body: String): SnackBarSuccessClass {

            // First we find a suitable parent for our custom view
            val parent = view.findSuitableParent() ?: throw IllegalArgumentException(
                "No suitable parent found from the given view. Please provide a valid view."
            )

            // We inflate our custom view
            val customView = LayoutInflater.from(view.context).inflate(
                R.layout.layout_snackbar_success,
                parent,
                false
            ) as SnackBarSuccessView

            val binding: SnackbarSuccessBinding = SnackbarSuccessBinding.inflate(
                LayoutInflater.from(view.context)
            )

            binding.apply {
                textViewHeader.text = title
                textViewHeader2.text = body
            }

            // We create and return our Snack-bar
            return SnackBarSuccessClass(
                parent,
                customView
            )
        }

    }

}