import { FETCH_START, FETCH_SUCCESS, fetchError } from '../App/AppActions';
import { FETCH_AUTH } from '../../util/auth';

export const SIGNUP_REQUESTS = 'SIGNUP_REQUESTS';
export const APPROVED = 'APPROVED';
export const REJECTED = 'REJECTED';

export function fetchSignupRequests() {
  return {
    [FETCH_AUTH]: {
      endpoint: '/registrations/pending',
      config: {
        method: 'GET',
      },
      types: {
        request: FETCH_START,
        fail: fetchError,
        success: SIGNUP_REQUESTS
      }
    }
  }
}

export function approve(user) {
  return {
    [FETCH_AUTH]: {
      endpoint: '/registrations/pending/decide',
      config: {
        method: 'POST',
        body: JSON.stringify({
          userId: user.id,
          decision: 'APPROVE'
        })
      },
      types: {
        request: FETCH_START,
        fail: fetchError,
        success: APPROVED
      },
      payload: user
    }
  }
}

export function reject(user) {
  return {
    [FETCH_AUTH]: {
      endpoint: '/registrations/pending/decide',
      config: {
        method: 'POST',
        body: JSON.stringify({
          userId: user.id,
          decision: 'REJECT',
        })
      },
      types: {
        request: FETCH_START,
        fail: fetchError,
        success: REJECTED
      },
      payload: user
    }
  }
}
