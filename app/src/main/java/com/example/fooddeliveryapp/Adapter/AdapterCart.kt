package com.example.fooddeliveryapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.AppDatabase
import com.example.fooddeliveryapp.Entity.CartItem
import com.example.fooddeliveryapp.Fragments.FragmentCart
import com.example.fooddeliveryapp.databinding.CartItemLayoutBinding
import com.example.fooddeliveryapp.databinding.FragmentCartBinding

class AdapterCart (val data:MutableList<CartItem>, var context: Context): RecyclerView.Adapter<AdapterCart.MyViewHolder>() {
    var cartBinding= FragmentCartBinding.inflate(LayoutInflater.from(context))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(CartItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            cartName.text=data[position].name
            cartPrice.text= data[position].unitPrice.toString()+" DA"
            cartIngreds.text=data[position].ingredients
            cartSize.text=data[position].size+ " x "+ data[position].quantity.toString()
            total.text=data[position].total.toString() + " DA"

            if(data[position].size=="Large"){
                cartSize.text=data[position].size+ "( +200 DA) x "+ data[position].quantity.toString()
            }
            remove.setOnClickListener{
                AppDatabase.buildDatabase(context)?.getCartDao()?.deleteCartItem(data[position])
                Toast.makeText(context,"Order Removed from Cart. ", Toast.LENGTH_LONG).show()

            }


        }
    }
    class MyViewHolder(val binding: CartItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

}