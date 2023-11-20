package com.example.expance_room_mvvm.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expance_room_mvvm.AddExpanseActivity
import com.example.expance_room_mvvm.CategoryModal
import com.example.expance_room_mvvm.databinding.CategoryIteamRawBinding

class CategoryRecyclerAdpater(val context : Context, val arrdata : ArrayList<CategoryModal>) : RecyclerView.Adapter<CategoryRecyclerAdpater.ViewHolder>(){
    class ViewHolder(val binding: CategoryIteamRawBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CategoryIteamRawBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return arrdata.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currModel = arrdata[position]

        holder.binding.imgCatg.setImageResource(currModel.imgPath)
        holder.binding.textcatgName.text = currModel.catgName

        holder.binding.llRow.setOnClickListener {

            //show after add category image
            (context as AddExpanseActivity).onCategoryDailogSelected(position)
        }
    }

}