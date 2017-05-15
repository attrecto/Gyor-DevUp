using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace MeetupExample.Models
{
    public class Item
    {
        public int Id { get; set; }

        public string Name { get; set; }
        public bool IsCompleted { get; set; }
        public bool IsDeleted { get; set; }

        [Timestamp]
        public byte[] RowVersion { get; set; }
    }
}
