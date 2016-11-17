// Karma configuration

module.exports = function(config) {
  config.set({

    // base path that will be used to resolve all patterns (eg. files, exclude)
    basePath: '',

    plugins : [
      'karma-jasmine',
      'karma-firefox-launcher',
    ],

    // frameworks to use
    // available frameworks: https://npmjs.org/browse/keyword/karma-adapter
    frameworks: ['jasmine'],


    // list of files / patterns to load in the browser
    files: [
      'src/main/webapp/plugins/angularjs/angular.min.js',
      'src/main/webapp/plugins/angularjs/angular-route.min.js',
      'src/main/webapp/plugins/angularjs/angular-resource.min.js',
      'src/main/webapp/plugins/angularjs/angular-sanitize.min.js',
      'src/main/webapp/plugins/angularjs/angular-mocks.js',
      'src/main/webapp/plugins/angularjs/ui-bootstrap-2.0.2.min.js',
      'src/main/webapp/plugins/angularjs/ui-bootstrap-tpls-1.3.2.min.js',
      'src/main/webapp/app/module.js',
      'src/main/webapp/app/module-config.js',
      'src/main/webapp/app/quarter/quarter-controller.js',
      'src/main/webapp/app/quarter/quarter-service.js',
      'src/main/webapp/app/datacenter/datacenter-controller.js',
      'src/test/**/*.js'
    ],


    // list of files to exclude
    exclude: [
	'/src/main/webapp/plugins/bootstrap/**/*.js'
    ],


//    // preprocess matching files before serving them to the browser
//    // available preprocessors: https://npmjs.org/browse/keyword/karma-preprocessor
//    preprocessors: {
//	  'src/**/*.js': ['coverage']
//	},
//	
//
//    // test results reporters to use
//    // available reporters: https://npmjs.org/browse/keyword/karma-reporter
//    reporters: ['dots','coverage','junit','progress'],
//    junitReporter : {
//    	outputDir: 'build/test-results',
//    	outputFile: 'karma-test-results.xml'
//    },
//    coverageReporter: {
//    	dir: 'build',
//    	subdir: 'test-results',
//    	type: 'cobertura'
//    },
	

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
    browsers: ['Firefox'],


    // Continuous Integration
    // if true, Karma captures browsers, runs the tests and exits
    singleRun: true,

    // Concurrency level
    // how many browser should be started simultaneous
    concurrency: Infinity
  })
}
