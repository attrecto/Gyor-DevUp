using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using MeetupExample.Models;
using System.Diagnostics;

namespace MeetupExample.Repositories
{
    public class TodoListRepository : ITodoListRepository
    {
        private readonly DAL.DataContext _context;

        public TodoListRepository(DAL.DataContext context)
        {
            _context = context;
        }

        public void Add(Item item)
        {
            _context.Items.Add(item);
            SaveDb();
        }

        public IEnumerable<Item> Get()
        {
            return _context.Items.Where(x => !x.IsDeleted);
        }

        public Item Get(int index)
        {
            return _context.Items.SingleOrDefault(x => !x.IsDeleted && x.Id == index);
        }

        public void Remove(int index)
        {
            var corpse = _context.Items.SingleOrDefault(x => !x.IsDeleted && x.Id == index);
            if (corpse != null)
            {
                corpse.IsDeleted = true;
                SaveDb(); 
            }
        }

        public void Update(Item oldItem, Item newContent)
        {
            if (oldItem != null)
            {
                oldItem.IsCompleted = newContent.IsCompleted;
                oldItem.Name = newContent.Name;
            }
            _context.Items.Update(oldItem);

            SaveDb();
        }

        private void SaveDb()
        {
            try
            {
                _context.SaveChanges();
            }
            catch (Exception ex)
            {
                // Add Logging logic here
                Debug.WriteLine("Error while saving the db!");
                Debug.WriteLine(ex.Message);
            }
        }
    }
}
