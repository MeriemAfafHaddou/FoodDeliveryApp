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
import com.example.fooddeliveryapp.databinding.FragmentHomeBinding
class FragmentHome : Fragment(), RestaurantClickListener {
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container,false)
        val myRecyclerView = view.findViewById(R.id.recyclerViewRestaurant) as RecyclerView
        val layoutManager = LinearLayoutManager(context)
        myRecyclerView.layoutManager=layoutManager
        val myAdapter= loadData()?.let { AdapterRestaurant(it,requireContext(), this) }
        myRecyclerView.adapter=myAdapter
        return view
    }

    fun loadData(): List<Restaurant>? {
        AppDatabase.buildDatabase(requireActivity())?.getRestaurantDao()?.addRestaurant(Restaurant(
            1,
            "Mega Pizza",
            "Fast Food",
            R.drawable.megapizza,
            "Elbiar",
            "https://maps.app.goo.gl/Hq7XA3rC6vRMCwMN9",
            "https://goo.gl/maps/Y68Z4dKBGjYkeDC59",
            4.6,
            "https://www.facebook.com/megapizzaelbiar/",
            "https://www.facebook.com/megapizzaelbiar/",
            "https://instagram.com/mega_pizza_elbiar?igshid=YmMyMTA2M2Y=",
            "https://www.instagram.com/mega_pizza_elbiar/?hl=fr",
        )
        )
        AppDatabase.buildDatabase(requireActivity())?.getRestaurantDao()?.addRestaurant(Restaurant(
            2,
            "O tacos",
            "Fast Food",
            R.drawable.megapizza,
            "Elbiar",
            "https://maps.app.goo.gl/Hq7XA3rC6vRMCwMN9",
            "https://goo.gl/maps/Y68Z4dKBGjYkeDC59",
            4.6,
            "https://www.facebook.com/megapizzaelbiar/",
            "https://www.facebook.com/megapizzaelbiar/",
            "https://instagram.com/mega_pizza_elbiar?igshid=YmMyMTA2M2Y=",
            "https://www.instagram.com/mega_pizza_elbiar/?hl=fr",
        )
        )
        AppDatabase.buildDatabase(requireActivity())?.getRestaurantDao()?.addRestaurant(Restaurant(
            3,
            "Chicken Street",
            "Fast Food",
            R.drawable.megapizza,
            "Elbiar",
            "https://maps.app.goo.gl/Hq7XA3rC6vRMCwMN9",
            "https://goo.gl/maps/Y68Z4dKBGjYkeDC59",
            4.6,
            "https://www.facebook.com/megapizzaelbiar/",
            "https://www.facebook.com/megapizzaelbiar/",
            "https://instagram.com/mega_pizza_elbiar?igshid=YmMyMTA2M2Y=",
            "https://www.instagram.com/mega_pizza_elbiar/?hl=fr",
        )
        )

        return AppDatabase.buildDatabase(requireActivity())?.getRestaurantDao()?.getRestaurants()
    }

    override fun onRestaurantClickListener(data: Restaurant) {
        val bundle= bundleOf("Restaurant" to data.id_restau)
        this.findNavController().navigate(R.id.action_Restaurant_to_menu, bundle)
    }
}