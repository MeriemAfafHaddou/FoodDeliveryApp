package com.example.fooddeliveryapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.databinding.CartItemLayoutBinding

class AdapterCart (val data:MutableList<CartItem>, var context: Context): RecyclerView.Adapter<AdapterCart.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(CartItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            cartName.text=data[position].order.name
            cartPrice.text= data[position].order.price.toString()+" DA"
            cartIngreds.text=data[position].order.ingredients
            cartSize.text=data[position].size+ " x "+ data[position].quantity.toString()
            total.text=data[position].total.toString() + " DA"

            if(data[position].size=="Large"){
                cartSize.text=data[position].size+ "( +200 DA) x "+ data[position].quantity.toString()
            }
            remove.setOnClickListener{
                Cart.orders.remove(data[position])
            }
        }
    }
    class MyViewHolder(val binding: CartItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

}