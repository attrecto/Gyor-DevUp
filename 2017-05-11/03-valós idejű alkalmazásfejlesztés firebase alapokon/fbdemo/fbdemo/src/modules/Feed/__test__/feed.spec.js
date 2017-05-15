import React from 'react';
import { shallow} from 'enzyme';
import sinon from 'sinon';
import { Feed } from '../components';
import moment from 'moment';


const props = {
  items: [
    {
      user: {
        email: 'test@domain.com',
        avatar: 'avatar src',
      },
      id: 'itemid',
      text: 'Something',
      created: moment().toISOString(),
    },
    {
      user: {
        email: 'test@domain.com',
        avatar: 'avatar src',
      },
      id: 'itemid',
      text: 'Something',
      created: moment().toISOString(),
    },
  ]
}

describe('Feed', () => {
  describe('Rendering', () => {
    let errorLog;

    beforeAll(() => {
      errorLog = sinon.stub(console, 'error').callsFake((message) => { throw new Error(message) });
    })

    afterAll(() => {
      errorLog.restore();
    })

    it('will fail when missing items property', () => {
      expect(() => shallow(<Feed />)).toThrow();
    });

    it('will success when items prop added', () => {
      expect(() => shallow(<Feed {...props} />)).not.toThrow();
    })
  })
});
