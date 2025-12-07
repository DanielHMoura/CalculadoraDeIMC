package com.calculadora.controller;

import com.calculadora.model.CalculoIMC;
import com.calculadora.model.GerenciadorHistorico;
import com.calculadora.model.RegistroIMC;
import com.calculadora.util.ResultadoValidacao;
import com.calculadora.util.ValidadorIMC;
import com.calculadora.util.MetaUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.Locale;
import java.io.File;

public class CalculadoraController {
    @FXML
    private TextField txtPeso;

    @FXML
    private TextField txtAltura;

    @FXML
    private TextField txtPesoAtual;

    @FXML
    private Label lblResultado;

    @FXML
    private Label lblClassificacao;

    @FXML
    private Label lblErro;

    @FXML
    private Label lblPesoIdeal;

    @FXML
    private Label lblMeta;

    @FXML
    private Label lblEstatisticas;

    @FXML
    private ListView<RegistroIMC> listViewHistorico;

    private GerenciadorHistorico gerenciadorHistorico = new GerenciadorHistorico();

    @FXML
    public void initialize() {
        listViewHistorico.setItems(gerenciadorHistorico.getHistorico());
        atualizarEstatisticas();

        adicionarFiltroNumerico(txtPeso);
        adicionarFiltroNumerico(txtAltura);
        adicionarFiltroNumerico(txtPesoAtual);
    }

    private void adicionarFiltroNumerico(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[0-9.,]*")) {
                textField.setText(newValue.replaceAll("[^0-9.,]", ""));
            }
        });
    }

    @FXML
    public void calcularIMC() {

        String txpeso = txtPeso.getText().replace(",",".");
        String txaltura = txtAltura.getText().replace(",",".");
        String pesoAtual = txtPesoAtual.getText().replace(",", ".").trim();

        ResultadoValidacao resultadoVazio = ValidadorIMC.valorVazio(txpeso, txaltura);
        if(!resultadoVazio.isValido()){
            lblErro.setText("Erro: " + resultadoVazio.getMensagem());
            lblResultado.setText("");
            lblClassificacao.setText("");
            lblPesoIdeal.setText("");
            txtPesoAtual.setText("");
            lblMeta.setText("");
            return;
        }

        double peso = Double.parseDouble(txpeso);
        double altura = Double.parseDouble(txaltura);

        ResultadoValidacao resultadoValidacao = ValidadorIMC.validar(peso, altura);
        if(!resultadoValidacao.isValido()){
            lblErro.setText("Erro: " + resultadoValidacao.getMensagem());
            lblResultado.setText("");
            lblClassificacao.setText("");
            lblPesoIdeal.setText("");
            txtPesoAtual.setText("");
            lblMeta.setText("");
            return;
        }

        CalculoIMC calculo = new CalculoIMC(peso, altura);
        double imc = calculo.calcularIMC();
        String classificacao = calculo.classificar();
        double pesoMinimo = CalculoIMC.calcularPesoMinimoSaudavel(altura);
        double pesoMaximo = CalculoIMC.calcularPesoMaximoSaudavel(altura);
        LocalDateTime horario = LocalDateTime.now();

        lblErro.setText("");
        lblResultado.setText(String.format("%.2f", imc));
        lblClassificacao.setText(classificacao);
        lblPesoIdeal.setText(String.format("%.2f kg - %.2f kg", pesoMinimo, pesoMaximo));

        Double pesoAtualObj = null;
        if (!pesoAtual.isBlank()) {
            try {
                pesoAtualObj = Double.valueOf(pesoAtual);
            } catch (NumberFormatException e) {
                pesoAtualObj = null;
            }
        }

        MetaUtils.atualizarLabel(lblMeta, peso, pesoAtualObj, new Locale("pt", "BR"));
        gerenciadorHistorico.adicionarRegistro(new RegistroIMC(peso, imc, classificacao, horario, pesoAtualObj));
        listViewHistorico.setItems(gerenciadorHistorico.getHistorico());
        atualizarEstatisticas();
    }

    @FXML
    public void limparHistorico() {
        gerenciadorHistorico.limparHistorico();
        atualizarEstatisticas();
        mostrarAlerta("Histórico limpo com sucesso!");
    }

    @FXML
    public void exportarCSV() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar Histórico");
        fileChooser.setInitialFileName("historico_imc.csv");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv")
        );

        File file = fileChooser.showSaveDialog(txtPeso.getScene().getWindow());

        if (file != null) {
            gerenciadorHistorico.exportarCSV(file.getAbsolutePath());
            mostrarAlerta("CSV exportado com sucesso!");
        }
    }

    private void atualizarEstatisticas() {
        double imcMedio = gerenciadorHistorico.calcularIMCMedio();
        double pesoMedio = gerenciadorHistorico.calcularPesoMedio();
        lblEstatisticas.setText(String.format(Locale.forLanguageTag("pt-BR"),
                "IMC Médio: %.2f | Peso Médio: %.2f kg",
                imcMedio,
                pesoMedio
        ));
    }

    private void mostrarErro(String mensagem) {
        lblErro.setText("Erro: " + mensagem);
        lblResultado.setText("");
        lblClassificacao.setText("");
        lblPesoIdeal.setText("");
        lblMeta.setText("");
    }

    private void mostrarAlerta(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public void limparCampos() {
        txtPeso.clear();
        txtAltura.clear();
        lblResultado.setText("");
        lblClassificacao.setText("");
        lblErro.setText("");
        lblPesoIdeal.setText("");
        txtPesoAtual.clear();
        lblMeta.setText("");
    }
}
