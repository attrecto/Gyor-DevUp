using OpenQA.Selenium;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace devup_demo.Pages
{
    class YouTube
    {
        private const string SEARCHFIELD_ID = "masthead-search-term";
        private const string RESULTS_CONTAINER_ID = "results";
        private const string RESULTS_ITEMS_XPATH = "./ol/li/ol/li";
        private const string RESULT_HEADER_LINK = ".//h3/a";

        public static void SearchFor(IWebDriver Driver, string text)
        {
            IWebElement searchField = Driver.FindElement(By.Id(SEARCHFIELD_ID));
            searchField.SendKeys(text);
            searchField.SendKeys(Keys.Return);

            Thread.Sleep(3000);
        }

        public static List<IWebElement> GetResults(IWebDriver Driver)
        {
            IWebElement result_container = Driver.FindElement(By.Id(RESULTS_CONTAINER_ID));
            return result_container.FindElements(By.XPath(RESULTS_ITEMS_XPATH)).ToList();
        }

        public static void OpenResult(IWebElement Result)
        {
            IWebElement header_link = Result.FindElement(By.XPath(RESULT_HEADER_LINK));
            header_link.Click();
            Thread.Sleep(3000);
        }
    }
}
