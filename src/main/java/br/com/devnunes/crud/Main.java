package br.com.devnunes.crud;

import br.com.devnunes.crud.bancodedados.BancoDeDados;
import br.com.devnunes.crud.exceptions.UsuarioExceptions;
import br.com.devnunes.crud.classes.Option;
import br.com.devnunes.crud.classes.Usuario;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    private static BancoDeDados banco = new BancoDeDados();

    public static void main(String[] args) {
        var scan = new Scanner(System.in);
        var continuar = true;
        while (continuar){
            try{
            System.out.println();
            Menu();
           var opcaoSelecionada = scan.nextInt();
            scan.nextLine();
            var opcao = Option.values()[opcaoSelecionada-1];
            switch (opcao) {
                case ADICIONAR -> AdicionarUsuario();
                case REMOVER -> DeletarUsuario();
                case ATUALIZAR -> AtualizarUsuario();
                case EXIBIR -> ExibirUsuario();
                case EXIBIR_ALL -> ExibirTodos();
                case SAIR -> {
                    System.out.println("Saindo, TCHAU👋🏾");
                    continuar = false;
                }
            }
            } catch (UsuarioExceptions e){
                System.err.println("⚠️ Erro:"+e.getMessage());
            }
        }
    }
    private static void Menu(){
        System.out.println("💻 CRUD DE USUÁRIOS");
        System.out.println("1 - 🙍🏾‍♂️ Adicionar");
        System.out.println("2 - ❌ Remover");
        System.out.println("3 - 🔄️ Atualizar");
        System.out.println("4 - 😜 Exibir");
        System.out.println("5 - 🤔 Exibir todos usuários");
        System.out.println("6 - 🏃 Sair");

    }
    private static void AdicionarUsuario(){

            var scan = new Scanner(System.in);
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

            banco.Save(usuario);
            System.out.println(String.format("Usuario %s adicionado com sucesso ao BancoDeDados!✅", usuario.getName()));

    }
    private static void ExibirUsuario(){
        var scan = new Scanner(System.in);
       System.out.println("Digite o ID do usuário");
       var id = scan.nextLong();
       var usuarioSelecionado = banco.FindByID(id);
       System.out.println(
               String.format(
                       "ID: %s | Nome: %s | Email: %s | Aniversário: %s 📖"
                       ,usuarioSelecionado.getId()
                       ,usuarioSelecionado.getName()
                       ,usuarioSelecionado.getEmail(),usuarioSelecionado.getBirthday()
               )
       );
    }
    private static Usuario DeletarUsuario(){
        var scan = new Scanner(System.in);
        System.out.println("Digite o ID do usuário que você quer remover");
        var id = scan.nextLong();
        scan.nextLine();
        var usuarioSelecionado = banco.FindByID(id);
        banco.Delete(id);
        System.out.println(String.format("Usuario %s deletado do BancoDeDados ❌",usuarioSelecionado.getName()));
        return usuarioSelecionado;
    }
    private static void AtualizarUsuario(){
        var scan = new Scanner(System.in);
        System.out.println("Digite o ID do usuário que você quer atualizar");
        var id = scan.nextLong();
        scan.nextLine();
        var usuarioSelecionado = banco.FindByID(id);
        banco.Update(id);
        System.out.println(String.format("Usuario %s Atualizado no BancoDeDados",usuarioSelecionado.getName()));

    }
    private static void ExibirTodos(){
        banco.FindAll().stream()
                .map(usuario -> String.format("Usuario - %s",usuario.getName()))
                .forEach(System.out::println);

    }

}