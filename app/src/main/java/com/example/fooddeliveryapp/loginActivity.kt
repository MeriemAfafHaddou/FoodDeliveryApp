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
import androidx.lifecycle.ViewModelProvider
import com.example.fooddeliveryapp.Entity.Client
import com.example.fooddeliveryapp.ViewModel.RestaurantModel
import com.example.fooddeliveryapp.ViewModel.UserModel


class loginActivity : AppCompatActivity() {
    lateinit var userModel : UserModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val btn = findViewById<Button>(R.id.login_btn)
        val pref = getSharedPreferences("userdb", Context.MODE_PRIVATE)
//        val connected=pref.getBoolean("connected",false)
        btn.setOnClickListener{
            val email=findViewById<EditText>(R.id.email_login).text.toString()
            val pwd=findViewById<EditText>(R.id.pwd_login).text.toString()
            userModel= ViewModelProvider(this).get(UserModel::class.java)
            userModel.login(email, pwd)

            val user=userModel.user.value
            if(user!=null){
                pref.edit {
                    putBoolean("connected",true)
                }
                val intent = Intent(this,MainActivity::class.java)
                intent.putExtra("Menu","Mega Pizza")
                this.startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,"Incorrect credentials !",Toast.LENGTH_LONG).show()

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