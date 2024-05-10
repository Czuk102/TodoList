package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_shopping.view.*

class ShoppingListAdapter(
    private val shoppingItems: MutableList<ShoppingItem>
) : RecyclerView.Adapter<ShoppingListAdapter.ShoppingListViewHolder>() {

    class ShoppingListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        return ShoppingListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_shopping,
                parent,
                false
            )
        )
    }

    fun addShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.add(shoppingItem)
        notifyItemInserted(shoppingItems.size - 1)
    }

    fun deleteBoughtItems() {
        shoppingItems.removeAll { shoppingItem ->
            shoppingItem.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvShoppingItemTitle: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvShoppingItemTitle.paintFlags = tvShoppingItemTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvShoppingItemTitle.paintFlags = tvShoppingItemTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        val curShoppingItem = shoppingItems[position]
        holder.itemView.apply {
            tvShoppingItemTitle.text = curShoppingItem.title
            cbBought.isChecked = curShoppingItem.isChecked
            toggleStrikeThrough(tvShoppingItemTitle, curShoppingItem.isChecked)
            cbBought.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvShoppingItemTitle, isChecked)
                curShoppingItem.isChecked = !curShoppingItem.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return shoppingItems.size
    }

}

