public class MotorCalculo {

    private final LLMCalculadora llm;

    public MotorCalculo(LLMCalculadora llm) {
        this.llm = llm;
    }

    /**
     * Cálculo automático:
     * - tenta cálculo local
     * - se não reconhecer, usa LLM
     */
    public String calcular(String input) throws Exception {

        input = input.trim();


        if (input.matches("-?\\d+(\\.\\d+)?\\s*\\+\\s*-?\\d+(\\.\\d+)?")) {
            String[] p = input.split("\\+");
            return String.valueOf(
                    Calculadora.ad(
                            Double.parseDouble(p[0].trim()),
                            Double.parseDouble(p[1].trim())
                    )
            );
        }


        if (input.matches("-?\\d+(\\.\\d+)?\\s*-\\s*-?\\d+(\\.\\d+)?")) {
            String[] p = input.split("-");
            return String.valueOf(
                    Calculadora.sub(
                            Double.parseDouble(p[0].trim()),
                            Double.parseDouble(p[1].trim())
                    )
            );
        }


        if (input.matches("-?\\d+(\\.\\d+)?\\s*\\*\\s*-?\\d+(\\.\\d+)?")) {
            String[] p = input.split("\\*");
            return String.valueOf(
                    Calculadora.mul(
                            Double.parseDouble(p[0].trim()),
                            Double.parseDouble(p[1].trim())
                    )
            );
        }


        if (input.matches("-?\\d+(\\.\\d+)?\\s*/\\s*-?\\d+(\\.\\d+)?")) {
            String[] p = input.split("/");
            return String.valueOf(
                    Calculadora.div(
                            Double.parseDouble(p[0].trim()),
                            Double.parseDouble(p[1].trim())
                    )
            );
        }


        if (input.matches("-?\\d+(\\.\\d+)?\\^\\s*-?\\d+(\\.\\d+)?")) {
            String[] p = input.split("\\^");
            return String.valueOf(
                    Calculadora.pot(
                            Double.parseDouble(p[0].trim()),
                            Double.parseDouble(p[1].trim())
                    )
            );
        }


        if (input.matches("raiz\\s*\\(.*\\)")) {
            String valores = input.substring(
                    input.indexOf('(') + 1,
                    input.lastIndexOf(')')
            );

            String[] p = valores.split(",");

            if (p.length == 2) {
                return String.valueOf(
                        Calculadora.raiz(
                                Double.parseDouble(p[0].trim()),
                                Double.parseDouble(p[1].trim())
                        )
                );
            }
        }

        // Caso não reconheça → texto livre
        return calcularPorExtenso(input);
    }

    /**
     * Força o uso do LLM para texto em linguagem natural.
     */
    public String calcularPorExtenso(String texto) throws Exception {
        return llm.askForCalculation(texto);
    }
}
