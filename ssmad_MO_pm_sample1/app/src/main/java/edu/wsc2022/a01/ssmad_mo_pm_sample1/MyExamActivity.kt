package edu.wsc2022.a01.ssmad_mo_pm_sample1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import edu.wsc2022.a01.ssmad_mo_pm_sample1.databinding.ActivityMyExamBinding
import org.json.JSONArray
import java.io.File

class MyExamActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyExamBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyExamBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fileName = "exams.json"
        val input = assets.open(fileName)
        val file = File(filesDir,fileName)
        if(!file.exists()){
            file.outputStream().use { input.copyTo(it) }
        }
        getData(file)
        binding.mexBackbt.setOnClickListener {
            finish()
        }

    }

    override fun onResume() {
        super.onResume()
        val list = binding.mexList
        list.layoutManager = LinearLayoutManager(this)
        val adapter = MyExamAdapter(Exam.examList,this)
        list.adapter = adapter
        adapter.setOnMyExamClicker(object : MyExamAdapter.MyExamClicker {
            override fun onClick(position: Int) {
             startActivity(Intent(this@MyExamActivity,ExamEditActivity::class.java).apply {
                 putExtra("pos",position)
             })
            }
        })
    }
    private fun getData(file: File){
        Exam.examList.clear()
        val jsonStr = file.bufferedReader().use { it.readText() }
        val jsonArray = JSONArray(jsonStr)
        for (i in 0 until jsonArray.length()){
            val jsonObject = jsonArray.getJSONObject(i)
            val name = jsonObject.getString("name")
            val score = jsonObject.getInt("score")
            val dif = jsonObject.getInt("difficulty")
            val photo = jsonObject.getString("photo")
            Exam.examList.add(ExamData(name,score,dif,photo,-1))
        }
    }
}