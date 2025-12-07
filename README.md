# Calculadora IMC

Aplicação desktop desenvolvida em JavaFX para calcular Índice de Massa Corporal (IMC) com histórico completo, estatísticas e exportação de dados em CSV.

## 🎯 Funcionalidades

- ✅ Calcular IMC baseado em peso e altura
- ✅ Classificação automática (Abaixo do peso, Normal, Sobrepeso, Obeso)
- ✅ Cálculo de peso ideal baseado na altura
- ✅ Definição e acompanhamento de meta de peso
- ✅ Histórico completo com data/hora de cada cálculo
- ✅ Estatísticas (IMC médio e peso médio)
- ✅ Exportar histórico em CSV
- ✅ Validação robusta de entrada de dados
- ✅ Filtro numérico em tempo real nos campos

## 📋 Requisitos

- **Java 11+**
- **Maven 3.6+**
- **JavaFX 11+**

## 🚀 Instalação

```bash
git clone https://github.com/DanielHMoura/calculadora-imc.git
cd calculadora-imc
mvn clean install
```

## ▶️ Como Executar

**Com Maven:**
```bash
mvn javafx:run
```

**Com JAR compilado:**
```bash
mvn clean package
java -jar target/calculadora-imc-1.0.jar
```

## 📂 Estrutura do Projeto

```
calculadora-imc/
├── src/
│   ├── main/
│   │   ├── java/com/calculadora/
│   │   │   ├── controller/         # Controllers JavaFX
│   │   │   │   └── CalculadoraController.java
│   │   │   ├── model/              # Lógica de negócio
│   │   │   │   ├── CalculoIMC.java
│   │   │   │   ├── RegistroIMC.java
│   │   │   │   └── GerenciadorHistorico.java
│   │   │   ├── util/               # Utilitários
│   │   │   │   ├── ValidadorIMC.java
│   │   │   │   ├── ResultadoValidacao.java
│   │   │   │   └── MetaUtils.java
│   │   │   └── Application.java
│   │   └── resources/
│   │       ├── fxml/               # Arquivos FXML
│   │       └── css/                # Estilos CSS
│   └── test/
│       └── java/com/calculadora/   # Testes unitários
├── pom.xml
├── README.md
└── .gitignore
```

## 🛠️ Tecnologias Utilizadas

- **JavaFX 21** - Interface gráfica
- **Maven** - Gerenciamento de dependências e build
- **JUnit 5** - Testes unitários
- **Java 11+** - Linguagem

## 📖 Como Usar

1. Abra a aplicação
2. Digite o peso (em kg) e a altura (em m)
3. Opcionalmente, digite o peso atual para acompanhamento de meta
4. Clique em **Calcular**
5. Visualize o resultado, classificação e peso ideal
6. Histórico é atualizado automaticamente
7. Exporte os dados em CSV quando necessário

## ✅ Validações

- Peso e altura são obrigatórios
- Apenas números, vírgula e ponto são aceitos
- Valores fora dos limites são rejeitados
- Mensagens de erro claras ao usuário

## 📊 Estatísticas

A aplicação mantém em tempo real:

- **IMC Médio** de todos os cálculos
- **Peso Médio** de todos os registros

## 💾 Exportação CSV

Os dados podem ser exportados em formato CSV com as colunas:

- Data/Hora
- Peso
- IMC
- Classificação
- Peso Atual (se preenchido)

## 📝 Exemplos de Classificação IMC

| IMC | Classificação |
|-----|---------------|
| < 18.5 | Abaixo do peso |
| 18.5 - 24.9 | Peso normal |
| 25.0 - 29.9 | Sobrepeso |
| ≥ 30.0 | Obeso |

## 🤝 Contribuindo

Sinta-se livre para fazer fork, criar branches e submeter pull requests!

## 📄 Licença

Este projeto está licenciado sob a MIT License - veja o arquivo LICENSE para detalhes.

---

**Desenvolvido por [Daniel H. Moura](https://github.com/DanielHMoura)**
