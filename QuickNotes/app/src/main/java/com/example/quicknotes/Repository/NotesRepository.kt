package com.example.quicknotes.Repository

import androidx.lifecycle.LiveData
import com.example.quicknotes.Dao.NotesDao
import com.example.quicknotes.Model.Notes

class NotesRepository(val dao:NotesDao) {
    fun getAllNotes():LiveData<List<Notes>>{
        return dao.getNotes()
    }

    fun insertNotes(notes:Notes){
        dao.insertNotes(notes)
    }

    fun  deleteNotes(id:Int){
        dao.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        dao.updateNotes(notes)
    }

    //filters

    fun getHighNotes():LiveData<List<Notes>>{
        return dao.getHighNotes()
    }

    fun getMediumNotes():LiveData<List<Notes>>{
        return dao.getMediumNotes()
    }

    fun getLowNotes():LiveData<List<Notes>>{
        return dao.getLowNotes()
    }
}