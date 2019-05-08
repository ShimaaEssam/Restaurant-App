package com.giants.giantsgym.firebaseHelper

import android.util.Log
import android.webkit.ValueCallback

import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.restaurantapp.Model.AdminOrders
import com.restaurantapp.Model.FoodOrder

import java.util.ArrayList



object FirebaseReader {
    fun getFireDataList(obClass: Class<*>, callback: ValueCallback<ArrayList<Any>>, vararg params: String) {
        FireHelper.getRequiredPath(*params)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            callback.onReceiveValue(DataFetcher.getListFromSnapShot(obClass, dataSnapshot))
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {

                    }

                })
    }
    fun removeReservsForSpcificUser(onSuccessListener: OnSuccessListener<in Void>,id:String){


        FireHelper.getRequiredPath("Reservations").orderByChild("email").equalTo(id)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for(snap in dataSnapshot.children){
                                val ref=snap.ref.toString()

                                snap.ref.removeValue().addOnSuccessListener (onSuccessListener)

                            }


                            //     callback.onReceiveValue(DataFetcher.getListFromSnapShot(obClass, dataSnapshot))
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {

                    }
                })

/*
        FireHelper.getRequiredPath("Order").orderByChild("email").equalTo(id).ref
                .removeValue()
                .addOnSuccessListener (onSuccessListener)
*/

    }
    fun removeOrdersForSpcificUser(onSuccessListener: OnSuccessListener<in Void>,id:String){


        FireHelper.getRequiredPath("Order").orderByChild("email").equalTo(id)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for(snap in dataSnapshot.children){
                                val ref=snap.ref.toString()

                                snap.ref.removeValue().addOnSuccessListener (onSuccessListener)

                            }


                            //     callback.onReceiveValue(DataFetcher.getListFromSnapShot(obClass, dataSnapshot))
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {

                    }
                })

/*
        FireHelper.getRequiredPath("Order").orderByChild("email").equalTo(id).ref
                .removeValue()
                .addOnSuccessListener (onSuccessListener)
*/

    }


    fun getFiliterdReservsHistory(obClass: Class<*>, callback: ValueCallback<ArrayList<Any>>,id:String){

        FireHelper.getRequiredPath("ReservationsHistory").orderByChild("email").equalTo(id)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            callback.onReceiveValue(DataFetcher.getListFromSnapShot(obClass, dataSnapshot))
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {

                    }
                })
    }
    fun getFiliterdReservs(obClass: Class<*>, callback: ValueCallback<ArrayList<Any>>,id:String){

        FireHelper.getRequiredPath("Reservations").orderByChild("email").equalTo(id)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            callback.onReceiveValue(DataFetcher.getListFromSnapShot(obClass, dataSnapshot))
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {

                    }
                })
    }
    fun getFiliterdOrdersHistory(obClass: Class<*>, callback: ValueCallback<ArrayList<Any>>,id:String){

        FireHelper.getRequiredPath("OrderHistory").orderByChild("email").equalTo(id)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            callback.onReceiveValue(DataFetcher.getListFromSnapShot(obClass, dataSnapshot))
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {

                    }
                })
    }
    fun getFiliterdOrders(obClass: Class<*>, callback: ValueCallback<ArrayList<Any>>,id:String){

        FireHelper.getRequiredPath("Order").orderByChild("email").equalTo(id)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            callback.onReceiveValue(DataFetcher.getListFromSnapShot(obClass, dataSnapshot))
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {

                    }
                })
    }
    fun getFireDataFiliterdList(obClass: Class<*>, callback: ValueCallback<ArrayList<Any>>, id:String) {
        FireHelper.getRequiredPath("Food").orderByChild("MenuId").equalTo(id)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            callback.onReceiveValue(DataFetcher.getListFromSnapShot(obClass, dataSnapshot))
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {

                    }
                })
    }
    fun getSnapShot(callback: ValueCallback<DataSnapshot>, vararg params: String) {
        FireHelper.getRequiredPath(*params).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                callback.onReceiveValue(dataSnapshot)
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

    }


    fun getSnapShotSinglieListner(callback: ValueCallback<DataSnapshot>, vararg params: String) {

        FireHelper.getRequiredPath(*params).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                callback.onReceiveValue(dataSnapshot)

            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

    }

    fun getFireDataSingleObject(obClass: Class<*>, callback: ValueCallback<Any>, vararg params: String) {
        FireHelper.getRequiredPath(*params)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            callback.onReceiveValue(dataSnapshot.getValue(obClass))
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {}
                })

    }


}
