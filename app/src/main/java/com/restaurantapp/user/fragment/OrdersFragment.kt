package com.restaurantapp.user.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.ValueCallback
import com.giants.giantsgym.firebaseHelper.FirebaseReader
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.restaurantapp.Adapter.OrderAdapter
import com.restaurantapp.Model.AdminOrders
import com.restaurantapp.Model.FoodOrder
import com.restaurantapp.Model.User

import com.restaurantapp.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [OrdersFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [OrdersFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class OrdersFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    lateinit var database: FirebaseDatabase
    //lateinit var service: DatabaseReference
    lateinit var  recyclerView: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val rootView =  inflater.inflate(R.layout.fragment_orders, container, false)

        //database= FirebaseDatabase.getInstance()
        //service=database.getReference("Services")
        //load menu
        recyclerView=rootView.findViewById(R.id.recyclerOrder)
        recyclerView.setHasFixedSize(true)
        layoutManager= LinearLayoutManager(context)
        recyclerView.layoutManager=layoutManager

        loadMenu()


        return rootView
    }


    private fun loadMenu() {
        var uID: String? = FirebaseAuth.getInstance().currentUser!!.email

        FirebaseReader.getFiliterdOrders(FoodOrder::class.java,ValueCallback { orders ->

            val ordersOfUsers = orders as ArrayList<*> as ArrayList<FoodOrder>

            val adapter= OrderAdapter(ordersOfUsers,context!!)
             recyclerView.adapter=adapter

        },FirebaseAuth.getInstance().currentUser!!.email.toString())
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OrdersFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                OrdersFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
