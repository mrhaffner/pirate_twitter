import TweetData from '../types/TweetData';
import Tweet from './Tweet';

interface Props {
  tweets: [TweetData];
}

const Feed = ({ tweets }: Props) => {
  <div>
    {tweets.map((tweet) => (
      <Tweet tweet={tweet} />
    ))}
  </div>;
};

export default Feed;
