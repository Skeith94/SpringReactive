package com.etra.reactiveNotes.reactiveNotes.controller;

import javax.validation.constraints.Min;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etra.reactiveNotes.reactiveNotes.controller.dto.ResponseNotesAndUserDTO;
import com.etra.reactiveNotes.reactiveNotes.controller.exceptions.WebException;
import com.etra.reactiveNotes.reactiveNotes.models.Note;
import com.etra.reactiveNotes.reactiveNotes.models.User;
import com.etra.reactiveNotes.reactiveNotes.services.NoteServices;
import com.etra.reactiveNotes.reactiveNotes.services.UserServices;

import reactor.core.publisher.Mono;

@RestController
@Validated
@RequestMapping("/userAndNotes")
public class UserAndNoteController {

	private final UserServices userServices;
	private final NoteServices noteServices;

	public UserAndNoteController(UserServices userServices, NoteServices noteServices) {
		super();
		this.userServices = userServices;
		this.noteServices = noteServices;
	}
	
	@PostMapping("/seekUserAndNote/{id}")
    public Mono<?> seekUserAndNote(@PathVariable("id") @Min(1) Integer id) {
        ResponseNotesAndUserDTO responseUserAndNotesDTO = new ResponseNotesAndUserDTO();
        Mono<User> user = userServices.getUserById(id).switchIfEmpty(Mono.error(new WebException("User not found")));
        Mono<Note> note = noteServices.getNoteById(id).switchIfEmpty(Mono.error(new WebException("Note not found")));
        return Mono.zip(user, note).map(zip -> {
        	responseUserAndNotesDTO.setUsername(zip.getT1().getUsername());
        	responseUserAndNotesDTO.setNote(zip.getT2().getNote());
        	return ResponseEntity.ok(responseUserAndNotesDTO);
        });
        
    }
}
