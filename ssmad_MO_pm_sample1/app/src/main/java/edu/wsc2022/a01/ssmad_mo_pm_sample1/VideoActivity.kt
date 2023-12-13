package edu.wsc2022.a01.ssmad_mo_pm_sample1

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.wsc2022.a01.ssmad_mo_pm_sample1.databinding.ActivityVideoBinding

class VideoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoBinding
    private var onPlay = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val video = binding.vVideo
        val url = "android.resource://" + packageName + "/" + R.raw.dummy
        video.setVideoURI(Uri.parse(url))
        binding.vStartbt.setOnClickListener {
            if(!onPlay){
                video.start()
                binding.vStartbt.text = "Pause"
                onPlay = true
            }
            else {
                video.pause()
                binding.vStartbt.text = "Start"
                onPlay = false
            }
        }
        binding.vReplaybt.setOnClickListener {
            video.stopPlayback()
            video.setVideoURI(Uri.parse(url))
            video.start()
            onPlay = true
            binding.vStartbt.text = "Pause"
        }
    }
}