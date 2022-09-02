package com.noelh.mediscreennotes.repository;

import com.noelh.mediscreennotes.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteRepository extends MongoRepository<Note, Long> {
}
