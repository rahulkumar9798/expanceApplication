package com.example.expance_room_mvvm

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expance_room_mvvm.databinding.SecondryRowBinding

class RecyclerSecondaryAdpter(val context: Context, val arrSecondryData : ArrayList<ExpanceTable>): RecyclerView.Adapter<RecyclerSecondaryAdpter.ViewHolder>(){
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
            //imgCat.setImageResource(arrSecondryData[position].)

            for (eachCat in AddExpanseActivity.arrData){
                if (eachCat.catId== arrSecondryData[position].catId.toInt()){
                    imgCat.setImageResource(eachCat.imgPath)
                }
            }
            txtName.text = arrSecondryData[position].title
            txtDese.text =  arrSecondryData[position].desc
            txtAmt.text = arrSecondryData[position].amount
            txtTotalAmt.text = "\u20B9 ${arrSecondryData[position].balance}"
            /* txtTotalAmt.text = arrSecondryData[position].amount*/
        }
    }


}