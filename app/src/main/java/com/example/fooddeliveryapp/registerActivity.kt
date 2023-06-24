package com.example.fooddeliveryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.fooddeliveryapp.Entity.Client

class registerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val add = findViewById<Button>(R.id.register_btn)
        var id:Int=4
        add.setOnClickListener{
            id += 1
            val name= findViewById<EditText>(R.id.fullName).text.toString()
            val email=findViewById<EditText>(R.id.email).text.toString()
            val pwd=findViewById<EditText>(R.id.pwd).text.toString()
            val phone=findViewById<EditText>(R.id.phoneNum).text.toString()
            val user= Client(id, name, email, pwd, phone)
            val intent = Intent(this,MainActivity::class.java)
        }
    }




}