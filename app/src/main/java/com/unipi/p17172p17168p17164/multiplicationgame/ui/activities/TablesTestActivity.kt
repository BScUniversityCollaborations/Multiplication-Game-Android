package com.unipi.p17172p17168p17164.multiplicationgame.ui.activities

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.unipi.p17172p17168p17164.multiplicationgame.R
import com.unipi.p17172p17168p17164.multiplicationgame.adapters.TablesListAdapter
import com.unipi.p17172p17168p17164.multiplicationgame.database.FirestoreHelper
import com.unipi.p17172p17168p17164.multiplicationgame.databinding.ActivityTablesListBinding
import com.unipi.p17172p17168p17164.multiplicationgame.models.Table
import java.util.*

class TablesTestActivity : BaseActivity() {
    // ~~~~~~~~ VARIABLES ~~~~~~~~
    private lateinit var binding: ActivityTablesListBinding
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTablesListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()
        loadTables()
    }

    private fun loadTables() {
        FirestoreHelper().getTablesList(this@TablesTestActivity)
    }

    /**
     * A function to get the successful tables list from cloud firestore.
     *
     * @param tablesList Will receive the tables list from cloud firestore.
     */
    fun successTablesList(tablesList: ArrayList<Table>) {

        if (tablesList.size > 0) {

            val tablesListAdapter = TablesListAdapter(this@TablesTestActivity, tablesList)

            binding.apply {
                recyclerView.run {
                    adapter = tablesListAdapter
                    layoutManager = LinearLayoutManager(this@TablesTestActivity, LinearLayoutManager.VERTICAL, false)
                    setHasFixedSize(true)
                }
            }
        }
    }

    private fun setupActionBar() {
        binding.toolbar.apply {
            setSupportActionBar(root)
            textViewLabel.text = getString(R.string.txt_tables)
        }

        val actionBar = supportActionBar
        actionBar?.let {
            it.setDisplayShowCustomEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_chevron_left_24dp)
        }
    }
}