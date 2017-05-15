import React from 'react';
import { shallow, mount } from 'enzyme';
import sinon from 'sinon';
import { Item } from '../components';
import { Message } from '../components';
import { Avatar } from '../components';
import moment from 'moment';

const onHotHandler = jest.fn();

const props = {
  user: {
    email: 'test@domain.com',
    avatar: 'avatar src',
  },
  id: 'itemid',
  text: 'Something',
  created: moment().toISOString(),
  onHot: onHotHandler,
}

describe('Item', () => {
  describe('Rendering', () => {
    let errorLog;

    beforeAll(() => {
      errorLog = sinon.stub(console, 'error').callsFake((message) => { throw new Error(message) });
    });

    afterAll(() => {
      errorLog.restore();
    });

    it('will fail when not set required properties', () => {
      expect(() => shallow(<Item />)).toThrow();
    });

    it('will success when set required properties', () => {
      expect(() => shallow(<Item {...props} />)).not.toThrow();
    });
  });

  describe('Component', () => {
    let wrapper;

    beforeEach(() => {
      wrapper = mount(<Item {...props} />);
    })

    describe('Avatar', () => {
      let avatar;

      beforeEach(() => {
        avatar = wrapper.find(Avatar);
      })

      it('is present', () => {
        expect(avatar).toBePresent();
      });

      it('has img with correct src property', () => {
        expect(avatar.find('img')).toHaveProp('src', props.user.avatar);
      })
    });

    describe('Message', () => {
      let message;

      beforeEach(() => {
        message = wrapper.find(Message);
      })

      it('is present', () => {
        expect(message).toBePresent();
      });

      it('present user message', () => {
        expect(message).toIncludeText(props.text);
      })
    })

    describe('Hot button', () => {
      let hot;

      beforeEach(() => {
        hot = wrapper.find('.hot');
      })

      it('is present', () => {
        expect(hot).toBePresent();
      });

      describe('click on button', () => {
        beforeEach(() => {
          hot.simulate('click');
        });

        it('onHotHandler called', () => {
          expect(onHotHandler).toBeCalled();
        })
      });
    })
  })
})
