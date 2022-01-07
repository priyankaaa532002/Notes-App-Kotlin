package com.example.quicknotes.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.quicknotes.Database.NotesDatabase
import com.example.quicknotes.Model.Notes
import com.example.quicknotes.Repository.NotesRepository

class NotesViewModel(application: Application):AndroidViewModel(application) {
    var repository:NotesRepository

    init {
        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository=NotesRepository(dao)
    }

    fun addNotes(notes: Notes){
        repository.insertNotes(notes)
    }
    fun getNotes():LiveData<List<Notes>> {
        return repository.getAllNotes()
    }
    fun deleteNotes(id:Int){
        repository.deleteNotes(id)
    }
    fun updateNotes(notes: Notes){
        repository.updateNotes(notes)
    }

    //filters
    fun getHighNotes():LiveData<List<Notes>>{
        return repository.getHighNotes()
    }

    fun getMediumNotes():LiveData<List<Notes>>{
        return repository.getMediumNotes()
    }

    fun getLowNotes():LiveData<List<Notes>>{
        return repository.getLowNotes()
    }
}