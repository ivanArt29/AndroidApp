package com.example.prj3.Views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.prj3.R
import com.example.prj3.TestBd.MyWords
import com.example.prj3.TestBd.RepositoryImpl
import com.example.prj3.TestBd.StagesListViewModelFactory
import com.example.prj3.databinding.FragmentTestInnerBinding
import com.example.prj3.viewModels.TestInnerViewModel
import com.example.prj3.viewModels.TestResultViewModel
import kotlinx.coroutines.Dispatchers


class Test_inner : Fragment() {

    var stageId = 1
    var levelId: Int? = null

    var correctCount = 0

//    var correctWordForStage = ""

    private val viewModel: TestInnerViewModel by viewModels(){
        StagesListViewModelFactory(RepositoryImpl(requireContext(), Dispatchers.IO))
    }
    private lateinit var binding: FragmentTestInnerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTestInnerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        levelId = arguments?.getInt("level")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (stageId == 1 ){
            correctCount = 0
            if (levelId != null) {
                viewModel.getWordsForStage(levelId!!, stageId)
                viewModel.getRuWordForStage(levelId!!, stageId)
            }

        }


        viewModel.wordsForStage.observe(viewLifecycleOwner) { words ->
            if (words != null) {
                updateButtonsWithWords(words)
            }
        }

        viewModel.ruWordsForStage.observe(viewLifecycleOwner) { words ->
            if (words != null) {
                binding.textViewWord.text = words.first().ruWord
            }
        }

        viewModel.correctCount.observe(viewLifecycleOwner){counter ->
            correctCount = counter
        }

    }



    private fun updateButtonsWithWords(words: List<MyWords>) {
        val buttonIds = listOf(
            R.id.button1,
            R.id.button2,
            R.id.button3,
            R.id.button4
        )
        words.take(4).forEachIndexed { index, word ->
            val button = requireView().findViewById<Button>(buttonIds[index])
            button.text = word.word
            button.setOnClickListener {
                if (levelId != null) {
                    handleButtonClick(word.word, levelId!!, stageId)
                }
            }
        }
    }

    private fun handleButtonClick(selectedWord: String, level: Int, stage: Int) {
        if (stageId == 5) {
            viewModel.correctCount.observe(viewLifecycleOwner) { counter ->
                correctCount = counter
            }
            if (levelId != null) {
                viewModel.getCorrectWord(levelId!!, stageId)
            }
            viewModel.correctWordForStage.observe(viewLifecycleOwner) { correctWord ->
                if (selectedWord == correctWord) {
                    viewModel.incrementCorrectCount()
                }
                Thread.sleep(50)
                navigateToResult(viewModel.correctCount.value ?: 0)
            }
        } else {
            if (levelId != null) {
                viewModel.getCorrectWord(levelId!!, stageId)
            }
            viewModel.correctWordForStage.observe(viewLifecycleOwner) { correctWord ->
                if (selectedWord == correctWord) {
                    viewModel.incrementCorrectCount()
                }
                if (levelId != null) {
                    viewModel.getWordsForStage(levelId!!, stageId)
                    viewModel.getRuWordForStage(levelId!!, stageId)
                }
            }
            stageId++
        }
    }

    private fun navigateToResult(correctCount: Int) {
        val bundle = Bundle().apply {
            putInt("correctCount", correctCount)
            levelId?.let { putInt("level", it) }
        }
        findNavController().navigate(R.id.testResult, bundle)
    }
}