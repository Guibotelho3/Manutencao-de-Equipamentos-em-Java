package funcionarios;

import java.math.BigDecimal;
import empresa.Setor;


public class Supervisor extends Funcionario {
    private String areaSupervisionada;
    private BigDecimal valorHora;

    public Supervisor(Integer id, String nome, String email, String telefone,
                   Setor setor, String areaSupervisionada, BigDecimal valorHora) {
        super(id, nome, email, telefone, setor);
        this.areaSupervisionada = areaSupervisionada;
        this.valorHora = valorHora;
    }
}
