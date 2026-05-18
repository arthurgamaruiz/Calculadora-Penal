# ⚖️ Calculadora Penal em Kotlin

Aplicação desenvolvida em Kotlin com o objetivo de estimar prazos relacionados à execução penal brasileira, como progressão de regime e livramento condicional, utilizando regras jurídicas baseadas na Lei nº 13.964/2019 (Pacote Anticrime).

---

## 📌 Sobre o Projeto

O sistema foi desenvolvido como parte do projeto de extensão da disciplina **Desenvolvimento de Aplicativos com a Linguagem Kotlin** do Instituto Mauá de Tecnologia.

A proposta busca aproximar a tecnologia da sociedade por meio de uma ferramenta acessível e educativa, auxiliando familiares de apenados, estudantes de Direito e cidadãos leigos a compreenderem melhor os cálculos relacionados à execução penal.

---

## 🎯 Objetivos

- Automatizar cálculos penais de forma simples e intuitiva;
- Aplicar regras jurídicas em lógica computacional;
- Facilitar o acesso à informação sobre execução penal;
- Desenvolver uma solução tecnológica com impacto social;
- Aplicar conceitos de Kotlin em um projeto real.

---

## 🧠 Funcionalidades

### ✅ Entrada de Dados
O usuário pode informar:

- Pena total (anos, meses e dias);
- Data de início do cumprimento da pena;
- Tempo de detração;
- Tipo de crime:
    - Comum;
    - Hediondo;
    - Hediondo com resultado morte;
- Status do apenado:
    - Primário;
    - Reincidente.

---

### ⚙️ Processamento
O sistema realiza:

- Cálculo de progressão de regime;
- Cálculo de livramento condicional;
- Aplicação automática das frações previstas em lei;
- Manipulação de datas;
- Validação de entradas;
- Tratamento de exceções.

---

### 📤 Resultados Gerados
Após o cálculo, o sistema apresenta:

- Data estimada para progressão ao regime semiaberto;
- Data estimada para progressão ao regime aberto;
- Data estimada para livramento condicional;
- Informações organizadas de forma clara e objetiva.

---

## ⚖️ Regras Jurídicas Utilizadas

### Progressão de Regime

| Situação | Fração da Pena |
|---|---|
| Crime comum + primário | 16% |
| Crime comum + reincidente | 20% |
| Violência ou grave ameaça | 25% |
| Violência/grave ameaça + reincidente | 30% |
| Crime hediondo | 40% |
| Crime hediondo + reincidente | 60% |
| Hediondo com resultado morte | 50% |
| Hediondo com resultado morte + reincidente | 70% |

---

### Livramento Condicional

| Situação | Fração da Pena |
|---|---|
| Réu primário | 1/3 |
| Reincidente | 1/2 |
| Crime hediondo | 2/3 |

> Crimes hediondos com resultado morte não possuem direito ao livramento condicional.

---

## 🛠️ Tecnologias Utilizadas

- Kotlin
- Android Studio *(caso mobile)*
- Kotlin/JVM
- Manipulação de Datas
- Estruturas Condicionais
- Programação Orientada a Objetos
- Git e GitHub

---

## 🏗️ Arquitetura do Projeto

O sistema está dividido em módulos responsáveis por:

```text
📦 src
 ┣ 📂 model
 ┃ ┗ Classes de domínio
 ┣ 📂 service
 ┃ ┗ Regras de negócio e cálculos
 ┣ 📂 utils
 ┃ ┗ Funções auxiliares
 ┣ 📂 ui
 ┃ ┗ Interface do usuário
 ┗ 📂 validation
   ┗ Validação de dados
````

## 👨‍💻 Integrantes do Grupo

| Nome | GitHub |
|---|---|
| Arthur Gama Ruiz | [Github](https://github.com/arthurgamaruiz) 
| Enzo Oliveira D'Onofrio | [Github](https://github.com/EnzoDOnofrio)
| Marcus Miyamoto | https://github.com/ 

---

## 🤝 Parceria Institucional

Este projeto é desenvolvido em parceria com o escritório de advocacia **Cespedes & Lourenço Advogados**, responsável por apresentar a demanda real utilizada como base para o desenvolvimento da aplicação.

A parceria possui papel fundamental nas seguintes etapas do projeto:

- Apresentação do problema jurídico;
- Validação dos requisitos do sistema;
- Apoio técnico-conceitual;
- Avaliação da aplicabilidade real da solução desenvolvida.

O objetivo da parceria é transformar a aplicação em uma ferramenta de apoio à comunidade, facilitando o acesso à informação sobre execução penal e auxiliando cidadãos que necessitem de orientação especializada.

📞 Contato da empresa parceira:  
**+55 11 98949-8044**
