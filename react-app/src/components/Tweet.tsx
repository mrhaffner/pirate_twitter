import TweetData from '../types/TweetData';
import Avatar from './Avatar';
import LikeButton from './LikeButton';
import RetweetButton from './RetweetButton';

interface Props {
  tweet: TweetData;
}

const Tweet = ({ tweet }: Props) => {
  const { id, datetime, content, user } = tweet;
  // needs to generate updated datetime
  return (
    <div>
      <div>
        <Avatar seed={user.id} />
      </div>
      <div>
        <div>
          <a href="#">
            <span>@{user.handle}</span>
          </a>
          <div>{datetime}</div>
        </div>
        <div>
          <div>{content}</div>
          <div>
            <RetweetButton tweetId={id} />
            <LikeButton tweet={tweet} />
          </div>
        </div>
      </div>
    </div>
  );
};

export default Tweet;
