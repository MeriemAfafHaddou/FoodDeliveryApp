package com.example.fooddeliveryapp.Adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fooddeliveryapp.Entity.Client
import com.example.fooddeliveryapp.Entity.Menu
import com.example.fooddeliveryapp.Entity.Review
import com.example.fooddeliveryapp.MainActivity
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.ViewModel.RestaurantModel
import com.example.fooddeliveryapp.databinding.ReviewLayoutBinding
import com.example.fooddeliveryapp.ViewModel.UserModel
import com.example.fooddeliveryapp.loginActivity

class AdapterReview (var context: Context): RecyclerView.Adapter<AdapterReview.MyViewHolder>() {
    var data= mutableListOf<Review>()
    lateinit var userModel: UserModel



    fun setReview(reviews: List<Review>){
        this.data=reviews.toMutableList()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ReviewLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun getItemCount() = data.size

    override  fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        userModel = ViewModelProvider(context as ViewModelStoreOwner).get(UserModel::class.java)
        userModel.getUserInfos(data[position].idClient)
        userModel.user.observe(context as MainActivity) { user ->
            if (user != null && data[position].idClient == user.idClient) {
                holder.binding.username.text = user.NomClient
            }
        }

        holder.binding.apply {
            Review.text = data[position].commentaire
            ratingBar.rating = data[position].note.toFloat()

        }

    }


    class MyViewHolder(val binding: ReviewLayoutBinding) : RecyclerView.ViewHolder(binding.root)

}