package com.example.fooddeliveryapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.fooddeliveryapp.AppDatabase
import com.example.fooddeliveryapp.Entity.CartItem
import com.example.fooddeliveryapp.ViewModel.RestaurantModel
import com.example.fooddeliveryapp.databinding.FragmentDetailsBinding

class FragmentDetails : Fragment() {
    lateinit var binding: FragmentDetailsBinding
    lateinit var restaurantsModel: RestaurantModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding= FragmentDetailsBinding.inflate(layoutInflater)
        restaurantsModel= ViewModelProvider(requireActivity()).get(RestaurantModel::class.java)
        val idMenu = arguments?.getInt("menu")
        if(idMenu!=null){
            var menuItem=restaurantsModel.menu.value?.find { it.idMenu==idMenu }
            if(menuItem!=null){
                binding.apply {
                    detailsName.text=menuItem?.nomMenu
                    detailsIngreds.text=menuItem?.ingredients
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
                        var total:Int=q* menuItem?.price!!.toInt()
                        if(size=="Large"){
                            total+=200*q
                        }
                        val notes=binding.cookingNotes.text.toString()
                        val order= AppDatabase.buildDatabase(requireActivity())!!.getCartDao().getCartItems()
                        print("\n id of restaurant : "+menuItem.idRestaurant)
                        try{
                            if(order.isEmpty()){
                                AppDatabase.buildDatabase(requireActivity())?.getCartDao()?.addToCart(
                                    CartItem(1,menuItem.idMenu,menuItem.idRestaurant,menuItem.nomMenu,menuItem.price,menuItem.ingredients,menuItem.calories,menuItem.rating,menuItem.imgMenu,size, q,notes, total)
                                )
                                Toast.makeText(activity,"Order added to cart", Toast.LENGTH_SHORT).show()
                            } else{
                                if(order.last().restaurant_id==menuItem.idRestaurant){
                                    id_item=order.last().idItem+1
                                    AppDatabase.buildDatabase(requireActivity())?.getCartDao()?.addToCart(
                                        CartItem(id_item,menuItem.idMenu,menuItem.idRestaurant,menuItem.nomMenu,menuItem.price,menuItem.ingredients,menuItem.calories,menuItem.rating,menuItem.imgMenu,size, q,notes, total)
                                    )
                                    Toast.makeText(activity,"Order added to cart", Toast.LENGTH_SHORT).show()
                                }else {
                                    Toast.makeText(activity, "The orders have to be from the same restaurant!", Toast.LENGTH_SHORT).show()
                                }
                            }

                        }catch(e:NoSuchElementException){
                            Toast.makeText(
                                activity,
                                "Rana fel catch honeey",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }
                }
                Glide.with(this).load(menuItem?.imgMenu).into(binding.detailsImage)

            }
        }
        return binding.root
    }
}