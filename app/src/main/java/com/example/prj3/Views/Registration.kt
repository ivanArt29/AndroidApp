package com.example.prj3.Views

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.prj3.R
import com.example.prj3.databinding.FragmentRegistrationBinding
import com.example.prj3.viewModels.RegistrationViewModel
import com.google.firebase.database.FirebaseDatabase

class Registration : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
    private val viewModel: RegistrationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.regButton.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString()
            val password = binding.editTextTextPassword.text.toString()

            // Регистрация пользователя
            viewModel.registerWithEmail(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Если регистрация успешна, отправляем письмо для подтверждения адреса электронной почты
                        viewModel.sendEmailVerification()?.addOnCompleteListener { emailTask ->
                            if (emailTask.isSuccessful) {
                                Toast.makeText(requireContext(), "Check your email", Toast.LENGTH_LONG).show()
                                viewModel.insertData(email)
                                findNavController().navigate(R.id.verification)
                            } else {
                                // Обработка ошибок отправки письма для подтверждения
                            }
                        }
                    } else {
                        // Обработка ошибок регистрации
                    }
                }
        }

        binding.textViewSignIn.setOnClickListener{
            findNavController().navigate(R.id.signIn)
        }
    }
}

