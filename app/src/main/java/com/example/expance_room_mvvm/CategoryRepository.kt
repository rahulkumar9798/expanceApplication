package com.example.expance_room_mvvm

class CategoryRepository(val db : MyRoomDataBase) {

    fun addCategory(newCategory : CategoryTable){
        db.CategaryDao().addCategory(newCategory)
    }

    fun getAllCategory() : List<ExpanceTable>{
        return db.ExpanceDoa().getAllExpance()
    }


    fun updateCategory(updateCategory : ExpanceTable){

    }

    fun deleteCategory(deleteCategory : CategoryTable){
        db.CategaryDao().deleteCategory(deleteCategory)
    }
}