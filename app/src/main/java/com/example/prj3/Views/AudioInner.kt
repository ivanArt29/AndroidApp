package com.example.prj3.Views

import android.R
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.prj3.AudioDB.Audio
import com.example.prj3.AudioDB.AudioViewModelFactory
import com.example.prj3.AudioDB.RepositoryAudio
import com.example.prj3.AudioDB.RepositoryAudioImpl
import com.example.prj3.SentencesDB.RepositorySentencesImpl
import com.example.prj3.SentencesDB.SentencesChoice
import com.example.prj3.SentencesDB.SentencesStagesListViewModelFactory
import com.example.prj3.databinding.FragmentAudioInnerBinding
import com.example.prj3.viewModels.AudioInnerViewModel
import com.example.prj3.viewModels.SentencesInnerViewModel
import kotlinx.coroutines.Dispatchers
import java.io.IOException


class AudioInner : Fragment() {

    companion object {
        fun newInstance() = AudioInner()
    }

    var stageId = 1
    var levelId: Int? = null

    var correctCount = 0

    private val viewModel: AudioInnerViewModel by viewModels(){
        AudioViewModelFactory(RepositoryAudioImpl(requireContext(), Dispatchers.IO) )
    }

    private lateinit var binding: FragmentAudioInnerBinding

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAudioInnerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        if (stageId == 1 ){
            correctCount = 0
            if (levelId != null) {
                viewModel.getAudioPathForStage(levelId!!, stageId)
                viewModel.getCorrectAudio(levelId!!,stageId)
            }

        }

        binding.buttonListen.setOnClickListener {
            if (levelId != null) {
                viewModel.getAudioPathForStage(levelId!!, stageId)
                viewModel.audioForStagePath.observe(viewLifecycleOwner) { audioPathList ->
                    if (audioPathList != null) {

                        val audioPath = "${audioPathList.first().path}.mp3"
                        playAudio(audioPath)
                    }
                }
                viewModel.getAudioPathForStage(levelId!!, stageId)
            }
        }


        viewModel.correctCount.observe(viewLifecycleOwner){counter ->
            correctCount = counter
        }

        binding.buttonAnswer.setOnClickListener {
            val sent = binding.editTextListen.text.toString()
            levelId?.let { it1 -> handleButtonClick(sent, it1,stageId) }
        }

    }

    private fun handleButtonClick(selectedSentence: String, level: Int, stage: Int) {

        levelId?.let { viewModel.getCorrectAudio(it,stageId) }
        if (stageId == 5)
        {
            levelId?.let { viewModel.getCorrectAudio(it,stageId) }
            val correctSent = viewModel.getCorrectValue()
            val currentSent = binding.editTextListen.text
            if (selectedSentence == correctSent)
            {
                correctCount++
            }
            navigateToResult(correctCount)
        }
        else{
            levelId?.let { viewModel.getCorrectAudio(it,stageId) }
            Thread.sleep(50)

                val correctWord = viewModel.getCorrectValue()
                if (selectedSentence == correctWord) {
                    correctCount++
                }

        }
        stageId++
        binding.editTextListen.setText("")
        if (stageId <= 5){
            levelId?.let { viewModel.getCorrectAudio(it,stageId) }
        }
        Thread.sleep(50)

    }

    private fun navigateToResult(correctCount: Int) {
        val bundle = Bundle().apply {
            putInt("correctCount", correctCount)
            levelId?.let { putInt("level", it) }
        }
        findNavController().navigate(com.example.prj3.R.id.audioResult, bundle)
    }


    private fun playAudio(audioFilePath: String) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer()
        }

        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.stop()
            mediaPlayer?.reset()
        }

        try {
            context?.assets?.let { assets ->
                val assetFileDescriptor = assets.openFd(audioFilePath)
                try {
                    mediaPlayer?.setDataSource(assetFileDescriptor.fileDescriptor, assetFileDescriptor.startOffset, assetFileDescriptor.length)
                    mediaPlayer?.prepare()
                } catch (e: IllegalStateException) {
                    mediaPlayer?.reset()
                    mediaPlayer?.setDataSource(assetFileDescriptor.fileDescriptor, assetFileDescriptor.startOffset, assetFileDescriptor.length)
                    mediaPlayer?.prepare()
                } catch (e: IOException) {
                } finally {
                    assetFileDescriptor.close()
                }
            }
        } catch (e: IOException) {
        }

        try {
            mediaPlayer?.start()
        } catch (e: IllegalStateException) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        levelId = arguments?.getInt("level")
    }


}