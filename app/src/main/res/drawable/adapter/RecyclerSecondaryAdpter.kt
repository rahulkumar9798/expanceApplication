package com.example.expense_ui_recycle_in_recycle.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expense_ui_recycle_in_recycle.databinding.SecondryRowBinding
import com.example.expense_ui_recycle_in_recycle.models.SecondaryModal

class RecyclerSecondaryAdpter(val context: Context, val arrSecondryData : ArrayList<SecondaryModal>) :RecyclerView.Adapter<RecyclerSecondaryAdpter.ViewHolder>() {
    class ViewHolder (val binding: SecondryRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(SecondryRowBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return arrSecondryData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding){
            imgCat.setImageResource(arrSecondryData[position].imgPath)
            txtName.text = arrSecondryData[position].title
            txtDese.text =  arrSecondryData[position].des
            txtAmt.text = arrSecondryData[position].amt
            txtTotalAmt.text = arrSecondryData[position].totalAmt
        }
    }


}