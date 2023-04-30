package com.example.noteappcleanarquitecture.feature_note.domain.use_case

import com.example.noteappcleanarquitecture.feature_note.domain.repository.NoteRepository

class GetNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Int) = repository.getNoteById(id)
}