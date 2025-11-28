# 🍕 Pizzaria Mobile

Aplicação mobile para gerenciamento de pedidos de pizzaria, desenvolvida com React Native e Expo.

## 📱 Sobre o Projeto

Este aplicativo mobile permite que garçons e atendentes gerenciem pedidos de mesas em uma pizzaria de forma rápida e eficiente. O sistema possibilita abrir mesas, adicionar produtos, visualizar pedidos em tempo real e enviar para a produção.

## ✨ Funcionalidades

- 🔐 **Autenticação de usuários** - Login seguro para garçons e atendentes
- 🪑 **Gerenciamento de mesas** - Abrir e fechar mesas
- 📝 **Criação de pedidos** - Adicionar produtos às mesas
- 🍕 **Catálogo de produtos** - Visualizar categorias e produtos disponíveis
- 📊 **Acompanhamento em tempo real** - Visualizar status dos pedidos
- 🧾 **Finalização de pedidos** - Enviar pedidos para a cozinha

## 🚀 Tecnologias Utilizadas

- [React Native](https://reactnative.dev/)
- [Expo](https://expo.dev/)
- [TypeScript](https://www.typescriptlang.org/)
- [Axios](https://axios-http.com/) - Para requisições HTTP
- [AsyncStorage](https://react-native-async-storage.github.io/async-storage/) - Para armazenamento local
- [React Navigation](https://reactnavigation.org/) - Para navegação entre telas

## 📋 Pré-requisitos

Antes de começar, certifique-se de ter instalado em sua máquina:

- [Node.js](https://nodejs.org/) (versão 14 ou superior)
- [npm](https://www.npmjs.com/) ou [Yarn](https://yarnpkg.com/)
- [Expo CLI](https://docs.expo.dev/get-started/installation/)
- [Git](https://git-scm.com/)

Para testar o aplicativo:
- [Expo Go](https://expo.dev/client) instalado no seu smartphone, ou
- Emulador Android/iOS configurado

## 🔧 Instalação

### 1. Clone o repositório

```bash
git clone https://github.com/thiago445/pizzaria-mobile.git
cd pizzaria-mobile
```

### 2. Instale as dependências

```bash
# Usando npm
npm install

# Ou usando yarn
yarn install
```

### 3. Configure as variáveis de ambiente

Crie um arquivo `.env` na raiz do projeto (ou configure conforme necessário):

```env
API_URL=http://seu-servidor-backend:3333
```

**Observação:** Substitua `http://seu-servidor-backend:3333` pela URL do seu backend.

### 4. Inicie o servidor de desenvolvimento

```bash
# Usando npm
npm start

# Ou usando yarn
yarn start

# Ou usando Expo CLI
expo start
```

### 5. Execute o aplicativo

Após iniciar o servidor, você verá um QR Code no terminal. Escaneie-o com:
- **Android:** Aplicativo Expo Go
- **iOS:** Câmera nativa do iPhone

Ou pressione:
- `a` - Para abrir no emulador Android
- `i` - Para abrir no simulador iOS
- `w` - Para abrir no navegador web

## 🔌 API - Endpoints

Este aplicativo consome uma API REST. Abaixo estão os principais endpoints utilizados:

### Autenticação

#### POST `/session`
Realiza login do usuário.

**Request:**
```json
{
  "email": "usuario@email.com",
  "password": "senha123"
}
```

**Response:**
```json
{
  "id": "uuid",
  "name": "Nome do Usuário",
  "email": "usuario@email.com",
  "token": "jwt-token-aqui"
}
```

**Como usar:**
```typescript
import axios from 'axios';

const response = await axios.post('http://seu-servidor:3333/session', {
  email: 'usuario@email.com',
  password: 'senha123'
});

const { token } = response.data;
```

---

### Mesas (Orders)

#### POST `/order`
Abre uma nova mesa.

**Headers:**
```
Authorization: Bearer {token}
```

**Request:**
```json
{
  "table": 5,
  "name": "Mesa 5"
}
```

**Response:**
```json
{
  "id": "uuid",
  "table": 5,
  "status": true,
  "draft": true,
  "name": "Mesa 5"
}
```

**Como usar:**
```typescript
const response = await api.post('/order', {
  table: 5,
  name: 'Mesa 5'
}, {
  headers: {
    Authorization: `Bearer ${token}`
  }
});
```

#### DELETE `/order`
Remove uma mesa (apenas se estiver vazia).

**Headers:**
```
Authorization: Bearer {token}
```

**Query Parameters:**
- `order_id` (string) - ID da mesa

**Como usar:**
```typescript
await api.delete('/order', {
  params: { order_id: 'uuid-da-mesa' },
  headers: { Authorization: `Bearer ${token}` }
});
```

#### PUT `/order/send`
Finaliza e envia o pedido para a cozinha.

**Headers:**
```
Authorization: Bearer {token}
```

**Request:**
```json
{
  "order_id": "uuid-da-mesa"
}
```

**Como usar:**
```typescript
await api.put('/order/send', {
  order_id: 'uuid-da-mesa'
}, {
  headers: { Authorization: `Bearer ${token}` }
});
```

---

### Categorias

#### GET `/category`
Lista todas as categorias de produtos.

**Headers:**
```
Authorization: Bearer {token}
```

**Response:**
```json
[
  {
    "id": "uuid",
    "name": "Pizzas"
  },
  {
    "id": "uuid",
    "name": "Bebidas"
  }
]
```

**Como usar:**
```typescript
const response = await api.get('/category', {
  headers: { Authorization: `Bearer ${token}` }
});

const categories = response.data;
```

---

### Produtos

#### GET `/category/product`
Lista produtos de uma categoria específica.

**Headers:**
```
Authorization: Bearer {token}
```

**Query Parameters:**
- `category_id` (string) - ID da categoria

**Response:**
```json
[
  {
    "id": "uuid",
    "name": "Pizza Margherita",
    "price": "35.00",
    "description": "Molho, mussarela, tomate e manjericão",
    "banner": "url-da-imagem",
    "category_id": "uuid"
  }
]
```

**Como usar:**
```typescript
const response = await api.get('/category/product', {
  params: { category_id: 'uuid-da-categoria' },
  headers: { Authorization: `Bearer ${token}` }
});

const products = response.data;
```

---

### Itens do Pedido

#### POST `/order/add`
Adiciona um item ao pedido.

**Headers:**
```
Authorization: Bearer {token}
```

**Request:**
```json
{
  "order_id": "uuid-da-mesa",
  "product_id": "uuid-do-produto",
  "amount": 2
}
```

**Response:**
```json
{
  "id": "uuid",
  "order_id": "uuid-da-mesa",
  "product_id": "uuid-do-produto",
  "amount": 2
}
```

**Como usar:**
```typescript
await api.post('/order/add', {
  order_id: 'uuid-da-mesa',
  product_id: 'uuid-do-produto',
  amount: 2
}, {
  headers: { Authorization: `Bearer ${token}` }
});
```

#### DELETE `/order/remove`
Remove um item do pedido.

**Headers:**
```
Authorization: Bearer {token}
```

**Query Parameters:**
- `item_id` (string) - ID do item

**Como usar:**
```typescript
await api.delete('/order/remove', {
  params: { item_id: 'uuid-do-item' },
  headers: { Authorization: `Bearer ${token}` }
});
```

#### GET `/order/detail`
Lista todos os itens de um pedido.

**Headers:**
```
Authorization: Bearer {token}
```

**Query Parameters:**
- `order_id` (string) - ID da mesa

**Response:**
```json
[
  {
    "id": "uuid",
    "amount": 2,
    "order_id": "uuid-da-mesa",
    "product_id": "uuid-do-produto",
    "product": {
      "id": "uuid",
      "name": "Pizza Margherita",
      "price": "35.00"
    }
  }
]
```

**Como usar:**
```typescript
const response = await api.get('/order/detail', {
  params: { order_id: 'uuid-da-mesa' },
  headers: { Authorization: `Bearer ${token}` }
});

const items = response.data;
```

---

## 🔐 Configuração do Axios

Exemplo de configuração do Axios com interceptor para token:

```typescript
// src/services/api.ts
import axios from 'axios';
import AsyncStorage from '@react-native-async-storage/async-storage';

const api = axios.create({
  baseURL: 'http://seu-servidor:3333'
});

// Interceptor para adicionar token automaticamente
api.interceptors.request.use(async (config) => {
  const token = await AsyncStorage.getItem('@pizzaria:token');
  
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  
  return config;
});

export default api;
```

## 📱 Fluxo de Uso

1. **Login:** O usuário faz login com email e senha
2. **Dashboard:** Visualiza as opções disponíveis
3. **Abrir Mesa:** Seleciona um número de mesa e abre
4. **Adicionar Produtos:** 
   - Seleciona uma categoria
   - Escolhe um produto
   - Define a quantidade
   - Adiciona ao pedido
5. **Revisar Pedido:** Visualiza todos os itens adicionados
6. **Enviar Pedido:** Finaliza e envia para a cozinha
7. **Fechar Mesa:** Após conclusão, fecha a mesa

## 📁 Estrutura de Pastas

```
pizzaria-mobile/
├── src/
│   ├── @types/          # Tipos TypeScript
│   ├── components/      # Componentes reutilizáveis
│   ├── contexts/        # Context API
│   ├── pages/           # Telas do aplicativo
│   ├── routes/          # Configuração de rotas
│   ├── services/        # Serviços (API, etc)
│   └── utils/           # Funções utilitárias
├── assets/              # Imagens e recursos
├── App.tsx              # Componente principal
├── app.json             # Configurações do Expo
├── package.json         # Dependências
└── tsconfig.json        # Configuração TypeScript
```

## 🎨 Telas do Aplicativo

- **Login** - Tela de autenticação
- **Dashboard** - Tela principal após login
- **Nova Mesa** - Abrir uma nova mesa
- **Pedido** - Adicionar produtos ao pedido
- **Finalizar** - Revisar e enviar pedido

## 🧪 Testes

```bash
# Executar testes
npm test

# Executar testes com coverage
npm run test:coverage
```

## 📦 Build

### Android

```bash
# Build APK de desenvolvimento
expo build:android -t apk

# Build AAB para Play Store
expo build:android -t app-bundle
```

### iOS

```bash
# Build para iOS
expo build:ios
```

## 🤝 Contribuindo

Contribuições são sempre bem-vindas! Sinta-se à vontade para:

1. Fazer um fork do projeto
2. Criar uma branch para sua feature (`git checkout -b feature/NovaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/NovaFeature`)
5. Abrir um Pull Request

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👤 Autor

**Thiago445**

- GitHub: [@thiago445](https://github.com/thiago445)

## 📞 Suporte

Para suporte, abra uma issue no repositório ou entre em contato através do GitHub.

---

⭐️ Se este projeto foi útil para você, considere dar uma estrela no repositório!
