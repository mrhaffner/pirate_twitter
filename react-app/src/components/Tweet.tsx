import Tweet from '../types/Tweet';
import Avatar from './Avatar';
import LikeButton from './LikeButton';
import RetweetButton from './RetweetButton';

interface Props {
  tweet: Tweet;
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
          <div>@{user.handle}</div>
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
