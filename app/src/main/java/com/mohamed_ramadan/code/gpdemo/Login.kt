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
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.navigation.Navigation
import androidx.room.util.StringUtil
import com.google.firebase.auth.FirebaseAuth
import com.mohamed_ramadan.code.gpdemo.R
import kotlinx.android.synthetic.main.fragment_dash_board.view.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.coroutines.*


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

            val email=email.text.toString()
            val password=pass.text.toString()

            if (!(email.isEmpty() || password.isBlank()) && !(password.isEmpty() || password.isBlank())){
                val user =User("00","00",email,password)
                view.progressBar2.visibility=View.VISIBLE
                LogIn(user)
            }
            else
            {
                Toast.makeText(this.requireContext(),"Please enter the data ", Toast.LENGTH_SHORT).show()
            }



        }



        return view

    }





    fun LogIn(user:User){

        val fireAuth= FirebaseAuth.getInstance()

        fireAuth.signInWithEmailAndPassword(user.Email,user.Password)
            .addOnCompleteListener(this.requireActivity()){ Task->
                if(Task.isSuccessful){
                    Toast.makeText(this.requireContext(),"Login Success ", Toast.LENGTH_SHORT).show()
                    Navigation.findNavController(this.requireView()).navigate(R.id.action_login_to_app_dash)
                }
                else {
                    Toast.makeText(this.requireContext(),"Login Failed ", Toast.LENGTH_SHORT).show()
                    this.requireView().progressBar2.visibility=View.GONE
                }
            }


    }





}