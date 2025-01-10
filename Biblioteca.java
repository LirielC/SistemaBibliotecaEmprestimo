import java.util.List;
import java.util.Scanner;

public class Bibliotecario extends Pessoa {
    private Validador validador;
    public Bibliotecario(int id, String nome, String email, String senha) {
        super(id, nome, email, senha);
        validador = new ValidadorImpl();
    }

    @Override
    public boolean autenticar(String email, String senha) {
        return this.getEmail().equals(email) && this.getSenha().equals(senha);
    }


    public void adicionarLivro(List<Livro> livros, Scanner scanner) {
        System.out.print("Título do Livro: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        if (!validador.validarIsbn(isbn)) {
            System.out.println("ISBN inválido!");
            return;
        }

        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine(); //

        livros.add(new Livro(titulo, autor, isbn, quantidade));
        System.out.println("Livro adicionado com sucesso!");
    }


    public void adicionarUsuario(List<Usuario> usuarios, Scanner scanner) {
        System.out.print("Nome do Usuário: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        if (!validador.validarEmail(email)) {
            System.out.println("Email inválido!");
            return;
        }
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        if (!validador.validarCpf(cpf)) {
            System.out.println("CPF inválido!");
            return;
        }

        usuarios.add(new Usuario(usuarios.size() + 1, nome, email, senha, telefone, cpf));
        System.out.println("Usuário adicionado com sucesso!");
    }

    public void emprestarLivroParaUsuario(List<Usuario> usuarios, List<Livro> livros, Scanner scanner) {
        System.out.print("CPF do Usuário: ");
        String cpf = scanner.nextLine();
        Usuario usuario = usuarios.stream()
                .filter(u -> u.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        System.out.print("Título do Livro: ");
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
        System.out.println("Livro emprestado ao usuário com sucesso!");
    }
}
