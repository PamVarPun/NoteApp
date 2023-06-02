package org.classapp.noteapp.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import org.classapp.noteapp.Models.Note
/*DAO หรือ Data Access Object เป็น class หลักในการกำหนด Database ที่จะใช้งาน เป็นที่ ๆ สามารถใช้ Query ในรูปแบบต่างเพื่อการเข้าถึงข้อมูล*/
/*A suspending function is simply a function that can be paused and resumed at a later time.*/
@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note : Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("Select * From notes_table order by id DESC")
    fun getAllNotes() : LiveData<List<Note>>


    @Query("Update notes_table Set title = :title, note = :note Where id = :id")
    suspend fun update(id : Int?, title : String?, note : String?)
}

