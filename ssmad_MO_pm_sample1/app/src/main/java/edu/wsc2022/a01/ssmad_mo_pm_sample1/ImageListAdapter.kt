package edu.wsc2022.a01.ssmad_mo_pm_sample1

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.wsc2022.a01.ssmad_mo_pm_sample1.databinding.ImageListItemBinding

class ImageListAdapter(private val dataList: MutableList<Boolean>,private val context: Context):RecyclerView.Adapter<ImageListAdapter.ImageListViewHolder>() {
    inner class ImageListViewHolder(private val binding: ImageListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bindData(data: Boolean){
            val image = if (data) context.resources.getDrawable(R.drawable.star,context.theme) else context.resources.getDrawable(R.drawable.star2,context.theme)
            binding.ilImage.setImageDrawable(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageListViewHolder {
        return ImageListViewHolder(ImageListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ImageListViewHolder, position: Int) {
        val data = dataList[position]
        holder.bindData(data)
    }
}