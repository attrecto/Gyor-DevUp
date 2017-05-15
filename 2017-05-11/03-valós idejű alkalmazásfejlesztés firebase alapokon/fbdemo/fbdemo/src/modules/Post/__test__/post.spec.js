import React from 'react';
import { mount, shallow } from 'enzyme';
import { Post } from '../components';
import sinon from 'sinon';

describe('Post', () => {
  describe('Post Rendering', () => {
    let logErr;

    beforeAll(() => {
      logErr = sinon.stub(console, 'error').callsFake(() => { throw new Error() });
    })

    afterAll(() => {
      logErr.restore();
    })

    it('fail when not add onSend function property', () => {
      expect(() => shallow(<Post />)).toThrowError();
    })

    it('success when add onSend property', () => {
      const onSend = jest.fn();
      expect(() => shallow(<Post onSend={onSend} />)).not.toThrowError();
    })
  })

  describe('Post Component', () => {
    let wrapper;
    let onSend;

    beforeAll(() => {
      onSend = jest.fn();
      wrapper = mount(<Post onSend={onSend} />);
    });

    it('Textbox is present', () => {
      expect(wrapper.find('textarea')).toBePresent();
    });

    it('Send button is present', () => {
      expect(wrapper.find('button')).toBePresent();
    });

    describe('Click on send', () => {
      it('textbox is empty, error message appear', () => {
        wrapper.find('button').simulate('click');
        expect(wrapper.find('.error')).toBePresent();
      });

      it('textbox content disappear, success message appear', () => {
        wrapper.setState({ value: 'Something' });
        wrapper.find('button').simulate('click');
        expect(wrapper.find('.done')).toBePresent();
        expect(wrapper.find('textarea').prop('value')).toHaveLength(0);
      });

      it('onSend handler called', () => {
        wrapper.setState({ value: 'Something' });
        wrapper.find('button').simulate('click');
        expect(onSend).toBeCalledWith('Something');
      })
    });
  });
});