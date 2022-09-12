import Feed from '../components/Feed';
import Menu from '../components/Menu';
import SideBar from '../components/SideBar';
import tweetData from '../tweet_data.json';

const Home = () => {
  return (
    <div className="flex">
      <div className="w-88">
        <Menu />
      </div>
      <div className="w-152">
        <Feed tweets={tweetData} />
      </div>
      <div className="">
        <SideBar />
      </div>
    </div>
  );
};

export default Home;
