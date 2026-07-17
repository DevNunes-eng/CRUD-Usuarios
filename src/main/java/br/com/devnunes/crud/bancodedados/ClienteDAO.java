package br.com.devnunes.crud.bancodedados;

import br.com.devnunes.crud.classes.Usuario;

import java.util.List;

public interface ClienteDAO {

    public Usuario Save(final Usuario usuario);
    public Usuario Update(final long id);
    public Usuario Delete(final long id);
    public Usuario FindByID(final long id);
    public List<Usuario> FindAll();
}
