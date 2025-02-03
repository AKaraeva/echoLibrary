package at.spengergasse.bookecho;

import at.spengergasse.bookecho.domain.Book;

public class FixtureFactory {
    public static Book book() {
       return Book.builder().isbn("978-3-16-148410-0").title("Determined").build();
    }
}
