import User from './User';

export default interface TweetData {
  id: number;
  content: string;
  likeCount: number;
  datetime: string;
  user: User;
}
