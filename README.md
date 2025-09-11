# Api-E-commerce

API RESTful de um sistema de e‑commerce, desenvolvida com Spring Boot, que fornece endpoints para gerenciamento de usuários, produtos, carrinho de compras, favoritos, pedidos e pagamentos.

---

## 🚀 Tecnologias

- **Java 17+**  
- **Spring Boot 3.2**  
  - Spring Web  
  - Spring Data JPA  
  - Spring Security  
- **Hibernate (JPA)**  
- **Postegre 17**  
- **Redis** (cache)  
- **Maven Wrapper** (`mvnw`)  
- **Docker & Docker Compose**  
- **Lombok**  
- **JUnit + Spring Security Test**

---

## 🏗️ Estrutura do Projeto
├── Dockerfile
<br>
├── docker-compose.yml
<br>
├── mvnw
<br>
├── mvnw.cmd
<br>
├── pom.xml
<br>
├── src
<br>
│ └── main
<br>
│ ├── java/com/ecommerce
<br>
│ │ ├── ApiecommerceApplication.java 
<br>
│ │ ├── aplication
<br>
│ │ │ ├── records/…
<br>
│ │ │ ├── services/… 
<br>
│ │ │ └── security/… 
<br>
│ │ └── infra
<br>
│ │ ├── controllers/… 
<br>
│ │ └── exceptions/… 
<br>
│ └── resources
<br>
│ └── application-prod.properties 
<br>
├── application.jar
<br>
└── LICENSE
<br>

---

## 🔧 Pré-requisitos

- **JDK 17** ou superior  
- **Maven** (ou usar o `mvnw`)  
- **Docker** & **Docker Compose**

---

## ⚙️ Configuração

### 1. Banco de dados

Crie um banco Postegre (17+) e um usuário com permissão de leitura e escrita. Defina as variáveis de ambiente:

```bash
export SPRING_DATASOURCE_URL=jdbc:postegre://<HOST>:<PORT>/<DB_NAME>
export SPRING_DATASOURCE_USERNAME=<DB_USER>
export SPRING_DATASOURCE_PASSWORD=<DB_PASS>
````

### 2. Redis (cache)
Configure o Redis (caso deseje usar cache):


```bash
export SPRING_REDIS_HOST=<REDIS_HOST>
export SPRING_REDIS_PORT=<REDIS_PORT>
````

### 3. Outras propriedades
properties

```bash
spring.jpa.hibernate.ddl-auto=${DDL_AUTO:update}
````
Edite diretamente o application-prod.properties ou forneça via variável de ambiente.

## ▶️ Como executar localmente
### 1. Via Maven Wrapper
```bash
chmod +x mvnw
./mvnw spring-boot:run -Dspring-boot.run.profiles=prod
````

### 2. Gerando e executando o JAR
```bash
./mvnw clean package -DskipTests
java -jar target/apiecommerce-1.0.0.jar --spring.profiles.active=prod
````


## 🐳 Rodando com Docker
Edite o docker-compose.yml ou crie um arquivo env/mysql.env:

```bash
POSTEGRE_ROOT_PASSWORD=admin
POSTEGRE_DATABASE=ecommerce
POSTEGRE_USER=user
POSTEGRE_PASSWORD=senha

SPRING_DATASOURCE_URL=jdbc:postegre://postegre:3306/ecommerce
SPRING_DATASOURCE_USERNAME=user
SPRING_DATASOURCE_PASSWORD=senha

SPRING_REDIS_HOST=redis
SPRING_REDIS_PORT=6379

DDL_AUTO=update
````

Suba os containers:

```bash
docker-compose up -d
````
Construa e execute a imagem da API:

```bash
docker build -t api-ecommerce .
docker run -d \
  --name api-ecommerce \
  --network ecommerce-net \
  -e SPRING_PROFILES_ACTIVE=prod \
  -e SPRING_DATASOURCE_URL=jdbc:postegre://postegre:3306/ecommerce \
  -e SPRING_DATASOURCE_USERNAME=user \
  -e SPRING_DATASOURCE_PASSWORD=senha \
  -p 8080:8080 \
  api-ecommerce
````

### 📚 Endpoints Principais
#### Base URL: http://localhost:8080

## 🔐 Autenticação / Usuário
#### Método	Rota	Descrição
POST	/login	Login 
<br>
POST	/register	Criar novo usuário
<br>
PUT	/alterar-senha	Alterar senha
<br>
GET	/usuario/{id}	Buscar dados do usuário
<br>
PUT	/usuario/{id}	Atualizar usuário
<br>
DELETE	/usuario/{id}	Excluir usuário
<br>

## 📦 Produtos
#### Leitura
#### Método	Rota	Descrição
GET	/produtos	Listar todos
<br>
GET	/produtos/{id}	Detalhar produto
<br>
GET	/produtos/buscarNome	Buscar por nome
<br>
GET	/produtos/buscarTipo	Filtrar por tipo
<br>
GET	/produtos/ordenar	Ordenar (preço, etc.)
<br>
GET	/produtos/existe	Verifica existência
<br>
####  Escrita
#### Método	Rota	Descrição
<br>
POST	/products	Criar produto
<br>
PUT	/products/{id}	Atualizar produto
<br>
DELETE	/products/{id}	Excluir produto
<br>

## 🛒 Carrinho
#### Método	Rota	Descrição
GET	/carrinho	Exibir itens
<br>
POST	/carrinho/itens	Adicionar item
<br>
DELETE	/carrinho/itens/{produtoId}	Remover item
<br>

## ❤️ Favoritos
#### Método	Rota	Descrição
<br>
GET	/favoritos	Listar favoritos
<br>
POST	/favoritos	Marcar como favorito
<br>
DELETE	/favoritos/{produtoId}	Desfavoritar produto
<br>
GET	/favoritos/recomendacoes	Sugerir produtos
<br>

## 📦 Pedidos
#### Método	Rota	Descrição
POST	/checkout	Criar pedido
<br>
GET	/usuario	Listar pedidos
<br>

## 💳 Pagamentos
#### Método	Rota	Descrição
POST	/pagamento/{pedidoId}	Criar pagamento
<br>
GET	/pagamento/{pedidoId}	Consultar status do pagamento
<br>

Nota: É necessário estar logado para acessar os endpoints protegidos — com exceção dos de registro e login.

#### 📄 Licença
Este projeto está licenciado sob a MIT License.
