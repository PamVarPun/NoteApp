package org.classapp.noteapp.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import org.classapp.noteapp.Models.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note : Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("Select * From notes_table order by id ASC")
    fun getAllNotes() : LiveData<List<Note>>


    @Query("Update notes_table Set title = :title, note = :note Where id = :id")
    suspend fun update(id : Int?, title : String?, note : String?)
}

