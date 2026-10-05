package empresa;

public class Setor {
    private String nome;        // "Recepção", "Financeiro", "Sala de Sócios"
    private String andar;

    public Setor(String nome, String andar) {
        this.nome = nome;
        this.andar = andar;
    }

    public String getNome() {
        return nome;
    }

    public String getAndar() {
        return andar;
    }
}
