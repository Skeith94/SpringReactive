package com.etra.reactiveNotes.reactiveNotes.controller.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestUserDTO {
	
	@Min(1)
	Integer id;
	@Size(min = 5, max = 20)
	String username;
	@NotNull
	Boolean caps;
	
}
