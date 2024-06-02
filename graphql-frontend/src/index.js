import React from 'react';
import { createRoot } from 'react-dom/client';
import './styles/index.css';
import App from './components/App';

import { Provider, createClient, fetchExchange } from 'urql'
import { devtoolsExchange } from '@urql/devtools';

const client = createClient({
  url: 'http://localhost:8080/graphql',
  exchanges: [devtoolsExchange, fetchExchange]
})

const container = document.getElementById('root'); // Get the root element
const root = createRoot(container); // Create a root

root.render(
  <React.StrictMode>
  <Provider value={client}>
    <App />
    </Provider>
  </React.StrictMode>
);



