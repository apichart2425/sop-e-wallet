import { action, observable } from 'mobx'
import { fetchWithToken } from '../utils/fetch'
import IAccount from '../interface/IAccount'


class Dashboard {
  @observable public account = {} as IAccount

  @action
  public async getAccount() {
    const api = await fetchWithToken(
      'account/account',
      {},
      'GET'
    )

    if (api.status === 200) {
      this.account = api.data
    }
  }
}

export default new Dashboard()