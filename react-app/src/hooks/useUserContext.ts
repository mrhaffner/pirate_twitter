import { createContext, useContext } from 'react';

type UserState = {
  user: any;
  loginFailed: boolean;
  loadingUser: boolean;
  login: (username: string, password: string) => void;
  logout: () => void;
};

export const UserContext = createContext<UserState>({
  user: null,
  login: (username: string, password: string) => {},
  logout: () => {},
  loginFailed: false,
  loadingUser: true,
});

// type UserState = {
//   user: any;
//   setUser: React.Dispatch<React.SetStateAction<null>>;
//   login: (value: any) => void;
//   logout: () => void;
//   signUp: (value: any) => void;
//   userFound: any;
//   loginData: any;
//   setUserFound: any;
// };

// export const UserContext = createContext<UserState>({
//   user: null,
//   userFound: null,
//   setUser: () => {},
//   login: () => {},
//   logout: () => {},
//   signUp: () => {},
//   loginData: null,
//   setUserFound: null,
// });

const useUserContext = () => useContext(UserContext);

export default useUserContext;
