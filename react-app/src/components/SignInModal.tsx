import { useEffect, useState } from 'react';
import useUserContext from '../hooks/useUserContext';
import Modal from './Modal';

interface Props {
  closeModal: any;
}

const SignInModal = ({ closeModal }: Props) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const { login, loginFailed, user } = useUserContext();

  // useEffect(() => {
  //   if (user) {
  //     console.log('hi');
  //     // redirect to home?
  //     // or does it do so automatically?
  //   }
  // }, [user]);

  // redirect on success
  // prompt on failure

  return (
    <Modal closeModal={closeModal}>
      <h3>Sign in to your account</h3>
      {/* <form onSubmit={() => login(username, password)}>
        <input value={username} onChange={(e) => setUsername(e.target.value)} />
        <input value={password} onChange={(e) => setPassword(e.target.value)} />
        <input value="Log in" type="submit" />
      </form> */}
      <form>
        <input value={username} onChange={(e) => setUsername(e.target.value)} />
        <input value={password} onChange={(e) => setPassword(e.target.value)} />
        <button type="button" onClick={() => login(username, password)}>
          Log in
        </button>
      </form>
    </Modal>
  );
};

export default SignInModal;
