package com.calculadora.util;

import javafx.scene.control.Label;
import java.util.Locale;

public final class MetaUtils {
    private MetaUtils() {}

    public static String formatMeta(double peso, Double pesoAtual, Locale locale) {
        if (pesoAtual == null) return "";
        double diff = peso - pesoAtual;
        if (Math.abs(diff) < 0.001) {
            return "Meta: Alcançada";
        } else if (diff > 0) {
            return String.format(locale, "Perder %.1f kg", diff);
        } else {
            return String.format(locale, "Ganhar %.1f kg", Math.abs(diff));
        }
    }

    public static void atualizarLabel(Label lblMeta, double peso, Double pesoAtual, Locale locale) {
        if (lblMeta == null) return;
        lblMeta.setText(formatMeta(peso, pesoAtual, locale));
    }
}