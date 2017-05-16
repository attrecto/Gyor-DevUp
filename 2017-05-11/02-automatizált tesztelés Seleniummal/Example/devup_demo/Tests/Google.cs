using NUnit.Framework;
using OpenQA.Selenium;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace devup_demo.Tests
{
    [Parallelizable]
    class Google : TestBase
    {
        private const string BASE_URL = "http://www.google.com/";
        private const string SELENIUM = "Selenium";
        private const string GYOR_DEVUP = "Győr DevUp";
        private const string DEVUP_FB_TEXT = "Győr DevUp - Kezdőlap | Facebook";

        public Google() : base(BASE_URL)
        {

        }
        
        [TestCase]
        public void Google_CheckResultsCount()
        {
            Pages.Google.SearchFor(Driver, SELENIUM);
            List<IWebElement> results = Pages.Google.GetResults(Driver);
            Assert.Greater(results.Count(), 5,
                $"The list should contain atleast 5 results, but it contains: { results.Count() }");
        }

        [TestCase]
        public void Google_DevUpFacebook()
        {
            Pages.Google.SearchFor(Driver, GYOR_DEVUP);
            IWebElement devup_fb_element 
                = Pages.Google.GetResultByTitle(Driver, DEVUP_FB_TEXT);
            Assert.IsNotNull(devup_fb_element, 
                $"Result with title { DEVUP_FB_TEXT } was not found");

            Pages.Google.ClickResultLink(devup_fb_element);
        }
        
    }
}
