import FollowerSuggestions from './FollowerSuggestions';
import SearchBar from './SearchBar';
import SearchDropDown from './SearchDropDown';
import tweetData from '../tweet_data.json';

const SideBar = () => {
  const userSuggestions = tweetData.map((tweet) => tweet.user);
  return (
    <div>
      <SearchBar />
      <SearchDropDown users={userSuggestions} />
      <FollowerSuggestions users={userSuggestions} />
    </div>
  );
};

export default SideBar;
