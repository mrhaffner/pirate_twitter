import { useState } from 'react';
import Modal from './Modal';
import { signup } from '../service/authService';

interface Props {
  closeModal: any;
}

const SignUpModal = ({ closeModal }: Props) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  return (
    <Modal closeModal={closeModal}>
      <h3>Create your account</h3>
      <input value={username} onChange={(e) => setUsername(e.target.value)} />
      <input value={password} onChange={(e) => setPassword(e.target.value)} />
      <button onClick={() => signup(username, password)}>Sign Up</button>
    </Modal>
  );
};

export default SignUpModal;
