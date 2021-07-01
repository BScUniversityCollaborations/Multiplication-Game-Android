package com.unipi.p17172p17168p17164.multiplicationgame.utils

import android.app.Activity
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

        dialog.setContentView(binding.root)

        binding.apply {
            textViewHeader.text = title
            textViewBody.text = body
            btnDismiss.setOnClickListener { dialog.dismiss() }
        }
        dialog.show()
    }

    fun showExitConfirmation(activity: Activity) {
        val dialog = Dialog(activity)
        val binding = DialogExitBinding.inflate(LayoutInflater.from(activity))

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window?.setWindowAnimations(R.style.DialogAnimation)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog.window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
        )

        dialog.setContentView(binding.root)

        binding.apply {
            btnDismiss.setOnClickListener { dialog.dismiss() }
            btnYes.setOnClickListener {
                dialog.dismiss()
                when (activity) {
                    is TableResultActivity -> {
                        activity.finish()
                        activity.overridePendingTransition(R.anim.anim_slide_in_right,
                            R.anim.anim_slide_out_right)
                    }
                }
            }
        }

        dialog.show()
    }

    fun showSkipConfirmation(activity: Activity) {
        val dialog = Dialog(activity)
        val binding = DialogSkipBinding.inflate(LayoutInflater.from(activity))

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window?.setWindowAnimations(R.style.DialogAnimation)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog.window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
        )

        dialog.setContentView(binding.root)

        binding.apply {
            btnDismiss.setOnClickListener { dialog.dismiss() }
            btnYes.setOnClickListener {
                dialog.dismiss()
                when (activity) {
                    is TableResultActivity -> activity.goToNextEquation(true)
                }
            }
        }

        dialog.show()
    }

    fun showTimeOut(activity: Activity) {
        val dialog = Dialog(activity)
        val binding = DialogTimeOutBinding.inflate(LayoutInflater.from(activity))

        // Dialog Properties
        dialog.run {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCanceledOnTouchOutside(false)
            window?.apply {
                // Add some show/hide dialog animations.
                setWindowAnimations(R.style.DialogAnimation)
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                // Remove the dim/shadow background behind the dialog.
                clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
                setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                )
            }

            setContentView(binding.root)

            // Set click event listeners.
            binding.apply {
                btnDismiss.setOnClickListener { dismiss() }
            }

            // When the dialog is dismiss go to next equation.
            setOnDismissListener {
                when (activity) {
                    is TableResultActivity -> activity.goToNextEquation(false)
                }
            }

            // Create an executor that executes tasks in a background thread.
            val backgroundExecutor: ScheduledExecutorService =
                Executors.newSingleThreadScheduledExecutor()

            // Execute a task in the background thread after X seconds.
            backgroundExecutor.schedule({
                dismiss()
            }, 10, TimeUnit.SECONDS)

            // Show dialog
            show()
        }
    }

    fun showTestResults(context: Context, title: String, body: String) {
        val dialog = Dialog(context)
        val binding = DialogTipBinding.inflate(LayoutInflater.from(context))

        // Dialog Properties
        dialog.run {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCanceledOnTouchOutside(false)
            window?.apply {
                // Add some show/hide dialog animations.
                setWindowAnimations(R.style.DialogAnimation)
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                // Remove the dim/shadow background behind the dialog.
                clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
                setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                )
            }

            setContentView(binding.root)

            // Set dialog title and body and also add click event listeners.
            binding.apply {
                textViewHeader.text = title
                textViewBody.text = body
                btnDismiss.setOnClickListener { dialog.dismiss() }
            }

            // Show dialog
            show()
        }
    }
}
