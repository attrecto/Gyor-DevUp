'use strict';

const gulp        = require('gulp');
const sourcemaps  = require('gulp-sourcemaps');
const sass        = require('gulp-sass');
const browserSync = require('browser-sync');

gulp.task('sass', function(){
  gulp.src('./assets/sass/main.scss')
    .pipe(sourcemaps.init())
      .pipe(sass().on('error', sass.logError))
    .pipe(sourcemaps.write())
    .pipe(gulp.dest('./public/css/'))
    .pipe(browserSync.stream());
});
