package com.noelh.mediscreennotes.service;

import com.noelh.mediscreennotes.dto.NoteDTO;
import com.noelh.mediscreennotes.model.Note;
import com.noelh.mediscreennotes.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Note Service
 */
@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository){
        this.noteRepository = noteRepository;
    }

    /**
     * Find all note
     * @return a list of note
     */
    public List<Note> getNoteList(){
        return noteRepository.findAll();
    }

    /**
     * Get a list of note by a patientId
     * @param patientId is the patientId of the note
     * @return a list of note
     */
    public List<Note> getNoteListByPatientId(Long patientId) {
        return noteRepository.getNotesByPatientId(patientId);
    }

    /**
     * get a note by his id
     * @param id is the id of the note
     * @return the wanted note
     */
    public Note getNoteById(String id){
        return noteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Note not found with id : " + id));
    }

    /**
     * Add a note
     * @param noteDTO is the dto who contains the required information to add
     * @return the added note
     */
    public Note addNote(NoteDTO noteDTO){
        Note note = new Note();
        note.setPatientId(noteDTO.getPatientId());
        note.setNoteOfThePractitioner(noteDTO.getNoteOfThePractitioner());
        return noteRepository.save(note);
    }

    /**
     * Update a note
     * @param id is the id of the note
     * @param noteDTO is the dto who contains the required information to update
     * @return the updated note
     */
    public Note updateNote(String id, NoteDTO noteDTO){
        Note note = getNoteById(id);
        note.setPatientId(noteDTO.getPatientId() == null ? note.getPatientId() : noteDTO.getPatientId());
        note.setNoteOfThePractitioner(noteDTO.getNoteOfThePractitioner() == null ? note.getNoteOfThePractitioner() : noteDTO.getNoteOfThePractitioner());
        return noteRepository.save(note);
    }

    /**
     * Delete a note
     * @param id is the id of the note
     * @return the deleted note
     */
    public Note deleteNote(String id){
        Note note = getNoteById(id);
        noteRepository.deleteById(id);
        return note;
    }
}
