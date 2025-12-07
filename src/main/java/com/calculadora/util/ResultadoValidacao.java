package com.calculadora.util;

public class ResultadoValidacao {
    private boolean valido;
    private String mensagem;

    public ResultadoValidacao(boolean valido, String mensagem) {
        this.valido = valido;
        this.mensagem = mensagem;
    }

    public boolean isValido() { return valido; }
    public String getMensagem() { return mensagem; }
}
