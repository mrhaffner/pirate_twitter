import Modal from './Modal';

interface Props {
  closeModal: any;
}

const SignUpModal = ({ closeModal }: Props) => {
  return (
    <Modal closeModal={closeModal}>
      <div>hi</div>
    </Modal>
  );
};

export default SignUpModal;
