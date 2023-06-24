package com.example.fooddeliveryapp.Fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fooddeliveryapp.Adapter.AdapterMenu
import com.example.fooddeliveryapp.Adapter.AdapterReview
import com.example.fooddeliveryapp.Entity.Review
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.ViewModel.RestaurantModel
import com.example.fooddeliveryapp.ViewModel.UserModel
import com.google.android.material.textfield.TextInputLayout


class FragmentMenu : Fragment()
    {
        lateinit var restaurantsModel: RestaurantModel
        lateinit var userModel: UserModel
        lateinit var progressBar2: ProgressBar

        var reviews = mutableListOf<Review>()

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view=inflater.inflate(R.layout.fragment_menu, container,false)
            progressBar2=view.findViewById(R.id.progressBar2) as ProgressBar
           return view
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            restaurantsModel= ViewModelProvider(requireActivity()).get(RestaurantModel::class.java)
            userModel=ViewModelProvider(requireActivity()).get(UserModel::class.java)
            val reviewsbtn=view.findViewById(R.id.BtnReviews) as Button
            val menubtn=view.findViewById(R.id.menubtn)as Button
            val submitbtn =view.findViewById<Button>(R.id.submitreviewbtn)
            val ratingBar=view.findViewById<RatingBar>(R.id.ratingBar2)
            val comment=view.findViewById<TextInputLayout>(R.id.reviewtext)
            val restaurantimg=view.findViewById<ImageView>(R.id.imageView5)
            val type=view.findViewById<TextView>(R.id.RestaurantType)
            val address=view.findViewById<TextView>(R.id.RestaurantLocation)
            val note=view.findViewById<TextView>(R.id.rating)
            val nom=view.findViewById<TextView>(R.id.RestaurantNom)

            val bundle=arguments
            if (bundle != null) {
                println(bundle)
                val img=bundle.getString("img")
                if (img != null) {
                    Glide.with(requireContext()).load(img).into(restaurantimg)
                }
                val nomRestaurant=bundle.getString("name")
                if (nomRestaurant != null) {
                    nom.text=nomRestaurant
                }

                val typeRestaurant=bundle.getString("type")
                if (typeRestaurant != null) {
                    type.text=typeRestaurant
                }
                val addressRestaurant=bundle.getString("address")
                if (addressRestaurant != null) {
                    address.text=addressRestaurant
                }
                val noteRestaurant=bundle.getDouble("rating")
                note.text=noteRestaurant.toString()

            }

            submitbtn.setOnClickListener{
                if (bundle != null) {
                    println(bundle)
                    val img=bundle.getString("img")
                    if (img != null) {
                        Glide.with(requireContext()).load(img).into(restaurantimg)
                    }
                    val nomRestaurant=bundle.getString("name")
                    if (nomRestaurant != null) {
                        nom.text=nomRestaurant
                    }

                    val typeRestaurant=bundle.getString("type")
                    if (typeRestaurant != null) {
                        type.text=typeRestaurant
                    }
                    val addressRestaurant=bundle.getString("address")
                    if (addressRestaurant != null) {
                        address.text=addressRestaurant
                    }
                    val noteRestaurant=bundle.getDouble("rating")
                    note.text=noteRestaurant.toString()

                }
                val idRestaurant= bundle!!.getInt("idRestaurant")
                val rating=ratingBar.rating.toDouble()
                val text = comment.editText?.text
                val pref1=context?.getSharedPreferences("userdb", Context.MODE_PRIVATE)
                val pref = context?.getSharedPreferences("userdb", Context.MODE_PRIVATE)
                val idClient=pref1?.getInt("id",0)
                val nomClient=pref1?.getString("name","").toString()
                val connected= pref?.getBoolean("connected",false)
                println("connect{$connected}")
                if(connected==true){
                    if (idClient != null) {
                        if (text != null) {
                            if(rating!=null){
                                val review=Review(nomClient,idRestaurant,idClient,text.toString(),rating)
                                restaurantsModel.rateRestaurant(review)
                                Toast.makeText(context,"Review added ! ", Toast.LENGTH_LONG).show()
                        }}
                    }
                }else{
                    this.findNavController().navigate(R.id.action_fragment2_to_login)
                    Toast.makeText(context,"You have to be connected ! ", Toast.LENGTH_LONG).show()
                }
            }
               








            val recyclerView = view.findViewById(R.id.recyclerViewMenu) as RecyclerView
            val layoutManager = LinearLayoutManager(context)
            val adapter = AdapterMenu(requireActivity())
            recyclerView.layoutManager=layoutManager
            recyclerView.adapter=adapter
            val idRestaurant=arguments?.getInt("idRestaurant")
            if (idRestaurant != null) {
                restaurantsModel.loadMenus(idRestaurant)
                println("Id restaurant : $idRestaurant")
                restaurantsModel.loading.observe(requireActivity()) { loading ->
                    println(loading)
                    if (loading) {
                        progressBar2.visibility = View.VISIBLE
                    } else {
                        progressBar2.visibility = View.GONE
                    }
                }

                restaurantsModel.errorMessage.observe(
                    requireActivity()
                ) { errorMessaage ->
                    Toast.makeText(requireContext(), errorMessaage, Toast.LENGTH_SHORT).show()
                }

                restaurantsModel.menu.observe(requireActivity()
                ) { data ->
                    adapter.setMenu(data)
                }
                reviewsbtn.setOnClickListener{
                    reviewsbtn.setTextColor(Color.parseColor("#FB8500"))
                    menubtn.setTextColor(Color.parseColor("#282828"))
                    restaurantsModel.loadReviews(idRestaurant)
                    println("Id restaurant : $idRestaurant")

                    // Create a new adapter for the reviews
                    val adapterReviews = AdapterReview(requireActivity())

                    // Change the content of the recyclerview
                    recyclerView.adapter = adapterReviews


                    restaurantsModel.review.observe(requireActivity()) { data ->
                        adapterReviews.setReview(data)
                        }

                }
                menubtn.setOnClickListener{
                    reviewsbtn.setTextColor(Color.parseColor("#282828"))
                    menubtn.setTextColor(Color.parseColor("#FB8500"))
                    restaurantsModel.loadMenus(idRestaurant)

                    // Change the content of the recyclerview
                    recyclerView.adapter = adapter

                    restaurantsModel.menu.observe(requireActivity()
                    ) { data ->
                        adapter.setMenu(data)
                    }
                }





                }
            }
        }


