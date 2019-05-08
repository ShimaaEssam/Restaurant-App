package com.restaurantapp.utils

import android.text.TextUtils
import android.util.Patterns

class Validation {
    fun isValidEmail(email: String): Boolean {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches())
    }

    fun isValidPass(pass:String):Boolean{
        return (!TextUtils.isEmpty(pass) &&pass.length>=6)
    }
    fun isValidPhone(phone:String):Boolean{
        return(!TextUtils.isEmpty(phone) && (phone.startsWith("011")|| phone.startsWith("010")||phone.startsWith("015")||phone.startsWith("012"))&& phone.length==11)
    }
}