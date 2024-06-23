package com.example.shoppinglistapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ShoppingListAdapter(
    private val itemList: MutableList<Item>,
    private val onDelete: (Int) -> Unit,
    private val onIncrease: (Int) -> Unit,
    private val onDecrease: (Int) -> Unit
) : RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewItem: TextView = view.findViewById(R.id.textViewItem)
        val textViewQuantity: TextView = view.findViewById(R.id.textViewQuantity)
        val buttonDelete: Button = view.findViewById(R.id.buttonDelete)
        val buttonIncrease: Button = view.findViewById(R.id.buttonIncrease)
        val buttonDecrease: Button = view.findViewById(R.id.buttonDecrease)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.textViewItem.text = item.name
        holder.textViewQuantity.text = item.quantity.toString()

        holder.buttonDelete.setOnClickListener {
            onDelete(position)
        }
        holder.buttonIncrease.setOnClickListener {
            onIncrease(position)
        }
        holder.buttonDecrease.setOnClickListener {
            onDecrease(position)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
