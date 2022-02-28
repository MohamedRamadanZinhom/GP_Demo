package com.mohamed_ramadan.code.gpdemo

import FireBase.Authentication
import User.User
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.ActionBar
import androidx.navigation.Navigation

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Login.newInstance] factory method to
 * create an instance of this fragment.
 */
class Login : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_login, container, false)

        ActionBar.DISPLAY_HOME_AS_UP
        val buttonLogin: Button =view.findViewById(R.id.logbtn)




        var email =view.findViewById<EditText>(R.id.emaillog)
        var pass =view.findViewById<EditText>(R.id.passlog)

        buttonLogin.setOnClickListener{

            val user =User("00","00",email.text.toString() ,pass.text.toString())
            if(!createRequest(user)){
                Navigation.findNavController(view).navigate(R.id.action_login_to_app_dash)
            }

        }



        return view

    }



    private fun createRequest(user: User): Boolean {

        val fireAuth = Authentication(this.requireActivity())
        return fireAuth.LogIn(user)

    }





}