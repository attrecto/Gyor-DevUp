'use strict';

const gulp = require('gulp');
const browserify = require('browserify');
const watchify = require('watchify');
const babelify = require('babelify');
const source = require('vinyl-source-stream');
const path = require('path');
const util = require('gulp-util');
const browserSync = require('browser-sync');
const buffer = require('vinyl-buffer');
const uglify = require('gulp-uglify');
const gif = require('gulp-if');

module.exports = function() {
  var bundler, rebundle,
    debug = process.env.NODE_ENV !== 'production'

  bundler = browserify({
    entries: ['./app/main.js'],
    debug: debug,
    cache: {},
    packageCache: {},
    fullPaths: true
  });

  if (debug) {
    bundler = watchify(bundler);
  }

  bundler.transform(babelify);

  rebundle = function() {
    return bundler.bundle()
      .on('error', function(err) {
        util.beep();
        util.log(err.message);
        this.emit('end');
      })
        .pipe(source('bundle.js'))
      .pipe(gif(true, buffer()))
      .pipe(gif(true, uglify()))
      .pipe(gulp.dest('./public/js/'))
      .pipe(browserSync.stream());
  };
  if (debug) {
    bundler.on('update', function(ids) {
      const files = ids.map(id => path.basename(id)).join(',');
      util.log('Changed', files);
      return rebundle();
    });

    bundler.on('log', function(msg) {
      util.log(msg);
    });
  }
  return rebundle();
}
