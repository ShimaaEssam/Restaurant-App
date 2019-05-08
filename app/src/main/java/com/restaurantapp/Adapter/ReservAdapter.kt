package com.restaurantapp.Adapter

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.restaurantapp.Model.TableReservation
import com.restaurantapp.R

class ReservAdapter() : RecyclerView.Adapter<ReservAdapter.MyViewHolder>(), Parcelable {
    private lateinit var list:ArrayList<TableReservation>
    private lateinit var  context: Context

    constructor(parcel: Parcel) : this() {

    }

    constructor(list: ArrayList<TableReservation>, context: Context) : this() {
        this.list = list
        this.context = context
    }
    fun updateData(list: List<TableReservation>){
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

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val view : View = LayoutInflater.from(viewGroup.context).inflate(R.layout.inner_item, viewGroup, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    class MyViewHolder: RecyclerView.ViewHolder{
        var phoneNum: TextView
        var people: TextView
        var date: TextView
        var time: TextView

        constructor(itemView: View) : super(itemView){
            phoneNum=itemView.findViewById(R.id.phoneR)
            people = itemView.findViewById(R.id.numberR)
            date=itemView.findViewById(R.id.dateR)
            time = itemView.findViewById(R.id.timeR)
        }
        fun bind(category: TableReservation) = with(itemView) {
            phoneNum.text ="phoneNumber: "+ category.phoneNumber
            people.text ="Number of People: "+ category.NumberofPeople
            date.text="Date: "+category.date
            time.text="Time: "+category.time

        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ReservAdapter> {
        override fun createFromParcel(parcel: Parcel): ReservAdapter {
            return ReservAdapter(parcel)
        }

        override fun newArray(size: Int): Array<ReservAdapter?> {
            return arrayOfNulls(size)
        }
    }


}

