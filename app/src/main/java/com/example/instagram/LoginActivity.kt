package com.example.instagram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Check if user is logged in
        //

        if (ParseUser.getCurrentUser() != null){
            goToMainActivity()
        }
        findViewById<Button>(R.id.login_button).setOnClickListener{
            val username = findViewById<EditText>(R.id.et_username).text.toString()
         val password = findViewById<EditText>(R.id.et_password).text.toString()
           loginUser(username, password)
        }

        findViewById<Button>(R.id.signupBn).setOnClickListener{
            val username = findViewById<EditText>(R.id.et_username).text.toString()
            val password = findViewById<EditText>(R.id.et_password).text.toString()
            loginUser(username, password)
        }
}
    private fun signUpUser(username:String, password: String){
        // Create the ParseUser
        val user = ParseUser()

// Set fields for the user to be created
        user.setUsername(username)
        user.setPassword(password)

        user.signUpInBackground { e ->
            if (e == null) {
                // Hooray! Let them use the app now.

                Log.i(TAG, "Successfully Signed Up")
                goToMainActivity()
            } else {
                e.printStackTrace()
                // Sign up didn't succeed. Look at the ParseException
                Toast.makeText(this, "Error Signing in", Toast.LENGTH_SHORT).show()
                // to figure out what went wrong
            }
        }
    }
    private fun loginUser(username: String, password: String) {
        ParseUser.logInInBackground(username, password, ({ user, e ->
            if (user != null) {
                // Hooray!  The user is logged in.
                Log.i(TAG, "Successfully Logged in user")
                goToMainActivity()
            } else
            {
                // Signup failed.  Look at the ParseException to see what happened.
                e.printStackTrace()
                Toast.makeText(this, "Error Logging in", Toast.LENGTH_SHORT).show()
            }})
        )
    }

    private fun goToMainActivity(){
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()

    }
    companion object{
        const val TAG = "LoginActivity"
    }
}