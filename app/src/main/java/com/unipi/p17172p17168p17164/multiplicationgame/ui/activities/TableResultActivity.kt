package com.unipi.p17172p17168p17164.multiplicationgame.ui.activities

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.content.res.AppCompatResources
import com.unipi.p17172.emarket.utils.SnackBarErrorClass
import com.unipi.p17172p17168p17164.multiplicationgame.R
import com.unipi.p17172p17168p17164.multiplicationgame.databinding.ActivityTableResultBinding
import com.unipi.p17172p17168p17164.multiplicationgame.services.BackgroundMusicService
import com.unipi.p17172p17168p17164.multiplicationgame.utils.Constants
import com.unipi.p17172p17168p17164.multiplicationgame.utils.CustomDialog
import com.unipi.p17172p17168p17164.multiplicationgame.utils.Utils


class TableResultActivity : BaseActivity() {
    // ~~~~~~~~ VARIABLES ~~~~~~~~
    private lateinit var binding: ActivityTableResultBinding
    private var numFirst: Int = 0
    private var numSecond: Int = 0
    private var limit: Int = 10
    private var correctResult: Int = -1

    private lateinit var timer: CountDownTimer
    private var timerState = TimerState.Running
    var millisInFuture: Long = Constants.DEFAULT_TEST_TIMER_DELAY //30 seconds
    var countDownInterval: Long = 1000 //1 second
    //Declare a variable to hold CountDownTimer remaining time
    private var timeRemaining: Long = 0

    enum class TimerState {
        Stopped, Paused, Running
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTableResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        checkIntentExtras()
        setupUI()
        startTimer()
        disableBackgroundMusic()
    }

    private fun checkIntentExtras() {
        intent.apply{
            if (intent.hasExtra(Constants.EXTRA_NUMBER_FIRST)
                && intent.hasExtra(Constants.EXTRA_NUMBER_SECOND)
                && intent.hasExtra(Constants.EXTRA_LIMIT)) {
                setEquationVariables()
            }
            else {
                // todo show error dialog
            }
        }
    }

    private fun setEquationVariables() {
        numFirst = intent.extras!!.getInt(Constants.EXTRA_NUMBER_FIRST)
        numSecond = intent.extras!!.getInt(Constants.EXTRA_NUMBER_SECOND)

        correctResult = numFirst * numSecond
        limit = intent.extras!!.getInt(Constants.EXTRA_LIMIT)

        binding.apply {
            txtViewNumberFirst.text = numFirst.toString()
            txtViewNumberSecond.text = numSecond.toString()
        }
    }

    private fun startTimer() {
        // Let's set the max progress value to the actual starting timer.
        binding.progressBarTimer.max = (millisInFuture / 1000).toInt()

        // Basically, if timer state is paused, we want to set the milliseconds to the
        // previous one before it was paused.
        if (timerState == TimerState.Paused) {
            millisInFuture = timeRemaining
        }
        else if (timerState == TimerState.Stopped)
            millisInFuture = Constants.DEFAULT_TEST_TIMER_DELAY

        timerState = TimerState.Running

        //Initialize a new CountDownTimer instance
        timer = object : CountDownTimer(millisInFuture, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                //do something in every tick
                if (timerState == TimerState.Paused
                    || timerState == TimerState.Stopped) {
                    //If the user request to cancel or paused the
                    //CountDownTimer we will cancel the current instance
                    cancel()
                }
                else {
                    //Display the remaining seconds to app interface
                    //1 second = 1000 milliseconds
                    binding.apply {
                        val secondsRemaining = millisUntilFinished / 1000
                        val currentSeconds = millisInFuture / 1000
                        val minutes = secondsRemaining / 60
                        val seconds = secondsRemaining % 60

                        progressBarTimer.progress = secondsRemaining.toInt()

                        if (currentSeconds / 2 == secondsRemaining) {
                            progressBarTimer.progressDrawable = AppCompatResources.getDrawable(this@TableResultActivity, R.drawable.progress_bar_middle)
                            txtViewTimerValue.setTextColor(getColor(R.color.colorYellow))
                        }
                        else if (currentSeconds / 4 == secondsRemaining) {
                            progressBarTimer.progressDrawable = AppCompatResources.getDrawable(this@TableResultActivity, R.drawable.progress_bar_low)
                            txtViewTimerValue.setTextColor(getColor(R.color.colorRedLight))
                        }

                        txtViewTimerValue.text =
                            String.format(
                                getString(R.string.txt_timer_format),
                                minutes,
                                seconds
                            )
                    }
                    //Put count down timer remaining time in a variable
                    timeRemaining = millisUntilFinished
                }
            }
            override fun onFinish() {
                //Do something when count down finished
                binding.progressBarTimer.progress = 0
                CustomDialog().showTimeOut(this@TableResultActivity)
            }

        }
        timer.start()
    }

    private fun resumeTimer() {

        timerState = TimerState.Running
        val millisInFuture = timeRemaining

        // Initialize a new CountDownTimer instance
        timer = object : CountDownTimer(millisInFuture, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                // do something in every tick
                if (timerState == TimerState.Paused
                    || timerState == TimerState.Stopped) {
                    //If the user request to cancel or paused the
                    //CountDownTimer we will cancel the current instance
                    cancel()
                }
                else {
                    //Display the remaining seconds to app interface
                    //1 second = 1000 milliseconds
                    binding.apply {
                        val minutes = millisUntilFinished / 1000 / 60
                        val seconds = millisUntilFinished / 1000 % 60
                        progressBarTimer.progress = (progressBarTimer.max + millisUntilFinished / 1000).toInt()
                        txtViewTimerValue.text =
                            String.format(
                                getString(R.string.txt_timer_format),
                                minutes,
                                seconds
                            )
                    }
                    //Put count down timer remaining time in a variable
                    timeRemaining = millisUntilFinished
                }
            }

            override fun onFinish() {
                //Do something when count down finished
                binding.apply {
                    progressBarTimer.progress = 0
                }
                // todo show time ran out dialog
            }

        }
        timer.start()
    }

    fun goToNextEquation(skip: Boolean) {
        numSecond++

        if (numSecond > limit) {
            // todo show dialog
            return
        }

        if (skip) {
            // todo count as mistake/skip
        }
        else {
            binding.apply {
                val userAnswer = txtInput.text.toString().trim { it <= ' ' }.toInt()
                if (userAnswer == correctResult) {
                    // todo count as correct
                }
                else {
                    // todo count as mistake
                }
            }
        }
        // If next number of table is not bigger than the limit e.x. bigger than 10
        val intent = Intent(this@TableResultActivity, TableResultActivity::class.java)
        intent.putExtra(Constants.EXTRA_NUMBER_FIRST, numFirst)
        intent.putExtra(Constants.EXTRA_NUMBER_SECOND, numSecond)
        intent.putExtra(Constants.EXTRA_LIMIT, limit)
        finish()
        startActivity(intent)
    }

    private fun pauseTimer() {
        timerState = TimerState.Paused
        startTimer()
    }

    override fun onResume() {
        super.onResume()

        resumeTimer()
    }

    override fun onPause() {
        super.onPause()

        pauseTimer()
    }

    override fun onStop() {
        super.onStop()

        timerState = TimerState.Stopped
        timer.cancel()
    }

    private fun validateFields(): Boolean {
        binding.apply {
            return when {
                TextUtils.isEmpty(txtInput.text.toString().trim { it <= ' ' }) -> {
                    SnackBarErrorClass
                        .make(root, getString(R.string.txt_error_empty_answer))
                        .show()
                    txtInputLayout.requestFocus()
                    txtInputLayout.error = getString(R.string.txt_error_empty_answer)
                    false
                }
                else -> true
            }
        }
    }

    private fun setupUI() {
        setupActionBar()
        setupClickListeners()
        binding.apply {
            txtInput.setOnFocusChangeListener { v, hasFocus ->
                if (!hasFocus)
                    Utils().hideSoftKeyboard(this@TableResultActivity, v)
            }
            txtInput.addTextChangedListener(object: TextWatcher {
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    txtInputLayout.isErrorEnabled = false
                }
                // Not Needed
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun afterTextChanged(s: Editable?) {}
            })
            val progressAnimator = ObjectAnimator.ofInt(progressBarTimer, "progress", 0,1);
            progressAnimator.duration = 7000;
            progressAnimator.start();
        }
    }

    private fun setupClickListeners() {
        binding.apply {
            btnNext.setOnClickListener {
                getButtonPressSound(this@TableResultActivity)
                if (validateFields()) {
                    goToNextEquation(false)
                }
            }
            btnSkip.setOnClickListener {
                getButtonPressSound(this@TableResultActivity)
                CustomDialog().showSkipConfirmation(this@TableResultActivity)
            }
            btnClear.setOnClickListener {
                getButtonPressSound(this@TableResultActivity)
                txtInput.setText("")
            }
            imgViewArrowLeft.setOnClickListener{
                getButtonPressSound(this@TableResultActivity)
                // Focus the text input box
                txtInputLayout.requestFocus()
                // Hide soft keyboard
                val imm: InputMethodManager =
                    getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.toggleSoftInput(
                    InputMethodManager.SHOW_FORCED,
                    InputMethodManager.HIDE_IMPLICIT_ONLY
                )
            }
        }
    }

    private fun setupActionBar() {
        binding.toolbar.apply {
            setSupportActionBar(root)
            // Set action bar title format to "Table of Z (Z x Y)"
            textViewLabel.text = String.format(getString(R.string.txt_table_format),
                numFirst.toString(),
                numFirst.toString() + "x" + numSecond.toString())
        }

        val actionBar = supportActionBar
        actionBar?.let {
            it.setDisplayShowCustomEnabled(true)
            it.setDisplayHomeAsUpEnabled(true) // Enable back button
            it.setHomeAsUpIndicator(R.drawable.ic_chevron_left_24dp) // Set custom back button icon
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        getButtonPressSound(this@TableResultActivity)
        CustomDialog().showExitConfirmation(this@TableResultActivity)
    }
}