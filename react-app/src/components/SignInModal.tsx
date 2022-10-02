import { useState } from 'react';
import { login } from '../service/authService';
import Modal from './Modal';

interface Props {
  closeModal: any;
}

const SignInModal = ({ closeModal }: Props) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  // redirect on success
  // prompt on failure

  return (
    <Modal closeModal={closeModal}>
      <h3>Sign in to your account</h3>
      <form onSubmit={() => login(username, password)}>
        <input value={username} onChange={(e) => setUsername(e.target.value)} />
        <input value={password} onChange={(e) => setPassword(e.target.value)} />
        <input value="Log in" type="submit" />
      </form>
    </Modal>
  );
};

export default SignInModal;
