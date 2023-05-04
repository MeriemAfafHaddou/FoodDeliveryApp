package com.example.fooddeliveryapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CartItem::class],version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getCartDao():CartItemsDao
    companion object {
        private var INSTANCE: AppDatabase? = null
        fun buildDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                INSTANCE =
                    Room.databaseBuilder(context,AppDatabase::class.java,
                        "cart_db").allowMainThreadQueries().build() }

            return INSTANCE
        }
    }
}