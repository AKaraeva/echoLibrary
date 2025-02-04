package at.spengergasse.bookecho.dtos;

import at.spengergasse.bookecho.domain.Book;

import java.util.Optional;

public class BookDtos {
    public record BasicInfoDto(String title){
        public static BasicInfoDto of(Book book){
            return new BasicInfoDto("%s".formatted(book.getTitle()));
        }
    }
}
