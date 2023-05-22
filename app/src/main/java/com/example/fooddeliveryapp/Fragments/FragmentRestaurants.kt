package com.example.fooddeliveryapp.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.Adapter.AdapterRestaurant
import com.example.fooddeliveryapp.BuildConfig
import com.example.fooddeliveryapp.ClickListener.RestaurantClickListener
import com.example.fooddeliveryapp.Entity.Restaurant
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.ViewModel.RestaurantModel
import com.example.fooddeliveryapp.Retrofit.RestaurantService
import com.example.fooddeliveryapp.SSLUtils
import kotlinx.coroutines.*

class FragmentRestaurants : Fragment(), RestaurantClickListener {
    lateinit var recyclerView: RecyclerView
    lateinit var restaurantsModel: RestaurantModel
    lateinit var progressBar: ProgressBar
    lateinit var adapter: AdapterRestaurant

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        restaurantsModel= ViewModelProvider(requireActivity()).get(RestaurantModel::class.java)
        recyclerView = view.findViewById(R.id.recyclerViewRestaurant) as RecyclerView
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager=layoutManager
        val adapter=AdapterRestaurant(requireActivity(), this@FragmentRestaurants)
        recyclerView.adapter= adapter
        restaurantsModel.loadRestaurants()

        restaurantsModel.loading.observe(requireActivity()) { loading ->
            if (loading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }

        restaurantsModel.errorMessage.observe(
            requireActivity()
        ) { errorMessaage ->
            Toast.makeText(requireContext(), errorMessaage, Toast.LENGTH_SHORT).show()
        }

        restaurantsModel.restaurants.observe(requireActivity()
        ) { data ->
            adapter.setRestaurants(data)
        }
    }


    override fun onRestaurantClickListener(data: Restaurant) {
        val bundle = bundleOf("Restaurant" to data.idRestaurant)
        this.findNavController().navigate(R.id.action_Restaurant_to_menu, bundle)
    }
}



