package br.com.devnunes.crud.bancodedados;

import br.com.devnunes.crud.exceptions.EmailCadastrado;
import br.com.devnunes.crud.exceptions.IdCadastrado;
import br.com.devnunes.crud.exceptions.UsuarioExiste;
import br.com.devnunes.crud.exceptions.UsuarioNotFoundException;
import br.com.devnunes.crud.classes.Usuario;

import java.util.ArrayList;
import java.util.List;

public class BancoDeDados {

 private final List<Usuario> listaDeUsuarios = new ArrayList<>();
 private long nextId= 1L;

 public Usuario Save(final Usuario usuario){
     if (!idCadastrado(usuario)) throw new IdCadastrado("ID já cadastrado!");
     if (!emailCadastrado(usuario)) throw new EmailCadastrado("Email já cadastrado!");
     //usuario.setId(nextId);
     //nextId += 1;
     //Antes, trabalhei com ID dado pelo programador, e não pelo usuário!
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
 private boolean UsuarioExiste(Usuario usuarioDigitado){
     return listaDeUsuarios.stream()
             .anyMatch(usuario -> usuario.getId() == usuarioDigitado.getId());
 }
 private boolean idCadastrado(final Usuario usuarioDigitado){
     return listaDeUsuarios.stream().noneMatch(usuario -> usuario.getId() == usuarioDigitado.getId());
 }
 private boolean emailCadastrado(final Usuario usuarioDigitado){
        return listaDeUsuarios.stream().noneMatch(usuario -> usuario.getEmail().equalsIgnoreCase(usuarioDigitado.getEmail()));
    }
 private void emailCadastrado2(final Usuario usuarioTeste){
     if(!listaDeUsuarios.stream().noneMatch(usuario -> usuario.getEmail().equalsIgnoreCase(usuarioTeste.getEmail()))){
         throw new RuntimeException("O emailCadastrado 2 funcionou..");
         //Caso queira evitar retorno de true ou false, coloque no save!
     }
 }


}
