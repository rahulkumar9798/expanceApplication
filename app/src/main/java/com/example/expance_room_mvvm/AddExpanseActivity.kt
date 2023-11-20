package com.example.expance_room_mvvm

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.expance_room_mvvm.adapter.CategoryRecyclerAdpater
import com.example.expance_room_mvvm.databinding.ActivityAddExpanseBinding
import com.example.expance_room_mvvm.databinding.CatorgyDialogBinding
import java.util.Calendar

class AddExpanseActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddExpanseBinding
    lateinit var expanceViewModal: ExpenseViewModal
    lateinit var dialogAdd : Dialog
    var balance = 0
    /*var arrData = ArrayList<CategoryModal>()*/
    var seletedType = 0
    var selectedDate = ""
    var selectedCatId = ""

    companion object{
        const val BALANCE_KEY = "balance"
        var arrData = ArrayList<CategoryModal>().apply {
            add(CategoryModal(1,  R.drawable.dogicon, "Dog"))
            add(CategoryModal(2, R.drawable.airplane, "Traval"))
            add(CategoryModal(3,R.drawable.cookie, "Food"))
            add(CategoryModal(4,R.drawable.shirt, "Cloth"))
            add(CategoryModal(5,R.drawable.grocery, "Dog"))
            add(CategoryModal(6,R.drawable.coffee, "coffee"))

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExpanseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        balance = intent.getStringExtra(BALANCE_KEY)!!.toInt()

        val db = MyRoomDataBase.getIntance(this)

        val repository = ExpanceRepository(db)

        expanceViewModal = ViewModelProvider(this, ExpanceViewModalFactory(repository))[ExpenseViewModal::class.java]

        val dropDownList = listOf( "Debit","Credit") //debit -> 0, cridet -> 1
        val adpter = ArrayAdapter(this, R.layout.dropdown_list, dropDownList)

        binding.dropdownMenu.setAdapter(adpter)
        binding.dropdownMenu.onItemClickListener = object : AdapterView.OnItemClickListener{

            override fun onItemClick(p0: AdapterView<*>?, p1: View?, index: Int, p3: Long) {
                seletedType = index

            }

        }





        /*var arrData = ArrayList<CategoryModal>().apply*/
       /* arrData.apply {
            add(CategoryModal(1,  R.drawable.dogicon, "Dog"))
            add(CategoryModal(2, R.drawable.airplane, "Traval"))
            add(CategoryModal(3,R.drawable.cookie, "Food"))
            add(CategoryModal(4,R.drawable.shirt, "Cloth"))


            add(CategoryModal(5,R.drawable.grocery, "Dog"))
            add(CategoryModal(6,R.drawable.petrol, "Traval"))
            add(CategoryModal(7,R.drawable.coffee, "coffee"))

        }*/

        binding.btnAddCatg.setOnClickListener {

            dialogAdd = Dialog(this)
            val dialogBinding = CatorgyDialogBinding.inflate(layoutInflater)
            dialogAdd.setContentView(dialogBinding.root)


            dialogBinding.recyclerCatorgyDialog.layoutManager = GridLayoutManager(this, 4)
            dialogBinding.recyclerCatorgyDialog.adapter = CategoryRecyclerAdpater(this, arrData)
            dialogAdd.show()
        }
        //date and time picker start

        binding.selectDate.setOnClickListener {
            val calender = Calendar.getInstance()
            val curryear = calender.get(Calendar.YEAR)
            val currMonth = calender.get(Calendar.MONTH)
            val currDate = calender.get(Calendar.DAY_OF_MONTH)
            val currHour = calender.get(Calendar.HOUR_OF_DAY)
            val currMin = calender.get(Calendar.MINUTE)


            //before lemda convert
            /*DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {

                }

            },curryear, currMonth, currDate)*/
            //after lemda convert
            DatePickerDialog(this,
                { p0, mYear, mMonth, mDate ->

                     //"08/10/20233" => "10/23" date example
                    var monthValue = "${mMonth+1}"
                    if(monthValue.length<2){
                        monthValue = "0$monthValue"
                    }
                    var dateValue = mDate.toString()
                    if(dateValue.length<2){
                        dateValue = "0${dateValue}"
                    }
                   selectedDate = "$dateValue/$monthValue/$mYear"
                   /*Log.d("SelectedDate", selectedDate) */
                    binding.selectDate.text = selectedDate

                    //start Time picker Dailog

                    //ye time picker ko comment ka kaeran hai ki hame current time chahiye
                    /*TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener{
                        override fun onTimeSet(p0: TimePicker?, mHour: Int, mMin: Int) {
                            *//*var selectedTime = "$mHour:$mMin"*//*

                            //ye comment time picker ko hatane ke liye kiya gaya hai
                           *//*selectedDate = "$selectedDate $mHour:$mMin"
                            Log.d("SelectedDateTime", selectedDate)
                            binding.selectDate.text = selectedDate*//*


                        }

                    }, currHour, currMin, false).show()*/

                    //Close Time picker Dailog
                },curryear, currMonth, currDate).show()
        }

        //date and time picker Close





        binding.btnSubmit.setOnClickListener {

            //ye current time ke liye kiya gaya hai Start

                val calender = Calendar.getInstance()
                val currHour = calender.get(Calendar.HOUR_OF_DAY)
                val currMin = calender.get(Calendar.MINUTE)
                selectedDate = "$selectedDate $currHour : $currMin"



            //ye current time ke liye kiya gaya hai close

            if (seletedType==0){  //debit
                balance -= binding.edtAmount.text.toString().toInt()
            }else{ //credit
                balance += binding.edtAmount.text.toString().toInt()
            }


            expanceViewModal.addExpace(
                ExpanceTable(
                    id = 0,
                    title = binding.edtTitle.text.toString(),
                    desc = binding.edtDesc.text.toString(),
                    amount = binding.edtAmount.text.toString(),
                    balance = balance.toString(),
                    date = selectedDate,
                    catId = selectedCatId,
                    type = seletedType.toString()

                )
            )
            finish()

        }


    }

    //set category dailog in set image and name
    fun onCategoryDailogSelected(index: Int){
            dialogAdd.dismiss()
        val selectedCat = arrData[index]

        binding.ctgImg.setImageResource((selectedCat.imgPath))
        binding.ctgText.text = selectedCat.catgName
        selectedCatId = selectedCat.catId.toString()


    }
}