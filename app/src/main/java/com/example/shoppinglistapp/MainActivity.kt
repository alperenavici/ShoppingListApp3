package com.example.shoppinglistapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var editTextItem: EditText
    private lateinit var buttonAdd: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ShoppingListAdapter
    private val itemList = mutableListOf<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextItem = findViewById(R.id.editTextItem)
        buttonAdd = findViewById(R.id.buttonAdd)
        recyclerView = findViewById(R.id.recyclerView)

        adapter = ShoppingListAdapter(itemList,
            onDelete = { position ->
                itemList.removeAt(position)
                adapter.notifyItemRemoved(position)
            },
            onIncrease = { position ->
                itemList[position].quantity++
                adapter.notifyItemChanged(position)
            },
            onDecrease = { position ->
                if (itemList[position].quantity > 1) {
                    itemList[position].quantity--
                    adapter.notifyItemChanged(position)
                }
            })

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        buttonAdd.setOnClickListener {
            val itemName = editTextItem.text.toString()
            if (itemName.isNotEmpty()) {
                itemList.add(Item(itemName, 1))
                adapter.notifyItemInserted(itemList.size - 1)
                editTextItem.text.clear()
            }
        }
    }
}
