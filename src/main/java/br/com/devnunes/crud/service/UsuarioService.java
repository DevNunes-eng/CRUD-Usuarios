package br.com.devnunes.crud.service;

import br.com.devnunes.crud.bancodedados.ClienteDAO;
import br.com.devnunes.crud.bancodedados.ClienteDAOMemoria;
import br.com.devnunes.crud.classes.Usuario;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UsuarioService {
    private ClienteDAO servico;
    private Scanner scan = new Scanner(System.in);

    public UsuarioService(ClienteDAO servico){
        this.servico = servico;
    }
    public void AdicionarUsuario(){


        System.out.println("Digite o id do usuário:");
        var id = scan.nextLong();
        scan.nextLine();
        System.out.println("Digite o nome do usuário");
        var nome = scan.nextLine();
        System.out.println("Digite o email do usuário");
        var email = scan.nextLine();
        System.out.println("Digite a data de nascimento(dd/MM/yyyy)");
        var birthdayString = scan.nextLine();
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(birthdayString, formatter);
        var birthday = localDate.atStartOfDay().atOffset(ZoneOffset.UTC);
        var usuario = new Usuario(
                id,
                nome,
                email,
                birthday
        );

        servico.Save(usuario);
        System.out.println(String.format("Usuario %s adicionado com sucesso ao BancoDeDados!✅", usuario.getName()));

    }
    public  void ExibirUsuario(){
        System.out.println("Digite o ID do usuário");
        var id = scan.nextLong();
        var usuarioSelecionado = servico.FindByID(id);
        System.out.println(
                String.format(
                        "ID: %s | Nome: %s | Email: %s | Aniversário: %s 📖"
                        ,usuarioSelecionado.getId()
                        ,usuarioSelecionado.getName()
                        ,usuarioSelecionado.getEmail(),usuarioSelecionado.getBirthday()
                )
        );
    }
    public Usuario DeletarUsuario(){
        System.out.println("Digite o ID do usuário que você quer remover");
        var id = scan.nextLong();
        scan.nextLine();
        var usuarioSelecionado = servico.FindByID(id);
        servico.Delete(id);
        System.out.println(String.format("Usuario %s deletado do BancoDeDados ❌",usuarioSelecionado.getName()));
        return usuarioSelecionado;
    }
    public  void AtualizarUsuario(){
        System.out.println("Digite o ID do usuário que você quer atualizar");
        var id = scan.nextLong();
        scan.nextLine();
        var usuarioSelecionado = servico.FindByID(id);
        servico.Update(id);
        System.out.println(String.format("Usuario %s Atualizado no BancoDeDados",usuarioSelecionado.getName()));

    }
    public  void ExibirTodos(){
        servico.FindAll().stream()
                .map(usuario -> String.format("Usuario - %s",usuario.getName()))
                .forEach(System.out::println);
    }



}

