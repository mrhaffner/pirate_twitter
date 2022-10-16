import { Link } from 'react-router-dom';
import User from '../types/User';
import Avatar from './Avatar';

interface Props {
  user: User;
}

const FollowerSuggestion = ({ user }: Props) => {
  return (
    <div className="flex justify-between">
      <div className="flex space-x-2">
        <Link to={user.username}>
          <Avatar seed={user.id} />
        </Link>
        <Link to={user.username}>
          <p>@{user.username}</p>
        </Link>
      </div>
      <button className="text-white bg-black rounded-full px-3">Follow</button>
    </div>
  );
};

export default FollowerSuggestion;
