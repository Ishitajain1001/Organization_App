package com.example.organizationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.organizationapp.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignUpActivity : AppCompatActivity() {

private lateinit var auth: FirebaseAuth
val email = findViewById<EditText>(R.id.pt_signUp_email)
val password = findViewById<EditText>(R.id.pt_signUp_password)
val signUpBtn = findViewById<Button>(R.id.btn_signUp)

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_sign_up)
    auth = FirebaseAuth.getInstance()

    signUpBtn.setOnClickListener {
//            signUpUser()
    }

}

public override fun onStart() {
    super.onStart()
    //if user is signed in then update User Interface (UI)
    val currentUser = auth.currentUser
    if(currentUser != null){
//            reload();
    }
}

fun updateUI(currentUser : FirebaseUser?) {

}
    fun signUpUser() {
        if (email.text.toString().isEmpty()) {
            email.error = "Please enter email"
            email.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
            email.error = "Please enter a valid email"
            email.requestFocus()
            return
        }

        if (password.text.toString().isEmpty()) {
            password.error = "Please enter password"
            password.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Sign Up failed.",
                            Toast.LENGTH_SHORT).show()
    //                        updateUI()
                }
            }
    }

}