package at.spengergasse.bookecho.commands;

import at.spengergasse.bookecho.domain.Book;
import jakarta.validation.constraints.NotNull;

public record ReplaceBookCommand (@NotNull Book.BookId bookId, @NotNull String title, @NotNull String isbn){}
