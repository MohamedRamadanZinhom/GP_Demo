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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.mohamed_ramadan.code.gpdemo.R


class Register : Fragment() {




    private lateinit var name:EditText
    private lateinit var email:EditText
    private lateinit var password:EditText




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
            //Toast.makeText(this.context, "User Count: $c", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(view).navigate(R.id.action_register_to_login)
        }



        return view
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
                //making Register Process
                Register(user)


            }


    }



    private fun changepasswordtextcolor(view:View){

        password.doOnTextChanged{text, start, before, count ->
            val x =view.findViewById<TextInputLayout>(R.id.ed3reg)
            if(password.text.length>=6)
            {
                x.counterTextColor= ColorStateList.valueOf(Color.GREEN)
                x.setHelperTextColor(ColorStateList.valueOf(Color.GREEN))
                x.helperText=""
            }
            else
            {
                x.helperText="Require at Least 6 Character "
                x.counterTextColor= ColorStateList.valueOf(Color.RED)
                x.setHelperTextColor(ColorStateList.valueOf(Color.RED))
            }
        }
    }


     fun Register(user: User){

        val fireAuth= FirebaseAuth.getInstance()

        val context =this.requireActivity()

        //Create New AuthenticationUser
        fireAuth.createUserWithEmailAndPassword(user.Email, user.Password)
            .addOnCompleteListener(context) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(context, "Authentication succeeded.", Toast.LENGTH_SHORT).show()
                    //Add_New User...
                    val u = fireAuth.currentUser
                    //user.ID=u!!.uid.toString()

                    UserTable().Add(user)


                    //UpdateUI(user)



                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show()

                }
            }



    }

    private fun UpdateUI(user: FirebaseUser?) {


    }





}