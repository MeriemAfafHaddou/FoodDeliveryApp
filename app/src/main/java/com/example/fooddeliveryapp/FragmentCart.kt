package com.example.fooddeliveryapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.databinding.FragmentCartBinding

class FragmentCart : Fragment() {
    lateinit var binding: FragmentCartBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container,false)
        val myRecyclerView = view.findViewById(R.id.recyclerViewCart) as RecyclerView
        val layoutManager = LinearLayoutManager(context)
        myRecyclerView.layoutManager=layoutManager
        val myAdapter=AdapterCart(Cart.orders,requireContext())
        myRecyclerView.adapter=myAdapter
        return view
    }
    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, FragmentCart::class.java)
        }
    }

}