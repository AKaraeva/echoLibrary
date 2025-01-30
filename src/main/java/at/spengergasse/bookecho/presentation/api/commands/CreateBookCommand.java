package at.spengergasse.bookecho.presentation.api.commands;

import jakarta.validation.constraints.NotNull;

public record CreateBookCommand(
        @NotNull String title, @NotNull String isbn
) {
}
