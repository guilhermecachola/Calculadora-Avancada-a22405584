## UML
<img width="1297" height="859" alt="image" src="https://github.com/user-attachments/assets/e3876f23-9819-4ec3-9e19-0cf0ed970ba2" />
 

## Explicação do modelo
- **Main**: Ponto de entrada; cria `LLMInteractionEngine`, `LLMCalculadora` e `Comandos`, faz arrancar tudo basicamente.
- **Comandos**: Interface de linha de comando. Gere o menu, o histórico que é apenas um arraylist, animações (neve, trenó, árvore), e invoca `Calculadora` e `LLMCalculadora`.
- **Calculadora**: Implementa operações determinísticas: `add`, `sub`, `mul`, `div`. Testes JUnit cobrem estes métodos.
- **LLMCalculadora**: Adapter que contém prompts e transforma pedidos em chamadas ao `LLMInteractionEngine`. Fornece: derivada, simplificação, resolução de equações.
- **LLMInteractionEngine**: Camada de comunicação HTTP com o servidor LLM (já fornecido).
- **JSONUtils**: Utilitários para parsing simples de JSON retornado pelo LLM.
- **Utils**: Utilitários de I/O para a CLI (`readCharFromKeyboard`, `readLine`).
- **historico**: Lista de `String` mantida dentro de `Comandos` .
