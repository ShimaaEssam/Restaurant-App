package com.restaurantapp.Adapter

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.restaurantapp.Model.AdminReservations
import com.restaurantapp.R

class AdminReservHistoryAdapter() : RecyclerView.Adapter<AdminReservHistoryAdapter.MyViewHolder>(), Parcelable {
    private lateinit var list:ArrayList<AdminReservations>
    private lateinit var  context: Context

    constructor(parcel: Parcel) : this() {

    }

    constructor(list: ArrayList<AdminReservations>, context: Context) : this() {
        this.list = list
        this.context = context
    }
    fun updateData(email: String?) {
        var result= this.list.filter {

            it.user?.email!=email
        }
        this.list.clear()
        this.list.addAll(result)
        this.notifyDataSetChanged()

    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, i: Int) {
        myViewHolder.bind(list[i],this)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val view : View = LayoutInflater.from(viewGroup.context).inflate(R.layout.adminreserv_item, viewGroup, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    class MyViewHolder: RecyclerView.ViewHolder{
        var idAdm: TextView
        var recycler: RecyclerView
        var btn: Button

        constructor(itemView: View) : super(itemView){
            idAdm=itemView.findViewById(R.id.idAreserv)
            recycler=itemView.findViewById(R.id.recyclerinnerreserv)
            btn=itemView.findViewById(R.id.reservDone)
        }
        fun bind(adminsOrders: AdminReservations,
                 adminOrderAdapter: AdminReservHistoryAdapter) = with(itemView) {
            idAdm.text = adminsOrders.user!!.userName

            var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(recycler.getContext(), LinearLayoutManager.VERTICAL, false)
            recycler.layoutManager = layoutManager
            val adapter = ReservAdapter(adminsOrders.reservs, context!!)
            recycler.adapter=adapter
            btn.visibility=View.GONE


        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AdminReservHistoryAdapter> {
        override fun createFromParcel(parcel: Parcel): AdminReservHistoryAdapter {
            return AdminReservHistoryAdapter(parcel)
        }

        override fun newArray(size: Int): Array<AdminReservHistoryAdapter?> {
            return arrayOfNulls(size)
        }
    }


}

