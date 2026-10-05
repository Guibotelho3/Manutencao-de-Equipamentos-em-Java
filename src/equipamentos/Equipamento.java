package equipamentos;

import java.time.LocalDate;
import empresa.Setor;
import funcionarios.Funcionario;

public abstract class Equipamento {
    private int id;
    private String marca;
    private String modelo;
    private String numeroSerie;
    private LocalDate dataAquisicao;
    private StatusEquipamento status;
    private Setor setor;
    private Funcionario responsavel;

    public Equipamento(int id, String marca, String modelo, String numeroSerie, LocalDate dataAquisicao, StatusEquipamento status, Setor setor, Funcionario responsavel) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
        this.dataAquisicao = dataAquisicao;
        this.status = status;
        this.setor = setor;
        this.responsavel = responsavel;
    }

    public int getId() { return id; }

    public String getMarca() { return marca; }

    public String getModelo() { return modelo; }

    public String getNumeroSerie() { return numeroSerie; }

    public LocalDate getDataAquisicao() { return dataAquisicao; }

    public StatusEquipamento getStatus() { return status; }

    public Setor getSetor() { return setor; }

    public Funcionario getResponsavel() { return responsavel; }
}
