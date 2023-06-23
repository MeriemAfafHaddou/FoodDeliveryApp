package com.example.fooddeliveryapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.Adapter.AdapterMenu
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.ViewModel.RestaurantModel
import com.example.fooddeliveryapp.databinding.FragmentHomeBinding


class FragmentMenu : Fragment()
    {
        lateinit var restaurantsModel: RestaurantModel
        lateinit var progressBar2: ProgressBar
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
            }
        }
    }

