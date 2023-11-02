package com.example.expance_room_mvvm

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.example.expance_room_mvvm.adapter.CategoryRecyclerAdpater
import com.example.expance_room_mvvm.databinding.ActivityAddExpanseBinding
import com.example.expance_room_mvvm.databinding.CatorgyDialogBinding

class AddExpanseActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddExpanseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExpanseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dropDownList = listOf("Credit", "Debit")
        val adpter = ArrayAdapter(this, R.layout.dropdown_list, dropDownList)

        binding.dropdownMenu.setAdapter(adpter)



        var arrData = ArrayList<CategoryModal>().apply {
            add(CategoryModal(R.drawable.dogicon, "Dog"))
            add(CategoryModal(R.drawable.airplane, "Traval"))
            add(CategoryModal(R.drawable.cookie, "Food"))
            add(CategoryModal(R.drawable.shirt, "Cloth"))


            add(CategoryModal(R.drawable.dogicon, "Dog"))
            add(CategoryModal(R.drawable.airplane, "Traval"))
            add(CategoryModal(R.drawable.cookie, "Food"))
            add(CategoryModal(R.drawable.shirt, "Cloth"))

            add(CategoryModal(R.drawable.dogicon, "Dog"))
            add(CategoryModal(R.drawable.airplane, "Traval"))
            add(CategoryModal(R.drawable.cookie, "Food"))
            add(CategoryModal(R.drawable.shirt, "Cloth"))

            add(CategoryModal(R.drawable.dogicon, "Dog"))
            add(CategoryModal(R.drawable.airplane, "Traval"))
            add(CategoryModal(R.drawable.cookie, "Food"))
            add(CategoryModal(R.drawable.shirt, "Cloth"))

        }

        binding.btnAddCatg.setOnClickListener {

            val dialogAdd = Dialog(this)
            val dialogBinding = CatorgyDialogBinding.inflate(layoutInflater)
            dialogAdd.setContentView(dialogBinding.root)




            dialogAdd.show()
        }


    }
}