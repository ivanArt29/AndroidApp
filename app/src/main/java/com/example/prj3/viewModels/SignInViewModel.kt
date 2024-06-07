package com.example.prj3.viewModels

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignInViewModel : ViewModel() {
    val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    fun registerWithEmail(email: String, password: String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, password)
    }

    fun sendEmailVerification(): Task<Void>? {
        val user = auth.currentUser
        return user?.sendEmailVerification()
    }

    fun signInWithEmail(email: String, password: String): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(email, password)
    }

    fun insertData(email: String){

        val innerEmail = email.replace(".", "_")
        val userData = hashMapOf(
            "test" to hashMapOf(
                "1" to false,
                "2" to false,
                "3" to false,
                "4" to false,
                "5" to false
            ),
            "sentences" to hashMapOf(
                "1" to false,
                "2" to false,
                "3" to false,
                "4" to false,
                "5" to false
            ),
            "audio" to hashMapOf(
                "1" to false,
                "2" to false,
                "3" to false,
                "4" to false,
                "5" to false
            )
        )

        val db: FirebaseDatabase by lazy {
            FirebaseDatabase.getInstance("https://userbase-7c254-default-rtdb.europe-west1.firebasedatabase.app")
        }
        val ref = db.getReference("users")

        ref.child(innerEmail).setValue(userData)


    }
}