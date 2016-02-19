var Jasmine2HtmlReporter = require('protractor-jasmine2-html-reporter');
exports.config = {

  // The address of a running selenium server.
  seleniumAddress: null,
  // Capabilities to be passed to the webdriver instance.
  capabilities: {
    'browserName': 'chrome'
  },
  framework: 'jasmine',
  getPageTimeout: 5000,

  // Spec patterns are relative to the configuration file location passed
  // to protractor (in this example conf.js).
  // They may include glob patterns.
  specs: ['specs/navigation-functionality/1.navigation-spec.js'],

  // Options to be passed to Jasmine-node.
  jasmineNodeOpts: {
    showColors: true // Use colors in the command line report.
  },

  onPrepare: function() {
    jasmine.getEnv().addReporter(
        new Jasmine2HtmlReporter({
          savePath: './Reports/',
          screenshotsFolder: 'images'
        })
    );
  }
};
