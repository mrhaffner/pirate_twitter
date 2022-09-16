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
        <Link to={user.handle}>
          <Avatar seed={user.id} />
        </Link>
        <Link to={user.handle}>
          <p>@{user.handle}</p>
        </Link>
      </div>
      <button className="text-white bg-black rounded-full px-3">Follow</button>
    </div>
  );
};

export default FollowerSuggestion;
