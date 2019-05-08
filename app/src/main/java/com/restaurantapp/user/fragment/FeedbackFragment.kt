package com.restaurantapp.user.fragment

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import com.giants.giantsgym.firebaseHelper.FirebaseWriter
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth

import com.restaurantapp.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FeedbackFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FeedbackFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FeedbackFragment : Fragment() {
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
    private lateinit var complaints: EditText
    private lateinit var sendBtn: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var rootView= inflater.inflate(R.layout.fragment_feedback, container, false)
        complaints=rootView.findViewById(R.id.message)
        sendBtn=rootView.findViewById(R.id.sendcomp)
        progressBar=rootView.findViewById(R.id.progressBarc)
        sendBtn.setOnClickListener OnClickListener@{

            val message = complaints.text.toString().trim()

            progressBar.visibility = View.VISIBLE
            val checker =message.isEmpty()
            if (checker) {

                progressBar.visibility = View.GONE
                complaints.error="please enter your feedback"
                return@OnClickListener

            }
            complaints.error=null
            progressBar.visibility = View.GONE

            val uID:String= FirebaseAuth.getInstance().currentUser!!.uid
            FirebaseWriter.writeWithAutoKey(message, OnSuccessListener<Void> {

                complaints.text=null
                val builder1 = AlertDialog.Builder(context)
                builder1.setMessage("Your feedback sent successfully.")
                builder1.setCancelable(true)

                builder1.setPositiveButton(
                        "Yes"
                ) { dialog, id -> dialog.cancel() }


                val alert11 = builder1.create()
                alert11.show()
            }, "Feedbacks", uID)


//            val database: FirebaseDatabase = FirebaseDatabase.getInstance()
//            database.getReference("Complaints").child(uID).push().setValue(message)


        }
        return rootView
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
         * @return A new instance of fragment FeedbackFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                FeedbackFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
