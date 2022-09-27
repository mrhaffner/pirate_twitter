import { useState } from 'react';
import SignInModal from './SignInModal';
import SignUpModal from './SignUpModal';

const AuthPage = () => {
  const [displaySignUpModal, setDisplaySignUpModal] = useState(false);
  const [displaySignInModal, setDisplaySignInModal] = useState(false);

  return (
    <div className="flex justify-center mt-20">
      <div>
        <div>logo</div>
        <h1 className="text-3xl font-bold mt-8">
          Arrrrrggg, Welcome to Piritter
        </h1>
        <h2 className="text-2xl font-bold mt-8">
          Join and sails the seas today.
        </h2>
        <div className="mt-4 w-80">
          <button
            className="rounded-full text-center py-2 bg-gray-400 w-full"
            onClick={() => setDisplaySignUpModal(true)}
          >
            Sign up
          </button>
        </div>
        <h3 className="text-xl font-bold mt-20">Already a pirate?</h3>
        <div className="mt-4 w-80">
          <button
            className="rounded-full text-center py-2 ring-2 ring-gray-400 w-full"
            onClick={() => setDisplaySignInModal(true)}
          >
            Sign in
          </button>
        </div>
      </div>
      {displaySignInModal ? (
        <SignInModal closeModal={setDisplaySignInModal} />
      ) : null}
      {displaySignUpModal ? (
        <SignUpModal closeModal={setDisplaySignUpModal} />
      ) : null}
    </div>
  );
};

export default AuthPage;
