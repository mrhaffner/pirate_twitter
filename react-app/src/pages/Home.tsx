import Feed from '../components/Feed';
import Menu from '../components/Menu';
import SideBar from '../components/SideBar';
import tweetData from '../tweet_data.json';

const Home = () => {
  return (
    <div className="h-full flex">
      <Menu />
      <Feed tweets={tweetData} />
      <SideBar />
    </div>
  );
};

export default Home;
