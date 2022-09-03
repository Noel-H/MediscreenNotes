package com.noelh.mediscreennotes.controller;

import com.noelh.mediscreennotes.dto.NoteDTO;
import com.noelh.mediscreennotes.model.Note;
import com.noelh.mediscreennotes.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.NoSuchElementException;

@Slf4j
@Controller
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }

    @GetMapping("")
    public String getNoteList(Model model){
        log.info("GET /");
        model.addAttribute("noteList", noteService.getNoteList());
        return "note/NoteList";
    }

    @GetMapping("/notes/{id}")
    public String getNoteListByPatientId(@PathVariable("id") Long patientId, Model model){
        log.info("GET /notes/{}", patientId);
        model.addAttribute("noteListByPatientId", noteService.getNoteListByPatientId(patientId));
        model.addAttribute("patientId", patientId);
        return "note/NoteListByPatientId";
    }

    @GetMapping("/add")
    public String getAddNote(Model model){
        log.info("GET /add");
        model.addAttribute("noteDTO", new NoteDTO());
        return "note/AddNote";
    }

    @PostMapping("/add")
    public String postAddNote(@ModelAttribute NoteDTO noteDTO){
        log.info("POST /add");
        noteService.addNote(noteDTO);
        return "redirect:/";
    }

    @GetMapping("/notes/add/{id}")
    public String getAddNoteFromHistory(@PathVariable("id") Long patientId, Model model){
        log.info("GET /addByPatientId/{}", patientId);
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setPatientId(patientId);
        model.addAttribute("noteDTO", noteDTO);
        return "note/AddNoteByPatientId";
    }

    @PostMapping("/notes/add/{id}")
    public String postAddNoteFromHistory(@PathVariable("id") Long patientId, @ModelAttribute NoteDTO noteDTO){
        log.info("POST /addByPatientId/{}", patientId);
        noteService.addNote(noteDTO);
        return "redirect:/notes/"+patientId;
    }

    @GetMapping("/update/{id}")
    public String getUpdateNote(@PathVariable("id") String id, Model model){
        log.info("GET /update/{}", id);
        Note note;
        try {
            note = noteService.getNoteById(id);
        } catch (NoSuchElementException e) {
            log.error("GET /update/{} : ERROR = {}", id, e.getMessage());
            return "redirect:/";
        }
        model.addAttribute("note", note);
        return "note/UpdateNote";
    }

    @PostMapping("/update/{id}")
    public String postUpdateNote(@PathVariable("id") String id, @ModelAttribute Note note){
        log.info("POST /update/{}", id);
        noteService.updateNote(id, new NoteDTO(note.getPatientId(), note.getNoteOfThePractitioner()));
        return "redirect:/";
    }

    @GetMapping("notes/update/{id}")
    public String getUpdateNoteFromHistory(@PathVariable("id") String id, Model model){
        log.info("GET notes/update/{}", id);
        Note note;
        try {
            note = noteService.getNoteById(id);
        } catch (NoSuchElementException e) {
            log.error("GET notes/update/{} : ERROR = {}", id, e.getMessage());
            return "redirect:http/localhost:8080/patient";
        }
        model.addAttribute("note", note);
        return "note/UpdateNoteByPatientId";
    }

    @PostMapping("notes/update/{id}")
    public String postUpdateNoteFromHistory(@PathVariable("id") String id, @ModelAttribute Note note){
        log.info("POST notes/update/{}", id);
        noteService.updateNote(id, new NoteDTO(note.getPatientId(), note.getNoteOfThePractitioner()));
        return "redirect:/notes/"+note.getPatientId();
    }

    @GetMapping("/delete/{id}")
    public String deleteNote(@PathVariable("id") String id){
        log.info("GET /delete/{}", id);
        try {
            noteService.deleteNote(id);
        } catch (NoSuchElementException e) {
            log.error("GET /delete/{} : ERROR = {}", id, e.getMessage());
            return "redirect:/";
        }
        return "redirect:/";
    }

    @GetMapping("notes/delete/{id}")
    public String deleteNoteFromHistory(@PathVariable("id") String id){
        log.info("GET notes/delete/{}", id);
        Note note;
        try {
            note = noteService.deleteNote(id);
        } catch (NoSuchElementException e) {
            log.error("GET notes/delete/{} : ERROR = {}", id, e.getMessage());
            return "redirect:http://localhost:8080/patient";
        }
        return "redirect:/notes/"+note.getPatientId();
    }

}
