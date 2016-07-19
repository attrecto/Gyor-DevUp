'use strict';

const gulp = require('gulp');
const lint = require('gulp-eslint');

gulp.task('lint', function(){
  return gulp.src('./app/**/*.js')
    .pipe(lint())
    .pipe(lint.format())
    .pipe(lint.failOnError());
});
