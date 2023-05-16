package com.example.fooddeliveryapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.Adapter.AdapterMenu
import com.example.fooddeliveryapp.ClickListener.MenuClickListener
import com.example.fooddeliveryapp.Entity.Menu
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.ViewModel.RestaurantModel


class FragmentMenu : Fragment(), MenuClickListener
    {
        lateinit var recyclerView:RecyclerView
        lateinit var restaurantModel: RestaurantModel
        lateinit var progressBar: ProgressBar
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
           return inflater.inflate(R.layout.fragment_menu, container,false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            restaurantModel= ViewModelProvider(requireActivity()).get(RestaurantModel::class.java)
            val recyclerView = view.findViewById(R.id.recyclerViewMenu) as RecyclerView
            val layoutManager = LinearLayoutManager(context)
            val adapter = AdapterMenu(requireActivity(),this@FragmentMenu)
            recyclerView.layoutManager=layoutManager
            recyclerView.adapter=adapter
            val idRestaurant=arguments?.getInt("idRestaurant")
            if (idRestaurant != null) {
                restaurantModel.loadMenus(idRestaurant)
            }

        }
        override fun onMenuClickListener(data: Menu) {
            var bundle= bundleOf("menuDetails" to data)
            this.findNavController().navigate(R.id.action_menu_to_details, bundle)
        }
    }

