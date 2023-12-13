package edu.wsc2022.a01.ssmad_mo_pm_sample1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.wsc2022.a01.ssmad_mo_pm_sample1.databinding.ActivityMainBinding
import org.json.JSONArray
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.mainVideobt.setOnClickListener {
            startActivity(Intent(this,VideoActivity::class.java))
        }
        binding.mainMexbt.setOnClickListener {
            startActivity(Intent( this,MyExamActivity::class.java))
        }

    }



}
