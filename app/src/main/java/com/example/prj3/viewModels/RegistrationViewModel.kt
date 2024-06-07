package com.example.prj3.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegistrationViewModel : ViewModel() {
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
//    private val auth: FirebaseAuth by lazy {
//        FirebaseAuth.getInstance()
//    }
//    val toastMessage = MutableLiveData<String>()
//    val liveData = MutableLiveData<AuthResult>()
//    val toastSuccess = MutableLiveData<String>()
//    val navigateToMainMenu = MutableLiveData<Boolean>()
//    val verToast = MutableLiveData<String>()
//    val verifiedToast = MutableLiveData<Boolean>()
//
//    val toastCheck = MutableLiveData<String>()
//
//    private fun checkFields(email: String, password: String): Boolean {
//        if (email.isBlank() || password.isBlank()) {
//            toastCheck.value = "Fill all strings"
//            return false
//        }
//        return true
//    }
//
//    fun onStart()
//    {
//        val currentUser = auth.currentUser
//
////        if (currentUser != null)
////        {
////            navigateToMainMenu.value = true
////            toastMessage.value = "Вы вошли как ${currentUser.email}"
////            auth.signOut()
////
////        }
////        else
////        {
////            toastMessage.value = "User null"
////
////        }
//
//    }
//
//    private fun registration(email: String, password: String)
//    {
//        auth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    send_email_ver() // Отправляем запрос на верификацию только после успешной регистрации
//                    toastSuccess.value = "User sign up successful"
//
//                } else {
//                    toastSuccess.value = "User sign up failed"
//                }
//            }
//    }
//    private fun sign_in(email: String, password: String) {
//        FirebaseAuth.getInstance()
//        auth.signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    toastSuccess.value = "User sign in successful"
//                    navigateToMainMenu.value = true
//                } else {
//                    toastSuccess.value = "User sign in failed"
//                }
//            }
//    }
//
//    private fun send_email_ver()
//    {
//        val user = auth.currentUser
//        user?.sendEmailVerification()?.addOnCompleteListener{ task ->
//            if (task.isSuccessful) {
//                toastMessage.value = "Check your email address"
//
//            } else {
//                toastMessage.value = "Verification failed"
//            }
//        }
//    }
//
//    private fun verified(): Boolean
//    {
//        return auth.currentUser?.isEmailVerified == true
//    }
//
//
//    fun onButtonClick(email: String, password: String) {
//        if (!checkFields(email,password))
//        {
//            return
//        }
//        else{
//            registration(email,password)
//        }
//
//    }
//
//    fun signInClick(email: String, password: String) {
//        if (!checkFields(email,password)) {
//            toastCheck.value = "Fill all fields"
//            return
//        }
//        FirebaseAuth.getInstance()
//        val currentUser = auth.currentUser
//        if (currentUser != null && currentUser.isEmailVerified) {
//            sign_in(email, password)
//        } else {
//            toastMessage.value = "Verify your email"
//        }
//    }

}