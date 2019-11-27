import React from 'react';
import { Route, Router } from 'react-router'
import { Provider } from 'mobx-react'
import { createGlobalStyle } from 'styled-components';

import history from './utils/history'
import store from './stores'
import Login from './login';
import Dashboard from './dashboard';
import Deposit from './deposit';
import Withdraw from './withdraw';
import Transfer from './transfer';
import Transaction from './transaction';

const GlobalStyle = createGlobalStyle`
  body {
    margin: 0;
    padding: 0;
    background: #F2F4F5 !important;
  }
`


const App: React.FC = () => {
  return (
    <Provider store={store}>
      <Router history={history}>
      <GlobalStyle />
        <Route path="/" component={Login} exact></Route>
        <Route path="/dashboard" component={Dashboard}></Route>
        <Route path="/deposit" component={Deposit}></Route>
        <Route path="/withdraw" component={Withdraw}></Route>
        <Route path="/transfer" component={Transfer}></Route>
        <Route path="/transaction" component={Transaction}></Route>
      </Router>
    </Provider>
  );
}

export default App;
