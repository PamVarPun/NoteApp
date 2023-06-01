package org.classapp.noteapp.Models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.classapp.noteapp.Database.NoteDatabase
import org.classapp.noteapp.Database.NotesRespository

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : NotesRespository

    val allnotes : LiveData<List<Note>>

    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NotesRespository(dao)
        allnotes = repository.allNotes
    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {

        repository.delete(note)

    }

    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {

        repository.insert(note)
    }

    fun  updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }

}