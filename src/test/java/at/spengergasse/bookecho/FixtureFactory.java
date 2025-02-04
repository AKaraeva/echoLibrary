package at.spengergasse.bookecho;

import at.spengergasse.bookecho.domain.*;

import java.util.List;

public class FixtureFactory {
    public static Author author(){
        return Author.builder()
                .lastname(Lastname.of("Sapolsky"))
                .firstname(Firstname.of("Robert"))
                .nationality(Country.USA)
                .bio("some text")
                .email(new Email("sapolsky@gmail.com"))
                .build();
    }

    public static Image image() {
        return Image.builder()
                .description("book cover image")
                .path("http://example.com")
                .build();
    }

    public static Book book() {
       return Book.builder()
               .isbn("978-3-16-148410-0")
               .image(image())
               .authors(List.of(author()))
               .title("Determined")
               .build();
    }
}
