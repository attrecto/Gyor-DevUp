import React from 'react';

import Request from './Request';

export default function RequestList(props) {
  const elements = props.elements;
  return (
    <table>
      <thead>
        <tr>
          <th>Name</th>
          <th>Email</th>
          <th>Company</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        {elements.map(elem => <Request 
          key={elem.user.id} 
          onApprove={props.approve}
          onReject={props.reject}
          {...elem}
        />)}
      </tbody>
    </table>
  )
}
