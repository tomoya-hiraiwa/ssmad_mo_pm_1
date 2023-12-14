package edu.wsc2022.a01.ssmad_mo_pm_sample1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.wsc2022.a01.ssmad_mo_pm_sample1.databinding.UncorrectListItemBinding

class UnCorrectListAdapter(private val dataList: MutableList<UnCorrectData>):RecyclerView.Adapter<UnCorrectListAdapter.UnCorrectViewHolder>() {
    inner class UnCorrectViewHolder(private val binding: UncorrectListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bindData(data: UnCorrectData){
            binding.ulQuestion.text = data.question
            binding.ulCorrectText.text = data.correct
            binding.ulChoiceText.text = data.choice
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnCorrectViewHolder {
        return UnCorrectViewHolder(UncorrectListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: UnCorrectViewHolder, position: Int) {
        val data = dataList[position]
        holder.bindData(data)
    }
}