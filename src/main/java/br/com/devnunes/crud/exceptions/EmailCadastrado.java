package br.com.devnunes.crud.exceptions;

public class EmailCadastrado extends RuntimeException {
    public EmailCadastrado(String message) {
        super(message);
    }
}
