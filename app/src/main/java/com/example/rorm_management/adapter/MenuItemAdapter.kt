package com.example.rorm_management.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rorm_management.MenuItemInfoActivity
import com.example.rorm_management.databinding.CardViewMenuItemBinding
import com.example.rorm_management.model.Menu

class MenuItemAdapter(
    private val context: Context,
    private val menuList: ArrayList<Menu>
) : RecyclerView.Adapter<MenuItemAdapter.MenuItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        val binding = CardViewMenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = menuList.size

    inner class MenuItemViewHolder(private val binding: CardViewMenuItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                val menuItem = menuList[position]
                val uriString = menuItem.menuItemImage
                val uri = Uri.parse(uriString)

                menuItemName.text = menuItem.menuItemName
                menuItemPrice.text = menuItem.menuItemPrice

                Glide.with(context).load(uri).into(menuItemImage)

                root.setOnClickListener {
                    val intent = Intent(context, MenuItemInfoActivity::class.java).apply {
                        putExtra("MenuItemName", menuItem.menuItemName)
                        putExtra("MenuItemPrice", menuItem.menuItemPrice)
                        putExtra("MenuItemDescription", menuItem.menuItemDescription)
                        putExtra("MenuItemIngredients", menuItem.menuItemIngredients)
                        putExtra("MenuItemImage", menuItem.menuItemImage)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }

    private fun deleteQuantity(position: Int) {
        menuList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, menuList.size)
    }
}

