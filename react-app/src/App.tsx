import './App.css';
import tweetData from './tweet_data.json';
import Feed from './components/Feed';

function App() {
  return (
    <div>
      <Feed tweets={tweetData} />
    </div>
  );
}

export default App;
