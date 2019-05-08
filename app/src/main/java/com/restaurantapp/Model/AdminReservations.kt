package com.restaurantapp.Model

class AdminReservations {
    //  var id:String?=null
    lateinit var map:HashMap<String,Pair<User,ArrayList<TableReservation>>>
    var user:User ?=null
    var   reservs:ArrayList<TableReservation> = ArrayList<TableReservation>()
    fun addNewReserv(newOrder:TableReservation){
        reservs.add(newOrder)
    }
    fun getAdminReservs():ArrayList<TableReservation>{
        return this.reservs
    }

}