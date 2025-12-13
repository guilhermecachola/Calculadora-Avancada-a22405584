## UML
<img width="1153" height="906" alt="UML" src="https://github.com/user-attachments/assets/f692326e-96ae-47c7-a89b-9a3f9b1f5b32" />

## Explicação do modelo
- **Main**: Ponto de entrada; cria `LLMInteractionEngine`, `LLMCalculadora` e `Comandos`, faz arrancar tudo basicamente.
- **Comandos**: Interface de linha de comando. Gere o menu, o histórico que é apenas um arraylist, animações (neve, trenó, árvore), e invoca `Calculadora` e `LLMCalculadora`.
- **Calculadora**: Implementa operações determinísticas: `add`, `sub`, `mul`, `div`. Testes JUnit cobrem estes métodos.
- **LLMCalculadora**: Adapter que contém prompts e transforma pedidos em chamadas ao `LLMInteractionEngine`. Fornece: derivada, simplificação, resolução de equações.
- **LLMInteractionEngine**: Camada de comunicação HTTP com o servidor LLM (já fornecido).
- **JSONUtils**: Utilitários para parsing simples de JSON retornado pelo LLM.
- **Utils**: Utilitários de I/O para a CLI (`readCharFromKeyboard`, `readLine`).
- **historico**: Lista de `String` mantida dentro de `Comandos` .
