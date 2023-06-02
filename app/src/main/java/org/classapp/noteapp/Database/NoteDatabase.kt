package org.classapp.noteapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.classapp.noteapp.Models.Note
import org.classapp.noteapp.Utilities.DATABASE_NAME


@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false )
abstract class NoteDatabase : RoomDatabase(){
/*It is a process of hiding internal working and showing only necessary details. In simple form abstraction means: Show Functionality.*/

    /*Companion Object สร้างขึ้นบนพื้นฐาน static declaration จากกฎการใช้ singleton ด้วยตัวแปรที่ชื่อ instance เป็นสากล พูดบ้าน ๆ ทั้งโปรเจคต์ มีตัวนี้ใช้ได้ ไหลได้ในทุก ๆ พื้นที่
    ทั้งนี้เป็นการบ่งบอกด้วยว่า จะใช่้ชื่อ class นั้นๆ เป็น object ที่จะใช้ในพื้นที่อื่นด้วย เพื่อป้องกันการสร้างซ้ำซ้อน*/

    /*@Volatile The @volatile annotation is part of the Kotlin programming language, and it is used for multithreading operations.
We use this annotation to denote that a variable must be read from this specific place in memory every time
and therefore not cached anywhere, as some threading operations might do in the background,
which if you are not aware of can lead to errors, and we don’t like those here. กำหนดให้สิ่งที่สร้างขึ่นสร้างนำไปใช้ในที่อื่นๆ ได้้ซ้ำ โดยที่ไม่ต้องผัง cached ใหม่ไปเรื่อยๆ */
    abstract fun getNoteDao() : NoteDao

    companion object{
        @Volatile
        private var INSTANCE : NoteDatabase? = null

        fun getDatabase(context : Context) : NoteDatabase{

            return INSTANCE ?: synchronized(this){

                val  instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    DATABASE_NAME
                ).build()

                INSTANCE = instance

                instance

            }
        }


    }


}