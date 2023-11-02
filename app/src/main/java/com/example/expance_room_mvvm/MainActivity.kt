package com.example.expance_room_mvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expance_room_mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val  data = ArrayList<MainModel>().apply{
            val secondrydata = ArrayList<SecondaryModal>().apply {
                add(SecondaryModal(R.drawable.dogicon, "Pets", "$3.35", "Treat", "$753"))
                add(SecondaryModal(R.drawable.cookie, "Snacks", "$2.35", "office", "$7383"))
                add(SecondaryModal(R.drawable.airplane, "Travel", "$8.35", "Treat", "$3874853"))
                add(SecondaryModal(R.drawable.shirt, "Salary", "$33.35", "office", "$74848585"))
            }
            add(MainModel("Today", "$100",secondrydata ))
            val secondrydata2 = ArrayList<SecondaryModal>().apply {
                add(SecondaryModal(R.drawable.airplane, "traveler", "$3.35", "Treat", "$753"))
                add(SecondaryModal(R.drawable.cookie, "Snacks", "$2.35", "office", "$7383"))
                add(SecondaryModal(R.drawable.airplane, "Travel", "$8.35", "Treat", "$3874853"))

            }
            add(MainModel("Yesterday", "$75",secondrydata2))

            val secondrydata3 = ArrayList<SecondaryModal>().apply {
                add(SecondaryModal(R.drawable.shirt, "salary", "$3.35", "Treat", "$753"))
                add(SecondaryModal(R.drawable.cookie, "Snacks", "$2.35", "office", "$7383"))

            }
            add(MainModel("23 aug 2023", "100",secondrydata3))
        }
        binding.recyclerMainView.layoutManager = LinearLayoutManager(this)
        binding.recyclerMainView.adapter = RecyclerMainAdpter(this, data)

        binding.floatingActionButton.setOnClickListener {
            startActivity(Intent(this, AddExpanseActivity::class.java))
        }

    }
}