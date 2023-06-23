package com.example.fooddeliveryapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import com.example.fooddeliveryapp.Entity.Client
import com.example.fooddeliveryapp.ViewModel.UserModel

class registerActivity : AppCompatActivity() {
    lateinit var userModel: UserModel
    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)
        val btn = findViewById<Button>(R.id.register_btn)
        val pref = getSharedPreferences("userdb", Context.MODE_PRIVATE)

        btn.setOnClickListener {
            val first=findViewById<EditText>(R.id.firstName).text.toString()
            val last=findViewById<EditText>(R.id.lastName).text.toString()
            val email=findViewById<EditText>(R.id.email).text.toString()
            val pwd=findViewById<EditText>(R.id.pwd).text.toString()
            val numTlf=findViewById<EditText>(R.id.phoneNum).text.toString()
            val pic=findViewById<EditText>(R.id.profilePicLink).text.toString()
            val new=Client(200, first, last, email, pwd, numTlf,pic)

            userModel= ViewModelProvider(this).get(UserModel::class.java)
            userModel.register(new)
            val user=userModel.user.value
            print("new user : "+user)
            if(user!=null){
                pref.edit {
                    putBoolean("connected",true)
                }
                val intent = Intent(this,MainActivity::class.java)
                this.startActivity(intent)
            }else{
                Toast.makeText(this,"Failed to register !", Toast.LENGTH_LONG).show()

            }

        }

    }
}