import React from 'react';

export default function Request(props) {
  const { name, email } = props.user;
  const company = props.company.name;

  return (
    <tr>
      <td>{name}</td>
      <td>{email}</td>
      <td>{company}</td>
      <td>
        <a href="javascript: void(0);" onClick={() => props.onApprove(props.user)}>Approve</a> | 
        <a href="javascript: void(0);" onClick={() => props.onReject(props.user)}>Reject</a>
      </td>
    </tr>
  )
}