'use strict';

const fs       = require('fs');
const jsFilter = require('./helper/scriptFilter');
let tasks      = fs.readdirSync('./gulp/tasks/').filter(jsFilter);

tasks.forEach(function(task){
	require('./tasks/' + task);
});
