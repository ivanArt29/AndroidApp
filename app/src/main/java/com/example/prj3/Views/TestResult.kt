package com.example.prj3.Views

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.prj3.R
import com.example.prj3.TestBd.RepositoryImpl
import com.example.prj3.TestBd.StagesListViewModelFactory
import com.example.prj3.databinding.FragmentTestInnerBinding
import com.example.prj3.databinding.FragmentTestResultBinding
import com.example.prj3.viewModels.TestInnerViewModel
import com.example.prj3.viewModels.TestResultViewModel
import kotlinx.coroutines.Dispatchers

class testResult : Fragment() {

    companion object {
        fun newInstance() = testResult()
    }

    private val viewModel: TestResultViewModel by viewModels()

//    private val prevTestViewModel: TestInnerViewModel by viewModels()

    private val previousViewModel: TestInnerViewModel by viewModels(){
        StagesListViewModelFactory(RepositoryImpl(requireContext(), Dispatchers.IO))
    }


    private lateinit var binding: FragmentTestResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



//        val correctCount = arguments?.getInt("correct_count") ?: 0
        val correctCount = arguments?.getInt("correctCount")
        val levelId = arguments?.getInt("level")

//        previousViewModel.correctCount.observe(viewLifecycleOwner) { correctCount ->
//            binding.textView5.text = "Ваш результат $correctCount"
//        }
        binding.textView5.text = "Ваш результат $correctCount/5"

        if (levelId != null) {
            if (correctCount != null) {
                viewModel.insertData(levelId,correctCount)
            }
        }


        binding.button1.setOnClickListener {
            findNavController().navigate(R.id.test_ex)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTestResultBinding.inflate(inflater,container,false)
        return binding.root
    }
}