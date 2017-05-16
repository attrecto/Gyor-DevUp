using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using NUnit.Framework;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using System.Threading;
using System.Drawing;
using OpenQA.Selenium.Remote;

namespace devup_demo
{
    [TestFixture]
    public class TestBase
    {
        public enum WindowOrientation { Left, Right }
        private IWebDriver driver;
        public IWebDriver Driver
        {
            get { return driver; }
        }

        private string base_url = "";

        public TestBase(string BaseURL)
        {
            this.base_url = BaseURL;
            //driver = new ChromeDriver();

            DesiredCapabilities capabilities = DesiredCapabilities.Chrome();
            driver = new RemoteWebDriver(
                new Uri("http://localhost:4444/wd/hub"), capabilities);

            driver.Manage().Window.Maximize();
        }

        [SetUp]
        public void SetUp()
        {
            driver.Navigate().GoToUrl(this.base_url);
            Thread.Sleep(3000);
        }

        [TearDown]
        public void TearDown()
        {
            Thread.Sleep(5000);
        }

        [OneTimeTearDown]
        public void OneTimeTearDown()
        {
            driver.Dispose();
        }

    }
}
