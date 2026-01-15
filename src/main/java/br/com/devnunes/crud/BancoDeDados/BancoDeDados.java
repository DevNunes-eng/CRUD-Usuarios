package br.com.devnunes.crud.BancoDeDados;

import br.com.devnunes.crud.Exceptions.UsuarioExiste;
import br.com.devnunes.crud.Exceptions.UsuarioNotFoundException;
import br.com.devnunes.crud.classes.Usuario;

import java.util.ArrayList;
import java.util.List;

public class BancoDeDados {

 private final List<Usuario> listaDeUsuarios = new ArrayList<>();
 private long nextId= 1L;

 public Usuario Save(final Usuario usuario){
     if(UsuarioExiste(usuario)) {
         throw new UsuarioExiste("Usuário " +usuario.getName()+ " já cadastrado!");
     }
     usuario.setId(nextId);
     nextId += 1;
     listaDeUsuarios.add(usuario);

     return usuario;
 }
 public Usuario Update(final long id){
     var usuarioPorId = FindByID(id);
     listaDeUsuarios.remove(usuarioPorId);
     listaDeUsuarios.add(usuarioPorId);

     return usuarioPorId;
 }
 public Usuario Delete(final long id){
     var usuarioPorId = FindByID(id);
     listaDeUsuarios.remove(usuarioPorId);
     return usuarioPorId;
 }

 public Usuario FindByID(final long id){
     var mensagem = String.format("Não há usuário cadastrado com o id %s! ❌",id);
     return listaDeUsuarios.stream()
             .filter(usuario1 -> usuario1.getId() == id)
             .findFirst()
             .orElseThrow(()->new UsuarioNotFoundException(mensagem));
 }
 public List<Usuario> FindAll(){
     return listaDeUsuarios;
 }
 public boolean UsuarioExiste(Usuario usuarioDigitado){
     return listaDeUsuarios.stream()
             .anyMatch(usuario -> usuario.getId() == usuarioDigitado.getId());
 }

}
