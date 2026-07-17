# 🚀 CRUD de Usuários

Um sistema de gerenciamento de usuários focado na aplicação de boas práticas de Engenharia de Software e Programação Orientada a Objetos (POO). 

Este projeto foi construído para ser escalável, utilizando estruturas de dados eficientes, arquitetura limpa e tratamento robusto de exceções. O sistema está em constante evolução!

---

## ✨ Novidades e Atualizações Recentes

*   **Arquitetura Baseada em Serviços (`UsuarioService`):** A lógica de negócios foi completamente extraída para uma camada de serviço. Isso garante que a classe `Main` fique limpa, lidando apenas com a interação do usuário.
*   **Padrão Data Access Object (`ClienteDAO`):** Implementação de uma interface de persistência. Isso desacopla o sistema de uma tecnologia específica, permitindo que o armazenamento mude de uma lista em memória para um banco de dados real sem que nenhuma regra de negócio precise ser alterada.
*   **Tratamento de Erros Customizado:** Criação de uma hierarquia de exceções próprias para garantir que o programa não sofra *crashes* inesperados e informe o usuário de maneira clara.

---

## 🛠️ Tecnologias e Conceitos Utilizados

*   **Linguagem:** Java
*   **Estruturas de Dados:** Java Collections (List, ArrayList)
*   **Boas Práticas:** Fail-Fast, Padrão DAO, Separação de Responsabilidades (Services).
*   **Tratamento de Exceções:** Blocos `try-catch` robustos e exceções personalizadas herdadas de `RuntimeException`.

---

## 🗺️ Roadmap (Metas Futuras)

*   **Validação em Lote (Notification Pattern):** Implementar um sistema que acumule os erros de digitação (ex: nome curto E e-mail inválido) e exiba todos de uma só vez para o usuário, melhorando a experiência.
*   **Persistência Definitiva:** Conectar o sistema a um Banco de Dados real (SQL/NoSQL) para garantir a persistência segura das informações.
*   **Importação e Exportação:** Adicionar recursos para importar ou exportar os dados do sistema em formatos como CSV ou JSON.

---
