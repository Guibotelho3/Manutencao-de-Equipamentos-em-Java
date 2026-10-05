package estoque;

import java.math.BigDecimal;

public class Peca {
    private Integer id;
    private String nome;
    private String codigoFabricante;
    private String compatibilidade;
    private int quantidadeEstoque;
    private int estoqueMinimo;
    private BigDecimal precoUnitario;

    public Peca(Integer id, String nome, String codigoFabricante, String compatibilidade,
                int quantidadeEstoque, int estoqueMinimo, BigDecimal precoUnitario) {
        this.id = id;
        this.nome = nome;
        this.codigoFabricante = codigoFabricante;
        this.compatibilidade = compatibilidade;
        this.quantidadeEstoque = quantidadeEstoque;
        this.estoqueMinimo = estoqueMinimo;
        this.precoUnitario = precoUnitario;
    }

    public boolean estoqueCritico() {
        return quantidadeEstoque <= estoqueMinimo;
    }

    public Integer getId() { return id; }

    public String getNome() { return nome; }

    public String getCodigoFabricante() { return codigoFabricante; }

    public String getCompatibilidade() { return compatibilidade; }

    public int getQuantidadeEstoque() { return quantidadeEstoque; }

    public int getEstoqueMinimo() { return estoqueMinimo; }

    public BigDecimal getPrecoUnitario() { return precoUnitario; }
}
