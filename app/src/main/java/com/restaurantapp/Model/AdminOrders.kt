package com.restaurantapp.Model

class AdminOrders {
    //  var id:String?=null
    var totalPrice: Double? = 0.0
    var user: User? = null
    var orders: ArrayList<FoodOrder> = ArrayList<FoodOrder>()
    fun addOrders(newOrders: ArrayList<FoodOrder>) {
        orders.clear()
        orders.addAll(newOrders)
        for (newOrder in newOrders) {
            totalPrice = totalPrice?.plus(newOrder.price!!.toDouble())
        }
    }

    fun getAdminOrders(): ArrayList<FoodOrder> {
        return this.orders
    }

}