package com.restaurantapp.Adapter

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.restaurantapp.Model.FoodOrder
import com.restaurantapp.R
import java.util.ArrayList

class OrderAdapter() : RecyclerView.Adapter<OrderAdapter.MyViewHolder>(), Parcelable {
    private lateinit var list:ArrayList<FoodOrder>
    private lateinit var  context: Context

    constructor(parcel: Parcel) : this() {

    }

    constructor(list: ArrayList<FoodOrder>, context: Context) : this() {
        this.list = list
        this.context = context
    }
   fun updateData(list: List<FoodOrder>){
       this.list.clear()
       this.list.addAll(list)
       this.notifyDataSetChanged()
   }
    fun clearAll(){
        this.list.clear()
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, i: Int) {
        myViewHolder.bind(list[i])
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int):OrderAdapter.MyViewHolder {
        val view : View = LayoutInflater.from(viewGroup.context).inflate(R.layout.order_item, viewGroup, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    class MyViewHolder: RecyclerView.ViewHolder{
        var name: TextView
        var price: TextView

        constructor(itemView: View) : super(itemView){
            name=itemView.findViewById(R.id.ordername)
            price = itemView.findViewById(R.id.orderprice)
        }
        fun bind(category: FoodOrder) = with(itemView) {
            name.text = "Dish: "+category.name
            price.text = "Price: "+category.price+" LE"

        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OrderAdapter> {
        override fun createFromParcel(parcel: Parcel): OrderAdapter {
            return OrderAdapter(parcel)
        }

        override fun newArray(size: Int): Array<OrderAdapter?> {
            return arrayOfNulls(size)
        }
    }


}

