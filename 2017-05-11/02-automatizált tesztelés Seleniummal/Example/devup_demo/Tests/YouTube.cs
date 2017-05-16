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
    class YouTube : TestBase
    {
        private const string BASE_URL = "https://www.youtube.com/";
        private const string GYOR_DEVUP = "Győr DevUp";

        public YouTube() : base(BASE_URL)
        {

        }
        
        [TestCase]
        public void YouTube_Search_OpenFirst()
        {
            Pages.YouTube.SearchFor(Driver, GYOR_DEVUP);
            var list = Pages.YouTube.GetResults(Driver);
            Assert.Greater(list.Count(), 10,
                $"There should be atleast 10 results, but there is only { list.Count() }");

            Pages.YouTube.OpenResult(list.First());
        }
    }
}
