package com.mohamed_ramadan.code.gpdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [spalsh.newInstance] factory method to
 * create an instance of this fragment.
 */
class spalsh : Fragment() {
    // TODO: Rename and change types of parameters



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_spalsh, container, false)
        val button:Button=view.findViewById(R.id.Logbutton)
        val buttonRegister:Button=view.findViewById(R.id.Regbutton)


        button.setOnClickListener({Navigation.findNavController(view).navigate(R.id.action_spalsh_to_login)})
        buttonRegister.setOnClickListener({Navigation.findNavController(view).navigate(R.id.action_spalsh_to_register)})

        return view
    }


    }





