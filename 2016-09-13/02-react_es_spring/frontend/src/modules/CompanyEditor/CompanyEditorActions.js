import { fetchError, FETCH_START } from '../App/AppActions';

import { FETCH_AUTH } from '../../util/auth';
import { initialize, reset } from 'redux-form';

import { fetchMe } from '../User/UserActions';

export const FETCH_MY_WORKERS = "FETCH_MY_WORKERS";
export const UPDATE_WORKER = "UPDATE_WORKER";
export const ADD_WORKER = "ADD_WORKER";
export const DELETE_WORKER = "DELETE_WORKER";

export const START_EDIT = "START_EDIT";
export const CANCEL_EDIT = "CANCEL_EDIT";

export const APPROVE_RESERVATION = "APPROVE_RESERVATION";
export const REJECT_RESERVATION = "REJECT_RESERVATION";

function doFetchMyWorkers(companyId) {
  return {
    [FETCH_AUTH]: {
      endpoint: `/companies/${companyId}/workers`,
      config: {
        method: 'GET',
      },
      types: {
        request: FETCH_START,
        fail: fetchError,
        success: FETCH_MY_WORKERS,
      }
    }
  }
}

export function fetchMyWorkers() {
  return (dispatch, getState) => {
    const { user: { companyId } } = getState();
    return dispatch(doFetchMyWorkers(companyId));
  }
}

export function startEdit(worker) {
  return (dispatch) => {
    dispatch({ type: START_EDIT, worker });
    dispatch(initialize('workerForm'));
  }
}

export function cancelEdit() {
  return (dispatch) => {
    dispatch({ type: CANCEL_EDIT });
    dispatch(initialize('workerForm'));
  }
}

function decideReservation(id, type, workerId) {
  const decision = (type === APPROVE_RESERVATION) ? 'APPROVE' : 'REJECT';
  return {
    [FETCH_AUTH]: {
      endpoint: '/workerReservations/decide',
      config: {
        method: 'POST',
        body: JSON.stringify({
          decision,
          reservationId: id,
        })
      },
      types: {
        request: FETCH_START,
        fail: fetchError,
        success: type
      },
      payload: {
        workerId,
        reservationId: id,
      }
    }
  }
}

export function approveReservation(reservationId, workerId) {
  return decideReservation(reservationId, APPROVE_RESERVATION, workerId);
}

export function rejectReservation(reservationId, workerId) {
  return decideReservation(reservationId, REJECT_RESERVATION, workerId);
}

export function addWorker(companyId, worker) {
  return {
    [FETCH_AUTH]: {
      endpoint: `/companies/${companyId}/workers`,
      config: {
        method: 'POST',
        body: JSON.stringify({
          name: worker.name,
          biography: worker.biography,
        })
      },
      types: {
        request: FETCH_START,
        fail: fetchError,
        success: ADD_WORKER
      },
      payload: worker
    }
  }
}

export function deleteWorker(companyId, workerId) {
  return {
    [FETCH_AUTH]: {
      endpoint: `/companies/${companyId}/workers/${workerId}`,
      config: {
        method: 'DELETE',
      },
      types: {
        request: FETCH_START,
        fail: fetchError,
        success: DELETE_WORKER
      },
      payload: workerId
    }
  }
}

export function updateWorker(companyId, workerId, worker) {
  return {
    [FETCH_AUTH]: {
      endpoint: `/companies/${companyId}/workers/${workerId}`,
      config: {
        method: 'PUT',
        body: JSON.stringify(worker)
      },
      types: {
        request: FETCH_START,
        fail: fetchError,
        success: UPDATE_WORKER
      },
      payload: worker
    }
  }
}
