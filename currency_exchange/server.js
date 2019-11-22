const express = require('express')
const bodyParser = require('body-parser')
const axios = require('axios')
const cron = require('node-cron');
const app = express()
let currency = {}
let list_currency = ['USD', 'THB', 'JPY', 'CNY', 'EUR', 'SGD']

//  set schedule fetch_one_hours()
let task = cron.schedule('0 0 */1 * * *', () => {
  console.log('\nRunning a task every 1 hour');
  fetch_one_hours()
});
task.start()

app.use(bodyParser.json())
app.use(bodyParser.urlencoded({
  extended: true
}))

function createJsonResponse(status, payload) {
  return {
    status,
    payload
  }
}

// get currency exchange base THB
app.get('/', (req, res) => {
  const base = req.query.base
  if (base === undefined || base === null) {
    return res.send(createJsonResponse('success', currency['THB']))
  } else if (list_currency.indexOf(base.toUpperCase()) === -1) {
    return res.send(createJsonResponse('error', { message: 'Unsupport currency' }))
  } else {
    return res.send(createJsonResponse('success', currency[base.toUpperCase()]))
  }
})

// ------------------ Server Config --------------------------------------------
let server = app.listen(3000, function () {
  let port = server.address().port
  console.log('Listening at port %s', port)
  fetch_one_hours()
  console.log("Start!")
});

// fetch data every 1 hour
function fetch_one_hours() {
  const currency_fetch = list_currency.map(async base_on => {
    const baseUrl = 'https://api.exchangeratesapi.io/latest?base=' + base_on
    let response = await axios.get(baseUrl)

    currency[base_on] = response.data.rates
  });

  Promise.all(currency_fetch)
  console.log("Currency rates have been updated")
}