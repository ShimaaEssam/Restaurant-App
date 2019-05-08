package com.restaurantapp.user

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.restaurantapp.R
import com.restaurantapp.utils.Validation

class SignInActivity : AppCompatActivity() {
    private lateinit var edtMail: EditText
    private  lateinit var edtPass: EditText
    private lateinit var forgetPass: TextView
    private lateinit var btnSignIn: Button
    private lateinit var signup: TextView
    private lateinit var progressBar: ProgressBar
    private var auth: FirebaseAuth? = null
    private lateinit var vaild: Validation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Get Firebase auth instance
        auth = (FirebaseAuth.getInstance()) as FirebaseAuth

        if (auth!!.currentUser != null) {
            //user is logged in
            startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
            this.finish()
        }
        setContentView(R.layout.activity_sign_in)
        edtMail=findViewById(R.id.email_in)
        edtPass=findViewById(R.id.pass_in)
        forgetPass=findViewById(R.id.forget_password)
        btnSignIn=findViewById(R.id.sign_in)
        signup=findViewById(R.id.signup_in)
        progressBar = findViewById(R.id.progressBarin)
        vaild= Validation()


        signup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        forgetPass.setOnClickListener {
            val intent = Intent(this, PasswordActivity::class.java)
            startActivity(intent)
        }

        btnSignIn.setOnClickListener OnClickListener@{

            var email:String=edtMail.text.toString()
            var password:String=edtPass.text.toString()
            progressBar.visibility = View.VISIBLE
            val checker =  email.isEmpty()|| password.isEmpty()||!vaild.isValidEmail(email) || !vaild.isValidPass(password)
            if (checker) {
                progressBar.visibility = View.GONE
                if( email.isEmpty())
                    edtMail.error="Empty Email"
                else if(!vaild.isValidEmail(email))
                    edtMail.error="Email is not valid"
                else if(password.isEmpty())
                    edtPass.error="Empty Password"
                else if(!vaild.isValidPass(password))
                    edtPass.error="Password is not valid"
                return@OnClickListener

            }
            //authenticate user
            auth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                progressBar.visibility = View.GONE
                if (!it.isSuccessful) {
                    Toast.makeText(this@SignInActivity, "There is no user record corresponding to this identifier", Toast.LENGTH_LONG).show()
                }
                else {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    this.finish()
                }
            }
        }

    }

    override fun onPause() {
        super.onPause()
        edtMail.error = null
        edtPass.error = null
    }
}
