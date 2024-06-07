package com.example.prj3.Views

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.prj3.viewModels.AudioExViewModel
import com.example.prj3.R
import com.example.prj3.databinding.FragmentAudioExBinding
import com.example.prj3.databinding.FragmentSentencesExBinding

class AudioEx : Fragment() {

    companion object {
        fun newInstance() = AudioEx()
    }

    private val viewModel: AudioExViewModel by viewModels()

    private lateinit var binding: FragmentAudioExBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.checkCompleted(1) { isCompleted ->
            if (isCompleted) {
                binding.btn1.setText("Уровень 1(пройден)")
            }
        }
        viewModel.checkCompleted(2) { isCompleted ->
            if (isCompleted) {
                binding.btn2.setText("Уровень 2(пройден)")
            }
        }
        viewModel.checkCompleted(3) { isCompleted ->
            if (isCompleted) {
                binding.btn3.setText("Уровень 3(пройден)")
            }
        }
        viewModel.checkCompleted(4) { isCompleted ->
            if (isCompleted) {
                binding.btn4.setText("Уровень 4(пройден)")
            }
        }
        viewModel.checkCompleted(5) { isCompleted ->
            if (isCompleted) {
                binding.btn5.setText("Уровень 5(пройден)")
            }
        }

        binding.buttonBack.setOnClickListener{
            findNavController().navigate(R.id.mainMenu)
        }

        binding.btn1.setOnClickListener{
            val bundle = Bundle().apply {
                putInt("level", 1)
            }
            findNavController().navigate(R.id.audioInner, bundle)
        }
        binding.btn2.setOnClickListener{
            val bundle = Bundle().apply {
                putInt("level", 2)
            }
            findNavController().navigate(R.id.audioInner, bundle)
        }
        binding.btn3.setOnClickListener{
            val bundle = Bundle().apply {
                putInt("level", 3)
            }
            findNavController().navigate(R.id.audioInner, bundle)
        }
        binding.btn4.setOnClickListener{
            val bundle = Bundle().apply {
                putInt("level", 4)
            }
            findNavController().navigate(R.id.audioInner, bundle)
        }
        binding.btn5.setOnClickListener{
            val bundle = Bundle().apply {
                putInt("level", 5)
            }
            findNavController().navigate(R.id.audioInner, bundle)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAudioExBinding.inflate(inflater,container,false)
        return binding.root
    }
}