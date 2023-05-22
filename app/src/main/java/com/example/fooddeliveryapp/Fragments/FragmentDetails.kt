package com.example.fooddeliveryapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.fooddeliveryapp.AppDatabase
import com.example.fooddeliveryapp.Entity.CartItem
import com.example.fooddeliveryapp.Entity.Menu
import com.example.fooddeliveryapp.databinding.FragmentDetailsBinding

class FragmentDetails : Fragment() {
    lateinit var binding: FragmentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding= FragmentDetailsBinding.inflate(layoutInflater)
        val menuItem = arguments?.get("menuDetails") as Menu
        Glide.with(this).load(menuItem.img).into(binding.detailsImage)
        binding.detailsName.text=menuItem.name
        binding.detailsIngreds.text=menuItem.ingredients
        binding.detailsCal.text=menuItem.cal.toString()+" Kcal"
        binding.detailsRating.text=menuItem.rating.toString()
        val add=binding.add
        val sub=binding.sub
        var q:Int=1
        add.setOnClickListener{
            q=q+1
            binding.quantity.text=q.toString()
        }
        sub.setOnClickListener{
            q=q-1
            if(q<1) q=1
            binding.quantity.text=q.toString()
        }
        var id_item:Int=3
        binding.addToCart.setOnClickListener{
            val selectedId=binding.radioGroup.checkedRadioButtonId
            var size:String = "Medium"
            if(selectedId==binding.medium.id){
                size="Medium"
            }else if(selectedId==binding.large.id){
                size="Large"
            }
            var total:Int=q*menuItem.price.toInt()
            if(size=="Large"){
                total+=200*q
            }
            val order= AppDatabase.buildDatabase(requireActivity())!!.getCartDao().getCartItems()

            try{
                if(order.last().restaurant_id==menuItem.restaurant){
                    id_item=order.last().id+1
                    AppDatabase.buildDatabase(requireActivity())?.getCartDao()?.addToCart(

                        CartItem(id_item,menuItem.restaurant,menuItem.name,menuItem.price,menuItem.ingredients,menuItem.cal,menuItem.rating,menuItem.img,size, q, total)
                    )
                    Toast.makeText(activity,"Order added to cart", Toast.LENGTH_SHORT).show()

                }else {
                    Toast.makeText(
                        activity,
                        "The orders have to be from the same restaurant!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }catch(e:NoSuchElementException){
                id_item=1
                AppDatabase.buildDatabase(requireActivity())?.getCartDao()?.addToCart(
                    CartItem(id_item,menuItem.restaurant,menuItem.name,menuItem.price,menuItem.ingredients,menuItem.cal,menuItem.rating,menuItem.img,size, q, total)
                )
            }
        }
        return binding.root
    }

}