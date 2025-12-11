| Campo | Conteúdo |
|-------|----------|
| **Tema escolhido** | Calculadora Avançada |
| **Elementos do grupo** | Guilherme Carvalho a22405584 |
| **Descrição geral do projeto** | Uma aplicação de consola que funciona como uma calculadora avançada, capaz de realizar operações matemáticas base (soma, subtração, multiplicação, divisão) e operações avançadas, como derivadas simbólicas simples, simplificação de expressões e resolução de equações lineares. Parte da lógica “avançada” será delegada a um LLM. |
| **Funcionalidade Java (não-LLM)** | Implementação das operações matemáticas determinísticas (soma, subtração, multiplicação, divisão), parsing básico da expressão, gestão do histórico, validação de inputs, interface de consola, e testes JUnit para estas funcionalidades sem LLM. |
| **Funcionalidade delegada ao LLM** | Processamento e resolução de expressões matemáticas avançadas difíceis de implementar em Java: derivação simbólica, simplificação de expressões, explicação passo-a-passo e resolução de equações simples. O LLM devolve sempre resultados em JSON estrito. |
| **Classes principais previstas** | Main, Calculadora(java a resolver funções simples), LLMCalculadora(Complexa), Utils, JSONUtils |
| **Fluxo de dados (resumo)** | Utilizador → Parser → (decisão: operação simples em Java OU operação avançada no LLM) → Execução → Resultado formatado → Histórico |
| **Prompts de integração esperadas** | **Prompt 1 — Derivação:** “Age como um assistente matemático. Dá a derivada simbólica da expressão fornecida. Responde EXCLUSIVAMENTE em JSON: `{ \"operation\": \"derive\", \"input\": \"...\", \"result\": \"...\" }`. Expressão: {{expressão}}.” <br> **Prompt 2 — Simplificação:** “Simplifica a expressão dada. Responde apenas em JSON com: `operation`, `input`, `result`, `steps` (lista). Expressão: {{expressão}}.” <br> **Prompt 3 — Resolver equação:** “Resolve a equação fornecida e devolve JSON: `{ \"solution\": \"...\", \"steps\": [...] }`. Não incluas texto fora do JSON.” |
| **Limitações conhecidas** | Dependência da API para funcionalidades avançadas; ambiguidades em expressões mal definidas; necessidade de validação de JSON; custos de chamadas ao LLM. |
