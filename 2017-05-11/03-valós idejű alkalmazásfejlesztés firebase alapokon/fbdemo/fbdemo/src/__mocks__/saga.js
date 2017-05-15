import { EventEmitter } from 'events';

class FakeAuth extends EventEmitter {
  onAuthStateChanged(cb) {
    this.on('authStateChanged', cb);
    return () => this.removeAllListeners();
  }

  signOut() {
    return new Promise((resolve) => {
      resolve(this.simulateAuthStateChange(null));
    })
  }

  simulateAuthStateChange(user) {
    this.emit('authStateChanged', user);
  }

  signInWithPopup(provider) {
    return Promise.resolve();
  }

  get fakeUser() {
    return {
      email: 'test@email.com',
      photoURL: 'http://bit.ly/2pjf8sP',
    };
  }

  signIn(provider) {
    return provider;
  }
}

class FakeRef extends EventEmitter {
  childAdded(data) {
    const child = {
      val() {
        return { ...data };
      },
      key: 'itemid',
    };

    this.emit('child_added', child);
  };

  childChanged(data) {
    const child = {
      val() {
        return { ...data };
      },
      key: 'itemid',
    };

    this.emit('child_changed', child);
  };

  childRemoved(id) {
    const child = {
      key: id
    };

    this.emit('child_removed', child);
  };

  off() {
    this.removeAllListeners();
  };

  push(message) {
    return Promise.resolve(message);
  }

  sendMessage(data) {
    return new Promise(
      resolve => resolve(data)
    );
  };
}

export const feedRef = new FakeRef();
export const auth = new FakeAuth();

export const GoogleAuthProvider = () => {
  return { name: 'Google' };
}

export const FacebookAuthProvider = () => {
  return { name: 'Facebook' };
}

export const TwitterAuthProvider = () => {
  return { name: 'Twitter' };
}