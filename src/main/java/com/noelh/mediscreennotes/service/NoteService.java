package com.noelh.mediscreennotes.service;

import com.noelh.mediscreennotes.dto.NoteDTO;
import com.noelh.mediscreennotes.model.Note;
import com.noelh.mediscreennotes.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository){
        this.noteRepository = noteRepository;
    }

    public List<Note> getNoteList(){
        return noteRepository.findAll();
    }

    public Note getNoteById(String id){
        return noteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Note not found with id : " + id));
    }

    public Note addNote(NoteDTO noteDTO){
        Note note = new Note();
        note.setPatientId(noteDTO.getPatientId());
        note.setNoteOfThePractitioner(noteDTO.getNoteOfThePractitioner());
        return noteRepository.save(note);
    }

    public Note updateNote(String id, NoteDTO noteDTO){
        Note note = getNoteById(id);
        note.setPatientId(noteDTO.getPatientId() == null ? note.getPatientId() : noteDTO.getPatientId());
        note.setNoteOfThePractitioner(noteDTO.getNoteOfThePractitioner() == null ? note.getNoteOfThePractitioner() : noteDTO.getNoteOfThePractitioner());
        return noteRepository.save(note);
    }

    public Note deleteNote(String id){
        Note note = getNoteById(id);
        noteRepository.deleteById(id);
        return note;
    }

}
