package com.unipi.p17172p17168p17164.multiplicationgame.ui.activities

import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.unipi.p17172p17168p17164.multiplicationgame.R
import com.unipi.p17172p17168p17164.multiplicationgame.adapters.ViewPagerLearnAdapter
import com.unipi.p17172p17168p17164.multiplicationgame.databinding.ActivityLearnBinding

class LearnActivity : BaseActivity() {
    // ~~~~~~~~ VARIABLES ~~~~~~~~
    private lateinit var binding: ActivityLearnBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLearnBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        setupUI()
    }

    private fun setupUI() {
        setupActionBar()
        setupTabs()
    }

    private fun setupTabs() {
        val adapter = ViewPagerLearnAdapter(supportFragmentManager, lifecycle)

        binding.apply {
            viewPagerLearnSections.adapter = adapter

            TabLayoutMediator(tabs, viewPagerLearnSections){tab, position ->
                when (position) {
                    0 -> tab.text = "Section 1"
                    1 -> tab.text = "Section 2"
                    2 -> tab.text = "Section 3"
                }
            }.attach()
        }
    }

    private fun setupActionBar() {
        binding.toolbar.apply {
            setSupportActionBar(root)
            textViewLabel.text = getString(R.string.txt_learn_about_multiplication_tables)
        }

        val actionBar = supportActionBar
        actionBar?.let {
            it.setDisplayShowCustomEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_chevron_left_24dp)
        }
    }

}