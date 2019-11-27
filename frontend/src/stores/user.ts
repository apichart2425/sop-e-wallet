import { action, observable } from 'mobx'
import { create, persist } from 'mobx-persist'
import { fetch, fetchWithToken } from '../utils/fetch'
import history from '../utils/history'
import { saveToken, getToken, removeToken } from '../utils/token-helper'
import IUser from '../interface/IUser'
import notification from '../utils/notification'


class User {
  @persist @observable public isAuthenticated: boolean = false;
  @persist('object') @observable public profile = {} as IUser

  @action
  public async authenticate(username: string, password: string) {
    // do authen here
    const api = await fetch('auth/authenticate', { username, password }, 'POST')

    if (api.status === 200) {
      saveToken(api.data.accessToken)

      const getProfile = await fetchWithToken(
        'auth/me',
        {},
        'GET',
        api.data.accessToken
      )

      if (getProfile.status === 200) {
        this.profile = getProfile.data
        this.isAuthenticated = true
        notification('success', 'Login Success!', `Welcome ${this.profile.name}!`)
        return history.push('/dashboard')
      }
    } else {
      notification('error', 'Invalid credential!', 'Incorrect username or password')
    }
  }

  @action
  public async checkAuthentication() {
    const api = await fetchWithToken(
      'auth/me',
      {},
      'GET',
      getToken()
    )

    if (api.status === 200) {
      return history.push('/dashboard')
    }
  }

  @action
  public async doLogout() {
    removeToken()
    this.isAuthenticated = false
    this.profile = {} as IUser
    return history.push('/')
  }
}

const hydrate = create({ jsonify: true })

const UserStore = new User()

export default UserStore
hydrate('user', UserStore)