const proxy = require('http-proxy-middleware');
module.exports = function (app) {
  app.use(
    '/services',
    proxy({
      target: 'https://sop.wiput.me',
      changeOrigin: true,
    })
  );
};