package com.unipi.p17172p17168p17164.multiplicationgame.ui.activities

import android.content.Intent
import android.os.Bundle
import com.unipi.p17172p17168p17164.multiplicationgame.databinding.ActivityMainMenuBinding

class MainMenuActivity : BaseActivity() {
    // ~~~~~~~~ VARIABLES ~~~~~~~~
    private lateinit var binding: ActivityMainMenuBinding
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        setupUI()
    }

    private fun setupUI() {
        binding.apply {
            btnStartTest.setOnClickListener {
                playButtonPressSound(this@MainMenuActivity)
//                val intent = Intent(this@MainMenuActivity, TestActivity::class.java)
//                startActivity(intent)
            }
            btnTables.setOnClickListener {
                playButtonPressSound(this@MainMenuActivity)
                val intent = Intent(this@MainMenuActivity, TablesListActivity::class.java)
                startActivity(intent)
            }
            btnMistakes.setOnClickListener {
                playButtonPressSound(this@MainMenuActivity)
//                val intent = Intent(this@MainMenuActivity, ProfileDetailsActivity::class.java)
//                startActivity(intent)
            }
            imgBtnProfile.setOnClickListener {
                playButtonPressSound(this@MainMenuActivity)
                val intent = Intent(this@MainMenuActivity, ProfileDetailsActivity::class.java)
                startActivity(intent)
            }
            imgBtnHelp.setOnClickListener {
                playButtonPressSound(this@MainMenuActivity)
                // TODO: create help dialog
            }
            imgBtnSettings.setOnClickListener {
                playButtonPressSound(this@MainMenuActivity)
//                val intent = Intent(this@MainMenuActivity, Settings::class.java)
//                startActivity(intent)
            }
        }
    }

    override fun onBackPressed() {
        doubleBackToExit()
    }
}