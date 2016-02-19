'use strict';
var page = require("../../Pos/Common");
describe ("Angularjs.org webpage test - ", function(){

  beforeAll(function(){
    browser.driver.manage().window().maximize();
    browser.driver.get("https://angularjs.org/");
  });

  it("Verify to load angularjs.org homepage", function (){
    var ele = element.all(by.css("ul[class='nav'] > li")).get(1);
    expect(page.getElementStateAsync(ele)).toBe("active");
  });

  it("Verify to navigate Tutorial page", function (){
    var devMenu = element.all(by.css("li > a[class='dropdown-toggle']")).get(1);
    var tutorial = element.all(by.css("ul[class='dropdown-menu'] > li > a[href*='tutorial']")).get(1);

    devMenu.click();
    browser.sleep(2000);
    tutorial.click();
    browser.sleep(2000);
    expect(browser.driver.getCurrentUrl()).toContain("tutorial");
  });

  it("Verify search functionality", function(){
    var searchBox = element(by.css("input[class*='search']"));
    searchBox.sendKeys("api reference").then(function (){
      return browser.sleep(2000);
    }).then(function (){
      var searchElements = element.all(by.repeater("item in value")).all(by.css("li[class='search-result ng-scope'] > a")).getText();
      expect(searchElements).toContain(jasmine.stringMatching(/api reference/i));
    });
  });

});
