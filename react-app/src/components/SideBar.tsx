import FollowerSuggestions from './FollowerSuggestions';
import SearchBar from './SearchBar';
import SearchDropDown from './SearchDropDown';
import tweetData from '../tweet_data.json';

const SideBar = () => {
  const userSuggestions = tweetData.map((tweet) => tweet.user);
  return (
    <div className="mt-4 ml-8 space-y-4">
      <SearchBar />
      <div className="hidden">
        <SearchDropDown users={userSuggestions} />
      </div>
      <FollowerSuggestions users={userSuggestions} />
    </div>
  );
};

export default SideBar;
