import './App.css';
import tweetData from './tweet_data.json';
import Feed from './components/Feed';
import Menu from './components/Menu';
import SideBar from './components/SideBar';

const App = () => {
  return (
    <div>
      <Menu />
      <Feed tweets={tweetData} />
      <SideBar />
    </div>
  );
};

export default App;
