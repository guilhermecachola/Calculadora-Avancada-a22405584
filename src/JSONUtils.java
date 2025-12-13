/**
 * Classe utilitária para operações simples relacionadas com JSON.
 * <p>
 * Fornece métodos básicos para formatação de strings JSON e extração
 * ingênua de valores associados a chaves específicas.
 * <p>
 * Nota: os métodos desta classe não implementam um parser JSON completo
 * e podem falhar em casos mais complexos.
 */
public class JSONUtils {

    /**
     * Formata rapidamente uma string JSON, adicionando indentação e quebras
     * de linha para melhorar a legibilidade.
     * <p>
     * Este método não valida o JSON e assume que a string de entrada
     * já se encontra num formato estruturalmente correto.
     *
     * @param json String JSON a formatar
     * @return String JSON formatada com indentação
     */
    static String quickJSONFormater(String json) {
        StringBuilder out = new StringBuilder();
        boolean inStr = false, esc = false;
        int indent = 0;
        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);
            if (esc) { out.append(c); esc = false; continue; }
            if (c == '\\') { out.append(c); esc = true; continue; }
            if (c == '\"') { inStr = !inStr; out.append(c); continue; }
            if (inStr) { out.append(c); continue; }

            switch (c) {
                case '{': case '[':
                    out.append(c).append('\n').append("  ".repeat(++indent));
                    break;
                case '}': case ']':
                    out.append('\n').append("  ".repeat(--indent)).append(c);
                    break;
                case ',':
                    out.append(c).append('\n').append("  ".repeat(indent));
                    break;
                case ':':
                    out.append(": ");
                    break;
                default:
                    if (!Character.isWhitespace(c)) out.append(c);
            }
        }
        return out.toString();

    }

    /**
     * Extrai de forma muito simples o valor associado a uma chave num JSON.
     * <p>
     * Procura ocorrências do padrão {@code "key": "value"} e devolve apenas
     * o conteúdo textual do valor.
     * <p>
     * Limitações conhecidas:
     * <ul>
     *   <li>Não funciona corretamente se existirem múltiplos campos com a mesma chave</li>
     *   <li>Não suporta estruturas JSON aninhadas complexas</li>
     *   <li>O tratamento de caracteres escapados é mínimo</li>
     * </ul>
     *
     * @param json String JSON onde procurar
     * @param key Chave cujo valor deve ser extraído
     * @return Valor associado à chave ou {@code null} se não for encontrado
     */
    static String getJsonString(String json, String key) {
        String pattern = "\"" + key + "\"";
        int keyPos = json.indexOf(pattern);
        if (keyPos < 0) return null;

        int colonPos = json.indexOf(':', keyPos + pattern.length());
        if (colonPos < 0) return null;

        // find opening quote of the value
        int firstQuote = json.indexOf('"', colonPos + 1);
        if (firstQuote < 0) return null;

        // find closing quote (very naive, minimal escaping handling)
        int secondQuote = json.indexOf('"', firstQuote + 1);
        while (secondQuote > 0 && json.charAt(secondQuote - 1) == '\\') {
            secondQuote = json.indexOf('"', secondQuote + 1);
        }
        if (secondQuote < 0) return null;

        return json.substring(firstQuote + 1, secondQuote);
    }

}
