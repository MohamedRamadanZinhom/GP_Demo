package com.mohamed_ramadan.code.gpdemo

import android.app.ActionBar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [disease_info.newInstance] factory method to
 * create an instance of this fragment.
 */
class disease_info : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_disease_info, container, false)

        val toolbar: androidx.appcompat.widget.Toolbar=view.findViewById(R.id.infotoolbar)
        toolbar.setTitle("")


        ActionBar.DISPLAY_HOME_AS_UP

        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val btn:Button=view.findViewById(R.id.inf_btn)

        btn.setOnClickListener{btn_clic(view)}





        return view
    }


    fun btn_clic(view:View){

        Navigation.findNavController(view).navigate(R.id.action_disease_info_to_questionTest)

    }

}