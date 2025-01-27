package at.spengergasse.bookecho.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FirstnameTest {

@Test
void ensureIsValidTestWorksWithUTF8(){
    assertTrue(Firstname.isValidFirstname.test("Özlem"));
    assertTrue(Firstname.isValidFirstname.test("K".repeat(64)));
    assertTrue(Firstname.isValidFirstname.test("Sabine-Christine"));

    //to fix
    //assertTrue(Firstname.isValidFirstname.test("CPO/"));
}
}