module.exports = function (grunt) {

    grunt.initConfig({
        //felolvassa a grunt modul-okat a csomagfájl alapján
        pkg: grunt.file.readJSON('package.json'),

        //temp könyvtár és előző build kitörlése
        clean: ["dist", '.tmp'],

        // másolási feladatok
        copy: {
            index: { // csak index fájl másolása
                src: 'client/index.html',
                dest: 'dist/client/index.html'
            },
            resources: {
                src: [ // olyan könyvtárak másolása, melyeknek a tartalmát nem dolgozzuk fel
                    'client/js/**/*',
                    'client/view/**/*',
                    'client/template/**/*',
                    'client/release/**/*',
                    'client/library/**/*',
                    'client/image/**/*',
                    'client/server/**/*',
                    'client/fonts/**/*',
                    'client/app/*.json'
                ],
                dest: 'dist/',
                dot: true
            },
            // betűtípusok megfelelő könyvtárba másolása
            fonts: {
                cwd: 'client/js/font-awesome-4.5.0/fonts',
                src: '**/*',
                flatten: true,
                dest: 'dist/client/fonts',
                expand: true
            },
            glyphicons: {
                cwd: 'client/js/fonts',
                src: '**/*',
                dest: 'dist/client/fonts',
                expand: true
            }
        },

        // felkészülés a usemin feladatra,
        // feldolgozzuk az index.html fájlt, és a dist könyvtárban létrehozzuk az átdolgozott változatot,
        // és concat-al összefűzzük a javascript fájlokat
        useminPrepare:  {
            html: 'client/index.html',
            options: {
                dest: 'dist/client'
            }
        },

        // egyedi fájlnevek generálása a cache kivédéséhez
        filerev: {
            options: {
                encoding: 'utf8',
                algorithm: 'md5',
                length: 20
            },
            release: {
                files: [{
                    src: [
                        'dist/client/js/*.js',
                        'dist/client/css/*.css'
                    ]
                }]
            }
        },

        // uglify beállítások
        uglify: {
            options: {
                mangle: false
            }
        },

        //usemin futtatása az index.html fájlon
        usemin: {
            html: ['dist/client/index.html']
        }

    });

    // A szükséges grunt modulokat matchdep-el automatikusan betöltjük
    require('matchdep').filterDev('grunt-*').forEach(grunt.loadNpmTasks);

    // Az alapértelmezett parancs futtatási sorrendje
    grunt.registerTask('default', [
        'clean','copy:index','useminPrepare','concat','cssmin','uglify','filerev', 'usemin', 'copy:resources', 'copy:fonts', 'copy:glyphicons'
    ]);

	// fejlesztői környezethez tartozó feladatlista, usemin és uglify nélkül
    grunt.registerTask('dev', [
        'clean','copy:index', 'copy:resources', 'copy:fonts', 'copy:glyphicons'
    ]);
};