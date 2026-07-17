package br.com.devnunes.crud;

import br.com.devnunes.crud.bancodedados.ClienteDAO;
import br.com.devnunes.crud.bancodedados.ClienteDAOMemoria;
import br.com.devnunes.crud.exceptions.UsuarioExceptions;
import br.com.devnunes.crud.classes.Option;
import br.com.devnunes.crud.service.UsuarioService;

import java.util.Scanner;

public class Main {

    private static ClienteDAO bancoDeClientes = new ClienteDAOMemoria();
    private static UsuarioService servico = new UsuarioService(bancoDeClientes);


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
                case ADICIONAR -> servico.AdicionarUsuario();
                case REMOVER -> servico.DeletarUsuario();
                case ATUALIZAR -> servico.AtualizarUsuario();
                case EXIBIR -> servico.ExibirUsuario();
                case EXIBIR_ALL -> servico.ExibirTodos();
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

}