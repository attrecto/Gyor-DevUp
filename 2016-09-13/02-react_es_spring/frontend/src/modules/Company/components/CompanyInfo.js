import React from 'react';

export function CompanyRow(props) {
  const { name, identifier, contact } = props;
  return (
    <tr>
      <td>{name}</td>
      <td>{identifier}</td>
      <td>{contact.name}</td>
      <td>{contact.phone}</td>
      <td>{contact.email}</td>
    </tr>
  );
}

export default function CompanyInfo(props) {
  const { company } = props;

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
        { company ? <CompanyRow {...company}/> : null }
      </tbody>
    </table>
  )
}
