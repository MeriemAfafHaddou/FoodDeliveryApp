package com.example.fooddeliveryapp.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.Adapter.AdapterCart
import com.example.fooddeliveryapp.AppDatabase
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.FragmentCartBinding

class FragmentCart : Fragment() {
    lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding= FragmentCartBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myRecyclerView = view.findViewById(R.id.recyclerViewCart) as RecyclerView
        val layoutManager = LinearLayoutManager(context)
        myRecyclerView.layoutManager=layoutManager
        val myAdapter= AppDatabase.buildDatabase(requireActivity())?.getCartDao()?.getCartItems()?.let {
            AdapterCart(
                it,requireContext())
        }


        var subtotal:Int=0
        val list=AppDatabase.buildDatabase(requireActivity())?.getCartDao()?.getCartItems()
        if (list != null) {
            for(item in list){
                subtotal+=item.total
            }
            binding.subtotal.text=subtotal.toString()
            binding.prixTotal.text=(subtotal+300).toString()
            print("subtotal = $subtotal")
        }
        myRecyclerView.adapter=myAdapter
        val btn=view.findViewById<Button>(R.id.Confirm)
        btn.setOnClickListener{
            val pref = context?.getSharedPreferences("userdb", Context.MODE_PRIVATE)
            val connected= pref?.getBoolean("connected",false)
            if(connected==true){
                val bundle=Bundle()
                bundle.putInt("total",subtotal+300)
                this.findNavController().navigate(R.id.action_fragmentCart_to_fragmentValidate, bundle)
            }else{
                this.findNavController().navigate(R.id.action_fragmentCart_to_login)
                Toast.makeText(context,"You have to be connected ! ", Toast.LENGTH_LONG).show()
            }
        }
    }

}