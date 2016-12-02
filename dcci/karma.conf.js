// Karma configuration

module.exports = function(config) {
  config.set({

    // base path that will be used to resolve all patterns (eg. files, exclude)
    basePath: '',

    plugins : [
      'karma-jasmine',
      'karma-coverage',
      'karma-chrome-launcher',
      'karma-firefox-launcher',
      'karma-ie-launcher',
      'karma-phantomjs-launcher',
      'karma-junit-reporter',
      'karma-istanbul-preprocessor'
    ],

    // frameworks to use
    // available frameworks: https://npmjs.org/browse/keyword/karma-adapter
    frameworks: ['jasmine'],


    // list of files / patterns to load in the browser
    files: [
      'src/main/java/*',
      'src/main/resources/public/app/*',
      'src/main/resources/public/index.html',
      'src/test/*'
    ],


    // list of files to exclude
    exclude: [
	'/src/main/resources/public/assets/css/*',
	'/src/main/resources/public/plugins/**/*'
    ],


    // preprocess matching files before serving them to the browser
    // available preprocessors: https://npmjs.org/browse/keyword/karma-preprocessor
    preprocessors: {
	  'src/**/*.js': ['coverage']
	},
	

    // test results reporters to use
    // available reporters: https://npmjs.org/browse/keyword/karma-reporter
    reporters: ['dots','coverage','junit','progress'],
    junitReporter : {
    	outputDir: 'target/test-reports',
    	outputFile: 'karma-test-reports.xml'
    },
    coverageReporter: {
    	dir: 'target',
    	subdir: 'test-reports',
    	type: 'cobertura'
    },
	

    // web server port
    port: 9876,


    // enable / disable colors in the output (reporters and logs)
    colors: true,


    // level of logging
    // possible values: config.LOG_DISABLE || config.LOG_ERROR || config.LOG_WARN || config.LOG_INFO || config.LOG_DEBUG
    logLevel: config.LOG_WARN,


    // enable / disable watching file and executing tests whenever any file changes
    autoWatch: true,


    // start these browsers
    // available browser launchers: https://npmjs.org/browse/keyword/karma-launcher
    browsers: ['PhantomJS'],


    // Continuous Integration
    // if true, Karma captures browsers, runs the tests and exits
    singleRun: true,

    // Concurrency level
    // how many browser should be started simultaneous
    concurrency: Infinity
  })
}
