import { observer, useObservable } from 'mobx-react-lite'
import React, { Fragment, useEffect } from 'react'
import history from '../utils/history'

import MenuBar from '../common/MenuBar'
import UserStore from '../stores/user'
import TransactionStore from '../stores/transaction'
import { Row, Col } from 'antd'
import ITransaction from '../interface/ITransaction'


const Admin = () => {
  const userStore = useObservable(UserStore)
  const transactionStore = useObservable(TransactionStore)


  useEffect(() => {
    transactionStore.getTransaction()
  }, [transactionStore])

  if (!userStore.isAuthenticated) {
    history.push(`/`)
    return <p>Unauthorized</p>
  }


  return (
    <Fragment>
      <MenuBar
        menus={[
          { icon: 'pie-chart', name: 'Dashboard', to: '/dashboard' },
          {
            icon: 'wallet',
            name: 'Account',
            submenu: [
              {
                icon: 'import',
                name: 'Deposit',
                to: '/deposit'
              },
              {
                icon: 'export',
                name: 'Withdraw',
                to: '/withdraw'
              },
              {
                icon: 'transaction',
                name: 'Transfer',
                to: '/transfer'
              }
            ],
            to: ''
          },
          { icon: 'file-sync', name: 'Transactions', to: '/transaction' },
        ]}
      >
        <h1>Transaction Log</h1>
        <Row>
          <Col>
            <table style={{ width: '600px'}}>
              <tr>
                <th>Action</th>
                <th>From</th>
                <th>To</th>
                <th>Currency From</th>
                <th>Currency To</th>
                <th>Amount</th>
              </tr>
              {transactionStore.transactions.map((t: ITransaction) => {
                return (
                  <tr key={t.id}>
                    <td>{t.service}</td>
                    <td>{t.service === 'transfer' && t.account_source}</td>
                    <td>{t.service === 'transfer' && t.account_destination}</td>
                    <td>{t.currency_source}</td>
                    <td>{t.service !== 'withdraw' && t.account_destination}</td>
                    <td>{t.balance.toFixed(2)}</td>
                  </tr>
                )
              })}
            </table>
          </Col>
        </Row>

      </MenuBar>
    </Fragment>
  )
}

export default observer(Admin)