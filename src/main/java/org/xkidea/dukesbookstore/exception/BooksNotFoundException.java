package org.xkidea.dukesbookstore.exception;

public class BooksNotFoundException extends Exception{

    private static final long serialVersionUID = -1L;

    public BooksNotFoundException() {
    }

    public BooksNotFoundException(String message) {
        super(message);
    }
}
