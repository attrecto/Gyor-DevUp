'use strict';

let path = require('path');

module.exports = (name) => /(\.(js)$)/i.test(path.extname(name));
