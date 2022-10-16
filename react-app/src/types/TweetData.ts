import User from './User';

export default interface TweetData {
  id: number;
  content: string;
  likeCount: number;
  retweetCount: number;
  creationTime: string;
  user: User;
}
