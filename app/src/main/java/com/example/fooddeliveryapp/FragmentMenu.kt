package com.example.fooddeliveryapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.databinding.FragmentMenuBinding


class FragmentMenu : Fragment(), MenuClickListener



    {
        lateinit var binding: FragmentMenuBinding
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.fragment_menu, container,false)
            val myRecyclerView = view.findViewById(R.id.recyclerViewMenu) as RecyclerView
            val layoutManager = LinearLayoutManager(context)
            myRecyclerView.layoutManager=layoutManager
            val myAdapter= loadData()?.let { AdapterMenu(it,requireContext(), this) }
            myRecyclerView.adapter=myAdapter
            // Inflate the layout for this fragment
            return view
        }

        fun loadData(): List<Menu>? {
            val data = mutableListOf<Menu>()
            data.add(Menu(1, "Pizza Marguarita",350,"Tomato Sauce, Olive, Mozarella, Salad.", 200, 4.6, R.drawable.pizza,1))
            data.add(Menu(1, "Pizza Marguarita",350,"Tomato Sauce, Olive, Mozarella, Salad.", 200, 4.6, R.drawable.pizza,1))
            data.add(Menu(1, "Pizza Marguarita",350,"Tomato Sauce, Olive, Mozarella, Salad.", 200, 4.6, R.drawable.pizza,1))
            data.add(Menu(1, "Pizza Marguarita",350,"Tomato Sauce, Olive, Mozarella, Salad.", 200, 4.6, R.drawable.pizza,1))
            data.add(Menu(1, "Pizza Marguarita",350,"Tomato Sauce, Olive, Mozarella, Salad.", 200, 4.6, R.drawable.pizza,1))
            data.add(Menu(1, "Pizza Marguarita",350,"Tomato Sauce, Olive, Mozarella, Salad.", 200, 4.6, R.drawable.pizza,1))
            data.add(Menu(1, "Pizza Marguarita",350,"Tomato Sauce, Olive, Mozarella, Salad.", 200, 4.6, R.drawable.pizza,1))
            data.add(Menu(1, "Pizza Marguarita",350,"Tomato Sauce, Olive, Mozarella, Salad.", 200, 4.6, R.drawable.pizza,1))
            data.add(Menu(1, "Pizza Marguarita",350,"Tomato Sauce, Olive, Mozarella, Salad.", 200, 4.6, R.drawable.pizza,1))
            data.add(Menu(1, "Pizza Marguarita",350,"Tomato Sauce, Olive, Mozarella, Salad.", 200, 4.6, R.drawable.pizza,1))
            data.add(Menu(1, "Pizza Marguarita",350,"Tomato Sauce, Olive, Mozarella, Salad.", 200, 4.6, R.drawable.pizza,1))

            return data
        }

        override fun onMenuClickListener(data: Menu) {
            var bundle= bundleOf("Id" to data.id)
            this.findNavController().navigate(R.id.action_menu_to_details, bundle)
        }
    }

