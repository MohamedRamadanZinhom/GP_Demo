package FireBase

import User.User
import android.content.Context
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseError

import com.google.firebase.database.DataSnapshot

import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class UserTable {


    private lateinit var Ref: DatabaseReference
    private val database= FirebaseDatabase.getInstance()





    fun Add(user:User){
        Ref=database.getReference("Users")
        Ref.push().key
        Ref.child(user.ID).setValue(user)
    }

    public fun Search(user:User){
        Ref.child("users").child(user.ID).get().addOnSuccessListener {

        }.addOnFailureListener{

        }
    }

    public fun Delete(user:User){
    }

    public fun Update(user:User){
    }


   var size :Int=0






     suspend fun Count():Int{

        var v = 0
        Ref=FirebaseDatabase.getInstance().getReference("Users")

        val listener = object :ValueEventListener{

            override fun  onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    v=(snapshot.childrenCount.toInt())
                    //Toast.makeText(ctx,"snapshot count "+(x).toString(),Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        }
        Ref.addValueEventListener(listener)
        //Toast.makeText(ctx,"snapshot count "+(x).toString(),Toast.LENGTH_SHORT).show()

        return  v

    }

  private  fun setSize_( ){

      GlobalScope.launch(Dispatchers.IO) {
          val v= async { Count() }
          size=v.await()
      }



  }

    public fun _GetSize():Int{
        setSize_()
        return this.size

    }



}