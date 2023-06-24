package com.example.fooddeliveryapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import com.example.fooddeliveryapp.ViewModel.UserModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.messaging.Constants.MessageNotificationKeys.TAG
import com.google.firebase.messaging.FirebaseMessaging

data class LoginRequest(
    val mail: String,
    val pwd: String
)

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

            val req= LoginRequest(email, pwd)
            userModel= ViewModelProvider(this).get(UserModel::class.java)
            userModel.login(req)
            val user=userModel.user.value
            userModel.errorMessage.observe(
                this
            ) { errorMessaage ->
                Toast.makeText(this, errorMessaage, Toast.LENGTH_SHORT).show()
            }

            userModel.user.observe(this
            ) { data ->
                findViewById<TextView>(R.id.nameProfile).text=user?.PrenomClient
            }
            if(user!=null){
                pref.edit {
                    putBoolean("connected",true)
                    putInt("id",user.idClient)
                    putString("name", user.PrenomClient+" "+user.NomClient)
                }
                FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                        return@OnCompleteListener
                    }

                    // Get new FCM registration token
                    val token = task.result
                    print("\n \n \n TOKEEEN :" + token)
                    // Log and toast

                })
                val intent = Intent(this,MainActivity::class.java)
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
                findViewById<TextView>(R.id.nameProfile).text=account.displayName
                intent.putExtra("email",account.email.toString())
                intent.putExtra("name",account.displayName.toString())
                startActivity(intent)
            }else{
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }
}