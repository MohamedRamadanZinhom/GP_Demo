package FireBase

import Disease.Disease
import User.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DiseaseTable {

    private lateinit var Ref: DatabaseReference
    private val database= FirebaseDatabase.getInstance()

    fun Add(disease: Disease){
        Ref=database.getReference("Users")
        val ID = Ref.push().key
        Ref.child(disease.ID.toString()).setValue(disease)
    }

    fun GetAll(){

    }

}