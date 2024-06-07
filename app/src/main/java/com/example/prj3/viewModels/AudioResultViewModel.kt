package com.example.prj3.viewModels

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class AudioResultViewModel : ViewModel() {
    fun insertData(levelId: Int, correct: Int){

        val innerEmail = Firebase.auth.currentUser?.email?.replace(".", "_")

        var completed = false


        val db: FirebaseDatabase by lazy {
            FirebaseDatabase.getInstance("https://userbase-7c254-default-rtdb.europe-west1.firebasedatabase.app")
        }
        val ref = db.getReference("users/$innerEmail/audio")

        if (correct >= 3) {
            completed = true

        } else{
            completed = false
        }

        if (completed == true)
        {
            ref.child(levelId.toString()).setValue(completed)
        }

    }
}