package equipamentos;

import java.time.LocalDate;
import java.util.List;
import empresa.Setor;
import funcionarios.Funcionario;
import chamados.Chamado;

public class Impressora extends Equipamento {
    private String tipo;                // "Laser", "Jato de Tinta", "Matricial"
    private boolean temScanner;

    public Impressora(int id, String marca, String modelo, String numeroSerie, LocalDate dataAquisicao, StatusEquipamento status, Setor setor, Funcionario responsavel, String tipo, boolean temScanner) {
        super(id, marca, modelo, numeroSerie, dataAquisicao, status, setor, responsavel);
        this.tipo = tipo;
        this.temScanner = temScanner;
    }
    
    public String getTipo() {
        return tipo;
    }

    public boolean getScanner() {
        return temScanner;
    }
}
