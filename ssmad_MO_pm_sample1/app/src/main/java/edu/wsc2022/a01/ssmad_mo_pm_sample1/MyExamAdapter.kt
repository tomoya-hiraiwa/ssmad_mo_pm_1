package edu.wsc2022.a01.ssmad_mo_pm_sample1

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import edu.wsc2022.a01.ssmad_mo_pm_sample1.databinding.ExamListItemBinding
import java.net.PortUnreachableException

class MyExamAdapter(private val dataList: MutableList<ExamData>,private val context: Context): RecyclerView.Adapter<MyExamAdapter.MyExamViewHolder>() {
    private var myExamClicker: MyExamClicker? = null
    interface MyExamClicker{
        fun onClick(position: Int)
    }
    fun setOnMyExamClicker(lis: MyExamClicker){
        myExamClicker = lis
    }
    inner class MyExamViewHolder(private val binding: ExamListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bindData(position: Int){
            val data = dataList[position]
            val input = context.assets.open(data.photo)
            val bitmap = BitmapFactory.decodeStream(input)
            val scBar = binding.elScBar
            val difBar = binding.elDifBar
            scBar.max = 100
            difBar.max = 100
            binding.elTitle.text = data.name
            binding.elImage.setImageBitmap(bitmap)
            binding.elDifText.text = data.diff.toString()
            binding.elSctext.text = data.score.toString()
            if (data.color != -1) {
                val color = when (data.color) {
                    0 -> context.getColor(R.color.blue)
                    1 -> context.getColor(R.color.green)
                    2 -> context.getColor(R.color.yellow)
                    3 -> context.getColor(R.color.purple)
                    else -> context.getColor(R.color.blue)
                }
                Log.d("colorChange", "move changeColor")
                scBar.progressDrawable.colorFilter = PorterDuffColorFilter(color,PorterDuff.Mode.SRC_IN)
                difBar.progressDrawable.colorFilter = PorterDuffColorFilter(color,PorterDuff.Mode.SRC_IN)
            }
            scBar.progress = data.score
            difBar.progress = data.diff
            binding.root.setOnClickListener {
                myExamClicker?.onClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyExamViewHolder {
        return MyExamViewHolder(ExamListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyExamViewHolder, position: Int) {
        holder.bindData(position)
    }
}