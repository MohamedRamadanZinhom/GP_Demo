package FireBase

import User.User
import code.Location

import com.google.firebase.database.*

class LocationTable {

    private lateinit var Ref: DatabaseReference
    private val database= FirebaseDatabase.getInstance()

    fun Add(location: Location){
        Ref=database.getReference("Location")
        val ID = Ref.push().key
        Ref.child(location.id.toString()).setValue(location)
    }

    public fun Search(location: Location){

    }

    public fun Delete(location: Location){

    }

    public fun Update(location: Location){
    }




}