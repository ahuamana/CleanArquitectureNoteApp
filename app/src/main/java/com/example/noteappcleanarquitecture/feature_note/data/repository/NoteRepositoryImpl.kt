package com.example.noteappcleanarquitecture.feature_note.data.repository

import com.example.noteappcleanarquitecture.feature_note.data.data_source.NoteDao
import com.example.noteappcleanarquitecture.feature_note.domain.model.Note
import com.example.noteappcleanarquitecture.feature_note.domain.repository.NoteRepository

class NoteRepositoryImpl(
    private val noteDao: NoteDao
) : NoteRepository {

    override fun getNotes() = noteDao.getNotes()

    override suspend fun getNoteById(id: Int) = noteDao.getNoteById(id)

    override suspend fun insertNote(note: Note) = noteDao.insertNote(note)

    override suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)

}