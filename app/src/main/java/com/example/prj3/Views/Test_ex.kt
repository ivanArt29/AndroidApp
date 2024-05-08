package com.example.prj3.Views

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.prj3.R
import com.example.prj3.TestBd.LevelsListViewModelFactory
import com.example.prj3.TestBd.RepositoryImpl
import com.example.prj3.TestBd.TestDB
import com.example.prj3.databinding.FragmentTestExBinding
import com.example.prj3.viewModels.TestExViewModel
import kotlinx.coroutines.Dispatchers

class Test_ex : Fragment() {

    private val viewModel: TestExViewModel by viewModels{
        LevelsListViewModelFactory(RepositoryImpl(requireContext(), Dispatchers.IO))
    }

    private lateinit var binding: FragmentTestExBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        return inflater.inflate(R.layout.fragment_test_ex, container, false)
        binding = FragmentTestExBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btn1.setOnClickListener{
            viewModel.insertLevel(TestDB.PREPOPULATE_DATA[0])
            viewModel.insertLevel(TestDB.PREPOPULATE_DATA[1])
            viewModel.insertLevel(TestDB.PREPOPULATE_DATA[2])

            viewModel.insertStage(TestDB.PREPOPULATE_STAGES[0])
            viewModel.insertStage(TestDB.PREPOPULATE_STAGES[1])
            viewModel.insertStage(TestDB.PREPOPULATE_STAGES[2])
            viewModel.insertStage(TestDB.PREPOPULATE_STAGES[3])

            viewModel.insertWord(TestDB.PREPOPULATE_WORDS[0])
            viewModel.insertWord(TestDB.PREPOPULATE_WORDS[1])
            viewModel.insertWord(TestDB.PREPOPULATE_WORDS[2])
            viewModel.insertWord(TestDB.PREPOPULATE_WORDS[3])

            viewModel.insertRuWord(TestDB.PREPOPULATE_RUWORDS[0])
            viewModel.insertRuWord(TestDB.PREPOPULATE_RUWORDS[1])
            viewModel.insertRuWord(TestDB.PREPOPULATE_RUWORDS[2])
            viewModel.insertRuWord(TestDB.PREPOPULATE_RUWORDS[3])

            findNavController().navigate(R.id.test_inner)
        }

    }


}