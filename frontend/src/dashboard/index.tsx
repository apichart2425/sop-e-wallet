import { observer, useObservable } from 'mobx-react-lite'
import React, { Fragment, useEffect } from 'react'
import history from '../utils/history'

import MenuBar from '../common/MenuBar'
import UserStore from '../stores/user'
import DashboardStore from '../stores/dashboard'
import Container from '../common/Container'
import Box from '../common/Box'

const Admin = () => {
  const userStore = useObservable(UserStore)
  const dashboardStore = useObservable(DashboardStore)

  useEffect(() => {
    dashboardStore.getAccount()
  }, [dashboardStore])

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
        <h1>Balance</h1>
        {dashboardStore.account.wallet &&
          <Container size={3}>
            <Box>
              <h1>{dashboardStore.account.wallet.thb.toFixed(2)}</h1>
              <span>THB</span>
            </Box>
            <Box>
              <h1>{dashboardStore.account.wallet.usd.toFixed(2)}</h1>
              <span>USD</span>
            </Box>
            <Box>
              <h1>{dashboardStore.account.wallet.sgd.toFixed(2)}</h1>
              <span>SGD</span>
            </Box>
            <Box>
              <h1>{dashboardStore.account.wallet.cny.toFixed(2)}</h1>
              <span>CNY</span>
            </Box>
            <Box>
              <h1>{dashboardStore.account.wallet.eur.toFixed(2)}</h1>
              <span>EUR</span>
            </Box>
            <Box>
              <h1>{dashboardStore.account.wallet.jpy.toFixed(2)}</h1>
              <span>JPY</span>
            </Box>
          </Container>
        }
      </MenuBar>
    </Fragment>
  )
}

export default observer(Admin)