package com.calculadora.util;

public class ValidadorIMC {
    private static final double PESO_MINIMO = 1.0;
    private static final double PESO_MAXIMO = 500.0;
    private static final double ALTURA_MINIMA = 0.5;
    private static final double ALTURA_MAXIMA = 3.0;

    public static ResultadoValidacao valorVazio(String pesoText, String alturaText) {
        if (pesoText == null || pesoText.trim().isEmpty()) {
            return new ResultadoValidacao(false, "O campo peso não pode estar vazio.");
        }
        if (alturaText == null || alturaText.trim().isEmpty()) {
            return new ResultadoValidacao(false, "O campo altura não pode estar vazio.");
        }
        return new ResultadoValidacao(true, "OK");
    }
    public static ResultadoValidacao validar(double peso, double altura) {
        // Validação específica
        if (peso < PESO_MINIMO) {
            return new ResultadoValidacao(false, "Peso muito baixo!");
        }

        if (peso > PESO_MAXIMO) {
            return new ResultadoValidacao(false, "Peso parece incorreto. Verifique o valor.");
        }

        if (altura < ALTURA_MINIMA) {
            return new ResultadoValidacao(false, "Altura muito baixa! Use metros (ex: 1.75)");
        }

        if (altura > ALTURA_MAXIMA) {
            return new ResultadoValidacao(false, "Altura parece incorreta. Use metros.");
        }

        if (peso > 200 && altura < 1.5) {
            return new ResultadoValidacao(false, "Valores parecem inconsistentes. Verifique.");
        }
        return new ResultadoValidacao(true, "OK");
    }
}

