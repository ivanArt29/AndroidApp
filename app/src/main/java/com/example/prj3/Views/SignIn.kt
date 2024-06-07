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
import com.example.prj3.databinding.FragmentSignInBinding
import com.example.prj3.viewModels.SignInViewModel

class SignIn : Fragment() {

    companion object {
        fun newInstance() = SignIn()
    }

    private lateinit var binding: FragmentSignInBinding
    private val viewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.SignIn.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString()
            val password = binding.editTextTextPassword.text.toString()

            // Вход пользователя
            viewModel.signInWithEmail(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = viewModel.auth.currentUser
                        if (user != null && user.isEmailVerified) {

                            Toast.makeText(requireContext(), "Sign in as ${viewModel.auth.currentUser?.email}", Toast.LENGTH_LONG).show()

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

        binding.textViewReg.setOnClickListener{
            findNavController().navigate(R.id.registration)
        }
    }


}