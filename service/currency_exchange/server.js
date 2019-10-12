const express = require('express')
const bodyParser = require('body-parser')
const fetch = require('node-fetch')
const axios = require('axios')
const app = express()
let currency = {}
// let currency = fetch_one_hours()

// set timer
setInterval(fetch_one_hours,60000)

app.use(bodyParser.json())
app.use(bodyParser.urlencoded({
  extended: true
}))

app.get('/', (req, res) => {
  res.send('Currency Exchange Service')
})

// get currency exchange base THB
app.get('/currency', (req, res) => {
  console.log("/currency")
  res.send(currency)
})

app.listen(3000, () => {
  console.log('Start server at port 3000.')
})

// fetch data every 1 hour
async function fetch_one_hours() {
  // build api URL with user zip
  console.log("currency has been updated")
  const baseUrl = 'https://api.exchangeratesapi.io/latest?base=THB';
  await axios.get(baseUrl)
    .then((res) => currency = res.data)
}