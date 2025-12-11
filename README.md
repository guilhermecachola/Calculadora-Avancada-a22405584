# Processamento-De-Imagem

Campo	Conteúdo
Tema escolhido	Calculadora Avançada
Elementos do grupo	(preencher com nomes + nº aluno)
Descrição geral do projeto	Uma aplicação de consola que funciona como uma calculadora avançada, capaz de realizar operações matemáticas base (soma, subtração, multiplicação, divisão) e operações avançadas, como derivadas simbólicas simples, simplificação de expressões e resolução de equações lineares. Parte da lógica “avançada” será delegada a um LLM.
Funcionalidade Java (não-LLM)	Implementação das operações matemáticas determinísticas (soma, subtração, multiplicação, divisão), parsing básico da expressão, gestão do histórico, validação de inputs, interface de consola, e testes JUnit para estas funcionalidades sem LLM.
Funcionalidade delegada ao LLM	Processamento e resolução de expressões matemáticas avançadas que não são triviais de implementar de raiz em Java: derivação simbólica, simplificação de expressões, explicação passo-a-passo de um cálculo, e resolução de equações simples. Esta funcionalidade recebe a expressão do utilizador e devolve um resultado em JSON estrito.
Classes principais previstas	Main, Calculator, AdvancedCalculatorLLMAdapter, ExpressionParser, HistoryManager, LLMService, ResultFormatter
Fluxo de dados (resumo)	Utilizador → Parser → (decisão: operação simples em Java OU operação avançada no LLM) → Execução → Resultado formatado → Histórico
Prompts de integração esperadas	Exemplo: Prompt 1 — Derivação: “Age como um assistente matemático. Dá a derivada simbólica da expressão fornecida. Responde EXCLUSIVAMENTE no formato JSON: { \"operation\": \"derive\", \"input\": \"...\", \"result\": \"...\" }. Expressão: {{expressão}}.” Prompt 2 — Simplificação: “Simplifica a expressão matemática dada. Responde apenas em JSON com os campos operation, input, result e steps (lista). Expressão: {{expressão}}.” Prompt 3 — Resolução de equações: “Resolve a equação fornecida e devolve a solução em JSON: { \"solution\": \"...\", \"steps\": [...] }. Não incluas texto fora do JSON.”
Limitações conhecidas	Dependência da API para operações avançadas; possíveis ambiguidades matemáticas em expressões mal definidas; necessidade de validação rigorosa do JSON; custos de chamadas ao LLM.

