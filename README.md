# E-Wallet

Project of Service-oriented Programming

# Architecture

![Architecture](assets/architecture.png)

1. Transactional Service - Deposit, Withdrawal, Exchange and Transfer
2. Account Service - Create, Get, Update and Delete Wallet
3. Authentication Service - Authenticate user
4. Currency Exchange Service - Provice currency exchange rate from external API

# Service Features

## Transactional Service
- Deposit money to wallet
- Withdraw money to external provider
- Transfer money to another wallet
- Exchange money with in wallet

## Account Service
- Get current wallet balance
- Update wallet balance
- Delete wallet on account deactivate

## Authentication Service
- Provide simple authentication using JWT
- Generate token for another service

## Currency Exchange Service
- Provice exchange rate with hourly update

# API Endpoints

## Transactional Service
- `/service/transaction/deposit`
- `/service/transaction/withdraw`
- `/service/transaction/exahange`
- `/service/transaction/transfer`

## Account Service
- `/service/account`

## Authentication Service
- `/service/auth/register`
- `/service/auth/login`
- `/service/auth/logout`
- `/service/auth/me`

## Currency Exchange Service
- `/service/exchange`

Remark : sample request and response coming soon
