package com.example.prj3.Views

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.prj3.R
import com.example.prj3.TestBd.MyWords
import com.example.prj3.TestBd.RepositoryImpl
import com.example.prj3.TestBd.RuWords
import com.example.prj3.TestBd.StagesListViewModelFactory
import com.example.prj3.databinding.FragmentTestInnerBinding
import com.example.prj3.viewModels.TestInnerViewModel
import kotlinx.coroutines.Dispatchers

class Test_inner : Fragment() {

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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var stageId = 1

        val levelId = 1

        viewModel.getWordsForStage(levelId,stageId)

        viewModel.getRuWordForStage(levelId,stageId)

        viewModel.wordsForStage.observe(viewLifecycleOwner) { words ->
            if (words != null) {
                updateButtonsWithWords(words)

            }
        }

//        viewModel.ruWordsForStage.observe(viewLifecycleOwner) { ruWord ->
//            if (ruWord != null) {
//                updateRuWord(ruWord,stageId)
//
//            }
//        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        }
    }

    private fun updateRuWord(ruWords: List<RuWords>, stage: Int){
        binding.textViewWord.text = ruWords[stage].toString()
    }

}