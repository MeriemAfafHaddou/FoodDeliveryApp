package com.example.fooddeliveryapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import com.example.fooddeliveryapp.Entity.Client
import com.example.fooddeliveryapp.Fragments.FragmentRegisterForm
import com.example.fooddeliveryapp.ViewModel.RestaurantModel
import com.example.fooddeliveryapp.ViewModel.UserModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class loginActivity : AppCompatActivity() {
    lateinit var userModel : UserModel
    lateinit var auth : FirebaseAuth
    lateinit var googleSignInClient : GoogleSignInClient
    @SuppressLint("MissingInflatedId")
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
            println(user?.nomClient)
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
            this.startActivity(intent)
        }

        auth = FirebaseAuth.getInstance()
        val gso=GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient=GoogleSignIn.getClient(this, gso)

        val google = findViewById<Button>(R.id.google)
        google.setOnClickListener{
            SignInGoogle()
        }

    }
    private fun SignInGoogle(){
        val signInIntent=googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->
                if(result.resultCode == Activity.RESULT_OK){
                    val task=GoogleSignIn.getSignedInAccountFromIntent(result.data)
                    handleResults(task)
                }
    }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if(task.isSuccessful){
            val account:GoogleSignInAccount? = task.result
            if(account != null){
                updateUI(account)
            }
        }else{
            Toast.makeText(this, task.exception.toString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener{
            if(it.isSuccessful){
                val intent:Intent=Intent(this, MainActivity::class.java)
                intent.putExtra("email",account.email.toString())
                intent.putExtra("name",account.displayName.toString())
                startActivity(intent)
            }else{
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }
}