import React from 'react';
import { Link } from 'react-router';
import { Logout } from '../User';

export default function Navbar({ authenticated, authorities }) {
  return (
    <nav>
      <section className="container">
        <Link className="nav-title" to="/">Huree</Link>
        <ul className="nav-list float-right">
          { (authenticated) ? <li><Link to="/">My Profile</Link></li> : null }
          { (authenticated) ? <li><Link to="/mycompany">My Company</Link></li> : null }
          { (!authenticated) ? <li><Link to="/signup">Sign up</Link></li> : null }
          { (!authenticated) ? <li><Link to="/signin">Sign in</Link></li> : null }
          { (authenticated && authorities.includes('APPROVE_USER')) ? <li><Link to="/signup/requests">Signup requests</Link></li> : null }
          { (authenticated && authorities.includes('VIEW_COMPANIES')) ? <li><Link to="/companies">Companies</Link></li> : null }
          { (authenticated) ? <li><Logout /></li> : null }
        </ul>
      </section>
    </nav>
  )
}
