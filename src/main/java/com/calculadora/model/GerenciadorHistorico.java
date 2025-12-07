package com.calculadora.model;

import com.calculadora.service.HistoricoService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorHistorico {
    private final ObservableList<RegistroIMC> historico = FXCollections.observableArrayList();
    private final int LIMITE_HISTORICO = 20;

    public GerenciadorHistorico() {
        carregarHistorico();
    }

    public void adicionarRegistro(RegistroIMC registro) {
        historico.add(0, registro);

        if (historico.size() > LIMITE_HISTORICO) {
            historico.remove(historico.size() - 1); // Remove o mais antigo
        }

        salvarHistorico();
    }

    public ObservableList<RegistroIMC> getHistorico() {
        return historico;
    }

    public void limparHistorico() {
        historico.clear();
        salvarHistorico();
    }

    private void carregarHistorico() {
        List<RegistroIMC> registrosCarregados = HistoricoService.carregar();
        historico.addAll(registrosCarregados);
    }

    private void salvarHistorico() {
        List<RegistroIMC> lista = new ArrayList<>(historico);
        HistoricoService.salvar(lista);
    }

    public void exportarCSV(String nomeArquivo) {
        List<RegistroIMC> lista = new ArrayList<>(historico);
        HistoricoService.exportarCSV(lista, nomeArquivo);
    }

    public List<RegistroIMC> buscarPorClassificacao(String classificacao) {
        List<RegistroIMC> resultado = new ArrayList<>();
        for (RegistroIMC registro : historico) {
            if (registro.getClassificacao().equalsIgnoreCase(classificacao)) {
                resultado.add(registro);
            }
        }
        return resultado;
    }

    public double calcularIMCMedio() {
        if (historico.isEmpty()) return 0;

        double soma = 0;
        for (RegistroIMC registro : historico) {
            soma += registro.getImc();
        }
        return soma / historico.size();
    }

    public double calcularPesoMedio() {
        if (historico.isEmpty()) return 0;

        double soma = 0;
        for (RegistroIMC registro : historico) {
            soma += registro.getPeso();
        }
        return soma / historico.size();
    }
}