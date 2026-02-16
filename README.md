# 🚀 DevFlow CLI

DevFlow é uma ferramenta de linha de comando (CLI) desenvolvida em Java para gerenciamento simples de tarefas diretamente pelo terminal.

Com ela, você pode:

- Criar atividades
- Listar tarefas com paginação
- Marcar tarefas como concluídas
- Remover tarefas
- Visualizar detalhes de uma tarefa específica

---

# 📦 Instalação

## ✅ Pré-requisitos

- Java 21+
- Maven

Verifique se estão instalados:

```bash
java -version
mvn -v
```

---

# 🔨 Build do Projeto

Na raiz do projeto, execute:

```bash
mvn clean package
```

Isso irá gerar o arquivo:

```
target/devflow-1.0.0.jar
```

---

# ⚡ Instalação Automática (Recomendado)

O projeto já possui um script de instalação automática para Windows:

```
install-devflow.bat
```

Após gerar o build, execute:

```bash
install-devflow.bat
```

O script irá:

- Criar a pasta de instalação
- Copiar o `.jar`
- Criar o executável `devflow.cmd`
- Adicionar o comando ao PATH do usuário

Depois disso:

1. Feche o terminal
2. Abra novamente
3. Teste:

```bash
devflow --help
```

---

# 🛠 Instalação Manual (Alternativa)

Caso prefira instalar manualmente:

1. Crie uma pasta para a CLI, por exemplo:

```
C:\Users\SEU_USUARIO\Documents\tools\devflow
```

2. Copie o arquivo `.jar` para essa pasta.
3. Crie um arquivo chamado:

```
devflow.cmd
```

4. Adicione o seguinte conteúdo dentro dele:

```cmd
@echo off
java --enable-native-access=ALL-UNNAMED -jar "%~dp0devflow-1.0.0.jar" %*
```

5. Adicione essa pasta ao `PATH` do Windows.
6. Reinicie o terminal.

---

# 📖 Como Utilizar

## 🔹 Comandos Disponíveis

```
add      - Realiza a adição de uma atividade
done     - Marca uma atividade como finalizada
list     - Lista todas as atividades
remove   - Remove uma atividade
show     - Mostra informações de uma atividade
```

---

# 🧠 Exemplos de Uso

## ➕ Adicionar uma tarefa

```bash
devflow add "Estudar Java" "Revisar conceitos de Maven e CLI"
```

---

## 📋 Listar tarefas

```bash
devflow list
```

### Paginação:

```bash
devflow list --page 2
```

---

## ✅ Marcar tarefa como concluída

```bash
devflow done 3
```

---

## ❌ Remover tarefa

```bash
devflow remove 3
```

---

## 🔍 Mostrar detalhes de uma tarefa

```bash
devflow show 3
```

---

# 🏗 Estrutura do Projeto

```
devflow/
 ├── src/
 │    └── main/
 │         └── java/
 ├── pom.xml
 ├── install-devflow.bat
 └── README.md
```

---

# 🛠 Tecnologias Utilizadas

- Java
- Maven
- SQLite

---

# 📌 Observações

- O banco de dados é criado automaticamente.
- O projeto utiliza empacotamento "fat jar" (dependências inclusas).
- Para Java 21+, é necessário habilitar acesso nativo (`--enable-native-access`).
- O script `install-devflow.bat` funciona apenas no Windows.

---

# 🎯 Roadmap Futuro

- [ ] Sistema de update automático
- [ ] Exportação para JSON
- [ ] Filtros avançados
- [ ] Executável nativo
- [ ] Instalação multiplataforma

---

# 📄 Licença

Projeto desenvolvido para fins educacionais e uso pessoal.
