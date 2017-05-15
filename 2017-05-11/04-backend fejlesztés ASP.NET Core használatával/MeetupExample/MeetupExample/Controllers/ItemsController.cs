using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using MeetupExample.Repositories;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using MeetupExample.Models;
using Microsoft.AspNetCore.Authorization;

namespace MeetupExample.Controllers
{
    //[Authorize]
    [Produces("application/json")]
    [Route("api/Items")]
    public class ItemsController : Controller
    {
        private readonly ITodoListRepository _context;

        public ITodoListRepository Context => _context;

        public ItemsController(ITodoListRepository context)
        {
            _context = context;
        }

        // GET: api/Items
        [HttpGet]
        public IActionResult Get()
        {
            var result = _context.Get();
            return Ok(result);
        }

        // GET: api/Items/5
        [HttpGet("{id}", Name = "GetItem")]
        public IActionResult Get(int id)
        {
            var result = _context.Get(id);

            if (result == null)
            {
                return NotFound();
            }

            return Ok(result);
        }
        
        // POST: api/Items
        [HttpPost]
        public IActionResult Post([FromBody]Item item)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.Add(item);
            return CreatedAtRoute("GetItem", new { id = item.Id }, item);
        }
        
        // PUT: api/Items/5
        [HttpPut("{id}")]
        public IActionResult Put(int id, [FromBody]Item item)
        {
            if (item == null || item.Id != id)
            {
                return BadRequest();
            }

            var originalItem = _context.Get(id);
            if (originalItem == null)
            {
                return NotFound();
            }

            _context.Update(originalItem, item);

            return new NoContentResult();
        }
        
        // DELETE: api/ApiWithActions/5
        [HttpDelete("{id}")]
        public IActionResult Delete(int id)
        {
            var result = _context.Get(id);

            if (result == null)
            {
                return NotFound();
            }

            _context.Remove(id);
            return Ok();
        }
    }
}
