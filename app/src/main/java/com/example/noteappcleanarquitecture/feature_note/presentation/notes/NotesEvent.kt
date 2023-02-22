package com.example.noteappcleanarquitecture.feature_note.presentation.notes

import com.example.noteappcleanarquitecture.feature_note.domain.model.Note
import com.example.noteappcleanarquitecture.feature_note.domain.util.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder): NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    object ToggleOrderSection: NotesEvent()
    object RestoreNote: NotesEvent()
    //object GetNotes: NotesEvent()
}