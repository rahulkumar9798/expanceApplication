package com.example.expance_room_mvvm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expance_room_mvvm.databinding.ActivityMainBinding
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var expanceViewModal: ExpenseViewModal
    var balance = "0"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var db = MyRoomDataBase.getIntance(this)
        var repository = ExpanceRepository(db)
        expanceViewModal = ViewModelProvider(this, ExpanceViewModalFactory(repository))[ExpenseViewModal::class.java]

        expanceViewModal.getAllExpances().observe(this){data->

           /* for(eachExpense in data){
                Log.d("Expense Data", "expense name: ${eachExpense.title}")
            }*/
            if (data.isNotEmpty()){
                binding.textBlance.visibility = View.VISIBLE
                binding.recyclerMainView.visibility = View.VISIBLE
                binding.mBarchat.visibility = View.VISIBLE
                binding.textNoExpance.visibility = View.GONE

                balance= data.last().balance
                binding.textBlance.text = "\u20B9 $balance"

                val dateWiseData = filterExpenseDateWise(data as ArrayList<ExpanceTable>)

                filterExpenseMonthWise(data as ArrayList<ExpanceTable>)

                filterExpenseYearWise(data as ArrayList<ExpanceTable>)

                updategraph(dateWiseData)

                binding.recyclerMainView.layoutManager = LinearLayoutManager(this)
                binding.recyclerMainView.adapter = RecyclerMainAdpter(this, dateWiseData)
            } else {
                binding.textBlance.visibility = View.GONE
                binding.recyclerMainView.visibility = View.GONE
                binding.mBarchat.visibility = View.GONE
                binding.textNoExpance.visibility = View.VISIBLE

            }
        }

       /* val  data = ArrayList<MainModel>().apply{
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
*/
        binding.floatingActionButton.setOnClickListener {
            startActivity(Intent(this, AddExpanseActivity::class.java).putExtra(AddExpanseActivity.BALANCE_KEY, balance ))
        }

    }
    /*fun filterExpenseDateWise(expenseData: ArrayList<ExpanceTable>) : ArrayList<MainModel>{
        val arrMainModel = ArrayList<MainModel>()
        val arrUniqueDates = ArrayList<String>()

        for(eachExpense in expenseData){
            val eachExpenseDate = eachExpense.date.split("")[0]
            *//*Log.d("eachDate", eachExpenseDate )*//*
            if(!arrUniqueDates.contains(eachExpenseDate)){
                arrUniqueDates.add(eachExpenseDate)
            }
        }

        Log.d("Unique Dates", arrUniqueDates.toString())
        for (eachDate in  arrUniqueDates){

            val arrEachDateExpane = ArrayList<ExpanceTable>()
            var eachDateAmt = 0
            for (eachExpanse in expenseData){
                val eachExpenseDate = eachExpanse.date.split("")[0]

                if(eachDate==eachExpenseDate){
                    arrEachDateExpane.add(eachExpanse)
                    if (eachExpanse.type=="0"){
                        eachDateAmt += eachExpanse.amount.toInt()
                    }else{
                        eachDateAmt -= eachExpanse.amount.toInt()
                    }
                }
            }
//            Log.d("Data","${eachDate}, ${eachDateAmt}")
            arrMainModel.add(MainModel(date, eachDateAmt.toString(), arrEachDateExpane ))
        }
        Log.d("ArrMainModal", arrMainModel.toString())

        return arrMainModel
    }*/

    fun filterExpenseDateWise(expenseData: ArrayList<ExpanceTable>): ArrayList<MainModel> {

        val arrMainModel = ArrayList<MainModel>()

        val arrUniqueDates = ArrayList<String>()

        for (eachExpense in expenseData) {

            val eachExpenseDate = eachExpense.date.split(" ")[0]
            if (!arrUniqueDates.contains(eachExpenseDate)) {
                arrUniqueDates.add(eachExpenseDate)
            }
        }

        Log.d("Unique Dates", arrUniqueDates.toString())

        for (eachDate in arrUniqueDates) {

            val arrEachDateExpenses = ArrayList<ExpanceTable>()
            var eachDateAmt = 0

            for (eachExpense in expenseData) {
                val eachExpenseDate = eachExpense.date.split(" ")[0]

                if (eachDate == eachExpenseDate) {
                    arrEachDateExpenses.add(eachExpense)
                    if (eachExpense.type == "0") {
                        eachDateAmt += eachExpense.amount.toInt()
                    } else {
                        eachDateAmt -= eachExpense.amount.toInt()
                    }
                }
            }
            Log.d("Data: ", "${eachDate}, $eachDateAmt")

            //for Today
            val calendar = Calendar.getInstance()
            var todayYear = calendar.get(Calendar.YEAR).toString()
            var todayMonth = calendar.get(Calendar.MONTH).toString()
            var todayDay = calendar.get(Calendar.DAY_OF_MONTH).toString()


            if (todayDay.length<2){
                todayDay = "0${todayDay}"
            }

            if(todayMonth.length<2){
                todayMonth = "0${todayMonth}"
            }
            val todayDate = "$todayDay/${todayMonth.toInt()+1}/$todayYear"

            //for Yesterday
            calendar.add(Calendar.DAY_OF_MONTH, -1)

            var yesterdayYear = calendar.get(Calendar.YEAR).toString()
            var yesterdayMonth = calendar.get(Calendar.MONTH).toString()
            var yesterdayDay = calendar.get(Calendar.DAY_OF_MONTH).toString()


            if (yesterdayDay.length<2){
                yesterdayDay = "0${yesterdayDay}"
            }

            if(yesterdayMonth.length<2){
                yesterdayMonth = "0${yesterdayMonth}"
            }
            val yesterdayDate = "$yesterdayDay/${yesterdayMonth.toInt()+1}/$yesterdayYear"


            var date  = eachDate

            if(date == todayDate){
                date = "Today"
            }

            if(date == yesterdayDate){
                date = "Yesterday"
            }



            arrMainModel.add(MainModel(date, eachDateAmt.toString(), arrEachDateExpenses))

        }


        Log.d("ArrMainModel", arrMainModel.toString())

        return arrMainModel

    }

    fun filterExpenseMonthWise(expenseData: ArrayList<ExpanceTable>): ArrayList<MainModel> {

        val arrMainModel = ArrayList<MainModel>()

        val arrUniqueMonths = ArrayList<String>()

        for (eachExpense in expenseData) {
            // date example date adding in data base 09/10/2023 18:16
            val eachExpenseMonths = eachExpense.date.substring(3, 10)

            Log.d("eachExpanseMonth", eachExpenseMonths)

            if (!arrUniqueMonths.contains(eachExpenseMonths)) {
                arrUniqueMonths.add(eachExpenseMonths)
            }
        }

        Log.d("Unique Months", arrUniqueMonths.toString())

        for (eachMonth in arrUniqueMonths) {

            val arrEachMonthExpenses = ArrayList<ExpanceTable>()
            var eachDateAmt = 0

            for (eachExpense in expenseData) {
                val eachExpenseMonths = eachExpense.date.substring(3, 10)

                if (eachMonth == eachExpenseMonths) {
                    arrEachMonthExpenses.add(eachExpense)
                    if (eachExpense.type == "0") {
                        eachDateAmt += eachExpense.amount.toInt()
                    } else {
                        eachDateAmt -= eachExpense.amount.toInt()
                    }
                }
            }
            Log.d("Data: ", "${eachMonth}, $eachDateAmt")



            arrMainModel.add(MainModel(eachMonth, eachDateAmt.toString(), arrEachMonthExpenses))

        }


        Log.d("ArrMainModel", arrMainModel.toString())

        return arrMainModel

    }

    fun filterExpenseYearWise(expenseData: ArrayList<ExpanceTable>): ArrayList<MainModel> {

        val arrMainModel = ArrayList<MainModel>()

        val arrUniqueYear = ArrayList<String>()

        for (eachExpense in expenseData) {
            // date example date adding in data base 09/10/2023 18:16
            val eachExpenseYear = eachExpense.date.substring(6, 10)

            Log.d("eachExpanseYear", eachExpenseYear)

            /*if (!eachExpenseYear.contains(eachExpenseYear)){
                eachExpenseYear.
            }
*/

            /*if (!eachExpenseYear.contains(eachExpenseYear)) {
                eachExpenseYear.(eachExpenseYear)
            }*/
        }

       Log.d("Unique Years", arrUniqueYear.toString())

        for (eachYear in arrUniqueYear) {

            val arrEachYearExpenses = ArrayList<ExpanceTable>()
            var eachDateAmt = 0

            for (eachExpense in expenseData) {
                val eachExpenseYear = eachExpense.date.substring(3, 10)

                if (eachYear == eachExpenseYear) {
                    arrEachYearExpenses.add(eachExpense)
                    if (eachExpense.type == "0") {
                        eachDateAmt += eachExpense.amount.toInt()
                    } else {
                        eachDateAmt -= eachExpense.amount.toInt()
                    }
                }
            }
            Log.d("Data: ", "${eachYear}, $eachDateAmt")



            arrMainModel.add(MainModel(eachYear, eachDateAmt.toString(), arrEachYearExpenses))

        }


        Log.d("ArrMainModel", arrMainModel.toString())

        return arrMainModel

    }










    fun updategraph(arrDayData : ArrayList<MainModel>){
        val arrBarEntry = arrayListOf<BarEntry>()
        val arrDates = arrayListOf<String>()

        for(i in 0..arrDayData.size-1){
            arrDates.add(arrDayData[i].date)
            val barEntry = BarEntry(i.toFloat(), arrDayData[i].amt.toFloat(),"Nov")
            arrBarEntry.add(barEntry)
        }

        val barDataSet = BarDataSet(arrBarEntry, "Month")

        //var barData = BarData(barDataSet)

        binding.mBarchat.setFitBars(true)
        binding.mBarchat.xAxis.valueFormatter = MyXAxisFormatter(arrDates)
        binding.mBarchat.data = BarData(barDataSet)
        //for auto update graph
        binding.mBarchat.invalidate()
    }

}

class MyXAxisFormatter(val data : ArrayList<String>): ValueFormatter(){
    //val months = arrayOf("JULY","AUG", "SEP", "OCT", "NOV")
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return data.getOrNull(value.toInt()) ?: value.toString()
    }
}