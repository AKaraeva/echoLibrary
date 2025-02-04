package at.spengergasse.bookecho.commands;

import jakarta.validation.constraints.NotNull;

public record CreateBookCommand(@NotNull String title, @NotNull String isbn) { }
