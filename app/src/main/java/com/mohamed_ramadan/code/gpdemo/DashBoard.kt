package com.mohamed_ramadan.code.gpdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import Disease.Disease
import Disease.Heart
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import code.DashAdapter
import com.google.firebase.database.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DashBoard.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashBoard : Fragment()  {



    private lateinit var  Ref :DatabaseReference
    private lateinit var recycle:RecyclerView

    private lateinit var list: ArrayList<Disease>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_dash_board, container, false)

        ActionBar.DISPLAY_HOME_AS_UP
        val heart= Heart()
       // CreatDisease(heart)










        recycle=view.findViewById(R.id.recycler_dash)
        recycle.setHasFixedSize(true)
        recycle.layoutManager= LinearLayoutManager(this.requireActivity())

        list= arrayListOf<Disease>()


        list.add(heart)
        GetAllDisease()
       // Toast.makeText(requireActivity(), list.size.toString(),Toast.LENGTH_SHORT).show()

        val adapter=DashAdapter(list)
        recycle.adapter=adapter







        return view
    }

    private fun CreatDisease(disease: Disease){

        //create instance from fire base database
        val database= FirebaseDatabase.getInstance()
        //create reference from users table in data base
        var ref =database.getReference("Disease")
        ref.push().key
        ref.child(disease.ID.toString()).setValue(disease)

    }
    private fun GetAllDisease() {


        Ref=FirebaseDatabase.getInstance().getReference("Disease")


        //--------------------------

        val listener = object  :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){

                    for (disease in snapshot.children){
                        val value =disease.getValue(Heart::class.java)

                        list.add(value!!)
                    }
                    //Toast.makeText(requireActivity(), snapshot.children.count().toString(),Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireActivity(), "Failed To Load Data",Toast.LENGTH_SHORT).show()
            }
        }

        Ref.addValueEventListener(listener)


//end
    }


}