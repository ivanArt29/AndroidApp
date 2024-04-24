package com.example.prj3.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class MainMenuViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var navigateToRegistrationViewModel = MutableLiveData<Boolean>()
    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    fun quit(){
        auth.signOut()
        navigateToRegistrationViewModel.value = true
    }
}