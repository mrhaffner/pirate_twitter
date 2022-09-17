import Feed from './components/Feed';
import Menu from './components/NavMenu';
import SideBar from './components/SideBar';
import { Outlet } from 'react-router-dom';

const App = () => {
  return (
    <div className="h-full flex">
      <Menu />
      <div>
        <Outlet />
        <Feed />
      </div>
      <SideBar />
    </div>
  );
};

export default App;
