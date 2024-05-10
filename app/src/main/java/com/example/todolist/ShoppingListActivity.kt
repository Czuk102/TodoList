package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_shopping_list.*

class ShoppingListActivity : AppCompatActivity() {

    private lateinit var shoppingListAdapter: ShoppingListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_list)
        shoppingListAdapter = ShoppingListAdapter(mutableListOf())

        rvShoppingListItems.adapter = shoppingListAdapter
        rvShoppingListItems.layoutManager = LinearLayoutManager(this)

        btnAddShoppingItem.setOnClickListener {
            val shoppingItemTitle = etShoppingItemTitle.text.toString()
            if(shoppingItemTitle.isNotEmpty()) {
                val shoppingItem = ShoppingItem(shoppingItemTitle)
                shoppingListAdapter.addShoppingItem(shoppingItem)
                etShoppingItemTitle.text.clear()
            }
        }
        btnDeleteBoughtItems.setOnClickListener {
            shoppingListAdapter.deleteBoughtItems()
        }
//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
//        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.action_todo -> {
//                    startActivity(Intent(this, TodoActivity::class.java))
//                    true
//                }
//                R.id.action_shopping_list -> {
//                    startActivity(Intent(this, ShoppingListActivity::class.java))
//
//                    true
//                }
//                else -> false
//            }
//        }
    }

}
