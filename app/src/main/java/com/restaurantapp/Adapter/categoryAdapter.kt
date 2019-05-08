package com.restaurantapp.Adapter

import android.content.Context
import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.restaurantapp.user.DetailActivity
import com.restaurantapp.Model.Category
import com.restaurantapp.R

class categoryAdapter()  : RecyclerView.Adapter<categoryAdapter.MyViewHolder>(), Parcelable {
    private lateinit var list:List<Category>
    private lateinit var  context: Context

    constructor(parcel: Parcel) : this() {

    }

    constructor(list: List<Category>, context: Context) : this() {
        this.list = list
        this.context = context
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, i: Int) {
        myViewHolder.bind(list[i])
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int):categoryAdapter.MyViewHolder {
        val view : View = LayoutInflater.from(viewGroup.context).inflate(R.layout.menu_item, viewGroup, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    class MyViewHolder: RecyclerView.ViewHolder{
        var menuName: TextView
        var menuImage: ImageView

        constructor(itemView: View) : super(itemView){
            menuName=itemView.findViewById(R.id.menu_name)
            menuImage = itemView.findViewById(R.id.menu_image)
        }
        fun bind(category: Category) = with(itemView) {
            menuName.text = category.Name
            Glide.with(context)
                    .load(category.Image)
                    .into(menuImage)
            this.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("ID",category.id)
                context.startActivity(intent)
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<categoryAdapter> {
        override fun createFromParcel(parcel: Parcel): categoryAdapter {
            return categoryAdapter(parcel)
        }

        override fun newArray(size: Int): Array<categoryAdapter?> {
            return arrayOfNulls(size)
        }
    }


}

