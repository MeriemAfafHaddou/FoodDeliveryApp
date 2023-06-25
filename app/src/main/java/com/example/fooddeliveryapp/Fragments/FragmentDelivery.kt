package com.example.fooddeliveryapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.fooddeliveryapp.ViewModel.RestaurantModel
import com.example.fooddeliveryapp.databinding.FragmentDeliveryEndBinding
import com.example.fooddeliveryapp.sendEmail
import com.example.fooddeliveryapp.startCall

class FragmentDelivery : Fragment() {
    lateinit var binding: FragmentDeliveryEndBinding
    lateinit var restaurantModel: RestaurantModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding= FragmentDeliveryEndBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        restaurantModel= ViewModelProvider(requireActivity()).get(RestaurantModel::class.java)
        val person=restaurantModel.person.value
        binding.apply {
            deliveryPhone.text=person?.numTel
            deliveryName.text=person?.Prenom +" "+ person?.Nom
        }
        binding.callDelivery.setOnClickListener {
            if (person != null) {
                startCall(requireContext(), person.numTel)
            }
        }
        binding.messageDelivery.setOnClickListener {
            if (person != null) {
                sendEmail(requireContext(), person.email)
            }

        }
    }

}