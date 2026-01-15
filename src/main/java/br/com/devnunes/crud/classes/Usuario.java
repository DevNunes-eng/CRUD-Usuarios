package br.com.devnunes.crud.classes;

import br.com.devnunes.crud.exceptions.UsuarioEmailInvalido;
import br.com.devnunes.crud.exceptions.UsuarioNomeInvalido;

import java.time.OffsetDateTime;
import java.util.Objects;

public class Usuario {
    private long id;
    private String name;
    private String email;
    private OffsetDateTime birthday;

    public Usuario(long id, String name, String email, OffsetDateTime birthday) {
        validarNome(name);
        validarEmail(email);
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
    }
    public Usuario(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        validarNome(name);
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        validarEmail(email);
        this.email = email;
    }

    public OffsetDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(OffsetDateTime birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id && Objects.equals(name, usuario.name) && Objects.equals(email, usuario.email) && Objects.equals(birthday, usuario.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, birthday);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                '}';
    }
    public void validarNome(String nome) {
        if (nome == null || nome.isEmpty() || nome.length() < 3) {
            throw new UsuarioNomeInvalido("Nome inválido!");
        }
    }
    public void validarEmail(String email){
        if(!email.contains("@") || !email.contains(".com") || email == null){
            throw new UsuarioEmailInvalido("Email inválido!");
        }
    }
}
