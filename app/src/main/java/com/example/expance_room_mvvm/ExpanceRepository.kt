package com.example.expance_room_mvvm

class ExpanceRepository(val db: MyRoomDataBase) {

    fun addExpance(newExpance : ExpanceTable){
        db.ExpanceDoa().addExpance(newExpance)
    }

    fun getAllExpance() : List<ExpanceTable>{
        return db.ExpanceDoa().getAllExpance()
    }


    fun updateExpance(updateExpance : ExpanceTable){

    }

    fun deleteExpance(deleteExpance : ExpanceTable){
        db.ExpanceDoa().deleteExpance(deleteExpance)
    }
}