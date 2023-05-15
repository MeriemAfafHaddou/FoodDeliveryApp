package com.example.fooddeliveryapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.edit
import com.example.fooddeliveryapp.Entity.User


class loginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val user= User(
            1,"Test","test@gmail.com","test","0000","algiers","algiers"
        )
        val btn = findViewById<Button>(R.id.login_btn)
        val pref = getSharedPreferences("userdb", Context.MODE_PRIVATE)
//        val connected=pref.getBoolean("connected",false)
        btn.setOnClickListener{
            val email=findViewById<EditText>(R.id.email_login).text.toString()
            val pwd=findViewById<EditText>(R.id.pwd_login).text.toString()
            if(email == user.email){
                if(pwd==user.pwd ){
                    pref.edit {
                        putBoolean("connected",true)
                    }
                    val intent = Intent(this,MainActivity::class.java)
                    intent.putExtra("Menu","Mega Pizza")
                    this.startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this,"Incorrect Password !",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this,"Incorrect Email !",Toast.LENGTH_LONG).show()

            }


        }

        val new = findViewById(R.id.create) as TextView
        new.setOnClickListener{
            val intent = Intent(this,registerActivity::class.java)
            intent.putExtra("Menu","Mega Pizza")
            this.startActivity(intent)
        }

    }
}