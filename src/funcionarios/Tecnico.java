package funcionarios;

import java.math.BigDecimal;
import empresa.Setor;

public class Tecnico extends Funcionario {
    private String areaDeAtuacao;
    private BigDecimal valorHora;

    public Tecnico(Integer id, String nome, String email, String telefone,
                   Setor setor, String areaDeAtuacao, BigDecimal valorHora) {
        super(id, nome, email, telefone, setor);
        this.areaDeAtuacao = areaDeAtuacao;
        this.valorHora = valorHora;
    }

    public BigDecimal getValorHora() {
        return this.valorHora;
    }

    public String getAreaDeAtuacao() {
        return this.areaDeAtuacao;
    }

    public void setValorHora(BigDecimal valorHora) {
        this.valorHora = valorHora;
    }

    public void setAreaDeAtuacao(String areaDeAtuacao) {
        this.areaDeAtuacao = areaDeAtuacao;
    }
}
