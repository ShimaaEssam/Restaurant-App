package com.restaurantapp.user.fragment

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.restaurantapp.R
import java.util.*
import android.app.DatePickerDialog
import java.text.SimpleDateFormat
import android.app.TimePickerDialog
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import com.giants.giantsgym.firebaseHelper.FirebaseWriter
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.restaurantapp.Model.TableReservation
import com.restaurantapp.utils.Validation


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ReservFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ReservFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ReservFragment : Fragment() {
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
    private lateinit var name:EditText
    private lateinit var phone:EditText
    private lateinit var people:EditText
    private lateinit var date:EditText
    private lateinit var time:EditText
    private lateinit var book:Button
    private lateinit var progressBar: ProgressBar
    val myCalendar = Calendar.getInstance()
    var timePickerDialog: TimePickerDialog? = null
    var currentHour: Int = 0
    var currentMinute: Int = 0
    var amPm: String? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var rootView= inflater.inflate(R.layout.fragment_reserv, container, false)
        name=rootView.findViewById(R.id.reservName)
        phone=rootView.findViewById(R.id.reservNumber)
        people=rootView.findViewById(R.id.reservNumberPeople)
        date=rootView.findViewById(R.id.reservDate)
        time=rootView.findViewById(R.id.reservTime)
        book=rootView.findViewById(R.id.bookNow)
        progressBar=rootView.findViewById(R.id.progressBarBook)
        val datee = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel()
        }
        date.setOnClickListener {
            DatePickerDialog(context, datee, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }
time.setOnClickListener {

    var calendar:Calendar = Calendar.getInstance()
    currentHour = calendar.get(Calendar.HOUR_OF_DAY)
    currentMinute = calendar.get(Calendar.MINUTE)


     timePickerDialog = TimePickerDialog(context, TimePickerDialog.OnTimeSetListener { timePicker, hourOfDay, minutes ->
         if (hourOfDay >= 12) {
             amPm = "PM"
         } else {
             amPm = "AM"
         }
         time.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm)
     },
             0, 0, false)

    timePickerDialog!!.show()
}

        book.setOnClickListener OnClickListener@{

            var nameR=name.text.toString()
            var phoneR=phone.text.toString()
            var peopleR=people.text.toString()
            var dateR=date.text.toString()
            var timeR=time.text.toString()
            var valid:Validation= Validation()
            progressBar.visibility = View.VISIBLE
            val checker =  nameR.isEmpty()|| phoneR.isEmpty()||peopleR.isEmpty()||dateR.isEmpty()||timeR.isEmpty()||!valid.isValidPhone(phoneR)
            if (checker) {
                progressBar.visibility = View.GONE
                if( nameR.isEmpty())
                    name.error="Empty Name"
                else
                    name.error=null
                 if(phoneR.isEmpty())
                    phone.error="Empty PhoneNumber"
                 else
                     phone.error=null
                 if(!valid.isValidPhone(phoneR))
                    phone.error="Phone is not valid"
                 else
                     phone.error=null
                 if(peopleR.isEmpty())
                    people.error="Empty Number of People"
                 else
                     people.error=null
                 if(dateR.isEmpty())
                    date.error="Empty Date"
                 else
                     date.error=null
                 if(timeR.isEmpty())
                    time.error="Empty Time"
                else
                    time.error=null

                return@OnClickListener

            }
            else {
                progressBar.visibility = View.GONE
                val builder1 = AlertDialog.Builder(context)
                builder1.setMessage("You book a table successfully !")
                builder1.setCancelable(true)

                builder1.setPositiveButton(
                        "Ok"
                ) { dialog, id ->
                    dialog.cancel()
                    name.text= null
                    phone.text=null
                    people.text=null
                    date.text=null
                    time.text=null
                    val email = FirebaseAuth.getInstance().currentUser!!.email
                    val reservation = TableReservation(nameR, phoneR, peopleR, dateR, timeR,email)
                    var uID: String = FirebaseAuth.getInstance().currentUser!!.uid
                   // val food = TableReservation(nameR, phoneR, peopleR, dateR, timeR,email)
                    var size = 0
                    var database: FirebaseDatabase = FirebaseDatabase.getInstance()
                    val ref: DatabaseReference = database.getReference("Reservations")


                    val event = object : ValueEventListener {
                        override fun onCancelled(p0: DatabaseError) {
                        }

                        override fun onDataChange(p0: DataSnapshot) {
                            size = p0.childrenCount.toInt()
                            Log.d("exercise1: ", size.toString()) // can log all
                            FirebaseWriter.writeWithAutoKey(reservation, OnSuccessListener<Void> {


                            }, "Reservations")

                        }


                    }
                    database.getReference("Reservations").addListenerForSingleValueEvent(event)




                    val event1 = object : ValueEventListener {
                        override fun onCancelled(p0: DatabaseError) {
                        }

                        override fun onDataChange(p0: DataSnapshot) {
                            size = p0.childrenCount.toInt()
                            Log.d("exercise1: ", size.toString()) // can log all
                            FirebaseWriter.writeWithAutoKey(reservation, OnSuccessListener<Void> {


                            }, "ReservationsHistory")

                        }


                    }
                    database.getReference("ReservationsHistory").addListenerForSingleValueEvent(event1)


                    // Toast.makeText(this@FoodDetailActivity, "item Added Successfully", Toast.LENGTH_LONG)

                }
                val alert11 = builder1.create()
                alert11.show()
            }
        }
        return rootView
    }

    override fun onPause() {
        super.onPause()
        name.error = null
        phone.error = null
        people.error = null
        date.error = null
        time.error = null
    }

    private fun updateLabel() {
        val myFormat = "MM/dd/yy" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)

        date.setText(sdf.format(myCalendar.time))
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            //throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
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
         * @return A new instance of fragment ReservFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ReservFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
