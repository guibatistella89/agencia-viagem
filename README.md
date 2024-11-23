# API de Agência de Viagem

Esta API permite gerenciar destinos turísticos, oferecendo funcionalidades para cadastrar, listar, pesquisar, avaliar e excluir destinos. É uma API RESTful construída com **Spring Boot**, permitindo fácil integração com front-ends ou outros sistemas.

## Tecnologias Utilizadas

- **Java** (versão 11 ou superior)
- **Spring Boot** (para o backend)
- **Maven** (como gerenciador de dependências)
- **Lombok** (para reduzir a verbosidade do código)

## Funcionalidades da API

A API oferece os seguintes recursos:

- **Cadastro de Destinos**: Permite adicionar destinos turísticos com informações como nome, localização, descrição, média de avaliação e número de avaliações.
- **Listagem de Destinos**: Retorna todos os destinos cadastrados no sistema.
- **Visualização de Destino Específico**: Permite visualizar detalhes de um destino específico através do ID.
- **Avaliação de Destinos**: Usuários podem avaliar um destino, atribuindo uma nota (de 1 a 5).
- **Pesquisa de Destinos**: Permite pesquisar destinos por nome ou localização.
- **Exclusão de Destinos**: Permite excluir um destino existente.

## Endpoints

### 1. **Cadastrar um novo destino**
- **Método**: `POST`
- **URL**: `/api/destinos`
- **Corpo da requisição**:
    ```json
    {
      "nome": "Praia do Forte",
      "localizacao": "Bahia",
      "descricao": "Praia paradisíaca",
      "mediaAvaliacao": 4.5,
      "totalAvaliacoes": 120
    }
    ```
- **Resposta**:
    ```json
    {
      "id": 1,
      "nome": "Praia do Forte",
      "localizacao": "Bahia",
      "descricao": "Praia paradisíaca",
      "mediaAvaliacao": 4.5,
      "totalAvaliacoes": 120
    }
    ```

### 2. **Buscar destino por ID**
- **Método**: `GET`
- **URL**: `/api/destinos/{id}`
- **Exemplo**: `/api/destinos/1`
- **Resposta**:
    ```json
    {
        "id": 1,
        "nome": "Praia do Forte",
        "localizacao": "Bahia",
        "descricao": "Praia paradisíaca",
        "mediaAvaliacao": 4.5,
        "totalAvaliacoes": 120
    }
    ```

### 3. **Avaliar um destino**
- **Método**: `PATCH`
- **URL**: `/api/destinos/{id}/avaliar`
- **Parâmetro**: `nota` (inteiro de 1 a 10)
- **Exemplo de URL**: `/api/destinos/1/avaliar?nota=5`
- **Resposta**:
    ```json
    {
        "message": "Avaliação registrada com sucesso!"
    }
    ```

### 4. **Excluir destino**
- **Método**: `DELETE`
- **URL**: `/api/destinos/{id}`
- **Exemplo**: `/api/destinos/1`
- **Resposta**:
    ```json
    {
        "message": "Destino excluído com sucesso!"
    }
    ```

### 5. **Pesquisar destinos**
- **Método**: `GET`
- **URL**: `/api/destinos/pesquisar`
- **Parâmetros**:
    - `nome` (opcional) - Nome do destino a ser pesquisado.
    - `localizacao` (opcional) - Localização do destino a ser pesquisado.

    **Exemplo de URL**:
    - `/api/destinos/pesquisar?nome=Praia`
    - `/api/destinos/pesquisar?localizacao=Bahia`

- **Resposta**:
    ```json
    [
        {
            "id": 1,
            "nome": "Praia do Forte",
            "localizacao": "Bahia",
            "descricao": "Praia paradisíaca",
            "mediaAvaliacao": 4.5,
            "totalAvaliacoes": 120
        }
    ]
    ```
