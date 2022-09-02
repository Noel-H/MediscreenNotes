package com.noelh.mediscreennotes.controller;

import com.noelh.mediscreennotes.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        return "Note";
    }
}
