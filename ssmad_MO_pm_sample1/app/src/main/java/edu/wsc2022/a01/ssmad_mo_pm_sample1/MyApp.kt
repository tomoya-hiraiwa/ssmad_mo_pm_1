package edu.wsc2022.a01.ssmad_mo_pm_sample1

import android.app.Application

object Exam{
    var examList = mutableListOf<ExamData>()
}
data class ExamData(val name: String,var score: Int,var diff: Int,val photo: String,var color: Int)