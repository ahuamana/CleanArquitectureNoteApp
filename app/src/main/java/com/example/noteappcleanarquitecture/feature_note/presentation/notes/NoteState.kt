package com.example.noteappcleanarquitecture.feature_note.presentation.notes

import com.example.noteappcleanarquitecture.feature_note.domain.model.Note
import com.example.noteappcleanarquitecture.feature_note.domain.util.NoteOrder
import com.example.noteappcleanarquitecture.feature_note.domain.util.OrderType

data class NoteState(
    val notes:List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible:Boolean = false
)
