import useGetTweets from '../hooks/useGetTweets';
import Tweet from './Tweet';

const Feed = () => {
  const tweets = useGetTweets();

  return (
    <div className="w-152">
      {tweets.length ? (
        tweets.map((tweet) => <Tweet key={tweet.id} tweet={tweet} />)
      ) : (
        <div>Loading</div>
      )}
    </div>
  );
};

export default Feed;
