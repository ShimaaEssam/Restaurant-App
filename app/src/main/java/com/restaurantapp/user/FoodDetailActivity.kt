package com.restaurantapp.user

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.*
import com.bumptech.glide.Glide
import com.giants.giantsgym.firebaseHelper.FirebaseWriter
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.restaurantapp.Model.FoodOrder
import com.restaurantapp.R

class FoodDetailActivity : AppCompatActivity() {
    private lateinit var image: ImageView
    private lateinit var name: TextView
    private lateinit var nameInArabic: TextView
    private lateinit var price: TextView
    private lateinit var cartBtn: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var foodName:String
    private lateinit var foodPrice:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_detail)
        image=findViewById(R.id.backdrop)
        name=findViewById(R.id.name)
        nameInArabic=findViewById(R.id.nameArabic)
        price=findViewById(R.id.price)
        cartBtn=findViewById(R.id.addToCart)
        progressBar=findViewById(R.id.progressBarD)
        foodName=name.text.toString()
        foodPrice=price.text.toString()
        initUi()



        }



    private fun initUi() {
        val nameFood:String
        val priceFood:String
        if (intent.hasExtra("IMAGE")) {
           val imageFood = intent.extras!!.getString("IMAGE")
             nameFood = intent.extras!!.getString("NAME")
            val nameAFood = intent.extras!!.getString("NAMEARABIC")
             priceFood = intent.extras!!.getString("PRICE")
            Log.i("HII","inactivity"+priceFood)


            name.text = nameFood
            nameInArabic.text=nameAFood
            price.text = priceFood+" LE"
            Glide.with(this)
                    .load(imageFood)
                    .into(image)
            cartBtn.setOnClickListener {
                val builder1 = AlertDialog.Builder(this)
                builder1.setMessage("You add the item to your cart !")
                builder1.setCancelable(true)

                builder1.setPositiveButton(
                        "Ok"
                ) { dialog, id -> dialog.cancel()


                    var uID:String= FirebaseAuth.getInstance().currentUser!!.uid
                    val email=FirebaseAuth.getInstance().currentUser!!.email
                    val food = FoodOrder(nameAFood,priceFood,email)

                    var size=0
                    var database: FirebaseDatabase = FirebaseDatabase.getInstance()
                    val ref: DatabaseReference =database.getReference("Order")


                    val event=object:ValueEventListener{
                        override fun onCancelled(p0: DatabaseError) {
                        }

                        override fun onDataChange(p0: DataSnapshot) {
                            size= p0.childrenCount.toInt()
                            Log.d("exercise1: ", size.toString()) // can log all
                            FirebaseWriter.writeWithAutoKey(food, OnSuccessListener<Void> {


                            }, "Order")

                        }


                    }
                    database.getReference("Order").addListenerForSingleValueEvent(event)


                    val event1=object:ValueEventListener{
                        override fun onCancelled(p0: DatabaseError) {
                        }

                        override fun onDataChange(p0: DataSnapshot) {
                            size= p0.childrenCount.toInt()
                            Log.d("exercise1: ", size.toString()) // can log all
                            FirebaseWriter.writeWithAutoKey(food, OnSuccessListener<Void> {


                            }, "OrderHistory")

                        }


                    }
                    database.getReference("OrderHistory").addListenerForSingleValueEvent(event1)
                    Toast.makeText(this@FoodDetailActivity, "item Added Successfully", Toast.LENGTH_LONG)

                }
                val alert11 = builder1.create()
                alert11.show()

            }
        } else {
            Toast.makeText(this, "No Data Found", Toast.LENGTH_LONG).show()
        }


    }
}

