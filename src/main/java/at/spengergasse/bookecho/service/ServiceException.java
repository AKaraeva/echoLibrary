package at.spengergasse.bookecho.service;

import jakarta.persistence.PersistenceException;

public class ServiceException extends RuntimeException {
    private ServiceException(String message) {super(message);}
    private ServiceException(String message, Throwable cause) {super(message, cause);}


    public static ServiceException whileSavingBook(String title, String isbn, PersistenceException pEx) {
        var msg = "Cannot save book with title %s".formatted(title);
        return new ServiceException(msg, pEx);
    }
}
