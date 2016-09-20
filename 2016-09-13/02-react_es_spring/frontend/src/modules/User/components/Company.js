import React from 'react';

export default function Company(props) {
  const { company } = props;
  const { name, identifier, contact } = company;

  return (
    <section>
      <h3>{name}</h3>
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
          <tr>
            <td>{name}</td>
            <td>{identifier}</td>
            <td>{contact.name}</td>
            <td>{contact.phone}</td>
            <td>{contact.email}</td>
          </tr>
        </tbody>
      </table>
    </section>
  )
}
