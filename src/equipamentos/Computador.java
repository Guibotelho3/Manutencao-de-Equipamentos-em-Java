package equipamentos;

import java.time.LocalDate;
import java.util.List;
import empresa.Setor;
import funcionarios.Funcionario;
import chamados.Chamado;

public class Computador extends Equipamento {
    private String processador;
    private int memoriaRAM;

    public Computador(int id, String marca, String modelo, String numeroSerie, LocalDate dataAquisicao, StatusEquipamento status, Setor setor, Funcionario responsavel, String processador, int memoriaRAM) {
        super(id, marca, modelo, numeroSerie, dataAquisicao, status, setor, responsavel);
        this.processador = processador;
        this.memoriaRAM = memoriaRAM;
    }
    
    public String getProcessador() { return processador;}

    public int getMemoriaRAM() { return memoriaRAM;}
}
