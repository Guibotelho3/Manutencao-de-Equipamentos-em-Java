package chamados;

import equipamentos.Equipamento;
import funcionarios.Funcionario;
import funcionarios.Tecnico;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Chamado {
    private int id;
    private String descricaoProblema;
    private LocalDateTime abertura;
    private LocalDateTime fechamento;
    private StatusChamado status;
    private Prioridade prioridade;
    private Equipamento equipamento;
    private Tecnico tecnico;
    private Funcionario solicitante;
    private List<ItemPeca> pecasUsadas;
    private BigDecimal horasTrabalhadas;
    private String solucao;

    public Chamado(int id, String descricaoProblema, Prioridade prioridade,
                   Equipamento equipamento, Funcionario solicitante) {
        this.id = id;
        this.descricaoProblema = descricaoProblema;
        this.prioridade = prioridade;
        this.equipamento = equipamento;
        this.solicitante = solicitante;
        this.abertura = LocalDateTime.now();
        this.status = StatusChamado.ABERTO;
        this.pecasUsadas = new ArrayList<>();
        this.horasTrabalhadas = BigDecimal.ZERO;
    }

    public void atribuirTecnico(Tecnico tecnico) {
        exigirStatus(StatusChamado.ABERTO);

        if (tecnico == null) {
            throw new IllegalArgumentException("O técnico não pode ser nulo.");
        }

        this.tecnico = tecnico;
    }

    public void iniciar() {
        if (status != StatusChamado.ABERTO && status != StatusChamado.AGUARDANDO_PECA) {
            throw new IllegalStateException(
                    "Só é possível iniciar um chamado aberto ou aguardando peça.");
        }

        if (tecnico == null) {
            throw new IllegalStateException("Atribua um técnico antes de iniciar o chamado.");
        }

        status = StatusChamado.EM_ANDAMENTO;
    }

    public void aguardarPeca() {
        exigirStatus(StatusChamado.EM_ANDAMENTO);
        status = StatusChamado.AGUARDANDO_PECA;
    }

    public void adicionarPeca(ItemPeca itemPeca) {
        exigirStatus(StatusChamado.EM_ANDAMENTO);

        if (itemPeca == null) {
            throw new IllegalArgumentException("O item de peça não pode ser nulo.");
        }

        pecasUsadas.add(itemPeca);
    }

    public void concluir(String solucao, BigDecimal horas) {
        exigirStatus(StatusChamado.EM_ANDAMENTO);

        if (solucao == null || solucao.isBlank()) {
            throw new IllegalArgumentException("Informe a solução do chamado.");
        }

        if (horas == null || horas.signum() < 0) {
            throw new IllegalArgumentException("As horas devem ser iguais ou maiores que zero.");
        }

        this.horasTrabalhadas = horas;
        this.solucao = solucao;
        this.fechamento = LocalDateTime.now();
        this.status = StatusChamado.CONCLUIDO;
    }

    public void cancelar() {
        if (status == StatusChamado.CONCLUIDO || status == StatusChamado.CANCELADO) {
            throw new IllegalStateException("O chamado já está encerrado.");
        }

        this.fechamento = LocalDateTime.now();
        this.status = StatusChamado.CANCELADO;
    }

    public BigDecimal calcularCusto() {
        if (tecnico == null) {
            throw new IllegalStateException(
                    "Atribua um técnico antes de calcular o custo.");
        }

        BigDecimal custoPecas = pecasUsadas.stream()
                .map(ItemPeca::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal custoMaoDeObra = tecnico.getValorHora().multiply(horasTrabalhadas);

        return custoPecas.add(custoMaoDeObra);
    }

    private void exigirStatus(StatusChamado esperado) {
        if (status != esperado) {
            throw new IllegalStateException(
                    "Operação inválida: chamado está " + status + ", esperado " + esperado + ".");
        }
    }

    public int getId() { return id; }

    public String getDescricaoProblema() { return descricaoProblema; }

    public LocalDateTime getAbertura() { return abertura; }

    public LocalDateTime getFechamento() { return fechamento; }

    public StatusChamado getStatus() { return status; }

    public Prioridade getPrioridade() { return prioridade; }

    public Equipamento getEquipamento() { return equipamento; }

    public Tecnico getTecnico() { return tecnico; }

    public Funcionario getSolicitante() { return solicitante; }

    public List<ItemPeca> getPecasUsadas() { return Collections.unmodifiableList(pecasUsadas); }

    public BigDecimal getHorasTrabalhadas() { return horasTrabalhadas; }

    public String getSolucao() { return solucao; }
}
