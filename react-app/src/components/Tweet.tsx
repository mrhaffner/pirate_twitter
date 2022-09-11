import TweetData from '../types/TweetData';
import Avatar from './Avatar';
import LikeButton from './LikeButton';
import RetweetButton from './RetweetButton';

interface Props {
  tweet: TweetData;
}

const Tweet = ({ tweet }: Props) => {
  const { id, datetime, content, user, likeCount, retweetCount } = tweet;
  // needs to generate updated datetime
  // truncate counts past 100?
  return (
    <div className="border border-black no-overlap flex">
      <div className="m-2">
        <Avatar seed={user.id} />
      </div>
      <div className="my-2 space-y-0.5 w-full">
        <div className="flex space-x-2">
          <a href="#">
            <span>@{user.handle}</span>
          </a>
          <div className="-my-1">.</div>
          <div>{datetime}</div>
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
