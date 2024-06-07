package com.example.prj3.viewModels

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SentencesExViewModel : ViewModel() {
    fun checkCompleted(levelId: Int, callback: (Boolean) -> Unit) {
        val firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser
        val email = currentUser?.email.toString().replace(".", "_")

        val databaseReference = FirebaseDatabase.getInstance("https://userbase-7c254-default-rtdb.europe-west1.firebasedatabase.app")
            .reference.child("users").child(email).child("sentences").child(levelId.toString())

        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Получение данных
                val value = dataSnapshot.value

                // Проверка и обработка полученных данных
                val result = value == true
                callback(result)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Обработка ошибки
                println("Error: ${databaseError.message}")
                callback(false)
            }
        })
    }
}