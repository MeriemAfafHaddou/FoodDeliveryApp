package com.example.fooddeliveryapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.Entity.Menu
import com.example.fooddeliveryapp.ClickListener.MenuClickListener
import com.example.fooddeliveryapp.databinding.MenuLayoutBinding

class AdapterMenu (val data:List<Menu>, var context: Context, val menuClickListener: MenuClickListener): RecyclerView.Adapter<AdapterMenu.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(MenuLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            menuname.text = data[position].name
            pricing.text = data[position].price.toString()
            ingredients.text = data[position].ingredients
            cal.text = data[position].cal.toString()
            menurating.text = data[position].rating.toString()
            menuimg.setImageResource(data[position].img)
        }
        holder.binding.root.setOnClickListener{
            menuClickListener.onMenuClickListener(data[position])
        }
    }


    class MyViewHolder(val binding: MenuLayoutBinding) : RecyclerView.ViewHolder(binding.root)

}