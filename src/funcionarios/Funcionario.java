package funcionarios;

import empresa.Setor;

public abstract class Funcionario {
    private Integer id;
    private String nome;
    private String email;
    private String telefone;
    private Setor setor;

    public Funcionario(Integer id, String nome, String email, String telefone, Setor setor) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.setor = setor;
    }

    public int getId() { return id; }

    public String getNome() { return nome; }

    public String getEmail() { return email; }

    public String getTelefone() { return telefone; }

    public Setor getSetor() { return setor; }
}