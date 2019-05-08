package com.restaurantapp.Adapter

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.restaurantapp.Model.AdminOrders
import android.support.v7.widget.LinearLayoutManager
import android.widget.Button
import android.widget.Toast
import com.giants.giantsgym.firebaseHelper.FirebaseReader
import com.google.android.gms.tasks.OnSuccessListener
import com.restaurantapp.R


class AdminOrderAdapter() : RecyclerView.Adapter<AdminOrderAdapter.MyViewHolder>(), Parcelable {
    private lateinit var list:ArrayList<AdminOrders>
    private lateinit var  context: Context

    constructor(parcel: Parcel) : this() {

    }

    constructor(list: ArrayList<AdminOrders>, context: Context) : this() {
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
        val view : View = LayoutInflater.from(viewGroup.context).inflate(R.layout.adminorder_item, viewGroup, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    class MyViewHolder: RecyclerView.ViewHolder{
        var idAdm: TextView
        var recycler:RecyclerView
        var btn:Button
        var totalP:TextView

        constructor(itemView: View) : super(itemView){
            idAdm=itemView.findViewById(R.id.idA)
            recycler=itemView.findViewById(R.id.recyclerinner)
            btn=itemView.findViewById(R.id.orderDone)
            totalP=itemView.findViewById(R.id.totalPrice)
        }
        fun bind(adminsOrders: AdminOrders,
                 adminOrderAdapter: AdminOrderAdapter) = with(itemView) {
           idAdm.text = adminsOrders.user!!.userName
            totalP.text="total Price: "+ adminsOrders.totalPrice.toString()
            var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(recycler.getContext(), LinearLayoutManager.VERTICAL, false)
            recycler.layoutManager = layoutManager
            val adapter = OrderAdapter(adminsOrders.orders,context!!)
            recycler.adapter=adapter

            btn.setOnClickListener {

                FirebaseReader.removeOrdersForSpcificUser(OnSuccessListener<Void> {

                 ///   adminsOrders.remove( adminsOrders[position])
                //   adminOrderAdapter.notifyItemChanged(position)
                    adminOrderAdapter.updateData(adminsOrders.user?.email)


                }, adminsOrders.user!!.email.toString())

            }
        }
    }



    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AdminOrderAdapter> {
        override fun createFromParcel(parcel: Parcel): AdminOrderAdapter {
            return AdminOrderAdapter(parcel)
        }

        override fun newArray(size: Int): Array<AdminOrderAdapter?> {
            return arrayOfNulls(size)
        }
    }


}

