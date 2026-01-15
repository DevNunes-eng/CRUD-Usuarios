package br.com.devnunes.crud.exceptions;

public class IdCadastrado extends RuntimeException {
    public IdCadastrado(String message) {
        super(message);
    }
}
