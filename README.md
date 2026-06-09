# Orbital Alert

## Integrantes

| Nome | RM |
|--------|--------|
| Bruno Ferreira | RM563489 |
| Gabriel Robertoni Padilha | RM566293 |
| Leonardo Aragaki Rodrigues | RM562944 |

---

# Descrição da Solução

O Orbital Alert é uma solução desenvolvida para monitoramento de regiões de risco ambiental e sensores inteligentes utilizados na prevenção de desastres naturais.

A aplicação permite cadastrar regiões monitoradas, registrar sensores associados a essas regiões e realizar operações completas de CRUD (Create, Read, Update e Delete), garantindo persistência dos dados através de banco PostgreSQL executado em container Docker.

A solução foi desenvolvida utilizando Java Spring Boot, PostgreSQL, Docker e Docker Compose, possibilitando sua execução em ambientes locais ou em nuvem.

---

# Benefícios para o Negócio

- Centralização dos dados de monitoramento.
- Cadastro e gerenciamento de regiões de risco.
- Cadastro de sensores ambientais.
- Facilidade de implantação em nuvem.
- Persistência segura dos dados.
- Arquitetura escalável baseada em containers.
- Isolamento entre aplicação e banco de dados.
- Facilidade de manutenção e atualização.

---

# Tecnologias Utilizadas

## Back-end

- Java 17
- Spring Boot 3
- Spring Data JPA
- Maven

## Banco de Dados

- PostgreSQL 16

## Containers

- Docker
- Docker Compose

## Infraestrutura

- Azure Virtual Machine Ubuntu Linux

---

# Arquitetura Macro

```text
Usuário / Insomnia
          │
          ▼
Azure Virtual Machine (Ubuntu)
          │
          ▼
Docker Compose
 ┌─────────────────────────────┐
 │ app-orbitalalert-563489     │
 │ Spring Boot API             │
 └─────────────┬───────────────┘
               │
               ▼
 ┌─────────────────────────────┐
 │ db-orbitalalert-563489      │
 │ PostgreSQL 16              │
 └─────────────┬───────────────┘
               │
               ▼
       Volume Nomeado
postgres-orbitalalert-data
```

---

# Modelo de Dados

## Tabela Regiao

- id
- nome
- cidade
- estado
- nivelRisco

## Tabela Sensor

- id
- tipo
- valorAtual
- status
- regiao_id

Relacionamento:

```text
REGIAO (1)
    |
    |------< SENSOR (N)
```

---

# Containers Utilizados

## Container da Aplicação

Nome:

```text
app-orbitalalert-563489
```

Características:

- Imagem personalizada
- Usuário não privilegiado (appuser)
- Porta 8080 exposta
- Variáveis de ambiente
- Diretório de trabalho definido

---

## Container do Banco

Nome:

```text
db-orbitalalert-563489
```

Características:

- PostgreSQL 16
- Porta 5432 exposta
- Volume nomeado
- Variáveis de ambiente

---

# Variáveis de Ambiente

## Aplicação

```properties
SPRING_DATASOURCE_URL
SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_PASSWORD
SERVER_PORT
```

## Banco

```properties
POSTGRES_DB
POSTGRES_USER
POSTGRES_PASSWORD
```

---

# Dockerfile

A aplicação utiliza Dockerfile personalizado contendo:

- Usuário não root (appuser)
- Diretório de trabalho /app
- Build automatizado Maven
- Porta 8080 exposta

---

# Docker Compose

Responsável por:

- Criar a rede Docker
- Criar o volume nomeado
- Construir a aplicação
- Inicializar o PostgreSQL
- Inicializar a API Spring Boot

---

# How To

## 1. Clonar o Projeto

```bash
git clone https://github.com/brunoferr10/Gs_Devops.git
cd orbital-alert
```

## 2. Construir e Executar os Containers

```bash
docker compose up -d --build
```

## 3. Verificar Containers

```bash
docker ps
```

## 4. Verificar Volume

```bash
docker volume ls
```

## 5. Verificar Rede

```bash
docker network ls
```

## 6. Verificar Logs

Aplicação:

```bash
docker logs app-orbitalalert-563489
```

Banco:

```bash
docker logs db-orbitalalert-563489
```

## 7. Acessar Container da Aplicação

```bash
docker exec -it app-orbitalalert-563489 /bin/sh
pwd
ls -l
whoami
```

## 8. Acessar Container do Banco

```bash
docker exec -it db-orbitalalert-563489 /bin/bash
pwd
ls -l
whoami
```

## 9. Testar API

### GET

```http
GET http://localhost:8080/regioes
```

### POST

```http
POST http://localhost:8080/regioes
```

### PUT

```http
PUT http://localhost:8080/sensores/1/regiao/1
```

### DELETE

```http
DELETE http://localhost:8080/sensores/1
```

## 10. Validar Persistência

```bash
docker exec -it db-orbitalalert-563489 psql -U orbital -d orbitalalert
```

```sql
SELECT * FROM regiao;
SELECT * FROM sensor;
```

---

# Evidências de Persistência

As operações CRUD foram validadas diretamente no banco PostgreSQL através de consultas SQL.

Operações demonstradas:

- Create
- Read
- Update
- Delete

Validadas através de:

```sql
SELECT * FROM regiao;
SELECT * FROM sensor;
```

---

# Execução em Nuvem

A solução foi implantada em uma máquina virtual Ubuntu hospedada na Microsoft Azure.

A infraestrutura contém:

- Azure VM Ubuntu
- Docker Engine
- Docker Compose
- API Spring Boot
- PostgreSQL
- Rede Docker
- Volume Nomeado

---

# Conclusão

A solução Orbital Alert atende aos requisitos da disciplina DevOps Tools & Cloud Computing, demonstrando conteinerização, persistência de dados, integração entre containers, execução em nuvem e aplicação das boas práticas DevOps.
