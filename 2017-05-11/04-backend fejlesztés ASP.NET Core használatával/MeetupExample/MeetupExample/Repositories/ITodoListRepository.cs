using MeetupExample.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace MeetupExample.Repositories
{
    public interface ITodoListRepository
    {
        IEnumerable<Item> Get();
        Item Get(int index);
        void Add(Item item);
        void Update(Item oldItem, Item newContent);
        void Remove(int index);
    }
}
