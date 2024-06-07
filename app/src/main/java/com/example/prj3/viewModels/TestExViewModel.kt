package com.example.prj3.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.prj3.TestBd.Levels
import com.example.prj3.TestBd.MyWords
import com.example.prj3.TestBd.Repository
import com.example.prj3.TestBd.RuWords
import com.example.prj3.TestBd.Stages
import com.example.prj3.TestBd.TestDB
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch

class TestExViewModel(private val repository: Repository) : ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading:LiveData<Boolean> = _dataLoading

    init {
//        for (level in TestDB.PREPOPULATE_DATA){
//            viewModelScope.launch {
//                repository.insert(level)
//            }
//        }
    }



    fun insertLevel(level: Levels)
    {
        showProgress()
        viewModelScope.launch {
            repository.insert(level)
        }
        hideProgress()
    }

    fun updateLevel(level: Levels)
    {
        showProgress()
        viewModelScope.launch {
            repository.update(level)
        }
        hideProgress()
    }

    fun deleteLevel(level: Levels)
    {
        showProgress()
        viewModelScope.launch {
            repository.delete(level)
        }
        hideProgress()
    }

    fun getAllLevels(): LiveData<List<Levels>>
    {
        val levels: LiveData<List<Levels>>?
        _dataLoading.value = true
        levels = repository.getAllLevels().asLiveData()
        _dataLoading.value = false
        return levels
    }

    fun insertStage(stage: Stages)
    {
        showProgress()
        viewModelScope.launch {
            repository.insertStage(stage)
        }
        hideProgress()
    }

    fun insertWord(word: MyWords)
    {
        showProgress()
        viewModelScope.launch {
            repository.insertWord(word)
        }
        hideProgress()
    }

    fun insertRuWord(ruWords: RuWords)
    {
        showProgress()
        viewModelScope.launch {
            repository.insertRuWord(ruWords)
        }
        hideProgress()
    }

    private fun showProgress(){
        _dataLoading.value = true
    }

    private fun hideProgress(){
        _dataLoading.value = false
    }

//    fun checkCompleted(levelId: Int) : Boolean {
//        val firebaseAuth = FirebaseAuth.getInstance()
//        var result = false
//        val currentUser = firebaseAuth.currentUser
//
//
//        val email = currentUser?.email.toString().replace(".", "_")
//
//        val databaseReference = FirebaseDatabase.getInstance("https://userbase-7c254-default-rtdb.europe-west1.firebasedatabase.app").reference.child("users").child(email).child("test").child(levelId.toString())
//
//        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                // Получение данных
//                val value = dataSnapshot.value
//
//                // Проверка и обработка полученных данных
//                if (value == true) {
//                    result = true
//                } else {
//                    result = false
//                }
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                // Обработка ошибки
//                println("Error: ${databaseError.message}")
//            }
//        })
//
//        return result
//
//    }

    fun checkCompleted(levelId: Int, callback: (Boolean) -> Unit) {
        val firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser
        val email = currentUser?.email.toString().replace(".", "_")

        val databaseReference = FirebaseDatabase.getInstance("https://userbase-7c254-default-rtdb.europe-west1.firebasedatabase.app")
            .reference.child("users").child(email).child("test").child(levelId.toString())

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