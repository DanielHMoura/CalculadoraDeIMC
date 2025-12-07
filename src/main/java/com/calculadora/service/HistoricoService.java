package com.calculadora.service;

import com.calculadora.model.RegistroIMC;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HistoricoService {
    private static final String ARQUIVO = "historico_imc.txt";
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void salvar(List<RegistroIMC> registros) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (RegistroIMC registro : registros) {
                String linha = String.format("%.2f|%.2f|%s|%s",
                        registro.getPeso(),
                        registro.getImc(),
                        registro.getClassificacao(),
                        registro.getHorario().format(TIME_FORMATTER)
                );
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar histórico: " + e.getMessage());
        }
    }

    public static List<RegistroIMC> carregar() {
        List<RegistroIMC> registros = new ArrayList<>();
        File arquivo = new File(ARQUIVO);

        if (!arquivo.exists()) {
            return registros;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                try {
                    String[] partes = linha.split("\\|");
                    double peso = Double.parseDouble(partes[0]);
                    double imc = Double.parseDouble(partes[1]);
                    String classificacao = partes[2];
                    LocalTime horario = LocalTime.parse(partes[3], TIME_FORMATTER);

                    registros.add(new RegistroIMC(peso, imc, classificacao, horario));
                } catch (Exception e) {
                    System.err.println("Erro ao ler linha: " + linha);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar histórico: " + e.getMessage());
        }

        return registros;
    }

    public static void exportarCSV(List<RegistroIMC> registros, String nomeArquivo) {
        try (PrintWriter writer = new PrintWriter(nomeArquivo)) {
            writer.println("Horario,Peso,IMC,Classificacao");
            for (RegistroIMC r : registros) {
                writer.println(String.format("%s,%.2f,%.2f,%s",
                        r.getHorario().format(TIME_FORMATTER),
                        r.getPeso(),
                        r.getImc(),
                        r.getClassificacao()
                ));
            }
            System.out.println("CSV exportado com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao exportar CSV: " + e.getMessage());
        }
    }
}