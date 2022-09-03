package com.noelh.mediscreennotes.service;

import com.noelh.mediscreennotes.dto.NoteDTO;
import com.noelh.mediscreennotes.model.Note;
import com.noelh.mediscreennotes.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NoteServiceTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    @Test
    public void getNoteList_Should_Return_NoteList(){
        //Given


        //When
        noteService.getNoteList();

        //Then
        verify(noteRepository, times(1)).findAll();
    }

    @Test
    public void getNoteListByPatientId_Should_Return_NoteList(){
        //Given


        //When
        noteService.getNoteListByPatientId(1L);

        //Then
        verify(noteRepository, times(1)).getNotesByPatientId(1L);
    }

    @Test
    public void getNoteById_Should_Return_Note(){
        //Given
        when(noteRepository.findById("1")).thenReturn(Optional.of(new Note()));

        //When
        noteService.getNoteById("1");

        //Then
        verify(noteRepository, times(1)).findById("1");
    }

    @Test
    public void getNoteById_Should_Return_Exception(){
        //Given
        when(noteRepository.findById("1")).thenReturn(Optional.empty());

        //When
        //Then
        assertThrows(NoSuchElementException.class, () -> noteService.getNoteById("1"));
    }

    @Test
    public void addNote_Should_Return_Note(){
        //Given
        NoteDTO noteDTO = new NoteDTO(
                1L,
                "TestNoteOfThePractitioner");

        Note note = new Note();
        note.setPatientId(1L);
        note.setNoteOfThePractitioner("TestNoteOfThePractitioner");

        //When
        noteService.addNote(noteDTO);

        //Then
        verify(noteRepository, times(1)).save(note);
    }

    @Test
    public void updateNote_Should_Return_Exception(){
        //Given
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setPatientId(1L);
        noteDTO.setNoteOfThePractitioner("TestNoteOfThePractitioner");

        when(noteRepository.findById("1")).thenReturn(Optional.empty());

        //When
        //Then
        assertThrows(NoSuchElementException.class, () -> noteService.updateNote("1",noteDTO));
    }

    @Test
    public void updateNote_Should_Return_Note(){
        //Given
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setPatientId(1L);
        noteDTO.setNoteOfThePractitioner("TestNoteOfThePractitioner");

        Note note = new Note();
        note.setId("1");
        note.setPatientId(1L);
        note.setNoteOfThePractitioner("TestNoteOfThePractitioner");

        when(noteRepository.findById("1")).thenReturn(Optional.of(note));

        //When
        noteService.updateNote("1", noteDTO);

        //Then
        verify(noteRepository,times(1)).save(note);
    }

    @Test
    public void updateNote_Without_LastName_And_FirstName_Should_Return_Note(){
        //Given
        NoteDTO noteDTO = new NoteDTO();

        Note note = new Note();
        note.setId("1");
        note.setPatientId(1L);
        note.setNoteOfThePractitioner("TestNoteOfThePractitioner");

        when(noteRepository.findById("1")).thenReturn(Optional.of(note));

        //When
        noteService.updateNote("1", noteDTO);

        //Then
        verify(noteRepository,times(1)).save(note);
    }

    @Test
    public void deleteNote_Should_Return_Note(){
        //Given
        Note note = new Note();
        note.setId("1");
        note.setPatientId(1L);
        note.setNoteOfThePractitioner("TestNoteOfThePractitioner");

        when(noteRepository.findById("1")).thenReturn(Optional.of(note));

        //When
        noteService.deleteNote("1");

        //Then
        verify(noteRepository,times(1)).deleteById("1");
    }

    @Test
    public void deleteNote_Should_Return_Exception(){
        //Given
        when(noteRepository.findById("1")).thenReturn(Optional.empty());

        //When
        //Then
        assertThrows(NoSuchElementException.class, () -> noteService.deleteNote("1"));
    }

}