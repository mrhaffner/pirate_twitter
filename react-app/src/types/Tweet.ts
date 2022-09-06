import User from './User';

export default interface Tweet {
  id: number;
  content: string;
  likeCount: number;
  datetime: string;
  user: User;
}
