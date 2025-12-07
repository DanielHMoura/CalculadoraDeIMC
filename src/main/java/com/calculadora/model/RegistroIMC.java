package com.calculadora.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;
import java.util.Locale;

public class RegistroIMC implements Serializable {
    private double peso;
    private double imc;
    private String classificacao;
    private LocalDateTime horario;
    private Double pesoAtual;

    public RegistroIMC(double peso, double imc, String classificacao, LocalDateTime horario, Double pesoAtual) {
        this.peso = peso;
        this.imc = imc;
        this.classificacao = classificacao;
        this.horario = horario;
        this.pesoAtual = pesoAtual;
    }

    public RegistroIMC(double peso, double imc, String classificacao, LocalTime horario) {
        this(peso, imc, classificacao, horario != null ? LocalDate.now().atTime(horario) : null, null);
    }

    public double getPeso() { return peso; }
    public double getImc() { return imc; }
    public String getClassificacao() { return classificacao; }
    public LocalDateTime getHorario() { return horario; }
    public Double getPesoAtual() { return pesoAtual; }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm");
        String dataHora = horario != null ? horario.format(dtf) : "-";
        String pesoStr = String.format(Locale.forLanguageTag("pt-BR"), "%.1f kg", peso);
        String metaStr = pesoAtual != null ? String.format(Locale.forLanguageTag("pt-BR"), "%.1f kg", pesoAtual) : "-";
        String imcStr = String.format(Locale.forLanguageTag("pt-BR"), "%.2f", imc);

        String classif = "-";
        if (classificacao != null && !classificacao.isBlank()) {
            String s = classificacao.trim().toLowerCase();
            classif = s.substring(0,1).toUpperCase() + s.substring(1);
        }

        return String.format(
                "%s%n Peso: %s | Meta: %s%n IMC: %s (%s)",
                dataHora, pesoStr, metaStr, imcStr, classif
        );
    }
}
