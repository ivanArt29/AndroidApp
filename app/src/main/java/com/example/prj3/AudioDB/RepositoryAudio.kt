package com.example.prj3.AudioDB

interface RepositoryAudio {
    fun getAudioForStage(levelId: Int, stageId: Int):List<Audio>

    fun getCorrectAudio(levelId: Int, stageId: Int):CorrectAudio

}