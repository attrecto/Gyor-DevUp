import { 
  FETCH_MY_WORKERS, 
  ADD_WORKER, 
  DELETE_WORKER, 
  UPDATE_WORKER,
  START_EDIT, 
  CANCEL_EDIT,
  APPROVE_RESERVATION,
  REJECT_RESERVATION,
} from './CompanyEditorActions';

const defaultState = {
  workers: [],
  edit: null,
  isEdit: false,
};

const reservations = (state = {}, action) => {
  switch(action.type) {
    case APPROVE_RESERVATION:
      if (state.id !== action.payload.reservationId) {
        return state;
      }

      return {...state, status: 'APPROVED'};
    case REJECT_RESERVATION:
      if (state.id !== action.payload.reservationId) {
        return state;
      }

      return {...state, status: 'REJECTED'};
  }
}

const worker = (state = {}, action) => {
  switch(action.type) {
    case UPDATE_WORKER: 
      if (state.id !== action.payload.id) {
        return state;
      }

      return Object.assign(state, action.payload);
    case APPROVE_RESERVATION:
    case REJECT_RESERVATION:
      if (state.id !== action.payload.workerId) {
        return state;
      }

      return {...state, reservations: state.reservations.map(r => reservations(r, action))}
  }
}

export default function editor(state = defaultState, action) {
  switch (action.type) {
    case START_EDIT:
      return { ...state, isEdit: true, edit: action.worker };
    case CANCEL_EDIT:
      return { ...state, isEdit: false, edit: null };
    case FETCH_MY_WORKERS:
      return { ...state, workers: action.body.elements };
    case ADD_WORKER:
      const newWorker = { ...action.payload, id: action.body.id };
      return {...state, workers: [...state.workers, newWorker]};
    case DELETE_WORKER:
      return {...state, workers: state.workers.filter(w => w.id != action.payload)}
    case UPDATE_WORKER:
      return {...state, workers: state.workers.map(w => worker(w, action))};
    case APPROVE_RESERVATION:
    case REJECT_RESERVATION:
      return {...state, workers: state.workers.map(w => worker(w, action))};
    default:
      return state;
  }
}
