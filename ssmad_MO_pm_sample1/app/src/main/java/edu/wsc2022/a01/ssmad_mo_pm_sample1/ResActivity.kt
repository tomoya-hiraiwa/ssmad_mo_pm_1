package edu.wsc2022.a01.ssmad_mo_pm_sample1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import edu.wsc2022.a01.ssmad_mo_pm_sample1.databinding.ActivityResBinding

class ResActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val imageList = binding.resImageList
        val uncorrectList = binding.uncorrectList
        imageList.layoutManager = LinearLayoutManager(this,).apply { orientation = LinearLayoutManager.HORIZONTAL }
        uncorrectList.layoutManager = LinearLayoutManager(this)
        val imageAdapter = ImageListAdapter(Practice.correctList,this)
        imageList.adapter = imageAdapter

        val unCorrectAdapter = UnCorrectListAdapter(Practice.unCorrectList)
        uncorrectList.adapter = unCorrectAdapter

        binding.resOkbt.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }


    }
}