import React, { Component } from 'react';

export function Status({status}) {
  return (
    <span>
    Status:
    {(() => {
      switch (status) {
        case "APPROVED":   return "Approved";
        case "REJECTED": return "Rejected";
        default:      return "Pending";
      }
    })()}
    </span>
  )
}

export function Reservation(props) {
  const { 
    startDate, 
    endDate, 
    status,
    own,
    id, 
    onApprove,
    onReject,
    bookingCompany: { 
      name 
    } 
  }= props;

  return (
    <li>
      From: {startDate}, 
      To: {endDate}, 
      By: {name}, 
      <Status status={status} />
      {(own) 
        ? <span> | <a href="javascript: void(0);" onClick={() => onApprove(id)}>Approve</a> | 
          <a href="javascript: void(0);" onClick={() => onReject(id)}>Reject</a></span>
        : null
      }
    </li>
  )
}

export class Reservations extends Component {
  constructor(props, context) {
    super(props, context);
  }

  render() {
    const { reservations, own, onApprove, onReject } = this.props;
    
    const reservation = (r) => <Reservation 
      onApprove={onApprove}
      onReject={onReject}
      own={own} 
      key={r.id} 
      {...r}
    />

    return (
      <ul>
        {reservations && reservations.map(reservation)}
      </ul>
    )
  }
}

export class ReservePanel extends Component {
  constructor(props, context) {
    super(props, context);
    this.clickHandler = this.clickHandler.bind(this);
  }
  
  clickHandler() {
    const reservation = {
      workerId: this.props.worker.id,
      startDate: this.refs.from.value,
      endDate: this.refs.to.value,
    };
    this.props.onReserve(reservation);

    this.refs.from.value = "";
    this.refs.to.value = "";
  }

  render() {
    return (
      <div>
        <label>From</label>
        <input type="date" ref="from" />
        <label>To</label>
        <input type="date" ref="to" />
        <br />
        <input type="button" className="button-primary" onClick={this.clickHandler} value="reserve" />
      </div>
    )
  }
}

export default function CompanyWorker(props) {
  const { worker, own, onReject, onApprove } = props; 
  return (
    <tr>
      <td>{worker.name}</td>
      <td>{worker.biography}</td>
      <td><Reservations own={own} onReject={(id) => onReject(id, worker.id)} onApprove={(id) => onApprove(id, worker.id)} reservations={worker.reservations} /></td>
      {!own && <td><ReservePanel worker={worker} onReserve={props.onReserve} /></td>}
      {own && <td><a href="javascript: void(0);" onClick={() => props.onEdit(worker)}>Edit</a></td>} 
      {own && <td><a href="javascript: void(0);" onClick={() => props.onDelete(worker)}>Delete</a></td>}
    </tr>
  );
};
