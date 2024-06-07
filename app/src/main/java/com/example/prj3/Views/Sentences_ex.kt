package com.example.prj3.Views

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.prj3.R
import com.example.prj3.databinding.FragmentSentencesExBinding

import com.example.prj3.viewModels.SentencesExViewModel

class Sentences_ex : Fragment() {

    private lateinit var binding: FragmentSentencesExBinding
    companion object {
        fun newInstance() = Sentences_ex()
    }

    private val viewModel: SentencesExViewModel by viewModels()

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
            findNavController().navigate(R.id.sentences_inner, bundle)
        }
        binding.btn2.setOnClickListener{
            val bundle = Bundle().apply {
                putInt("level", 2)
            }
            findNavController().navigate(R.id.sentences_inner, bundle)
        }
        binding.btn3.setOnClickListener{
            val bundle = Bundle().apply {
                putInt("level", 3)
            }
            findNavController().navigate(R.id.sentences_inner, bundle)
        }
        binding.btn4.setOnClickListener{
            val bundle = Bundle().apply {
                putInt("level", 4)
            }
            findNavController().navigate(R.id.sentences_inner, bundle)
        }
        binding.btn5.setOnClickListener{
            val bundle = Bundle().apply {
                putInt("level", 5)
            }
            findNavController().navigate(R.id.sentences_inner, bundle)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSentencesExBinding.inflate(inflater,container,false)
        return binding.root
//        return inflater.inflate(R.layout.fragment_sentences_ex, container, false)

    }
}