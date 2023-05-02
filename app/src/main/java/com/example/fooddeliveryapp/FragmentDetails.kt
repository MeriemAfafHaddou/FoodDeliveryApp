package com.example.fooddeliveryapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.fooddeliveryapp.databinding.FragmentDetailsBinding

class FragmentDetails : Fragment() {
    lateinit var binding: FragmentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding= FragmentDetailsBinding.inflate(layoutInflater)
        val menuItem = Menu(
            1, "Pizza Margerita",
            350,
            "Tomato, Cheese, Olives",
            200,
            4.6,
            R.drawable.pizza
        )
        binding.detailsImage.setImageResource(menuItem.img)
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
            val id_item:Int=1
            val cartItem=CartItem(id_item,menuItem,size, q, total)
            Cart.addToCart(cartItem)
            Toast.makeText(activity,"Order added to cart", Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }

}