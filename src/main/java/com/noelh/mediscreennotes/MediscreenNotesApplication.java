package com.noelh.mediscreennotes;

import com.noelh.mediscreennotes.model.Note;
import com.noelh.mediscreennotes.repository.NoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MediscreenNotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediscreenNotesApplication.class, args);
    }

    //Bean pour test la connection à mongoDB
    @Bean
    CommandLineRunner runner(NoteRepository noteRepository){
        return args -> {
            Note note = new Note(
                    "TestName",
                    "Test of recommandation of the practitioner.");

            noteRepository.insert(note);
        };
    }

}
