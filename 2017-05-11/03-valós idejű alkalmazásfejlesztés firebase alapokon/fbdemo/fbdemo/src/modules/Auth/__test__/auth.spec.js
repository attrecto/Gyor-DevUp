import React from 'react';
import { shallow, mount } from 'enzyme';
import sinon from 'sinon';
import { Auth } from '../components';

const handleAuthFacebook = jest.fn();
const handleAuthGoogle = jest.fn();
const handleAuthTwitter = jest.fn();
const handleAutEmailPass = jest.fn();

const props = {
  onAuthFacebook: handleAuthFacebook,
  onAuthGoogle: handleAuthGoogle,
  onAuthTwitter: handleAuthTwitter,
  onAuthEmailPass: handleAutEmailPass,
};

describe('Auth', () => {
  describe('Rendering', () => {
    let errorLog;

    beforeAll(() => {
      errorLog = sinon.stub(console, 'error')
        .callsFake((message) => { throw new Error(message) });
    });

    afterAll(() => {
      errorLog.restore();
    });

    it('will fail when not added properties', () => {
      expect(() => shallow(<Auth />)).toThrow();
    });

    it('will success when added properties', () => {
      expect(() => shallow(<Auth {...props} />)).not.toThrow()
    });
  });

  describe('Component', () => {
    let wrapper;

    beforeEach(() => {
      wrapper = mount(<Auth {...props} />);
    });

    it('Login via facebook is present', () => {
      expect(wrapper.find('button.facebook')).toBePresent();
    })

    it('Login via google is present', () => {
      expect(wrapper.find('button.google')).toBePresent();
    });

    it('Login via twitter is present', () => {
      expect(wrapper.find('button.twitter')).toBePresent();
    });

    describe('Click on facebook login', () => {
      beforeEach(() => {
        wrapper.find('button.facebook').simulate('click');
      });

      it('handler function is called', () => {
        expect(handleAuthFacebook).toBeCalled();
      });
    });

    describe('Click on google login', () => {
      beforeEach(() => {
        wrapper.find('button.google').simulate('click');
      });

      it('handler function is called', () => {
        expect(handleAuthGoogle).toBeCalled();
      });
    });

    describe('Click on twitter login', () => {
      beforeEach(() => {
        wrapper.find('button.twitter').simulate('click');
      });

      it('handler function is called', () => {
        expect(handleAuthTwitter).toBeCalled();
      });
    });
  });
});
