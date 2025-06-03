# Nome do Projeto

Esse projeto é um case técnico da TOTVS focado em criar uma aplicação para registro de clientes

## 🛠️ Tecnologias Utilizadas

- **Backend:** Spring Boot + Maven
- **Frontend:** Angular
- **Banco de Dados:** PostgreSQL
- **Containerização:** Docker & Docker Compose

## Configurações utilizadas
- **Maven:** Apache Maven 3.9.9 + Spring Boot
- **Angular:** Angular 19.2.14 + PO-UI
- **Banco de dados:** PostgreSQL
- **Containers:**

## 📋 Pré-requisitos

Antes de começar, certifique-se de ter instalado em sua máquina:

- [Docker](https://www.docker.com/get-started) (versão 20.0 ou superior)
- [Docker Compose](https://docs.docker.com/compose/install/) (versão 2.0 ou superior)
- [Git](https://git-scm.com/)

### Opcional (para desenvolvimento local):
- [Java 17+](https://adoptium.net/)
- [Maven 3.8+](https://maven.apache.org/download.cgi)
- [Node.js 18+](https://nodejs.org/)
- [Angular CLI](https://angular.io/cli)

## 🚀 Como Executar o Projeto

### Opção 1: Usando Docker (Recomendado)

1. **Clone o repositório:**
```bash
   git clone https://github.com/mozca33/case-tecnico-totvs.git
   cd case-tecnico-totvs
```

## Execute com Docker Compose:
```bash
    docker-compose up -d
```
Aguarde a inicialização (pode levar alguns minutos na primeira execução)

Acesse a aplicação:

Frontend: 
[Angular](http://localhost:4200/clients)
Backend API: 
[API](http://localhost:8080/api/clients)
Banco de dados: localhost:5432

# Opção 2: Execução Local (Desenvolvimento)

1. Configurar o Banco de Dados
## Subir apenas o PostgreSQL
```bash 
    docker-compose up -d postgres
```
2. Executar o Backend

## Navegar para o diretório do backend
```bash 
    cd backend
```

## Instalar dependências e executar
```bash
    mvn clean install
    mvn spring-boot:run
```

3. Executar o Frontend

## Em outro terminal, navegar para o diretório do frontend
```bash
    cd frontend
```

## Instalar dependências
```bash
    npm install
```

## Executar em modo de desenvolvimento
```bash 
    ng serve
```
# 🐳 Comandos Docker Úteis

### Parar todos os containers
```bash
    docker-compose down
```

# Parar e remover volumes (limpa o banco)
```bash
    docker-compose down -v
```

# Ver logs de um serviço específico
```bash
    docker-compose logs -f backend
    docker-compose logs -f frontend
    docker-compose logs -f postgres
```

# Reconstruir as imagens
```bash
    docker-compose build
```

# Executar em modo detached (background)
```bash
    docker-compose up -d
```

# Executar apenas um serviço
```bash
    docker-compose up postgres
```

# 📁 Estrutura do Projeto
projeto/
├── backend/                 # Aplicação Spring Boot
│   ├── src/
│   ├── pom.xml
│   └── Dockerfile
├── frontend/                # Aplicação Angular
│   ├── src/
│   ├── package.json
│   └── Dockerfile
├── docker-compose.yml       # Orquestração dos containers
└── README.md

# ⚙️ Configurações
Variáveis de Ambiente
O projeto utiliza as seguintes variáveis de ambiente (já configuradas no docker-compose.yaml):

## Exemplo de docker-compose.yml

Aqui está um exemplo básico do arquivo `docker-compose.yml` que você pode incluir:

```yaml
version: '3.8'

services:
  postgres:
    image: postgres:15-alpine
    container_name: projeto_postgres
    environment:
      POSTGRES_DB: projeto_db
      POSTGRES_USER: projeto_user
      POSTGRES_PASSWORD: projeto_pass
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data
    networks:
      - projeto_network

  backend:
    build:
      context: ./backend
      dockerfile: Dockerfile
    container_name: projeto_backend
    environment:
      SPRING_PROFILES_ACTIVE: docker
      DB_HOST: postgres
      DB_PORT: 5432
      DB_NAME: projeto_db
      DB_USER: projeto_user
      DB_PASSWORD: projeto_pass
    ports:
      - "8080:8080"
    depends_on:
      - postgres
    networks:
      - projeto_network

  frontend:
    build:
      context: ./frontend
      dockerfile: Dockerfile
    container_name: projeto_frontend
    ports:
      - "4200:80"
    depends_on:
      - backend
    networks:
      - projeto_network

volumes:
  postgres_data:

networks:
  projeto_network:
    driver: bridge

```

# 👥 Autores
Rafael F. Cordeiro - Desenvolvimento inicial
