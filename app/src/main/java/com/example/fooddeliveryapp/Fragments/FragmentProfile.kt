package com.example.fooddeliveryapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth

class FragmentProfile : Fragment() {
    lateinit var binding:FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val auth=FirebaseAuth.getInstance()
        val name=arguments?.getInt("name")
        binding.nameProfile.text=name.toString()
        binding.signout.setOnClickListener{
            auth.signOut()
            this.findNavController().navigate(R.id.action_fragmentProfile_to_login)
        }
    }
}