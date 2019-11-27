import axios, { AxiosRequestConfig, AxiosResponse } from 'axios'

import { API_ENDPOINT } from '../config'
import { getToken } from './token-helper'

// request data
export const fetch = async (
  route: string,
  data: any = '',
  method: AxiosRequestConfig['method'] = 'GET'
) => {
  const authOptions: AxiosRequestConfig = {
    data,
    headers: {
      'Content-Type': 'application/json',
    },
    method,
    url: `${API_ENDPOINT}/${route}`
  }
  // do http request
  return axios(authOptions).then((res: AxiosResponse) => res).catch((res: AxiosResponse) => res)
}

// request data from token in localStorage
export const fetchWithToken = async (
  route: string,
  data: any = {},
  method: AxiosRequestConfig['method'] = 'POST',
  token: string | null = ''
) => {
  const authOptions: AxiosRequestConfig = {
    data,
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token || getToken()}`
    },
    method,
    url: `${API_ENDPOINT}/${route}`
  }
  // do http request
  try {
    const response = await axios(authOptions).then(
      (res: AxiosResponse) => res
    )

    // return response data
    return response
  } catch (e) {
    return e
  }
}