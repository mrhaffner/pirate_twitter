import './App.css';
import Feed from './components/Feed';
import Menu from './components/NavMenu';
import SideBar from './components/SideBar';
import tweetData from './tweet_data.json';
import { Outlet } from 'react-router-dom';

const App = () => {
  return (
    <div className="h-full flex">
      <Menu />
      <div>
        <Outlet />
        <Feed tweets={tweetData} />
      </div>
      <SideBar />
    </div>
  );
};

export default App;
