package edu.wsc2022.a01.ssmad_mo_pm_sample1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import edu.wsc2022.a01.ssmad_mo_pm_sample1.databinding.ActivityExamEditBinding

class ExamEditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExamEditBinding
    private var colorNum = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val pos = intent.getIntExtra("pos",0)
        val data = Exam.examList[pos]
        println(data)
        val scSeek = binding.eeScseek
        val difSeek = binding.eeDifseek
        val scText = binding.eeScText
        val difText = binding.eeDifText
        val blue = binding.blue
        val green = binding.green
        val yellow = binding.yellow
        val purple = binding.purple
        val selectFrame = resources.getDrawable(R.drawable.rect_red,theme)
        val frame = resources.getDrawable(R.drawable.rect,theme)
        binding.eeName.text = data.name
        difSeek.apply {
            max = 100
            min = 0
            progress = data.diff
            difText.text = data.diff.toString()
            difText.x = data.diff * 10.toFloat()
        }
        scSeek.apply {
            max = 100
            min = 0
            progress = data.score
            scText.text = data.score.toString()
            scText.x = data.score * 10.toFloat()
        }
        scSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val value = seekBar?.progress
                scText.text = value.toString()
                val cur = seekBar!!.width / seekBar.max
                if (value !=0){
                    scText.x = (cur * value!!).toFloat() + 12
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
        difSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val value = seekBar?.progress
                difText.text = value.toString()
                val cur = seekBar!!.width / seekBar.max
                if (value !=0){
                    difText.x = (cur * value!!).toFloat() + 12
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
        blue.setOnClickListener {
            colorNum = 0
            it.background = selectFrame
            green.background = frame
            yellow.background = frame
            purple.background = frame
        }
        green.setOnClickListener {
            colorNum = 1
            it.background = selectFrame
            blue.background = frame
            yellow.background = frame
            purple.background = frame
        }
        yellow.setOnClickListener {
            colorNum = 2
            it.background = selectFrame
            green.background = frame
            blue.background = frame
            purple.background = frame
        }
        purple.setOnClickListener {
            colorNum = 3
            it.background = selectFrame
            green.background = frame
            yellow.background = frame
            blue.background = frame
        }
        binding.eeOkbt.setOnClickListener {
            Exam.examList[pos].apply {
                score = scSeek.progress
                diff = difSeek.progress
                color = colorNum
            }
            finish()
        }

    }
}