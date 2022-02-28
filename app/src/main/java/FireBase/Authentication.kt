package FireBase

import User.User
import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mohamed_ramadan.code.gpdemo.R
import kotlin.properties.Delegates


 class Authentication(private val ctx:Activity) {



     var v:Boolean=false
     private var auth: FirebaseAuth = Firebase.auth

    public fun Register(user: User):Boolean{
        //Create New AuthenticationUser
        val context =ctx
        var check:Boolean=false
        auth.createUserWithEmailAndPassword(user.Email.toString(), user.Password.toString())
            .addOnCompleteListener(context) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(context, "Authentication succeeded.",
                        Toast.LENGTH_SHORT).show()

                   check=true

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(context, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    check=false
                }
            }
        return check
    }

    public fun LogIn(user:User) :Boolean{

        val fireAuth= FirebaseAuth.getInstance()
        fireAuth.signInWithEmailAndPassword(user.Email,user.Password)
            .addOnCompleteListener(ctx){ Task->
                val x = if(Task.isSuccessful){
                    Toast.makeText(ctx,"Login Success ", Toast.LENGTH_SHORT).show()
                    true

                } else {
                    Toast.makeText(ctx,"Login Failed ", Toast.LENGTH_SHORT).show()
                    false
                }
               this.v=x
        }
        return this.v
    }


}


