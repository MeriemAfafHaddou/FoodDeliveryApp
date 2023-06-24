package com.example.fooddeliveryapp.Adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fooddeliveryapp.Entity.Menu
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.MenuLayoutBinding

class AdapterMenu (var context: Context): RecyclerView.Adapter<AdapterMenu.MyViewHolder>() {

    var data= mutableListOf<Menu>()

    fun setMenu(menus: List<Menu>){
        this.data=menus.toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(MenuLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            menuname.text = data[position].nomMenu
            pricing.text = data[position].price.toString()
            ingredients.text = data[position].ingredients
            Glide.with(context).load(data[position].imgMenu).into(menuimg)
        }
        val bundle=Bundle()
        bundle.putInt("menu",data[position].idMenu)
        holder.binding.root.setOnClickListener{
            it.findNavController().navigate(R.id.action_menu_to_details,bundle)
        }
    }


    class MyViewHolder(val binding: MenuLayoutBinding) : RecyclerView.ViewHolder(binding.root)

}