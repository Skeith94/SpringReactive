package com.etra.reactiveNotes.reactiveNotes.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.etra.reactiveNotes.reactiveNotes.models.Note;

public class NoteTest {

    @Test
    public void testConstructorWithoutArgs() {
        Note model=new Note();
        Note modelEqual=new Note(1, "Hello world!");

        Note model2=new Note();
        model2.setId(2);


        model.setId(1);
        model.setNote("Hello world!");

        Assertions.assertEquals(2,model2.getId());
        Assertions.assertEquals("Hello world!",model.getNote());

        Assertions.assertTrue(model.equals(modelEqual) && modelEqual.equals(model));
        Assertions.assertEquals(model.hashCode(), modelEqual.hashCode());

    }
}