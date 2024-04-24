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

class Registration : Fragment() {

//private lateinit var binding: FragmentRegistrationBinding
//    private lateinit var viewModel: RegistrationViewModel
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        viewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)
//
//        // Вызываем метод onStart() во время создания фрагмента
//        viewModel.onStart()
//
//        // Обработчики нажатия кнопок регистрации и входа
//        binding.regButton.setOnClickListener {
//            val email = binding.editTextTextEmailAddress.text.toString()
//            val password = binding.editTextTextPassword.text.toString()
//            viewModel.onButtonClick(email, password)
//        }
//
//        binding.SignIn.setOnClickListener {
//            val email = binding.editTextTextEmailAddress.text.toString()
//            val password = binding.editTextTextPassword.text.toString()
//            viewModel.signInClick(email, password)
//        }
//
//        // Наблюдатели за изменениями в LiveData
////        viewModel.navigateToMainMenu.observe(viewLifecycleOwner, Observer { navigate ->
////            if (navigate) {
////                findNavController().navigate(R.id.mainMenu)
////            }
////        })
//        viewModel.navigateToMainMenu.observe(viewLifecycleOwner, Observer { navigate ->
//            if (navigate) {
//                navigateToMainMenu()
//            }
//        })
//
//        viewModel.toastMessage.observe(viewLifecycleOwner, Observer { message ->
//            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
//        })
//
//
//    }
//    private fun navigateToMainMenu() {
//        findNavController().navigate(R.id.mainMenu)
//    }
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
                                // Перенаправляем пользователя на экран подтверждения
                                // (например, экран, сообщающий об отправке письма для подтверждения)
                                Toast.makeText(requireContext(), "Check your email", Toast.LENGTH_LONG).show()
                            } else {
                                // Обработка ошибок отправки письма для подтверждения
                            }
                        }
                    } else {
                        // Обработка ошибок регистрации
                    }
                }
        }

        binding.SignIn.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString()
            val password = binding.editTextTextPassword.text.toString()

            // Вход пользователя
            viewModel.signInWithEmail(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = viewModel.auth.currentUser
                        if (user != null && user.isEmailVerified) {
                            // Пользователь успешно вошел в систему и адрес электронной почты подтвержден
                            // Перенаправляем пользователя на главный экран приложения
                            Toast.makeText(requireContext(), "Sign in as $user.", Toast.LENGTH_LONG).show()
                            findNavController().navigate(R.id.mainMenu)
                        } else {
                            // Обработка случая, когда пользователь не подтвердил адрес электронной почты
                            Toast.makeText(requireContext(), "Verify your email", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        // Обработка ошибок входа
                    }
                }
        }
    }
}

