package com.mohamed_ramadan.code.gpdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import Disease.Disease
import Disease.Heart
import FireBase.DiseaseTable
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import code.DashAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_dash_board.view.*
import kotlinx.coroutines.*


class DashBoard : Fragment()  {

    private lateinit var  Ref :DatabaseReference
    private lateinit var recycle:RecyclerView
    var list=arrayListOf<Disease>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_dash_board, container, false)

        ActionBar.DISPLAY_HOME_AS_UP

        view.progressBar_recycle.visibility=View.VISIBLE
       // CreatDisease(heart)

        recycle=view.findViewById(R.id.recycler_dash)
        recycle.setHasFixedSize(true)
        recycle.layoutManager= LinearLayoutManager(this.requireActivity())




        Get_All_Disease(view)










        //Toast.makeText(requireActivity(), list.size.toString(),Toast.LENGTH_SHORT).show()

        return view
    }









    private  fun  Get_All_Disease(view: View){

        list.clear()

        val ref =FirebaseDatabase.getInstance().getReference("Disease")
        ref.addValueEventListener( object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){

                    for (disease in snapshot.children){

                        val dis=disease.getValue(Heart::class.java)
                        list.add(dis!!)
                    }

                    val adapter=DashAdapter(list)
                    recycle.adapter=adapter

                    itemaction(adapter,view)
                }

            }


            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        view.progressBar_recycle.visibility=View.GONE




    }

    private fun itemaction(adapter:DashAdapter,view:View){
        val adapt=adapter
        adapt.setOnClickLisener(object :DashAdapter.adapterclick{
            override fun onclick(position: Int) {
                Navigation.findNavController(view).navigate(R.id.action_dashBoard_to_disease_info)
            }

        })
    }

    private fun CreatDisease(disease: Disease){

        //create instance from fire base database
        val database= FirebaseDatabase.getInstance()
        //create reference from users table in data base
        var ref =database.getReference("Disease")
        ref.push().key
        ref.child(disease.ID.toString()).setValue(disease)

    }

}