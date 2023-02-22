package com.etra.reactiveNotes.reactiveNotes.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.etra.reactiveNotes.reactiveNotes.BaseTest;
import com.etra.reactiveNotes.reactiveNotes.models.Note;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class BaseTestController extends BaseTest{

	Note testFromId;
	Note getAllId;
	List<Note> testGetAll;
	Note insertTwoNote1;
	Note insertTwoNote2;
	
	@Override
	public void init() {
		testFromId = new Note();
		testFromId.setId(1);
		testGetAll = new ArrayList<>();
		for(int i = 1; i < 6; i++){
			 getAllId = new Note();
		     getAllId.setId(i);
		     testGetAll.add(getAllId);
		}
		insertTwoNote1 = new Note();
		insertTwoNote1.setId(6);
		insertTwoNote2 = new Note();
		insertTwoNote2.setId(7);
	}
	
	@Test
	@Order(1)
	public void getNoteFromId() throws Exception {
		String url = "/notes/getFromId/1";
		MvcResult mvcResult = mockMvc.perform(get(url).characterEncoding("utf-8")).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
		Note result = (Note) mvcResult.getAsyncResult();
		boolean isEqual = result.equals(testFromId);
		Assertions.assertTrue(isEqual);
	}
	
	@Test
	@Order(2)
	public void getAllNotes() throws Exception {
		String url = "/notes/getAll";
		MvcResult mvcResult = mockMvc.perform(get(url).characterEncoding("utf-8")).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
		List<Note> result = (List<Note>) mvcResult.getAsyncResult();
		boolean isEqual = result.equals(testGetAll);
		Assertions.assertTrue(isEqual);
	}
	
	@Test
	@Order(3)
	public void insertTwoNotes()throws Exception{
		String url = "/notes/insert";
		String text = "Test";
		MvcResult mvcResult1 = mockMvc.perform(post(url).param("text", text).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")).andExpect(status().isCreated()).andReturn();
		Note resultPost1 = (Note) mvcResult1.getAsyncResult();
		MvcResult mvcResult2 = mockMvc.perform(post(url).param("text", text).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")).andExpect(status().isCreated()).andReturn();
		Note resultPost2 = (Note) mvcResult2.getAsyncResult();
		boolean isEqual = resultPost1.equals(insertTwoNote1) && resultPost2.equals(insertTwoNote2);
		Assertions.assertTrue(isEqual);
		log.info("Success! /n");
	}

}
