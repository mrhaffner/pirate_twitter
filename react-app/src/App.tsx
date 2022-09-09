import './App.css';
import tweetData from './tweet_data.json';
import Feed from './components/Feed';
import Menu from './components/Menu';

const App = () => {
  return (
    <div>
      <Menu />
      <Feed tweets={tweetData} />
    </div>
  );
};

export default App;
