package com.github.wnuk.myhero.infrastracture.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.wnuk.myhero.model.character.CharacterDao
import com.github.wnuk.myhero.model.character.CharacterEntity

@Database(entities = arrayOf(CharacterEntity::class), version = 1)
public abstract class CharacterDb : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    companion object {
        @Volatile
        private var INSTANCE: CharacterDb? = null

        fun getDatabase(context: Context): CharacterDb {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CharacterDb::class.java,
                    "character_database5"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}