package com.example.expance_room_mvvm

class CategoryRepository(val db : MyRoomDataBase) {

    fun addCategory(newCategory : CategoryTable){
        db.CategaryDao().addCategory(newCategory)
    }

    fun getAllCategory() : List<CategoryTable>{
        return db.CategaryDao().getAllCategory()
    }


    fun updateCategory(updateCategory : CategoryTable){

    }

    fun deleteCategory(deleteCategory : CategoryTable){
        db.CategaryDao().deleteCategory(deleteCategory)
    }
}