package com.restaurantapp.user

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.restaurantapp.Model.User
import com.restaurantapp.R
import com.restaurantapp.utils.Validation

class SignUpActivity : AppCompatActivity() {
    private lateinit var edtFirstName: EditText
    private  lateinit var edtLastName: EditText
    private  lateinit var edtMobile: EditText
    private lateinit var edtEmail: EditText
    private  lateinit var edtUserName: EditText
    private  lateinit var edtPass: EditText
    private lateinit var btnSignUp: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var signIn: TextView
    private var auth: FirebaseAuth? = null
    private lateinit var vaild: Validation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        edtFirstName=findViewById(R.id.firstName)
        edtLastName=findViewById(R.id.lastName)
        edtMobile=findViewById(R.id.mobileNumber)
        edtEmail=findViewById(R.id.email)
        edtUserName=findViewById(R.id.userName)
        edtPass=findViewById(R.id.password)
        btnSignUp=findViewById(R.id.signUp)
        signIn=findViewById(R.id.Signin_up)
        progressBar = findViewById(R.id.progressBarup)
        auth = (FirebaseAuth.getInstance()as  FirebaseAuth)
        vaild=Validation()
        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val tableUser: DatabaseReference =database.getReference("User")

        signIn.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            this.finish()
        }

        btnSignUp.setOnClickListener OnClickListener@{

            val firstName = edtFirstName.text.toString().trim()
            val lastName = edtLastName.text.toString().trim()
            val mobile = edtMobile.text.toString().trim()
            val email = edtEmail.text.toString().trim()
            val userName = edtUserName.text.toString().trim()
            val password: String = edtPass.text.toString()

            progressBar.visibility = View.VISIBLE
            val checker =
                    !vaild.isValidEmail(email) || !vaild.isValidPass(password) || !vaild.isValidPhone(mobile)
                            || firstName.isEmpty() || lastName.isEmpty() ||  userName.isEmpty()
            if (checker) {

                progressBar.visibility = View.GONE
                if(firstName.isEmpty())
                    edtFirstName.error = "First Name is Empty"
                else
                    edtFirstName.error=null
                if(lastName.isEmpty())
                    edtLastName.error="Last Name is Empty"
                else
                    edtLastName.error=null
                if(!vaild.isValidPhone(mobile))
                    edtMobile.error="Mobile Phone is not valid"
                else
                    edtMobile.error=null
                if(!vaild.isValidEmail(email))
                    edtEmail.error="Email is not valid"
                else
                    edtEmail.error=null

                if(userName.isEmpty())
                    edtUserName.error="User Name is Empty"
                else
                    edtUserName.error=null
                if(!vaild.isValidPass(password))
                    edtPass.error="Password is not valid"
                else
                    edtPass.error=null
                return@OnClickListener

            }

            //create user
            auth!!.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                progressBar.visibility = View.GONE
                if (!it.isSuccessful) {
                    Toast.makeText(this, "Authentication failed." + it.getException(),
                            Toast.LENGTH_LONG).show()
                } else {
                    val user: User = User(firstName,lastName,mobile,email,userName)
                    var uID:String=FirebaseAuth.getInstance().currentUser!!.uid
                    tableUser.child("User").child(uID).setValue(user)
                    startActivity(Intent(this, SignInActivity::class.java))
                    this.finish()
                }
            }


        }

    }

    override fun onPause() {
        super.onPause()
        edtFirstName.error = null
        edtLastName.error = null
        edtUserName.error = null
        edtEmail.error = null
        edtPass.error= null
        edtMobile.error=null
    }
}
