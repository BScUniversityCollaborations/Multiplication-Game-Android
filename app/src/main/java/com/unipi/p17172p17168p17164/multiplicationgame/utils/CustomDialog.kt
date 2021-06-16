package com.unipi.p17172p17168p17164.multiplicationgame.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import com.unipi.p17172p17168p17164.multiplicationgame.databinding.DialogTipBinding

class CustomDialog {
    fun showTip(context: Context, title: String, body: String) {
        val dialog = Dialog(context)
        val binding: DialogTipBinding = DialogTipBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(binding.root)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog.window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
        )

        binding.apply {
            textViewHeader.text = title
            textViewBody.text = body
            btnDismiss.setOnClickListener { dialog.dismiss() }
        }
        dialog.show()
    }
}
