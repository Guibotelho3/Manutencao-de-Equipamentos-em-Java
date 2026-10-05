import chamados.Chamado;
import chamados.ItemPeca;
import chamados.Prioridade;
import chamados.StatusChamado;
import empresa.Setor;
import equipamentos.Computador;
import equipamentos.StatusEquipamento;
import estoque.Peca;
import funcionarios.Supervisor;
import funcionarios.Tecnico;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Setor ti = new Setor("TI", "2º andar");

        Supervisor solicitante = new Supervisor(1, "Carlos Mendes", "carlos@empresa.com",
                "11999990001", ti, "Infraestrutura", new BigDecimal("80.00"));

        Tecnico tecnico = new Tecnico(2, "Ana Lima", "ana@empresa.com",
                "11999990002", ti, "Hardware", new BigDecimal("60.00"));

        Computador pc = new Computador(1, "Dell", "OptiPlex 7010", "SN-001",
                LocalDate.of(2022, 3, 15), StatusEquipamento.ATIVO, ti, solicitante,
                "Intel Core i5", 16);

        Peca memoria = new Peca(1, "Memória RAM DDR4 8GB", "SAM-DDR4-8",
                "Dell OptiPlex 7010", 5, 2, new BigDecimal("150.00"));

        // Abrir chamado
        System.out.println("Equipamento: " + pc.getMarca() + " " + pc.getModelo()
                + " | Setor: " + pc.getSetor().getNome());
        System.out.print("Descreva o problema: ");
        String descricao = sc.nextLine();

        System.out.println("Prioridades disponíveis: BAIXA, MEDIA, ALTA, CRITICA");
        Prioridade prioridade = null;
        while (prioridade == null) {
            System.out.print("Prioridade: ");
            try {
                prioridade = Prioridade.valueOf(sc.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Prioridade inválida. Digite uma das opções acima.");
            }
        }

        Chamado chamado = new Chamado(1, descricao, prioridade, pc, solicitante);
        System.out.println("Chamado #" + chamado.getId() + " aberto. Status: " + chamado.getStatus());

        // Atribuir técnico
        System.out.println("\nTécnico disponível: [1] " + tecnico.getNome()
                + " - " + tecnico.getAreaDeAtuacao());
        int escolha = 0;
        while (escolha != 1) {
            System.out.print("Informe o número do técnico para atribuir: ");
            try {
                escolha = Integer.parseInt(sc.nextLine());
                if (escolha != 1) System.out.println("Opção inválida. Digite 1.");
            } catch (NumberFormatException e) {
                System.out.println("Digite apenas o número da opção.");
            }
        }

        if (escolha == 1) {
            chamado.atribuirTecnico(tecnico);
            System.out.println("Técnico " + tecnico.getNome() + " atribuído.");
        }

        // Iniciar
        System.out.print("\nIniciar atendimento? (s/n): ");
        if (sc.nextLine().equalsIgnoreCase("s")) {
            chamado.iniciar();
            System.out.println("Status: " + chamado.getStatus());
        }

        // Aguardar peça
        System.out.print("\nPrecisa aguardar alguma peça? (s/n): ");
        if (sc.nextLine().equalsIgnoreCase("s")) {
            chamado.aguardarPeca();
            System.out.println("Status: " + chamado.getStatus());

            System.out.println("\nPeça disponível em estoque: [1] " + memoria.getNome()
                    + " | R$ " + memoria.getPrecoUnitario()
                    + " | Estoque: " + memoria.getQuantidadeEstoque());
            System.out.print("Peça chegou. Retomar atendimento? (s/n): ");
            if (sc.nextLine().equalsIgnoreCase("s")) {
                chamado.iniciar();
                System.out.println("Status: " + chamado.getStatus());
            }
        }

        // Adicionar peça
        System.out.print("\nAdicionar peça utilizada? (s/n): ");
        if (sc.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Quantidade de \"" + memoria.getNome() + "\": ");
            int qtd = 0;
            while (qtd <= 0) {
                try {
                    qtd = Integer.parseInt(sc.nextLine());
                    if (qtd <= 0) System.out.println("Digite uma quantidade maior que zero.");
                } catch (NumberFormatException e) {
                    System.out.println("Digite apenas um número inteiro.");
                    System.out.print("Quantidade de \"" + memoria.getNome() + "\": ");
                }
            }
            chamado.adicionarPeca(new ItemPeca(memoria, qtd, memoria.getPrecoUnitario()));
            System.out.println(qtd + "x peça adicionada.");
        }

        // Concluir
        System.out.print("\nConcluir chamado? (s/n): ");
        if (sc.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Descreva a solução aplicada: ");
            String solucao = sc.nextLine();

            System.out.print("Horas trabalhadas: ");
            BigDecimal horas = null;
            while (horas == null) {
                try {
                    horas = new BigDecimal(sc.nextLine());
                    if (horas.signum() < 0) {
                        System.out.println("As horas não podem ser negativas.");
                        horas = null;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Digite um número válido (ex: 2.5).");
                    System.out.print("Horas trabalhadas: ");
                }
            }

            chamado.concluir(solucao, horas);
            System.out.println("Chamado concluído. Status: " + chamado.getStatus());
            System.out.println("Custo total: R$ " + chamado.calcularCusto());
        }

        // Tentar cancelar chamado já concluído
        System.out.print("\nTentar cancelar o chamado? (s/n): ");
        if (sc.nextLine().equalsIgnoreCase("s")) {
            try {
                chamado.cancelar();
            } catch (IllegalStateException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        sc.close();
    }
}
