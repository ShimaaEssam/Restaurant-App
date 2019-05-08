package com.giants.giantsgym.firebaseHelper

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



object FireHelper {
    fun getRequiredPath(vararg params: String): DatabaseReference {
        var ref = ""
        for (node in params) {
            ref += "/$node"
        }
        return FirebaseDatabase.getInstance().reference.child(ref)


    }
}
