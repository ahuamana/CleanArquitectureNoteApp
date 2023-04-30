package com.example.noteappcleanarquitecture.feature_note.domain.use_case

data class NoteUseCases (
    val getNotes: GetNotesUseCase,
    val deleteNote: DeleteNoteUseCase,
    val getNote: GetNote,
    val addNote: AddNoteUseCase
)