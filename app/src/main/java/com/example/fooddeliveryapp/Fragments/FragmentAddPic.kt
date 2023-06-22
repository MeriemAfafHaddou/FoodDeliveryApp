package com.example.fooddeliveryapp.Fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.findNavController
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.FragmentAddPicBinding

class FragmentAddPic : Fragment() {
    lateinit var binding: FragmentAddPicBinding
    lateinit var imageView: ImageView
    lateinit var button: Button
    private lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAddPicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageView = binding.addPic
        imageView.setOnClickListener{
            imagePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data: Intent? = result.data
                    val imageUri: Uri? = data?.data
                    imageView.setImageURI(imageUri)
                    //send the pic to the backend
                }
            }

        }
        button=binding.register
        button.setOnClickListener{
            view.findNavController().navigate(R.id.action_fragmentAddPic_to_fragmentRegisterEnd)
        }
    }
}