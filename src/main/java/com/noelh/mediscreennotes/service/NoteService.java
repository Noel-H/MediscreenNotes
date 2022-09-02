package com.noelh.mediscreennotes.service;

import com.noelh.mediscreennotes.model.Note;
import com.noelh.mediscreennotes.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository){
        this.noteRepository = noteRepository;
    }

    public List<Note> getNoteList(){
        return noteRepository.findAll();
    }

//    public Note addNote(NoteDTO noteDTO){
//        Note note = new Note();
//        note.setPatientName(noteDTO.getPatientName());
//        note.setNoteOfThePractitioner(noteDTO.getNoteOfThePractitioner());
//        return noteRepository.save(note);
//    }

}
