package com.example.expoyou

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.expoyou.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {

        auth = Firebase.auth

        super.onCreate(savedInstanceState)

        binding =
            ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentuser = auth.currentUser

        if (currentuser != null) {
            startActivity(Intent(this@MainActivity, HomeActivity::class.java))
            finish()
        }
//---------------------------------------------------------------------------------------------

//        binding.imageButton.setOnClickListener {
//sign()
//
//        }

//-----------------------------------------------------------------------------------
        binding.SignIn.setOnClickListener {
            val email =
                binding.mailaddress.text.toString()    //storing typed email text on a variable
            val password =
                binding.password.text.toString()    //storing typed password on a variable
            if (email.isEmpty() || password.isEmpty()) {      //giving condition of if email and password tray are empty or not
                Toast.makeText(this, "enter email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            signIn(
                email,
                password
            )    //if email and password are not empty then implementing function(signin) and passing 2 parameter email and password
        }


        binding.SignUp.setOnClickListener {
            val email =
                binding.mailaddress.text.toString()   //storing typed email text on a variable
            val password = binding.password.text.toString()   //storing typed password on a variable
            if (email.isEmpty() || password.isEmpty()) {  //giving condition of if email and password tray are empty or not
                Toast.makeText(this, "enter email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            createAccount(
                email,
                password
            )    //if email and password are not empty then implementing function(signup) and passing 2 parameter email and password
        }

    }


//    private fun sign() {
//        val signInIntent = googleSignInClient.signInIntent
//        startActivityForResult(signInIntent, MainActivity.RC_SIGN_IN)
//    }
//----------------------------------------------------------------------------------------------------------------------------------------------

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload();
        }
    }

//--------------------------------------------------------------------------------------------------------------------------------------------------------

    private fun createAccount(
        email: String,
        password: String
    ) { //function taking two parameter email adn password
        // [START create_user_with_email]

        auth.createUserWithEmailAndPassword(
            email,
            password
        )    //a function of firebase taking email and password
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {   //if values are passed on this then
                    auth.currentUser?.sendEmailVerification()     //implementing a function(sendemailverification()) to send email to entered email
                        ?.addOnCompleteListener { task ->
                            val user = auth.currentUser
                            if (task.isSuccessful) {
                                Toast.makeText(this, "Email is sent", Toast.LENGTH_SHORT)
                                    .show()
                                // Sign in success, update UI with the signed-in user's informationval
                                val user = auth.currentUser

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(
                                    this, "failed to create an account.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                updateUI(null)
                            }
                        }
                } else {
                    Toast.makeText(this, "already an account", Toast.LENGTH_SHORT).show()
                }
            }
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------

    private fun signIn(email: String, password: String) {
        // [START sign_in_with_email]


        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@addOnCompleteListener
                }

                // [END sign_in_with_email]
            }
    }
//------------------------------------------------------------------------------------------------------------------------------------------------------------

    private fun sendEmailVerification() {
        // [START send_email_verification]
        val user = auth.currentUser!!
        user.sendEmailVerification()
            .addOnCompleteListener(this) { task ->
                // Email Verification sent
            }
        // [END send_email_verification]
    }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------

    private fun updateUI(user: FirebaseUser?) {
        if (auth.currentUser != null) {
            if (auth.currentUser!!.isEmailVerified) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
                Toast.makeText(this, "Email not verified", Toast.LENGTH_SHORT).show()

            }
        }
    }
//----------------------------------------------------------------------------------------------------------------------------------------------------------------

    private fun reload() {

    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//
//        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//            val exception = task.exception
//            if(task.isSuccessful){
//                try {
//                    // Google Sign In was successful, authenticate with Firebase
//                    val account = task.getResult(ApiException::class.java)!!
//                    Log.i("MainActivity", "firebaseAuthWithGoogle:" + account.id)
//                    Toast.makeText(this, "login", Toast.LENGTH_SHORT).show()
//                    firebaseAuthWithGoogle(account.idToken!!)
//                    val intent = Intent(this, MainWindow1::class.java)
//                    startActivity(intent)
//                    finish()
//
//                } catch (e: ApiException) {
//                    // Google Sign In failed, update UI appropriately
//                    Log.i(TAG, "Google sign in failed", e)
//                }
//            }else{
//                Log.w(TAG, exception.toString())
//            }
//
//        }
//
//    }
//    private fun firebaseAuthWithGoogle(idToken: String) {
//        val credential = GoogleAuthProvider.getCredential(idToken, null)
//        auth.signInWithCredential(credential)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d("MainActivity", "signInWithCredential:success")
//                    Toast.makeText(this, "successfull setup", Toast.LENGTH_SHORT).show()
//                    val intent = Intent(this, MainWindow1::class.java)
//                    startActivity(intent)
//                    finish()
//
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "signInWithCredential:failure", task.exception)
//                    Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
//                }
//            }
//    }


//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------

    companion object {
        private const val TAG = "EmailPassword"
//        private const val RC_SIGN_IN = 120
    }


}