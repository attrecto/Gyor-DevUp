using OpenQA.Selenium;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace devup_demo.Pages
{
    class Google
    {
        private const string SEARCHFIELD_ID = "lst-ib";
        private const string SEARCHFIELD_NAME = "q";
        private const string SEARCHFIELD_CONTAINER_CLASS = "lst-c";
        private const string INPUT_TAG = "input";
        private const string LINK_TAG = "a";
        private const string RESULT_CONTAINER_CLASS = "g";
        private const string HEADER3_TAG = "h3";

        public static void SearchFor(IWebDriver Driver, string text)
        {
            IWebElement searchField = GetSearchField(Driver);
            searchField.SendKeys(text);
            searchField.SendKeys(Keys.Return);

            Thread.Sleep(3000);
        }

        public static IWebElement GetSearchField(IWebDriver Driver)
        {
            return Driver.FindElement(By.Id(SEARCHFIELD_ID));

            //return Driver.FindElement(By.Name(SEARCHFIELD_NAME));

            //IWebElement containerDiv = Driver.FindElement(By.ClassName(SEARCHFIELD_CONTAINER_CLASS));
            //return containerDiv.FindElement(By.TagName(INPUT_TAG));
        }
       
        public static IWebElement GetResultByTitle(IWebDriver Driver, string title)
        {
            List<IWebElement> results = GetResults(Driver);
            
            foreach (var result in results)
            {
                if (GetResultTitle(result) == title)
                    return result;
            }
            return null;
        }

        public static List<IWebElement> GetResults(IWebDriver Driver)
        {
            return Driver.FindElements(By.ClassName(RESULT_CONTAINER_CLASS)).ToList();
        }

        private static string GetResultTitle(IWebElement result)
        {
            IWebElement header = result.FindElement(By.TagName(HEADER3_TAG));
            return header.Text;
        }

        public static void ClickResultLink(IWebElement result)
        {
            IWebElement link = result.FindElement(By.TagName(LINK_TAG));
            link.Click();
        }
    }
}
