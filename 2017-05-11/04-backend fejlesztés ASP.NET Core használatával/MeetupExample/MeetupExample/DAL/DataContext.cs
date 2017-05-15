using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using JetBrains.Annotations;
using MeetupExample.Models;

namespace MeetupExample.DAL
{
    public class DataContext : DbContext
    {
        // Add Db tables here
        public DbSet<Item> Items { get; set; }

        public DataContext(DbContextOptions<DataContext> options) : base(options)
        {
            // Add db structure configuration here. Example Cascade on delete
        }
    }
}
