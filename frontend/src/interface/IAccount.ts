import IWallet from "./IWallet";

interface IAccount {
  id: number
  userId: number
  wallet: IWallet
}

export default IAccount
