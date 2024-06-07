package com.example.prj3.Views

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.prj3.R
import com.example.prj3.databinding.FragmentSentencesResultBinding
import com.example.prj3.viewModels.SentencesResultViewModel

class SentencesResult : Fragment() {

    companion object {
        fun newInstance() = SentencesResult()
    }

    private val viewModel: SentencesResultViewModel by viewModels()


    private lateinit var binding: FragmentSentencesResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSentencesResultBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val correctCount = arguments?.getInt("correctCount")
        val levelId = arguments?.getInt("level")

        binding.textView5.text = "Ваш результат $correctCount/5"

        if (levelId != null) {
            if (correctCount != null) {
                viewModel.insertData(levelId,correctCount)
            }
        }

        binding.button1.setOnClickListener {
            findNavController().navigate(R.id.sentences_ex)
        }
    }


}