package chamados;

import java.math.BigDecimal;
import estoque.Peca;

public class ItemPeca {
    private Peca peca;
    private int quantidade;
    private BigDecimal precoNoMomento;

    public ItemPeca(Peca peca, int quantidade, BigDecimal precoNoMomento) {
        if (peca == null) {
            throw new IllegalArgumentException("A peça não pode ser nula.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        
        this.peca = peca;
        this.quantidade = quantidade;
        this.precoNoMomento = precoNoMomento;
    }

    public BigDecimal subtotal() {
        return precoNoMomento.multiply(BigDecimal.valueOf(quantidade));
    }

    public Peca getPeca() { return peca; }
    public int getQuantidade() { return quantidade; }
    public BigDecimal getPrecoNoMomento() { return precoNoMomento; }
}