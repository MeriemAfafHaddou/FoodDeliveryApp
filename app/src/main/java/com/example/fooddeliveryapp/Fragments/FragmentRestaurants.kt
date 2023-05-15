package com.example.fooddeliveryapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.Adapter.AdapterRestaurant
import com.example.fooddeliveryapp.ClickListener.RestaurantClickListener
import com.example.fooddeliveryapp.Entity.Restaurant
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.Retrofit.RestaurantService
import com.example.fooddeliveryapp.ViewModel.RestaurantModel
import kotlinx.coroutines.*

class FragmentRestaurants : Fragment(), RestaurantClickListener {
    lateinit var recyclerView:RecyclerView
    lateinit var restaurantsModel: RestaurantModel
    lateinit var progressBar: ProgressBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  inflater.inflate(R.layout.fragment_home, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        restaurantsModel= ViewModelProvider(requireActivity()).get(RestaurantModel::class.java)
        val recyclerView = view.findViewById(R.id.recyclerViewRestaurant) as RecyclerView
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager=layoutManager

        if(restaurantsModel.data.isEmpty()){
            loadRestaurants()
        }else{
            recyclerView.adapter=AdapterRestaurant(restaurantsModel.data,requireContext(), this)
        }
    }

    private fun loadRestaurants() {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            requireActivity().runOnUiThread {
                progressBar.visibility = View.INVISIBLE
                Toast.makeText(requireActivity(), "Une erreur s'est produite", Toast.LENGTH_SHORT).show()
            }

        }
        progressBar.visibility = View.VISIBLE

        CoroutineScope(Dispatchers.IO+ exceptionHandler).launch {
            val response = RestaurantService.createEndpoint().getAllRestaurants()
            withContext(Dispatchers.Main) {
                progressBar.visibility = View.INVISIBLE
                if (response.isSuccessful && response.body() != null) {
                    restaurantsModel.data = response.body()!!.toMutableList()
                    recyclerView.adapter = AdapterRestaurant(restaurantsModel.data,requireContext(),this@FragmentRestaurants)
                } else {
                    Toast.makeText(requireActivity(), "Une erreur s'est produite", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun onRestaurantClickListener(data: Restaurant) {
        val bundle= bundleOf("Restaurant" to data.id)
        this.findNavController().navigate(R.id.action_Restaurant_to_menu, bundle)
    }
}