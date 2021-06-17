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

        setupUI()
    }

    private fun setupUI() {
        binding.apply {
            btnStartTest.setOnClickListener {
//                val intent = Intent(this@MainMenuActivity, TestActivity::class.java)
//                startActivity(intent)
            }
            btnTables.setOnClickListener {
                val intent = Intent(this@MainMenuActivity, TablesTestActivity::class.java)
                startActivity(intent)
            }
            btnMistakes.setOnClickListener {
//                val intent = Intent(this@MainMenuActivity, ProfileDetailsActivity::class.java)
//                startActivity(intent)
            }
            imgBtnProfile.setOnClickListener {
                val intent = Intent(this@MainMenuActivity, ProfileDetailsActivity::class.java)
                startActivity(intent)
            }
            imgBtnHelp.setOnClickListener {
                // TODO: create help dialog
            }
            imgBtnSettings.setOnClickListener {
//                val intent = Intent(this@MainMenuActivity, Settings::class.java)
//                startActivity(intent)
            }
        }
    }

    override fun onBackPressed() {
        doubleBackToExit()
    }
}