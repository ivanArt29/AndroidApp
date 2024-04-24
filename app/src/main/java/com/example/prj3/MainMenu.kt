package com.example.prj3

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.prj3.databinding.FragmentMainMenuBinding
import com.example.prj3.databinding.FragmentRegistrationBinding
import com.example.prj3.viewModels.MainMenuViewModel
import com.example.prj3.viewModels.RegistrationViewModel

class MainMenu : Fragment() {

    private lateinit var binding: FragmentMainMenuBinding

    private lateinit var viewModel: MainMenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainMenuViewModel::class.java)

//        binding.buttonQuit.setOnClickListener {
//            viewModel.quit()
//        }

        // TODO: Use the ViewModel
    }

    private fun navigateToRegistration() {
        findNavController().navigate(R.id.registration)
    }
}