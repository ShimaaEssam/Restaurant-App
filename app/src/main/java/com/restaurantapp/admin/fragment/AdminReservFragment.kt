package com.restaurantapp.admin.fragment

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
import com.google.firebase.database.FirebaseDatabase
import com.restaurantapp.Adapter.AdminReservsAdapter
import com.restaurantapp.Model.AdminReservations
import com.restaurantapp.Model.TableReservation
import com.restaurantapp.Model.User

import com.restaurantapp.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AdminReservFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AdminReservFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class AdminReservFragment : Fragment() {
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
    lateinit var  recyclerView: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_admin_reserv, container, false)

        recyclerView=rootView.findViewById(R.id.recyclerReservAdmin)
        recyclerView.setHasFixedSize(true)
        layoutManager= LinearLayoutManager(context)
        recyclerView.layoutManager=layoutManager

        loadMenu()


        return rootView
    }
    private fun loadMenu() {

        val listAdmin:ArrayList<AdminReservations> =ArrayList<AdminReservations>()
        val adapter= AdminReservsAdapter(listAdmin, context!!)
        FirebaseReader.getFireDataList(User::class.java, ValueCallback { users ->
            val list = users as ArrayList<*> as ArrayList<User>
            for (user in list){
                FirebaseReader.getFiliterdReservs(TableReservation::class.java, ValueCallback { userOrders->
                    val ordersOfUsers = userOrders as ArrayList<*> as ArrayList<TableReservation>
                    val order=AdminReservations()
                    order.user=user
                    order.reservs=ordersOfUsers
                    listAdmin.add(order)
                    adapter.notifyDataSetChanged()

                },user.email!!)
            }
            recyclerView.adapter=adapter


        },"User","User")
/*
        FirebaseReader.getFireDataList(FoodOrder::class.java, ValueCallback { objects ->
            val list = objects as ArrayList<*> as ArrayList<FoodOrder>
            for (foodOrder in list) {
                FirebaseReader.getFireDataSingleObject(User::class.java, ValueCallback { myUser->

                    val order=AdminOrders()
                    order.user=myUser as User
                    order.addNewOrder(foodOrder)
                    listAdmin.add(order)
                    adapter.notifyDataSetChanged()

                },"User","User", foodOrder.userId!!)

            }


            recyclerView.adapter=adapter

        }, "Order")
*/

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
         * @return A new instance of fragment AdminReservFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                AdminReservFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
