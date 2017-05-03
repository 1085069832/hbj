package com.doubanapp.hbj.douban;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);

        Person person1 = new Person();
        person1.name = "person1";
        Person person2 = new Person();
        person2.name = "person2";

        System.out.println(person1.name + " " + person2.name + " " + Test1.男);
    }
}

enum Test1 {
    男, 女;
}