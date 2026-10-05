# Manutenção de Equipamentos

Sistema de chamados de manutenção de equipamentos corporativos desenvolvido em Java.

Um chamado é aberto por um funcionário informando o equipamento com problema e a prioridade. Um técnico é atribuído, o atendimento é iniciado, peças e horas trabalhadas são registradas e ao final o técnico descreve a solução. O custo total é calculado automaticamente com base nas peças e na hora do técnico.

```
ABERTO → atribuirTecnico → iniciar → EM_ANDAMENTO → aguardarPeca → AGUARDANDO_PECA
                                           ↓                               ↓
                                       concluir                         iniciar
                                           ↓                               ↓
                                       CONCLUIDO                     EM_ANDAMENTO
                                       
Em qualquer status antes de concluído: cancelar → CANCELADO
```
