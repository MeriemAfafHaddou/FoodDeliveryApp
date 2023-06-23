package com.example.fooddeliveryapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.Adapter.AdapterRestaurant
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.ViewModel.RestaurantModel


class FragmentRestaurants : Fragment() {
    lateinit var recyclerView:RecyclerView
    lateinit var restaurantsModel: RestaurantModel
    lateinit var progressBar1: ProgressBar
    lateinit var refresh: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_home, container,false)
        progressBar1=view.findViewById(R.id.progressBar)
        refresh=view.findViewById(R.id.tryagain1)
        return  view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        restaurantsModel= ViewModelProvider(requireActivity()).get(RestaurantModel::class.java)
        recyclerView = view.findViewById(R.id.recyclerViewRestaurant) as RecyclerView
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager=layoutManager
        val adapter=AdapterRestaurant(requireActivity())
        recyclerView.adapter= adapter
        refresh.visibility=View.GONE
        restaurantsModel.loadRestaurants()

        restaurantsModel.loading.observe(requireActivity()) { loading ->
            println(loading)
            if (loading) {
                progressBar1.visibility = View.VISIBLE
            } else {
                progressBar1.visibility = View.GONE
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
}