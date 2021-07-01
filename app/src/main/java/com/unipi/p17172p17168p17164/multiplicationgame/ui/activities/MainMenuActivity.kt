package com.unipi.p17172p17168p17164.multiplicationgame.ui.activities

import android.content.Intent
import android.os.Bundle
import com.google.android.material.button.MaterialButton
import com.unipi.p17172p17168p17164.multiplicationgame.databinding.ActivityMainMenuBinding
import com.unipi.p17172p17168p17164.multiplicationgame.services.BackgroundMusicService

class MainMenuActivity : BaseActivity() {
    // ~~~~~~~~ VARIABLES ~~~~~~~~
    private lateinit var binding: ActivityMainMenuBinding
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        setupUI()
    }

    private fun init() {
        val playerIntent = Intent(this, BackgroundMusicService::class.java)
        startService(playerIntent)
    }

    private fun setupUI() {
        binding.apply {
            btnStartTest.setOnClickListener {
                getButtonPressSound(this@MainMenuActivity)
//                val intent = Intent(this@MainMenuActivity, TestActivity::class.java)
//                startActivity(intent)
            }
            btnTables.setOnClickListener {
                getButtonPressSound(this@MainMenuActivity)
                val intent = Intent(this@MainMenuActivity, TablesListActivity::class.java)
                startActivity(intent)
            }
            btnMistakes.setOnClickListener {
                getButtonPressSound(this@MainMenuActivity)
//                val intent = Intent(this@MainMenuActivity, ProfileDetailsActivity::class.java)
//                startActivity(intent)
            }
            imgBtnProfile.setOnClickListener {
                getButtonPressSound(this@MainMenuActivity)
                val intent = Intent(this@MainMenuActivity, ProfileDetailsActivity::class.java)
                startActivity(intent)
            }
            imgBtnHelp.setOnClickListener {
                getButtonPressSound(this@MainMenuActivity)
                // TODO: create help dialog
            }
            imgBtnSettings.setOnClickListener {
                getButtonPressSound(this@MainMenuActivity)
//                val intent = Intent(this@MainMenuActivity, Settings::class.java)
//                startActivity(intent)
            }
        }
    }

    override fun onBackPressed() {
        doubleBackToExit()
    }
}