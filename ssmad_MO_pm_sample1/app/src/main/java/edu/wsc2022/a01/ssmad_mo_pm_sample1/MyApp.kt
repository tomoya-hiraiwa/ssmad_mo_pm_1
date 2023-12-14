package edu.wsc2022.a01.ssmad_mo_pm_sample1

import android.app.Application

object Exam{
    var examList = mutableListOf<ExamData>()
}
object Practice{
    var questionList = mutableListOf<QuestionData>()
    var correctList = mutableListOf<Boolean>()
    var unCorrectList = mutableListOf<UnCorrectData>()
}
data class ExamData(val name: String,var score: Int,var diff: Int,val photo: String,var color: Int)
data class QuestionData(val question: String,val choices: List<String>)

data class UnCorrectData(val question: String,val choice: String,val correct: String)