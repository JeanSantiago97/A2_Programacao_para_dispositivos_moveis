package com.example.calculadora;

import android.widget.Toast;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import java.math.BigInteger;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class CalculosdeBase {

    private boolean bigIntegerTryParse(String s) {
        try {
            new BigInteger(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String revertString(String s) {
        String string = "";
        char[] array = s.toCharArray();
        for (int i = array.length-1; i >= 0; i--)
            string += String.valueOf(array[i]);
        return string;
    }

    private boolean checkBase(String valor, int base) {
        int num;
        for (int i = 0; i < valor.length(); i++) {
            char c = valor.charAt(i);
            if (c >= 48 && c <= 57)
                num = Integer.parseInt(String.valueOf(c));
            else if (c >= 65 && c <= 86)
                num = c - 55;
            else if (c >= 97 && c <= 118)
                num = c - 87;
            else
                return false;

            if (num >= base)
                return false;
        }
        return true;
    }

    private char numParaValor(int num) {
        char valor;
        if (num >= 10 && num <= 31)
            valor = (char)(num + 55);
        else
            valor = (char)(num + 48);
        return valor;
    }

    private int valorParaNum(char valor) {
        int num;
        if (valor >= 65 && valor <= 86)
            num = valor - 55;
        else if (valor >= 97 && valor <= 118)
            num = valor - 87;
        else
            num = Integer.parseInt(String.valueOf(valor));
        return num;
    }

    //Converte valor para decimal
    private String cvtValorParaDecimal(String valor, int base)
            throws Exception {
        if (valor.length() <= 0)
            throw new Exception("Digite o valor.");
        if (valor.contains(".,"))
            throw new Exception("Utilize apenas valores inteiros.");
        if (!checkBase(valor, base))
            throw new Exception("Valor errado para a base escolhida.");

        int i, exp = 0;
        BigInteger num = BigInteger.ZERO;
        BigInteger auxN, auxB;
        for (i = 0; i < valor.length(); i++) {
            exp = (valor.length() - (1 + i));
            auxN = BigInteger.valueOf(valorParaNum(valor.charAt(i)));
            auxB = BigInteger.valueOf(base);
            auxN = auxN.multiply(auxB.pow(exp));
            num = num.add(auxN);
        }
        if (!bigIntegerTryParse(num.toString()))
            throw new Exception("O valor a ser convertido é muito alto.");
        return num.toString();
    }

    //Converte decimal para valor
    private String cvtDecimalParaValor(String valor, int base)
            throws Exception {
        if (valor.length() <= 0)
            throw new Exception("Digite o valor.");
        if (valor.contains(".,"))
            throw new Exception("Utilize apenas valores inteiros.");
        if (!checkBase(valor, 10))
            throw new Exception("Valor errado para a base escolhida.");
        BigInteger resto, num, auxB;
        num = new BigInteger(valor);
        valor = "";
        while (num.compareTo(BigInteger.ZERO) == 1) {
            auxB = BigInteger.valueOf(base);
            resto = num.mod(auxB);
            valor += numParaValor(Integer.parseInt(resto.toString()));
            num = num.divide(auxB);
        }
        return revertString(valor);
    }

    public String converterValor(String valor, int daBase, int paraBase)
            throws Exception {
        try {
            return cvtDecimalParaValor(cvtValorParaDecimal(valor, daBase), paraBase);
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
