import React from 'react';
import { Link } from 'react-router';

export function CompanyRow(props) {
  const { name, identifier, id, contact } = props;
  return (
    <tr>
      <td><Link to={`companies/${id}/${name}`}>{name}</Link></td>
      <td>{identifier}</td>
      <td>{contact.name}</td>
      <td>{contact.phone}</td>
      <td>{contact.email}</td>
    </tr>
  );
}

export default function CompaniesList(props) {
  const companies = props.companies;

  return (
    <table>
      <thead>
        <tr>
          <th>Name</th>
          <th>Identifier</th>
          <th>Contact name</th>
          <th>Contact phone</th>
          <th>Contact email</th>
        </tr>
      </thead>
      <tbody>
        {companies.map(c => <CompanyRow key={c.id} {...c}/>)}
      </tbody>
    </table>
  )
}
