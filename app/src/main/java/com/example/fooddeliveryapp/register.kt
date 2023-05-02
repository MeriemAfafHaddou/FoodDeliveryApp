package com.example.fooddeliveryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val add = findViewById<Button>(R.id.register_btn)
        var id:Long=4
        add.setOnClickListener{
            id += 1
            val name= findViewById<EditText>(R.id.fullName).text.toString()
            val email=findViewById<EditText>(R.id.email).text.toString()
            val pwd=findViewById<EditText>(R.id.pwd).text.toString()
            val phone=findViewById<EditText>(R.id.phoneNum).text.toString()
            val city=findViewById<EditText>(R.id.city).text.toString()
            val ad=findViewById<EditText>(R.id.ad).text.toString()
            val user=User(id, name, email, pwd, phone, city, ad)
            AppDatabase.buildDatabase(this)?.getUserDao()?.addUsers(user)
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("Menu","Mega Pizza")
        }
    }




}