'use strict';

const gulp    = require('gulp');
const scripts = require('../helper/browserify');

gulp.task('browserify', function(){
	return scripts(true);
});
