package com.unipi.p17172p17168p17164.multiplicationgame.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import com.unipi.p17172p17168p17164.multiplicationgame.R
import com.unipi.p17172p17168p17164.multiplicationgame.databinding.DialogExitBinding
import com.unipi.p17172p17168p17164.multiplicationgame.databinding.DialogSkipBinding
import com.unipi.p17172p17168p17164.multiplicationgame.databinding.DialogTimeOutBinding
import com.unipi.p17172p17168p17164.multiplicationgame.databinding.DialogTipBinding
import com.unipi.p17172p17168p17164.multiplicationgame.ui.activities.TableResultActivity
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class CustomDialog {
    fun showTip(context: Context, title: String, body: String) {
        val dialog = Dialog(context)
        val binding = DialogTipBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(binding.root)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window?.setWindowAnimations(R.style.DialogAnimation)
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

    fun showExitConfirmation(context: Context) {
        val dialog = Dialog(context)
        val binding = DialogExitBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(binding.root)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window?.setWindowAnimations(R.style.DialogAnimation)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog.window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
        )

        binding.apply {
            btnDismiss.setOnClickListener { dialog.dismiss() }
        }

        dialog.show()
    }

    fun showSkipConfirmation(context: Context) {
        val dialog = Dialog(context)
        val binding = DialogSkipBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(binding.root)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window?.setWindowAnimations(R.style.DialogAnimation)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog.window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
        )

        binding.apply {
            btnDismiss.setOnClickListener { dialog.dismiss() }
            btnYes.setOnClickListener {
                dialog.dismiss()
                when (context) {
                    is TableResultActivity -> TableResultActivity().goToNextEquation(true)
                }
            }
        }

        dialog.show()
    }

    fun showTimeOut(context: Context) {
        val dialog = Dialog(context)
        val binding = DialogTimeOutBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(binding.root)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setWindowAnimations(R.style.DialogAnimation)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog.window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
        )

        binding.apply {
            btnDismiss.setOnClickListener { dialog.dismiss() }
        }

        dialog.show()

        // Create an executor that executes tasks in a background thread.
        val backgroundExecutor: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()

        // Execute a task in the background thread after 5 seconds.
        backgroundExecutor.schedule({
            dialog.dismiss()
        }, 10, TimeUnit.SECONDS)

        dialog.setOnDismissListener{
            when (context) {
                is TableResultActivity -> TableResultActivity().goToNextEquation(false)
            }
        }
    }

    fun showTestResults(context: Context, title: String, body: String) {
        val dialog = Dialog(context)
        val binding = DialogTipBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(binding.root)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setWindowAnimations(R.style.DialogAnimation)
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
