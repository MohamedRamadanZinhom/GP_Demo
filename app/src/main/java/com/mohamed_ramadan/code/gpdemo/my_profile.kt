package com.mohamed_ramadan.code.gpdemo

import Disease.Heart
import FireBase.Authentication
import FireBase.UserTable
import User.User
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import code.DashAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_my_profile.*
import kotlinx.android.synthetic.main.fragment_my_profile.view.*


class my_profile : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_my_profile, container, false)

       // Toast.makeText(this.requireContext(),FirebaseAuth.getInstance().uid,Toast.LENGTH_SHORT).show()
        UserData(view)

        view.update_pro.setOnClickListener{

            val name=profile_name.text.toString()
            val email=profile_Email.text.toString()
            val password=profile_password.text.toString()
            if(check()){

                val user =User("3",name,email,password)
                Update(user)
                Toast.makeText(this.context, "Data Updated successfully.", Toast.LENGTH_SHORT).show()

            }
        }


        return view
    }

    fun UserData(view: View){
        val auth=FirebaseAuth.getInstance()

        val ref = FirebaseDatabase.getInstance().getReference("Users")
        ref.child(auth.uid!!).addValueEventListener( object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    val email=snapshot.child("email").value
                    val id=snapshot.child("id").value
                    val name=snapshot.child("name").value
                    val password=snapshot.child("password").value

                    view.profile_name.setText(name.toString())
                    view.profile_Email.setText(email.toString())
                    view.profile_password.setText(password.toString())
                    view.pro_username.setText(name.toString())


                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    fun Update(user:User){

        val database= FirebaseDatabase.getInstance()
        val Ref=database.getReference("Users")
        val auth= FirebaseAuth.getInstance()
        val id=auth.uid
        Ref.child(id.toString()).setValue(user)
        UserData(this.requireView())
    }

    private fun check():Boolean{

        if(profile_name.text!!.isEmpty())
        {
            Toast.makeText(this.context, "Please Enter Your Name.",
                Toast.LENGTH_SHORT).show()
            return false
        }

        if(profile_Email.text!!.isEmpty())
        {
            if(!profile_Email.text!!.contains('@')) {
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

        if(profile_password.text!!.isEmpty())
        {
            Toast.makeText(this.context, "Please Enter Your Name.",
                Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }













}