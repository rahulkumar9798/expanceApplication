package com.example.expance_room_mvvm

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [ExpanceTable::class, CategoryTable::class], exportSchema = false, version = 1)
abstract class MyRoomDataBase(): RoomDatabase(){

       abstract fun ExpanceDoa() : ExpanceDoa
       abstract fun CategaryDao() : CategaryDao


       companion object{

           const val DB_NAME = "expanceDB"
           var DB_INSTANCE : MyRoomDataBase?=null


            fun getIntance(context: Context) : MyRoomDataBase{
                if (DB_INSTANCE == null){
                    DB_INSTANCE = Room.databaseBuilder(context,
                        MyRoomDataBase::class.java,
                        DB_NAME).allowMainThreadQueries().build()
                }
                return DB_INSTANCE!!
            }



       }

}