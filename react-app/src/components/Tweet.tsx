import { Link } from 'react-router-dom';
import TweetData from '../types/TweetData';
import Avatar from './Avatar';
import LikeButton from './LikeButton';
import RetweetButton from './RetweetButton';

interface Props {
  tweet: TweetData;
}

const Tweet = ({ tweet }: Props) => {
  const { id, creationTime, content, user, likeCount, retweetCount } = tweet;
  // needs to generate updated datetime
  // update icons if liked or retweeted by me!!!
  return (
    <div className="border border-black no-overlap flex">
      <Link className="m-2" to={user.username}>
        <Avatar seed={user.id} />
      </Link>
      <div className="my-2 space-y-0.5 w-full">
        <div className="flex space-x-2">
          <Link to={user.username}>
            <span>@{user.username}</span>
          </Link>
          <div className="-my-1">.</div>
          <div>{creationTime}</div>
        </div>
        <div>
          <div>{content}</div>
          <div className="flex mt-2">
            <div className="flex w-36 space-x-1">
              <div className="flex items-center">
                <RetweetButton tweetId={id} />
              </div>
              <div>{retweetCount}</div>
            </div>
            <div className="flex space-x-1">
              <div className="flex items-center">
                <LikeButton tweet={tweet} />
              </div>
              <div>{likeCount}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Tweet;
