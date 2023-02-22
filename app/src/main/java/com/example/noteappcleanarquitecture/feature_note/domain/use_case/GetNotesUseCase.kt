package com.example.noteappcleanarquitecture.feature_note.domain.use_case

import com.example.noteappcleanarquitecture.feature_note.domain.repository.NoteRepository
import com.example.noteappcleanarquitecture.feature_note.domain.util.NoteOrder
import com.example.noteappcleanarquitecture.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.map

class GetNotesUseCase(
    private val repository: NoteRepository
) {
    operator fun invoke(order: NoteOrder = NoteOrder.Date(OrderType.Descending)) = repository.getNotes().map {notes->
        when(order.orderType){
            is OrderType.Ascending -> {
                when(order){
                    is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                    is NoteOrder.Date -> notes.sortedBy { it.timestamp }
                    is NoteOrder.Color -> notes.sortedBy { it.color }
                }
            }
            is OrderType.Descending -> {
                when(order){
                    is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
                    is NoteOrder.Date -> notes.sortedByDescending { it.timestamp }
                    is NoteOrder.Color -> notes.sortedByDescending { it.color }
                }
            }
        }
    }
}