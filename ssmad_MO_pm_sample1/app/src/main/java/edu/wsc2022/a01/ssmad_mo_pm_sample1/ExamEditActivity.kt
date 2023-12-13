package edu.wsc2022.a01.ssmad_mo_pm_sample1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.wsc2022.a01.ssmad_mo_pm_sample1.databinding.ActivityExamEditBinding

class ExamEditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExamEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val pos = intent.getIntExtra("pos",0)
        val data = Exam.examList[pos]
        println(data)
    }
}