package com.crm.Utils;

public class ValidarCPF {
    public static boolean isCPF(String cpf) {
        cpf = LocalizarTextos.extractText(cpf, LocalizarTextos.APENAS_NUMEROS_SEM_ESPACO_OU_TABULACOES);
        if (cpf.equalsIgnoreCase("00000000000") ^ cpf.equalsIgnoreCase("11111111111")
                ^ cpf.equalsIgnoreCase("22222222222") ^ cpf.equalsIgnoreCase("33333333333") ^ cpf.equalsIgnoreCase("44444444444")
                ^ cpf.equalsIgnoreCase("55555555555") ^ cpf.equalsIgnoreCase("66666666666") ^ cpf.equalsIgnoreCase("77777777777")
                ^ cpf.equalsIgnoreCase("88888888888") ^ cpf.equalsIgnoreCase("99999999999")) {
            return false;
        } else if (cpf.length() != 11) {
            //JOptionPane.showMessageDialog(null, "Quantidade de dígitos Inválido!", ("Aviso do Sistema"), JOptionPane.WARNING_MESSAGE);
            return false;
        }
        int digitos[] = new int[11];
        int i;
        for (i = 10; i >= 0; i--) {
            digitos[i] = (int) (cpf.charAt(i) - 48);
        }

        int soma = 0, peso = 2, mod = 0, dig10 = 0, dig11 = 0;
        for (i = 8; i >= 0; i--) {
            soma += digitos[i] * peso;
            peso++;
        }

        mod = soma % 11;
        if (mod < 2) {
            dig10 = 0;
        } else {
            dig10 = 11 - mod;
        }

        if (digitos[9] != dig10) {
            return false;
        }

        /**
         * calculo do segundo digito verificador
         */
        soma = 0;
        peso = 2;
        mod = 0;
        for (i = 9; i >= 0; i--) {
            soma += digitos[i] * peso;
            peso++;
        }

        mod = soma % 11;
        if (mod < 2) {
            dig11 = 0;
        } else {
            dig11 = 11 - mod;
        }

        if (digitos[10] == dig11) {
            return true;
        } else {
            return false;
        }
    }
}
