package com.etra.reactiveNotes.reactiveNotes.controller.dto;

import com.etra.reactiveNotes.reactiveNotes.models.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ResponseUserDTO {
	
	public ResponseUserDTO(String changes) {
		this.changes = changes;
	}
	
	User user;
	String changes;

}
