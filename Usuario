import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Usuario extends Pessoa {
    private String telefone;
    private String cpf;
    private List<String> listaDeLivros;

    public Usuario(int id, String nome, String email, String senha, String telefone, String cpf) {
        super(id, nome, email, senha);
        this.telefone = telefone;
        this.cpf = cpf;
        this.listaDeLivros = new ArrayList<>();
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setTelefone(String telefone) {
        if (telefone != null && !telefone.isEmpty()) {
            this.telefone = telefone;
        }
    }

    public List<String> getListaDeLivros() {
        return listaDeLivros;
    }

    public void emprestarLivro(String titulo) {
        if (titulo != null && !titulo.isEmpty() && !listaDeLivros.contains(titulo)) {
            listaDeLivros.add(titulo);
            System.out.println("Livro \"" + titulo + "\" emprestado com sucesso.");
        } else {
            System.out.println("Não foi possível emprestar o livro: título inválido ou já emprestado.");
        }
    }

    public boolean devolverLivro(String tituloLivro) {
        return listaDeLivros.remove(tituloLivro);
    }

    public void verificarConta() {
        System.out.println("Nome: " + getNome());
        System.out.println("Email: " + getEmail());
        System.out.println("Telefone: " + getTelefone());
        System.out.println("Livros emprestados: " + getListaDeLivros());
    }

    @Override
    public void entradaDados(Scanner sc) {
        super.entradaDados(sc);
        System.out.print("Telefone: ");
        setTelefone(sc.nextLine());
        System.out.print("CPF: ");
        this.cpf = sc.nextLine();
    }

    @Override
    public boolean autenticar(String email, String senha) {
        return this.getEmail().equals(email) && this.getSenha().equals(senha);
    }
}
