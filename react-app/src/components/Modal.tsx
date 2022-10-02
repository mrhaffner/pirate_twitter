import { ReactNode } from 'react';

interface Props {
  children: ReactNode;
  closeModal: any; // ?
}

const Modal = ({ children, closeModal }: Props) => {
  return (
    <>
      <div className="justify-center items-center flex overflow-x-hidden overflow-y-auto fixed inset-0 z-50 outline-none focus:outline-none">
        <div className="relative w-auto my-6 mx-auto max-w-3xl">
          <div className="relative rounded bg-white">
            <button onClick={() => closeModal(false)}>X</button>
            <form>{children}</form>
          </div>
        </div>
      </div>
      <div className="opacity-25 fixed inset-0 z-40 bg-black"></div>
    </>
  );
};

export default Modal;
