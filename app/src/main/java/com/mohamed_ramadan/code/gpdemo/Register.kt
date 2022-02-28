package com.mohamed_ramadan.code.gpdemo

import FireBase.Authentication
import FireBase.UserTable
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.core.widget.doOnTextChanged
import androidx.navigation.Navigation
import User.User
import com.google.android.material.textfield.TextInputLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Register.newInstance] factory method to
 * create an instance of this fragment.
 */
class Register : Fragment() {




    private lateinit var name:EditText
    private lateinit var email:EditText
    private lateinit var password:EditText
    private var istrue:Boolean=false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        val buttonRegister:Button=view.findViewById(R.id.regbutton)

        ActionBar.DISPLAY_HOME_AS_UP
        name =view.findViewById<EditText>(R.id.usenamereg)
        email =view.findViewById<EditText>(R.id.emailreg)
        password =view.findViewById<EditText>(R.id.passwordreg)




        //-------------------
        //change password counter text color
        changepasswordtextcolor(view)
        //-------

        buttonRegister.setOnClickListener{




                btnaction()

           val c= UserTable()._GetSize()

                //navigate to Login user
              Toast.makeText(this.context, "User Count: $c", Toast.LENGTH_SHORT).show()


                Navigation.findNavController(view).navigate(R.id.action_register_to_login)


        }



        return view
    }



    private fun createRequest(user: User):Boolean {
        val fireAuth = Authentication(this.requireActivity())
        return fireAuth.Register(user)
    }

    private fun check():Boolean{

        if(name.text.isEmpty())
        {
            Toast.makeText(this.context, "Please Enter Your Name.",
                Toast.LENGTH_SHORT).show()
            return false
        }

        if(email.text.isEmpty())
        {
            if(!email.text.contains('@')) {
                Toast.makeText(
                    this.context, "Please Enter valid Email contain '@' .",
                    Toast.LENGTH_SHORT
                ).show()
                return false
            }
            Toast.makeText(
                this.context, "Please Enter Your Email.",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        if(password.text.isEmpty())
        {
            Toast.makeText(this.context, "Please Enter Your Name.",
                Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }



    private fun btnaction(){
            //create user
            if(check())
            {


                //----------------------------------
                val user = User((3).toString(), name.text.toString(), email.text.toString(), password.text.toString())
                //make Authentication by adding new user
                adduser(user)
                createRequest(user)

            }


    }

    private fun adduser(user:User){
       UserTable().Add(user)
    }

    private fun changepasswordtextcolor(view:View){

        password.doOnTextChanged{text, start, before, count ->
            val x =view.findViewById<TextInputLayout>(R.id.ed3reg)
            if(password.text.length>=6)
            {
                x.counterTextColor= ColorStateList.valueOf(Color.GREEN)
                x.setHelperTextColor(ColorStateList.valueOf(Color.GREEN))
            }
            else
            {
                x.counterTextColor= ColorStateList.valueOf(Color.RED)
            }
        }
    }



}