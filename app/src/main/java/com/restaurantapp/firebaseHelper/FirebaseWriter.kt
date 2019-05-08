package com.giants.giantsgym.firebaseHelper

import com.google.android.gms.tasks.OnSuccessListener

object FirebaseWriter {

    //write with pushing key
    fun writeWithAutoKey(`object`: Any, onSuccessListener: OnSuccessListener<in Void>, vararg params: String) {
        FireHelper.getRequiredPath(*params).push().setValue(`object`).addOnSuccessListener(onSuccessListener)
    }

    // write with customekey
    fun writeWithCustomKey(`object`: Any, onSuccessListener: OnSuccessListener<in Void>, vararg params: String) {
        FireHelper.getRequiredPath(*params).setValue(`object`).addOnSuccessListener(onSuccessListener)
    }

}
