package com.unipi.p17172p17168p17164.multiplicationgame.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.unipi.p17172p17168p17164.multiplicationgame.databinding.ItemTableBinding
import com.unipi.p17172p17168p17164.multiplicationgame.models.Table
import com.unipi.p17172p17168p17164.multiplicationgame.ui.activities.TablesTestActivity
import com.unipi.p17172p17168p17164.multiplicationgame.utils.Constants

/**
 * A adapter class for tables list items.
 */
open class TablesListAdapter(
    private val context: Context,
    private var list: ArrayList<Table>
) : RecyclerView.Adapter<TablesListAdapter.TablesViewHolder>() {

    /**
     * Inflates the item views which is designed in xml layout file
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TablesViewHolder {
        return TablesViewHolder(
            ItemTableBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        )
    }

    /**
     * Binds each item in the ArrayList to a view
     */
    override fun onBindViewHolder(holder: TablesViewHolder, position: Int) {
        val model = list[position]

        holder.binding.apply {
            txtViewHeader.text = model.name
            txtViewDescription.text = model.desc
            txtViewNumberIcon.text = model.number.toString()
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(context, TablesTestActivity::class.java)
            intent.putExtra(Constants.EXTRA_TABLE_ID, model.tableId)
            intent.putExtra(Constants.EXTRA_NUMBER, model.number)
            intent.putExtra(Constants.EXTRA_LIMIT, model.limit)
            context.startActivity(intent)
        }
    }

    /**
     * Gets the number of items in the list
     */
    override fun getItemCount(): Int {
        return list.size
    }

    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     */
    class TablesViewHolder(val binding: ItemTableBinding) : RecyclerView.ViewHolder(binding.root)
}