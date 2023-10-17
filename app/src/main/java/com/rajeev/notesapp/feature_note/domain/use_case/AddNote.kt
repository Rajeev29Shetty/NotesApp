package com.rajeev.notesapp.feature_note.domain.use_case

import com.rajeev.notesapp.feature_note.domain.model.InvalidNoteException
import com.rajeev.notesapp.feature_note.domain.model.Note
import com.rajeev.notesapp.feature_note.domain.repository.NoteRepository

class AddNote(
    private val repository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {

        if(note.title.isBlank()){
            throw InvalidNoteException("The title of the Note can't be empty.")
        }
        if(note.content.isBlank()){
            throw InvalidNoteException("The content of the Note can't be empty.")
        }

        repository.insertNote(note)
    }
}