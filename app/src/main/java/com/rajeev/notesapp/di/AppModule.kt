package com.rajeev.notesapp.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rajeev.notesapp.feature_note.data.data_source.NoteDatabase
import com.rajeev.notesapp.feature_note.data.repository.NoteRepositoryImpl
import com.rajeev.notesapp.feature_note.domain.repository.NoteRepository
import com.rajeev.notesapp.feature_note.domain.use_case.AddNote
import com.rajeev.notesapp.feature_note.domain.use_case.DeleteNote
import com.rajeev.notesapp.feature_note.domain.use_case.GetNote
import com.rajeev.notesapp.feature_note.domain.use_case.GetNotes
import com.rajeev.notesapp.feature_note.domain.use_case.NoteUseCases
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
    fun getNoteRepository(db: NoteDatabase) : NoteRepository{
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun getNoteDatabase(app: Application) : NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun getNoteUseCases(noteRepository: NoteRepository) : NoteUseCases{
        return NoteUseCases(
            getNotes = GetNotes(noteRepository),
            deleteNote = DeleteNote(noteRepository),
            addNote = AddNote(noteRepository),
            getNote = GetNote(noteRepository)
        )
    }
}