package com.example.fooddeliveryapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.FragmentAddPicBinding
import com.example.fooddeliveryapp.databinding.FragmentRegisterEndBinding

class FragmentRegisterEnd : Fragment() {
    lateinit var binding: FragmentRegisterEndBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRegisterEndBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerEnd.setOnClickListener {
            view.findNavController().navigate(R.id.action_fragmentAddPic_to_fragmentRegisterEnd)
        }
    }

}