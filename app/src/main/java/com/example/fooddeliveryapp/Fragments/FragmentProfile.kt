package com.example.fooddeliveryapp.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.ViewModel.UserModel
import com.example.fooddeliveryapp.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth

class FragmentProfile : Fragment() {
    lateinit var binding:FragmentProfileBinding
    lateinit var userModel : UserModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding=FragmentProfileBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userModel= ViewModelProvider(requireActivity()).get(UserModel::class.java)
        val auth=FirebaseAuth.getInstance()
        val pref = context?.getSharedPreferences("userdb", Context.MODE_PRIVATE)
        val id=pref?.getInt("id",0)
        if(id==null){
            binding.nameProfile.text="You are not connected !"
            binding.signout.visibility=View.GONE
            binding.profilePic.visibility=View.GONE
            binding.textView10.visibility=View.GONE
            binding.loginProfile.visibility=View.VISIBLE
            binding.loginProfile.setOnClickListener {
                this.findNavController().navigate(R.id.action_fragmentProfile_to_login)
            }
        }else{
            binding.nameProfile.text=pref?.getString("name","")
            binding.signout.setOnClickListener{
                pref.edit {
                    putBoolean("connected", false)
                    putInt("id",0)
                    putString("name","")
                }
                auth.signOut()
                this.findNavController().navigate(R.id.action_fragmentProfile_to_login)
            }
        }

    }
}