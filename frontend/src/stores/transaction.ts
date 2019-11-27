import { action, observable } from 'mobx'
import { fetchWithToken } from '../utils/fetch'
import history from '../utils/history'
import IAccount from '../interface/IAccount'
import notification from '../utils/notification'
import ITransaction from '../interface/ITransaction'


class Transaction {
  @observable public account = {} as IAccount
  @observable public transactions = [] as ITransaction[]

  @action
  public async doDeposit(source: string, destination: string, amount: number) {
    const api = await fetchWithToken(
      'account/deposit',
      {
        action: "deposit",
        currency_source: source,
        currency_destination: destination,
        balance: amount
      },
      'POST'
    )

    if (api.status === 200) {
      notification('success', 'Deposit completed!', '')
      return history.push('/dashboard')
    }
  }

  @action
  public async doTransfer(source: string, destination: string, amount: number, account: number) {
    const api = await fetchWithToken(
      'account/transfer',
      {
        action: "transfer",
        currency_source: source,
        currency_destination: destination,
        balance: amount,
        destination_user_id: account
      },
      'POST'
    )

    if (api.status === 200) {
      notification('success', 'Transfer completed!', '')
      return history.push('/dashboard')
    }
  }

  @action
  public async getTransaction() {
    const api = await fetchWithToken(
      'account/transactions',
      {},
      'GET'
    )

    if (api.status === 200) {
      this.transactions = api.data.payload
    }
  }

  @action
  public async doWithdraw(source: string, amount: number) {
    const api = await fetchWithToken(
      'account/withdraw',
      {
        action: "withdraw",
        currency_source: source,
        balance: amount
      },
      'POST'
    )

    if (api.status === 200) {
      notification('success', 'Withdraw completed!', '')
      return history.push('/dashboard')
    }
  }
}

export default new Transaction()
