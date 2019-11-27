import { observer, useObservable } from 'mobx-react-lite'
import React, { Fragment, useEffect, useState } from 'react'
import history from '../utils/history'

import MenuBar from '../common/MenuBar'
import UserStore from '../stores/user'
import DashboardStore from '../stores/dashboard'
import TransactionStore from '../stores/transaction'
import { Row, Col, Select, Input, Button } from 'antd'

const {Option} = Select

const Admin = () => {
  const userStore = useObservable(UserStore)
  const dashboardStore = useObservable(DashboardStore)
  const transactionStore = useObservable(TransactionStore)

  const [currencySource, setCurrencySource] = useState('THB')
  const [currencyDestination, setCurrencyDestination] = useState('THB')
  const [amount, setAmount] = useState(0)

  useEffect(() => {
    dashboardStore.getAccount()
  }, [dashboardStore])

  if (!userStore.isAuthenticated) {
    history.push(`/`)
    return <p>Unauthorized</p>
  }

  const handleSubmit = () => {
    transactionStore.doDeposit(currencySource, currencyDestination, amount)
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
        <h1>Deposit</h1>
        <Row>
          <Col>
            From Currency :{' '}
            <Select defaultValue="THB" value={currencySource} onChange={(v: string) => setCurrencySource(v)}>
              <Option value="THB">THB</Option>
              <Option value="USD">USD</Option>
              <Option value="SGD">SGD</Option>
              <Option value="CNY">CNY</Option>
              <Option value="EUR">EUR</Option>
              <Option value="JPY">JPY</Option>
            </Select>
          </Col>
        </Row>
        <Row>
          <Col>
            To Currency :{' '}
            <Select defaultValue="THB" value={currencyDestination} onChange={(v: string) => setCurrencyDestination(v)}>
              <Option value="THB">THB</Option>
              <Option value="USD">USD</Option>
              <Option value="SGD">SGD</Option>
              <Option value="CNY">CNY</Option>
              <Option value="EUR">EUR</Option>
              <Option value="JPY">JPY</Option>
            </Select>
          </Col>
        </Row>
        <Row>
          <Col>
            Amount :{' '}
            <Input placeholder="Amount" type="number" style={{width: '200px'}} value={amount} onChange={(e) => setAmount(Number.parseFloat(e.target.value))}></Input>
          </Col>
        </Row>
        <Row>
          <Col>
            <Button type="primary" onClick={() => handleSubmit()}>Deposit</Button>
          </Col>
        </Row>
      </MenuBar>
    </Fragment>
  )
}

export default observer(Admin)