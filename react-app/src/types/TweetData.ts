import User from './User';

export default interface TweetData {
  id: number;
  content: string;
  likeCount: number;
  retweetCount: number;
  datetime: string;
  user: User;
}
