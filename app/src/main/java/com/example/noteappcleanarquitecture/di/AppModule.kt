package com.example.noteappcleanarquitecture.di

import android.app.Application
import androidx.room.Room
import com.example.noteappcleanarquitecture.feature_note.data.data_source.NoteDatabase
import com.example.noteappcleanarquitecture.feature_note.data.repository.NoteRepositoryImpl
import com.example.noteappcleanarquitecture.feature_note.domain.repository.NoteRepository
import com.example.noteappcleanarquitecture.feature_note.domain.use_case.AddNoteUseCase
import com.example.noteappcleanarquitecture.feature_note.domain.use_case.DeleteNoteUseCase
import com.example.noteappcleanarquitecture.feature_note.domain.use_case.GetNotesUseCase
import com.example.noteappcleanarquitecture.feature_note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app:Application) = Room.databaseBuilder(
        app,
        NoteDatabase::class.java,
        NoteDatabase.DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideNoteRepository(db:NoteDatabase):NoteRepository = NoteRepositoryImpl(db.noteDao)

    @Provides
    @Singleton
    fun provideUseCases(repository: NoteRepository) = NoteUseCases(
        getNotes = GetNotesUseCase(repository),
        deleteNote = DeleteNoteUseCase(repository),
        addNote = AddNoteUseCase(repository)
    )
}