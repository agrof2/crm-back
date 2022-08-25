package com.crm.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatasEHoras {

    public static LocalDate parseDate(String date, String format) {
        if (!isDataValida(date, format)) {
            return null;
        }
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
    }

    public static final boolean isDataValida(String data, String pattern) {
        try {
            if (data == null | (LocalizarTextos.extractText(data, LocalizarTextos.APENAS_NUMEROS_SEM_ESPACO_OU_TABULACOES).length()) != 8) {
                return false;
            }
            data = LocalizarTextos.extractText(data, LocalizarTextos.APENAS_NUMEROS_SEM_ESPACO_OU_TABULACOES);
            SimpleDateFormat sdf = new SimpleDateFormat(LocalizarTextos.extractText(pattern, LocalizarTextos.APENAS_TEXTOS_SEM_ESPACO_OU_TABULACOES));
            sdf.setLenient(false);
            sdf.parse(data);
            return true;
        } catch (ParseException erro) {
            erro.printStackTrace();
            return false;
        }
    }
}
