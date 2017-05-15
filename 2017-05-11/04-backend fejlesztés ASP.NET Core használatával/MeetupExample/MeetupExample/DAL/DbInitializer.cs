using MeetupExample.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace MeetupExample.DAL
{
    public static class DbInitializer
    {
        public static void Initialize(DataContext context)
        {
            context.Database.EnsureCreated();

            // Check for seed data
            if (context.Items.Any())
            {
                return;
            }

            // Create db with test data
            context.Items.Add(new Item { Name = "test 1" });
            context.Items.Add(new Item { Name = "test 2" });
            context.Items.Add(new Item { Name = "test 3" });

            var itemLst = new List<Item>
            {
                new Item{ Name = "test 4" },
                new Item{ Name = "test 5" },
            };
            context.AddRange(itemLst);

            context.SaveChanges();
        }
    }
}
