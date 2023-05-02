package com.example.fooddeliveryapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.databinding.RestaurantLayoutBinding

class AdapterRestaurant(val data:List<Restaurant>, var context: Context, val RestaurantClickListener:RestaurantClickListener):RecyclerView.Adapter<AdapterRestaurant.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RestaurantLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            name.text = data[position].name
            type.text = data[position].type
            img.setImageResource(R.drawable.megapizza)
            address.text=data[position].address
            ratingValue.text=data[position].rating.toString()
            fb.setOnClickListener {
                openPage(context,data[position].fb,data[position].fbweb)
            }
            ig.setOnClickListener{
                openPage(context,data[position].ig,data[position].igweb)
            }
            map.setOnClickListener{
                openPage(context,data[position].map,data[position].mapweb)
            }
            phone.setOnClickListener{
                startCall(context,"0798806201")
            }
            mail.setOnClickListener{
                sendEmail(context, "megapizzaelbiar@gmail.com")
            }
        }

        holder.binding.root.setOnClickListener{
            RestaurantClickListener.onRestaurantClickListener(data[position])
        }
    }


    class MyViewHolder(val binding: RestaurantLayoutBinding) : RecyclerView.ViewHolder(binding.root)

}



