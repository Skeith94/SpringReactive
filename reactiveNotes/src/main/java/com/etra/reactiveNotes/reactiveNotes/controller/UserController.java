package com.etra.reactiveNotes.reactiveNotes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etra.reactiveNotes.reactiveNotes.controller.dto.RequestUserDTO;
import com.etra.reactiveNotes.reactiveNotes.controller.dto.ResponseUserDTO;
import com.etra.reactiveNotes.reactiveNotes.controller.exceptions.WebException;
import com.etra.reactiveNotes.reactiveNotes.services.UserServices;

import reactor.core.publisher.Mono;

@RestController
@Validated
@RequestMapping("/users")
public class UserController {

	private final UserServices userServices;

	public UserController(UserServices userServices) {
		super();
		this.userServices = userServices;
	}

	@PostMapping("/seekAndChangeUser")
    public Mono<?> seekAndChangeUser(@RequestBody RequestUserDTO requestDto) {
        ResponseUserDTO responseUserDTO = new ResponseUserDTO();
        return userServices.getUserById(requestDto.getId())
                .switchIfEmpty(Mono.error(new WebException("ID not found")))
                .flatMap(result -> {
                    responseUserDTO.setUser(result);
                    if (requestDto.getCaps()) {
                        result.setUsername(requestDto.getUsername().toUpperCase());
                        responseUserDTO.setChanges("Upper case selected");
                    } else {
                        result.setUsername(requestDto.getUsername().toLowerCase());
                        responseUserDTO.setChanges("Lower case selected");
                    }
                    return userServices.createUser(result)
                            .thenReturn(responseUserDTO);
                })
                .map(responseUserDTO1 -> ResponseEntity.ok(responseUserDTO1));
    }

}
