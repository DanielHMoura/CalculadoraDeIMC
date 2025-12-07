package com.calculadora.model;

public class CalculoIMC {
    private double peso;
    private double altura;

    public CalculoIMC(double peso, double altura) {
        if (peso <= 0 || altura <= 0) {
            throw new IllegalArgumentException("Peso e Altura devem ser valores positivos.");
        }
        this.peso = peso;
        this.altura = altura;
    }

    public double calcularIMC(){
        double imc = peso / (altura * altura);
        return imc;
    }

    public String classificar() {
        double imc = this.calcularIMC();

        if (imc < 18.5) {
            return "Abaixo do peso";
        } else if (imc <= 25.0) {
            return "Peso normal";
        } else if (imc <= 30.0) {
            return "Sobrepeso";
        } else if (imc <= 35.0) {
            return "Obesidade grau I";
        } else if (imc <= 40.0) {
            return "Obesidade grau II";
        } else {
            return "Obesidade grau III";
        }
    }

    public static double calcularPesoMinimoSaudavel(double altura){
        return 18.5 * altura * altura;
    }

    public static double calcularPesoMaximoSaudavel(double altura){
        return 24.9 * altura * altura;
    }

    public static String metaPeso(double peso, double pesoAtual){
        double pesoA = peso - pesoAtual;
        String pesoFormatado = String.format("%.2f", pesoA);
        if(pesoA > peso){
            return ("Precisa perder " + pesoFormatado + "kg para atingir sua meta");
        } if(pesoA < peso){
                String z = "Precisa perder " + pesoFormatado + "kg para atingir sua meta";
        return z;
        } else {
            return ("Você já atingiu sua meta de peso!");
        }}
    }
