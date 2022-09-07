import TweetData from '../types/TweetData';

interface Props {
  tweet: TweetData;
}

const LikeButton = ({ tweet }: Props) => {
  const { id, likeCount } = tweet;
  return <button></button>;
};

export default LikeButton;
