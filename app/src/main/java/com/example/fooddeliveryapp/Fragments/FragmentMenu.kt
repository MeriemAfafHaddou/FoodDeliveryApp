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
import com.example.fooddeliveryapp.Adapter.AdapterMenu
import com.example.fooddeliveryapp.ClickListener.MenuClickListener
import com.example.fooddeliveryapp.Entity.Menu
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.Retrofit.RestaurantService
import com.example.fooddeliveryapp.Retrofit.MenuService
import com.example.fooddeliveryapp.ViewModel.MenuModel
import com.example.fooddeliveryapp.databinding.FragmentMenuBinding
import kotlinx.coroutines.*


class FragmentMenu : Fragment(), MenuClickListener
    {
        lateinit var binding: FragmentMenuBinding
        lateinit var recyclerView:RecyclerView
        lateinit var menuModel: MenuModel
        lateinit var progressBar: ProgressBar
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
           return inflater.inflate(R.layout.fragment_menu, container,false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            menuModel= ViewModelProvider(requireActivity()).get(MenuModel::class.java)
            val recyclerView = view.findViewById(R.id.recyclerViewMenu) as RecyclerView
            val layoutManager = LinearLayoutManager(context)
            recyclerView.layoutManager=layoutManager
            if(menuModel.data.isEmpty()){
                loadRestaurants()
            }else{
                recyclerView.adapter= AdapterMenu(menuModel.data,requireContext(), this)
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
                val response = MenuService.createEndpoint().getMenuByRestaurant()
                withContext(Dispatchers.Main) {
                    progressBar.visibility = View.INVISIBLE
                    if (response.isSuccessful && response.body() != null) {
                        menuModel.data = response.body()!!.toMutableList()
                        recyclerView.adapter = AdapterMenu(menuModel.data,requireContext(), this@FragmentMenu)
                    } else {
                        Toast.makeText(requireActivity(), "Une erreur s'est produite", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        override fun onMenuClickListener(data: Menu) {
            var bundle= bundleOf("Id" to data.id)
            this.findNavController().navigate(R.id.action_menu_to_details, bundle)
        }
    }

