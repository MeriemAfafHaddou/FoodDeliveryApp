package com.example.fooddeliveryapp.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import androidx.navigation.findNavController
import com.example.fooddeliveryapp.Entity.Client
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.registerActivity

class FragmentRegisterForm : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_register_form, container,false)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val add = requireActivity().findViewById<Button>(R.id.register_btn)
        var id:Long=4
        add.setOnClickListener{
            id += 1
            val name= requireActivity().findViewById<EditText>(R.id.fullName).text.toString()
            val email=requireActivity().findViewById<EditText>(R.id.email).text.toString()
            val pwd=requireActivity().findViewById<EditText>(R.id.pwd).text.toString()
            val phone=requireActivity().findViewById<EditText>(R.id.phoneNum).text.toString()
            val user= Client(id, name, email, pwd, phone)
            //register to the backend
            view.findNavController().navigate(R.id.action_fragmentRegisterForm_to_fragmentAddPic)
        }
    }
}