package com.example.prj3.SentencesDB

interface RepositorySentences {
    fun getSentenceForStage(levelId: Int, stageId: Int):List<Sentences>

    fun getSentencesChoicesForStage(levelId: Int, stageId: Int):List<SentencesChoice>

    fun getCorrectSentenceForStageByLevel(levelId: Int, stageId: Int): SentencesChoice
}