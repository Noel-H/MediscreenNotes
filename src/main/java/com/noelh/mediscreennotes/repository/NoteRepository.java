package com.noelh.mediscreennotes.repository;

import com.noelh.mediscreennotes.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {
    List<Note> getNotesByPatientId(Long patientId);
}
