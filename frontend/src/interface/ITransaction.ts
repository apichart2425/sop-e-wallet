interface ITransaction {
  account_destination: number
  account_source: number
  balance: number
  created_on: Date
  currency_source: string
  currency_destination: string
  id: number
  service: string
}

export default ITransaction