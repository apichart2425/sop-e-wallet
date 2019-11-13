const express = require('express')
const bodyParser = require('body-parser')
const fetch = require('node-fetch')
const axios = require('axios')
const cron = require('node-cron');
const app = express()
let currency = {}
let list_currency = ['USD', 'THB', 'JPY', 'CNY', 'EUR', 'SGD']
let base = "THB"

//  set schedule fetch_one_hours()
let task = cron.schedule('0 0 */1 * * *', () => {
  console.log('\nrunning a task every 1 hours');
  fetch_one_hours(base)
});
task.start()

app.use(bodyParser.json())
app.use(bodyParser.urlencoded({
  extended: true
}))

// app.get('/', (req, res) => {
//   res.send('Currency Exchange Service method GET /services/exchange to get currency exchage (base on THB) or /services/exchange?base=currency currency support [USD, THB, JPY, CNY, EUR, SGD]')
// })

// get currency exchange base THB
app.get('/', (req, res) => {
  let ip = req.headers['x-forwarded-for'] || req.connection.remoteAddress;
  console.log(ip,"/currency?base=",req.query.base)
  if (req.query.base === undefined || req.query.base === null || list_currency.indexOf(req.query.base.toUpperCase()) === -1) {
    res.send(currency['THB'])
  } else {
    res.send(currency[req.query.base.toUpperCase()])
  }
})

// ------------------ Server Config --------------------------------------------
let server = app.listen(3000, function () {
  let host = server.address().address
  let port = server.address().port
  console.log('Listening at http://%s:%s', host, port)
  fetch_one_hours(base)
  console.log("Start! fetch currency")
});

// fetch data every 1 hour
async function fetch_one_hours() {
  // build api URL with user zip
  console.log("currency has been updated")
  await list_currency.forEach(base_on => {
    const baseUrl = 'https://api.exchangeratesapi.io/latest?base=' + base_on
    axios.get(baseUrl)
      .then((res) => currency[base_on] = res.data)
  });

}