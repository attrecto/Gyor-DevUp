'use strict';

const gulp = require('gulp');

gulp.task('default', ['serve'], function() {
  gulp.watch('./assets/sass/**/*.scss', ['sass']);
  gulp.watch('./app/view/**/*.html', ['views']);
});
