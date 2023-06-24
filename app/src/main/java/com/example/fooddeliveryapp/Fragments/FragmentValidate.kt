package com.example.fooddeliveryapp.Fragments

import android.Manifest
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.fooddeliveryapp.AppDatabase
import com.example.fooddeliveryapp.Entity.Commande
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.ViewModel.RestaurantModel
import com.example.fooddeliveryapp.ViewModel.UserModel
import com.example.fooddeliveryapp.databinding.FragmentValidateBinding
import android.location.LocationManager
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Geocoder
import android.widget.EditText
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class FragmentValidate : Fragment() {
    lateinit var binding: FragmentValidateBinding
    lateinit var restaurantModel: RestaurantModel
    private lateinit var locationManager: LocationManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentValidateBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        restaurantModel=ViewModelProvider(requireActivity()).get(RestaurantModel::class.java)
        val prix=arguments?.getInt("total")
        val validate=view.findViewById<Button>(R.id.validate)
        val address=view.findViewById<EditText>(R.id.deliveryAddress)

            validate.setOnClickListener {
            val orders= AppDatabase.buildDatabase(requireActivity())!!.getCartDao().getCartItems()
            val pref = context?.getSharedPreferences("userdb", Context.MODE_PRIVATE)
            val id=pref?.getInt("id",0)
            val commande=Commande(
                id,
                prix,
                30,
                binding.deliveryAddress.text.toString(),
                binding.deliveryNote.text.toString(),
                1,
                orders
            )
            restaurantModel.validateCommand(commande)

            restaurantModel.errorMessage.observe(
                requireActivity()
            ) { errorMessaage ->
                Toast.makeText(requireContext(), errorMessaage, Toast.LENGTH_SHORT).show()
            }

            restaurantModel.person.observe(requireActivity()
            ) {
                this.findNavController().navigate(R.id.action_fragmentValidate_to_fragmentDelivery)
            }
        }
    }
}