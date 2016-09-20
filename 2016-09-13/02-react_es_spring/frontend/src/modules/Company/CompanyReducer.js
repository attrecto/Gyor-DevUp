import { FETCH_WORKERS, COMPANIES_LIST, RESERVE_WORKER } from './CompanyActions';

const defaultState = {
  workers: [],
  list: [],
  reserved: false,
};

function reservation(state, action) {
  const payload = action.payload;
  const { reservation, bookingCompany } = payload;

  switch (action.type) {
    case RESERVE_WORKER:
      if (state.id !== reservation.workerId) {
        return state;
      }

      const newReservation = {...reservation, bookingCompany};
      return {...state,  reservations: [...state.reservations, newReservation]};
  }
}

export default function company(state = defaultState, action) {
  switch (action.type) {
    case COMPANIES_LIST:
      return { ...state, list: action.body.elements };
    case FETCH_WORKERS:
      return { ...state, workers: action.body.elements };
    case RESERVE_WORKER:
      return { ...state, workers: state.workers.map(w => reservation(w, action))};
    default:
      return state;
  }
}
