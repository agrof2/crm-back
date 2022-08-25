package com.crm.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocalizarTextos {

    /**
 * Números e Texto sem espaçamento entre eles
 */
    public static final String NUMEROS_E_TEXTOS_SEM_ESPACO_OU_TABULACOES = "([a-zA-Z0-9])";

    /**
     * Números e textos
     */
    public static final String NUMEROS_E_TEXTOS_COM_ESPACO_OU_TABULACOES = "([a-zA-Z0-9 \t])";

    /**
     * Apenas texto sem espaçamento
     */
    public static final String APENAS_TEXTOS_SEM_ESPACO_OU_TABULACOES = "([a-zA-Z])";

    /**
     * Apenas textos
     */
    public static final String APENAS_TEXTOS_COM_ESPACO_OU_TABULACOES = "([a-zA-Z \t])";

    /**
     * Números e textos com caracteres
     */
    public static final String NUMEROS_E_TEXTOS_COM_CARACTERES_COM_ESPACO_OU_TABULACOES = "([a-zA-Z0-9\\W\t])";

    /**
     * Números e textos com caracteres sem espaco
     */
    public static final String NUMEROS_E_TEXTOS_COM_CARACTERES_SEM_ESPACO_OU_TABULACOES = "([a-zA-Z0-9\\W])";

    /**
     * Números com caracteres
     */
    public static final String NUMEROS_COM_CARACTERES_COM_ESPACO_OU_TABULACOES = "([0-9\\W\t])";

    /**
     * Números com caracteres
     */
    public static final String NUMEROS_COM_CARACTERES_SEM_ESPACO_OU_TABULACOES = "([0-9\\-])";

    /**
     * Apenas textos com caracteres
     */
    public static final String APENAS_TEXTOS_COM_CARACTERES_COM_ESPACO_OU_TABULACOES = "([a-zA-Z\\W\t])";

    /**
     * Apenas Números inteiros
     */
    public static final String APENAS_NUMEROS_SEM_ESPACO_OU_TABULACOES = "([0-9])";

    /**
     * Apenas números decimais
     */
    public static final String NUMEROS_DECIMAIS_SEM_ESPACO_OU_TABULACOES = "([0-9 . , -])";

    /**
     * todo tipo de caracter
     */
    public static final String TUDO = "(.)";

    /**
     * Apenas caracteres especiais
     */
    public static final String APENAS_PONTUACAO = "([\\W])";

    public static String extractText(String texto, String tipoLocalizacao) {
        try {
            Pattern modeloExtracao = Pattern.compile(tipoLocalizacao, Pattern.DOTALL);
            Matcher matcher = modeloExtracao.matcher((texto == null ? "" : texto));
            String sReturn = "";
            while (matcher.find()) {
                sReturn += matcher.group(1);
            }
            return sReturn.replace("'", "''").replace("\\", "\\\\");
        } catch (Exception erro) {
            erro.printStackTrace();
            return null;
        }
    }



    public static boolean isEmail(String email) {
        if (LocalizarTextos.extractText(email, LocalizarTextos.NUMEROS_E_TEXTOS_SEM_ESPACO_OU_TABULACOES).isEmpty()) {
            return false;
        } else {
            String emailPattern = "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
            Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        }
    }

    public static String capitalize(String originalText) {
        if (originalText == null | (originalText != null && originalText.isEmpty())) {
            return "";
        }
        originalText = originalText.substring(0, 1).toUpperCase() + originalText.substring(1);
        String words[] = originalText.split("\\s");
        String capitalizeStr = "";

        for (String s : words) {
            if (s.length() < 1) {
                capitalizeStr += s;
                continue;
            } else if (s.length() < 3) {
                capitalizeStr += s.toLowerCase() + " ";
                continue;
            }
            String firstLetter = s.substring(0, 1);
            capitalizeStr += firstLetter.toUpperCase() + s.substring(1).toLowerCase() + " ";
        }

        return capitalizeStr;
    }
}
