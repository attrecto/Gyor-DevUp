'use strict';

const gulp        = require('gulp');
const template    = require('gulp-angular-templatecache');

gulp.task('views', function(){
  return gulp.src('app/view/**/*.html')
    .pipe(template({
      standalone: true
    }))
    .pipe(gulp.dest('app'));
});
