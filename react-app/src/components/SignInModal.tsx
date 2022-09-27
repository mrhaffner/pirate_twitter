import Modal from './Modal';

interface Props {
  closeModal: any;
}

const SignInModal = ({ closeModal }: Props) => {
  return (
    <Modal closeModal={closeModal}>
      <div>hi</div>
    </Modal>
  );
};

export default SignInModal;
