import { Navigate, Route, Routes, useNavigate } from 'react-router-dom';
import useUserContext from './hooks/useUserContext';
import AuthPage from './components/AuthPage';
import Home from './components/Home';
import { useEffect } from 'react';
import Explore from './components/Explore';
import User from './components/User';

const App = () => {
  const { user, loadingUser } = useUserContext();
  let navigate = useNavigate();

  useEffect(() => {
    if (!loadingUser && !user) {
      navigate('/login');
    } else {
    }
  }, [user, loadingUser]);

  return (
    <>
      {loadingUser ? (
        <div>loading</div>
      ) : (
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/explore" element={<Explore />} />
          <Route
            path="/login"
            element={user ? <Navigate replace to="/" /> : <AuthPage />}
          />
          <Route path="/:user" element={<User />} />
        </Routes>
      )}
    </>
  );
};

export default App;
