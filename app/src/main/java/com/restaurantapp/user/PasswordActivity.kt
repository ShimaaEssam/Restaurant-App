package com.restaurantapp.user

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.restaurantapp.R
import com.restaurantapp.utils.Validation

class PasswordActivity : AppCompatActivity() {
    private lateinit var inputEmail: EditText
    private lateinit var btnReset: Button
    private lateinit var btnBack: TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var progressBar: ProgressBar
    private lateinit var vaild: Validation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)
        inputEmail = findViewById(R.id.email)
        btnReset = findViewById(R.id.btn_reset_password)
        btnBack = findViewById(R.id.signinTV)
        progressBar = findViewById(R.id.progressBar)

        vaild=Validation()
        auth = (FirebaseAuth.getInstance() as FirebaseAuth)

        btnBack.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            this.finish()
        }
        btnReset.setOnClickListener OnClickListener@{
            val email = inputEmail.text.toString().trim()
            progressBar.visibility = View.VISIBLE
            val checker =   !vaild.isValidEmail(email)
            if (checker) {
                progressBar.visibility = View.GONE
                inputEmail.error="Email is not valid"
                return@OnClickListener
            }
            auth.sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "We have sent you instructions to reset your password!", Toast.LENGTH_LONG)
                            .show()
                } else {
                    Toast.makeText(this, "Failed to send reset email ,there is no user email like you entered!", Toast.LENGTH_LONG).show()
                }

                progressBar.visibility = View.GONE
            }

        }
    }

    override fun onPause() {
        super.onPause()
        inputEmail.error=null
    }
}
