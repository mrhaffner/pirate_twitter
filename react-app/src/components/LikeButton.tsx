import Tweet from '../types/Tweet';

interface Props {
  tweet: Tweet;
}

const LikeButton = ({ tweet }: Props) => {
  const { id, likeCount } = tweet;
  return <button></button>;
};

export default LikeButton;
