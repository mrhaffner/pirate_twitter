import TweetData from '../types/TweetData';
import Tweet from './Tweet';

interface Props {
  tweets: TweetData[];
}

const Feed = ({ tweets }: Props) => {
  return (
    <div className="w-152">
      {tweets.map((tweet) => (
        <Tweet tweet={tweet} />
      ))}
    </div>
  );
};

export default Feed;
