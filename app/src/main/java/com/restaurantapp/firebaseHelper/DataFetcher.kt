package com.giants.giantsgym.firebaseHelper

import com.google.firebase.database.DataSnapshot

import java.util.ArrayList


object DataFetcher {
    // rbna yostor w mtdrb4 xD
    fun getListFromSnapShot(obClass: Class<*>, dataSnapshot: DataSnapshot): ArrayList<Any> {
        val list = ArrayList<Any>()
        for (dataShot in dataSnapshot.children) {
            list.add(dataShot.getValue(obClass)!!)
        }
        return list
    }

}
