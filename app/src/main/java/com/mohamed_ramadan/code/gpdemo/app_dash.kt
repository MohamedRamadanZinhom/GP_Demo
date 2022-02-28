package com.mohamed_ramadan.code.gpdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [app_dash.newInstance] factory method to
 * create an instance of this fragment.
 */
class app_dash : Fragment() {

    lateinit var diseasebtn:CardView
    lateinit var ideabtn:CardView
    lateinit var profilebtn:CardView
    lateinit var infobtn:CardView
    lateinit var statisticsbtn:CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_app_dash, container, false)


        diseasebtnAction(view)

        return view
    }

    fun ideabtnAction(view: View){

    }

    fun profilebtnAction(view: View){

    }

    fun infobtnAction(view: View){

    }

    fun statisticsbtnAction(view: View){

    }

    fun diseasebtnAction(view: View){
        diseasebtn=view.findViewById(R.id.disease_cardview)
        diseasebtn.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_app_dash_to_dashBoard)
        }
    }



}