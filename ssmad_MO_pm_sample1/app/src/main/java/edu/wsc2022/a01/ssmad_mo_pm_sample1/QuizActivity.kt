package edu.wsc2022.a01.ssmad_mo_pm_sample1

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.google.android.material.circularreveal.cardview.CircularRevealCardView
import edu.wsc2022.a01.ssmad_mo_pm_sample1.databinding.ActivityQuizBinding
import org.json.JSONArray
import java.io.File

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    private var questionNum = 1
    private lateinit var circle: CircleView
    private lateinit var rgp: RadioGroup
    private lateinit var questionData: QuestionData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fileName = "questions.json"
        val input = assets.open(fileName)
        val file = File(filesDir,fileName)
        circle = binding.qTimer
        rgp = binding.qRgp
        if (!file.exists()){
            file.outputStream().use { input.copyTo(it) }
        }
        getData(file)
        display()
        binding.qNextbt.setOnClickListener {

          if (rgp.checkedRadioButtonId != -1){
              timer.cancel()
              circle.angle = 0f
              circle.invalidate()
              getAnswer(false)
              nextDisplay()
          }
            else{
              Toast.makeText(this, "Please select the answer", Toast.LENGTH_SHORT).show()
            }
        }
        binding.qBackbt.setOnClickListener {
            finish()
        }
    }
    private fun display(){
        questionData = Practice.questionList[questionNum -1]
        binding.qNumText.text = "${questionNum}/5"
        binding.qQtext.text = questionData.question
        val choiceData = questionData.choices.shuffled()
        binding.qA1.text = choiceData[0]
        binding.qA2.text = choiceData[1]
        binding.qA3.text = choiceData[2]
        binding.qA4.text = choiceData[3]
        timer.start()
    }
    private val timer = object : CountDownTimer(10000,1000){
            override fun onTick(millisUntilFinished: Long) {
                circle.angle  = circle.angle +36f
                circle.invalidate()
            }

            override fun onFinish() {
                circle.angle = 0f
                circle.invalidate()
                val onTime = rgp.checkedRadioButtonId == -1
                getAnswer(onTime)
                nextDisplay()
            }
        }
    private fun nextDisplay(){
       if (questionNum <= 4){
           questionNum +=1
           display()
       }
    }
    private fun getAnswer(onTime: Boolean){
        if (onTime){
            Practice.correctList.add(false)
            val question = questionData.question
            val correct = questionData.choices.firstOrNull { it.contains("*") }
            val choice = "Count Down Over"
            Practice.unCorrectList.add(UnCorrectData(question,choice,correct!!))
            Log.d("uncorrect", "${Practice.unCorrectList}")
            Log.d("correct", "${Practice.correctList}")
        }
        else{
            val choiceButton = findViewById<RadioButton>(rgp.checkedRadioButtonId)
            val choice = choiceButton.text.toString()
            choiceButton.isChecked = false
            if (choice.contains("*")){
                Practice.correctList.add(true)
                Log.d("correct", "${Practice.correctList}")
            }
            else{
                val question = questionData.question
                val correct = questionData.choices.firstOrNull { it.contains("*") }
                Practice.unCorrectList.add(UnCorrectData(question,choice,correct!!))
                Practice.correctList.add(false)
                Log.d("uncorrect", "${Practice.unCorrectList}")
            }
        }
        if (questionNum == 5){
            startActivity(Intent(this,ResActivity::class.java))
        }
    }
    private fun getData(file: File){
        val jsonStr = file.bufferedReader().use{it.readText()}
        val jsonArray = JSONArray(jsonStr)
        for (i in 0 until jsonArray.length()){
            val jsonObject = jsonArray.getJSONObject(i)
            val question = jsonObject.getString("question")
            val ansArray = jsonObject.getJSONArray("choices")
            var ansList = mutableListOf<String>()
            for (i in 0 until ansArray.length()){
                val ans = ansArray.getString(i)
                ansList.add(ans)
            }
            Practice.questionList.add(QuestionData(question,ansList))
            Practice.questionList.shuffled()
        }
    }
}
class CircleView(context: Context,attributeSet: AttributeSet):View(context,attributeSet){
    var angle = 0f
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val backRectF = RectF(2f,2f,198f,198f)
        val rectF  = RectF(0f,0f,200f,200f)
        val backPaint = Paint().apply {
            color  = Color.RED
        }
        val paint = Paint().apply {
            color = Color.WHITE
        }
        canvas.drawArc(backRectF,0f,360f,true,backPaint)
        canvas.drawArc(rectF,-90f,angle,true,paint)
    }
}