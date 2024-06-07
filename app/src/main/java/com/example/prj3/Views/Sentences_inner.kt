package com.example.prj3.Views

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.prj3.R
import com.example.prj3.SentencesDB.RepositorySentences
import com.example.prj3.SentencesDB.RepositorySentencesImpl
import com.example.prj3.SentencesDB.SentencesChoice
import com.example.prj3.SentencesDB.SentencesStagesListViewModelFactory
import com.example.prj3.TestBd.MyWords
import com.example.prj3.TestBd.RepositoryImpl
import com.example.prj3.TestBd.StagesListViewModelFactory
import com.example.prj3.databinding.FragmentSentencesInnerBinding
import com.example.prj3.databinding.FragmentTestInnerBinding
import com.example.prj3.viewModels.SentencesInnerViewModel
import com.example.prj3.viewModels.TestInnerViewModel
import kotlinx.coroutines.Dispatchers

class sentences_inner : Fragment() {

    companion object {
        fun newInstance() = sentences_inner()
    }
    var stageId = 1
    var levelId: Int? = null

    var correctCount = 0

    private val viewModel: SentencesInnerViewModel by viewModels(){
        SentencesStagesListViewModelFactory(RepositorySentencesImpl(requireContext(), Dispatchers.IO))
    }
    private lateinit var binding: FragmentSentencesInnerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        levelId = arguments?.getInt("level")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSentencesInnerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (stageId == 1 ){
            correctCount = 0
            if (levelId != null) {
                viewModel.getSentencesForStage(levelId!!, stageId)
                viewModel.getEmptySentenceForStage(levelId!!, stageId)
            }

        }


        viewModel.sentencesForStage.observe(viewLifecycleOwner) { words ->
            if (words != null) {
                updateButtonsWithWords(words)
            }
        }

        viewModel.emptySentenceForStage.observe(viewLifecycleOwner) { words ->
            if (words != null) {
                binding.textViewWord.text = words.first().text
            }
        }

        viewModel.correctCount.observe(viewLifecycleOwner){counter ->
            correctCount = counter
        }

    }



    private fun updateButtonsWithWords(words: List<SentencesChoice>) {
        val buttonIds = listOf(
            R.id.button1,
            R.id.button2,
            R.id.button3,
            R.id.button4
        )
        words.take(4).forEachIndexed { index, word ->
            val button = requireView().findViewById<Button>(buttonIds[index])
            button.text = word.text
            button.setOnClickListener {
                if (levelId != null) {
                    handleButtonClick(word.text, levelId!!, stageId)
                }
            }
        }
    }

    private fun handleButtonClick(selectedSentence: String, level: Int, stage: Int) {
        if (stageId == 5) {
            viewModel.correctCount.observe(viewLifecycleOwner) { counter ->
                correctCount = counter
            }
            if (levelId != null) {
                viewModel.getCorrectSentence(levelId!!, stageId)
            }
            viewModel.correctSentenceForStage.observe(viewLifecycleOwner) { correctWord ->
                if (selectedSentence == correctWord) {
                    viewModel.incrementCorrectCount()
                }
                Thread.sleep(50)
                navigateToResult(viewModel.correctCount.value ?: 0)
            }
        } else {
            if (levelId != null) {
                viewModel.getCorrectSentence(levelId!!, stageId)
            }
            viewModel.correctSentenceForStage.observe(viewLifecycleOwner) { correctWord ->
                if (selectedSentence == correctWord) {
                    viewModel.incrementCorrectCount()
                }
                if (levelId != null) {
                    viewModel.getSentencesForStage(levelId!!, stageId)
                    viewModel.getEmptySentenceForStage(levelId!!, stageId)
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
        findNavController().navigate(R.id.sentencesResult, bundle)
    }
}