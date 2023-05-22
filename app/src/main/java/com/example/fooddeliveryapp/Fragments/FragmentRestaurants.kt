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
import com.example.fooddeliveryapp.Retrofit.RestaurantService
import com.example.fooddeliveryapp.SSLUtils
import com.example.fooddeliveryapp.ViewModel.RestaurantModel
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException
import java.io.InputStream
import java.net.URL
import java.net.URLConnection


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
        restaurantsModel = ViewModelProvider(requireActivity()).get(RestaurantModel::class.java)
        recyclerView = view.findViewById(R.id.recyclerViewRestaurant)
        progressBar = view.findViewById(R.id.progressBar)

        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        adapter = AdapterRestaurant(restaurantsModel.data, requireContext(), this)
        recyclerView.adapter = adapter


        if (restaurantsModel.data.isEmpty()) {
            loadRestaurants()
        }
    }

    private fun loadRestaurants() {
        SSLUtils.ignoreSSLValidation()
       val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            requireActivity().runOnUiThread {
                progressBar.visibility = View.INVISIBLE
                Toast.makeText(requireActivity(), throwable.message, Toast.LENGTH_LONG).show()
            }
           throwable.printStackTrace()
        }
        progressBar.visibility = View.VISIBLE

        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = RestaurantService.createEndpoint().getAllRestaurants()
            withContext(Dispatchers.Main) {
                progressBar.visibility = View.INVISIBLE
                if (response.isSuccessful && response.body() != null) {
                    restaurantsModel.data = response.body()!!.toMutableList()
                    //update the adapter and reload recyclerview
                    adapter.notifyDataSetChanged()

                } else {
                    Toast.makeText(requireActivity(), "Une erreur s'est produite", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    override fun onRestaurantClickListener(data: Restaurant) {
        val bundle = bundleOf("Restaurant" to data.idRestaurant)
        this.findNavController().navigate(R.id.action_Restaurant_to_menu, bundle)
    }
}



