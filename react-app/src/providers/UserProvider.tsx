import { ReactNode, useEffect, useState } from 'react';
import { UserContext } from '../hooks/useUserContext';
import { login as loginService } from '../service/authService';
import { getUserFromToken } from '../service/userService';

interface Props {
  children: ReactNode;
}

const UserProvider = ({ children }: Props) => {
  // const [token, setToken] = useState<string | null>(null);
  const [token, setToken] = useState('');
  const [user, setUser] = useState(null);
  const [loginFailed, setLoginFailed] = useState(false);
  const [loadingUser, setLoadingUser] = useState(true);

  // check if there is a token
  // attempt to login that user if so
  useEffect(() => {
    (async () => {
      const userToken = localStorage.getItem('piritter-user-token'); // wasn't await before
      if (userToken) {
        // get user from token, new route?
        const userResponse = await getUserFromToken(userToken);
        // does this need to be an await?
        if (userResponse) {
          setToken(userToken);
          setUser(userResponse);
        }
      }
      setLoadingUser(false);
    })();
  }, [token]);

  const login = async (username: string, password: string) => {
    const userResponse = await loginService(username, password);
    const tokenResponse = (await userResponse?.token) ?? null;
    if (tokenResponse) {
      setToken(userResponse.token);
      localStorage.setItem('piritter-user-token', userResponse.token);
      setLoginFailed(false);
    } else {
      setLoginFailed(true);
    }
  };

  const logout = () => {
    localStorage.removeItem('piritter-user-token');
    setToken(''); // this used to be null
    setUser(null);
    setLoadingUser(true);
  };

  return (
    <UserContext.Provider
      value={{ user, login, logout, loginFailed, loadingUser, token }}
    >
      {children}
    </UserContext.Provider>
  );
};

// const UserProvider = ({ children }: Props) => {
//   const [token, setToken] = useState(null);
//   const { user, setUser, userFound, setUserFound, getUserData } = useGetUser();

//   const [login, { data: loginData }] = useMutation(LOGIN);
//   const [signUp, signUpData] = useMutation(CREATE_user);

//   const logout = () => {
//     localStorage.removeItem('piritter-user-token'); ////
//     setToken(null);
//     setUser(null);
//     setUserFound(false);
//   };

//   useEffect(() => {
//     const userToken = localStorage.getItem('piritter-user-token');
//     if (userToken) {
//       getUserData();
//     } else {
//       setUserFound(false);
//     }
//   }, []);

//   useEffect(() => {
//     if (loginData?.login) {
//       const token = loginData.login.value;
//       setToken(token);
//       localStorage.setItem('piritter-user-token', token);
//       window.location.reload(); // ?
//     }
//   }, [loginData]);

//   useEffect(() => {
//     if (signUpData.data) {
//       const token = signUpData.data.createuser.value;
//       setToken(token);
//       localStorage.setItem('piritter-user-token', token);
//       window.location.reload(); // ?
//     }
//   }, [signUpData.data]);

//   useEffect(() => {
//     if (token) {
//       getUserData();
//     }
//   }, [token]);

//   return (
//     <UserContext.Provider
//       value={{
//         user,
//         setUser,
//         login,
//         logout,
//         signUp,
//         userFound,
//         loginData,
//         setUserFound,
//       }}
//     >
//       {children}
//     </UserContext.Provider>
//   );
// };

export default UserProvider;
