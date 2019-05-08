package com.restaurantapp.Adapter

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.restaurantapp.R

class complaintsAdapter() : RecyclerView.Adapter<complaintsAdapter.MyViewHolder>(), Parcelable {
    override fun onBindViewHolder(myViewHolder: MyViewHolder,i: Int) {
        myViewHolder.bind(list[i])
    }


    private lateinit var list:List<String>
    private lateinit var  context: Context

    constructor(parcel: Parcel) : this() {
        list = parcel.createStringArrayList()
    }

    constructor(list: ArrayList<String>, context: Context) : this() {
        this.list = list
        this.context = context
    }



    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val view : View = LayoutInflater.from(viewGroup.context).inflate(R.layout.feedback_admin_item, viewGroup, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    class MyViewHolder: RecyclerView.ViewHolder{
        // var offerDesc: TextView
        var txt: TextView

        constructor(itemView: View) : super(itemView){
            //   offerDesc=itemView.findViewById(R.id.menu_name)
            txt = itemView.findViewById(R.id.complainttxt)
        }
        fun bind(offer: String) = with(itemView) {
            txt.text =offer

        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeStringList(list)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<complaintsAdapter> {
        override fun createFromParcel(parcel: Parcel): complaintsAdapter {
            return complaintsAdapter(parcel)
        }

        override fun newArray(size: Int): Array<complaintsAdapter?> {
            return arrayOfNulls(size)
        }
    }

}

