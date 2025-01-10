import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaBiblioteca  {
    private List<Usuario> usuarios;
    private List<Bibliotecario> bibliotecarios;
    private List<Livro> livros;
    private Scanner scanner;
    private Validador validador;

    public SistemaBiblioteca() {
        usuarios = new ArrayList<>();
        bibliotecarios = new ArrayList<>();
        livros = new ArrayList<>();
        scanner = new Scanner(System.in);
        validador = new ValidadorImpl();

        usuarios.add(new Usuario(1, "João", "joao@email.com", "senha123", "2199205025", "01709655708"));
        bibliotecarios.add(new Bibliotecario(1, "Maria", "maria@biblioteca.com", "senha456"));
        livros.add(new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", "9788533613379", 5));
    }

    public void iniciar() {
        while (true) {
            System.out.println("\n--- Sistema de Empréstimo de Livros ---");
            System.out.println("1. Login como Usuário");
            System.out.println("2. Login como Bibliotecário");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1 -> loginUsuario();
                case 2 -> loginBibliotecario();
                case 3 -> {
                    System.out.println("Obrigado por usar o sistema. Até logo!");
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void loginUsuario() {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        for (Usuario usuario : usuarios) {
            if (usuario.autenticar(email, senha)) {
                menuUsuario(usuario);
                return;
            }
        }
        System.out.println("Email ou senha inválidos.");
    }

    private void loginBibliotecario() {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        for (Bibliotecario bibliotecario : bibliotecarios) {
            if (bibliotecario.autenticar(email, senha)) {
                menuBibliotecario(bibliotecario);
                return;
            }
        }
        System.out.println("Email ou senha inválidos.");
    }

    private void menuUsuario(Usuario usuario) {
        while (true) {
            System.out.println("\n--- Menu do Usuário ---");
            System.out.println("1. Emprestar Livro");
            System.out.println("2. Devolver Livro");
            System.out.println("3. Listar Livros");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1 -> emprestarLivro(usuario);
                case 2 -> devolverLivro(usuario);
                case 3 -> listarLivros();
                case 4 -> {
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void menuBibliotecario(Bibliotecario bibliotecario) {
        while (true) {
            System.out.println("\n--- Menu do Bibliotecário ---");
            System.out.println("1. Adicionar Livro");
            System.out.println("2. Adicionar Usuário");
            System.out.println("3. Emprestar Livro para Usuário");
            System.out.println("4. Listar Livros");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            switch (opcao) {
                case 1 -> bibliotecario.adicionarLivro(livros, scanner);
                case 2 -> bibliotecario.adicionarUsuario(usuarios, scanner);
                case 3 -> bibliotecario.emprestarLivroParaUsuario(usuarios, livros, scanner);
                case 4 -> listarLivros();
                case 5 -> {
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void emprestarLivro(Usuario usuario) {
        System.out.print("Título do livro para emprestar: ");
        String titulo = scanner.nextLine();

        Livro livro = livros.stream()
                .filter(l -> l.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);

        if (livro == null || !livro.emprestarLivro()) {
            System.out.println("Livro não disponível.");
            return;
        }

        usuario.emprestarLivro(titulo);
        System.out.println("Livro emprestado com sucesso!");
    }

    private void devolverLivro(Usuario usuario) {
        System.out.print("Título do livro para devolver: ");
        String titulo = scanner.nextLine();

        Livro livro = livros.stream()
                .filter(l -> l.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);

        if (livro == null || !usuario.devolverLivro(titulo)) {
            System.out.println("Erro ao devolver o livro.");
            return;
        }

        livro.devolverLivro();
        System.out.println("Livro devolvido com sucesso!");
    }

    private void listarLivros() {
        System.out.println("\n--- Lista de Livros ---");
        livros.forEach(System.out::println);
    }

    public static void main(String[] args) {
        SistemaBiblioteca sistema = new SistemaBiblioteca();
        sistema.iniciar();
    }
}
