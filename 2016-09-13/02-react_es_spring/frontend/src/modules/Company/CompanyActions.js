import { fetchError, FETCH_START } from '../App/AppActions';
import { FETCH_AUTH } from '../../util/auth';

export const FETCH_INFO = "FETCH_INFO";
export const FETCH_WORKERS = "FETCH_WORKERS";
export const COMPANIES_LIST = 'COMPANIES_LIST';
export const RESERVE_WORKER = "RESERVE_WORKER";

export function fetchCompaniesList() {
  return {
    [FETCH_AUTH]: {
      endpoint: '/companies',
      config: {
        method: 'GET',
      },
      types: {
        request: FETCH_START,
        fail: fetchError,
        success: COMPANIES_LIST
      }
    }
  }
}

export function fetchWorkers(companyId) {
  return {
    [FETCH_AUTH]: {
      endpoint: `/companies/${companyId}/workers`,
      config: {
        method: 'GET',
      },
      types: {
        request: FETCH_START,
        fail: fetchError,
        success: FETCH_WORKERS
      }
    }
  }
}


export function reserveWorker(reservation, bookingCompany) {
  const { companyId, workerId, startDate, endDate } = reservation;
  return {
    [FETCH_AUTH]: {
      endpoint: `/companies/${companyId}/workers/${workerId}/reserve`,
      config: {
        method: 'POST',
        body: JSON.stringify({
          startDate,
          endDate
        })
      },
      types: {
        request: FETCH_START,
        fail: fetchError,
        success: RESERVE_WORKER
      },
      payload: {
        reservation,
        bookingCompany
      }
    }
  }
}
