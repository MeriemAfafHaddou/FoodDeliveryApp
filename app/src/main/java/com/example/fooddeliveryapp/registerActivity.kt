package com.example.fooddeliveryapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.fooddeliveryapp.Entity.Client
import com.example.fooddeliveryapp.Fragments.FragmentAddPic
import com.example.fooddeliveryapp.Fragments.FragmentRegisterForm

class registerActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<FragmentRegisterForm>(R.id.frameLayout8)
            }
        }
        setContentView(R.layout.activity_register)

    }
}