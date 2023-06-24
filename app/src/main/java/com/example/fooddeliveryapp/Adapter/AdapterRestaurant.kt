package com.example.fooddeliveryapp.Adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fooddeliveryapp.*
import com.example.fooddeliveryapp.Entity.Restaurant
import com.example.fooddeliveryapp.databinding.RestaurantLayoutBinding

class AdapterRestaurant(var context: Context):RecyclerView.Adapter<AdapterRestaurant.MyViewHolder>() {

    var data= mutableListOf<Restaurant>()

    fun setRestaurants(restaurants: List<Restaurant>){
        this.data=restaurants.toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RestaurantLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            name.text = data[position].nom

            type.text = data[position].type
            img.setImageResource(R.drawable.megapizza)
            address.text=data[position].address
            ratingValue.text=data[position].rating.toString()
            fb.setOnClickListener {
                openPage(context,data[position].fb,data[position].fbWeb)
            }
            map.setOnClickListener{
                openPage(context,data[position].map,data[position].mapWeb)
            }

            phone.setOnClickListener{
                startCall(context,"0798806201")
            }
            mail.setOnClickListener{
                sendEmail(context, "megapizzaelbiar@gmail.com")
            }
            Glide.with(context).load(data[position].img).into(img)
        }
        val bundle= Bundle()
        bundle.putInt("idRestaurant",data[position].idRestaurant)
        bundle.putString("img",data[position].img)
        bundle.putString("address",data[position].address)
        bundle.putString("name",data[position].nom)
        bundle.putDouble("rating",data[position].rating)
        bundle.putString("type",data[position].type)



        holder.binding.root.setOnClickListener{
            it.findNavController().navigate(R.id.action_Restaurant_to_menu, bundle)
        }
    }


    class MyViewHolder(val binding: RestaurantLayoutBinding) : RecyclerView.ViewHolder(binding.root)

}



