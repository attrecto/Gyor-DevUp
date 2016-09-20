require('dotenv').load();
if (process.env.NODE_ENV === 'development') {
    module.exports = require('./webpack.config.dev.js');
} else {
    module.exports = require('./webpack.config.prod.js')
}