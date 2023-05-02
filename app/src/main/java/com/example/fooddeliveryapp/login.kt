package com.example.fooddeliveryapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class login : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val btn = findViewById<Button>(R.id.login_btn)
        btn.setOnClickListener{
            val email=findViewById<EditText>(R.id.email_login).text.toString()
            val pwd=findViewById<EditText>(R.id.pwd_login).text.toString()
            val user=AppDatabase.buildDatabase(this)?.getUserDao()?.getUsersByEmail(email)
            if(user != null){
                if(pwd==user.pwd ){
                    val intent = Intent(this,MainActivity::class.java)
                    intent.putExtra("Menu","Mega Pizza")
                    this.startActivity(intent)
                }else{
                    Toast.makeText(this,"Incorrect Password !",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this,"Incorrect Email !",Toast.LENGTH_LONG).show()

            }


        }

        val new = findViewById(R.id.create) as TextView
        new.setOnClickListener{
            val intent = Intent(this,register::class.java)
            intent.putExtra("Menu","Mega Pizza")
            this.startActivity(intent)
        }

    }
}