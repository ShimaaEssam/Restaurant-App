package com.restaurantapp.Adapter

import android.content.Context
import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.restaurantapp.user.FoodDetailActivity
import com.restaurantapp.Model.Food
import com.restaurantapp.R

class foodAdapter()  : RecyclerView.Adapter<foodAdapter.MyViewHolder>(), Parcelable {
    private lateinit var list:List<Food>
    private lateinit var  context: Context
    constructor(parcel: Parcel) : this() {

    }

    constructor(list: List<Food>, context: Context) : this() {
        this.list = list
        this.context = context

    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, i: Int) {
        myViewHolder.bind(list[i])
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int):foodAdapter.MyViewHolder {
        val view : View = LayoutInflater.from(viewGroup.context).inflate(R.layout.detail_item, viewGroup, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    class MyViewHolder: RecyclerView.ViewHolder{
        var menuName: TextView
        var menuImage: ImageView

        constructor(itemView: View) : super(itemView){
            menuName=itemView.findViewById(R.id.detail_name)
            menuImage = itemView.findViewById(R.id.detail_image)
        }
        fun bind(food: Food) = with(itemView) {
                menuName.text = food.Name
                Glide.with(context)
                        .load(food.Image)
                        .into(menuImage)
            this.setOnClickListener {
                val intent = Intent(context, FoodDetailActivity::class.java)
                intent.putExtra("IMAGE",food.Image)
                intent.putExtra("NAME",food.Name)
                intent.putExtra("NAMEARABIC",food.nameArabic)
                intent.putExtra("PRICE",food.Price)
                Log.i("HII","inadapter"+food.Price)
                context.startActivity(intent)
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<foodAdapter> {
        override fun createFromParcel(parcel: Parcel): foodAdapter {
            return foodAdapter(parcel)
        }

        override fun newArray(size: Int): Array<foodAdapter?> {
            return arrayOfNulls(size)
        }
    }


}

