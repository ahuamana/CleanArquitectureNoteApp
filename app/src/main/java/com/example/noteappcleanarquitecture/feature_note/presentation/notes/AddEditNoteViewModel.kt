package com.example.noteappcleanarquitecture.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappcleanarquitecture.feature_note.domain.model.Note
import com.example.noteappcleanarquitecture.feature_note.domain.use_case.NoteUseCases
import com.example.noteappcleanarquitecture.feature_note.presentation.notes.add_edit_note.AddEditNoteEvent
import com.example.noteappcleanarquitecture.feature_note.presentation.notes.add_edit_note.NoteTextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: androidx.lifecycle.SavedStateHandle
): ViewModel() {

    init {

        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if(noteId != -1){
                viewModelScope.launch {
                    noteUseCases.getNote(noteId)?.let { note ->
                        currentNoteId = note.id
                        _noteTitle.value = noteTitle.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )
                        _noteContent.value = noteContent.value.copy(
                            text = note.content,
                            isHintVisible = false
                        )
                        _noteColor.value = note.color
                    }
                }
            }
        }
    }

    private val _noteTitle = mutableStateOf(NoteTextFieldState(
        hint = "Enter title..."
    ))
    val noteTitle: State<NoteTextFieldState> = _noteTitle

    private val _noteContent = mutableStateOf(NoteTextFieldState(
        hint = "Enter some content..."
    ))
    val noteContent: State<NoteTextFieldState> = _noteContent

    private val _noteColor = mutableStateOf(Note.noteColors.random().toArgb())
    val noteColor: State<Int> = _noteColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId: Int? = null

    fun onEvent(event:AddEditNoteEvent){
        when(event){
            is AddEditNoteEvent.EnteredTitle -> {
                _noteTitle.value = _noteTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditNoteEvent.ChangeTitleFocus -> {
                _noteTitle.value = _noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused && noteTitle.value.text.isBlank()
                )
            }

            is AddEditNoteEvent.EnteredContent -> {
                _noteContent.value = _noteContent.value.copy(
                    text = event.value
                )
            }

            is AddEditNoteEvent.ChangeContentFocus -> {
                _noteContent.value = _noteContent.value.copy(
                    isHintVisible = !event.focusState.isFocused && noteContent.value.text.isBlank()
                )
            }

            is AddEditNoteEvent.ChangeColor -> {
                _noteColor.value = event.color
            }

            is AddEditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        val note = Note(
                            title = noteTitle.value.text,
                            content = noteContent.value.text,
                            timestamp = System.currentTimeMillis(),
                            color = noteColor.value,
                            id = currentNoteId
                        )
                        noteUseCases.addNote(note)
                        _eventFlow.emit(UiEvent.SaveNote)

                    }catch (e: Exception){
                        _eventFlow.emit(UiEvent.ShowSnackbar(
                            message = e.message ?: "Couldn't save note"
                        ))
                    }
                }
            }


        }
    }

    sealed class UiEvent{
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveNote: UiEvent()
    }
}