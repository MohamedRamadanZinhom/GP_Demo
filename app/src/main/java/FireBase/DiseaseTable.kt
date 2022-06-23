package FireBase

import Disease.Disease
import Disease.Heart
import User.User
import android.view.View
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import code.DashAdapter
import com.google.firebase.database.*

class DiseaseTable {

    private lateinit var Ref: DatabaseReference
    private val database= FirebaseDatabase.getInstance()

    fun Add(disease: Disease){
        Ref=database.getReference("Users")
        val ID = Ref.push().key
        Ref.child(disease.ID.toString()).setValue(disease)
    }


    private fun itemaction(adapter:DashAdapter,view: View){
        val adapt=adapter
        adapt.setOnClickLisener(object :DashAdapter.adapterclick{
            override fun onclick(position: Int) {
               //Navigation.findNavController(view).navigate(R.id.action_dashBoard_to_disease_info)
            }

        })
    }

    fun GetAll(recycle:RecyclerView){

       val list = arrayListOf<Disease>()
        val ref =FirebaseDatabase.getInstance().getReference("Disease")
        ref.addValueEventListener( object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){

                    for (disease in snapshot.children){

                        val dis=disease.getValue(Heart::class.java)
                        list.add(dis!!)
                    }

                    val adapter= DashAdapter(list)
                    recycle.adapter=adapter

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }



}