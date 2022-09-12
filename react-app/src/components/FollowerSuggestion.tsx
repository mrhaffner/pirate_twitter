import User from '../types/User';
import Avatar from './Avatar';

interface Props {
  user: User;
}

const FollowerSuggestion = ({ user }: Props) => {
  return (
    <div className="flex justify-between">
      <div className="flex space-x-2">
        <div>
          <Avatar seed={user.id} />
        </div>
        <div>
          <p>@{user.handle}</p>
        </div>
      </div>
      <button>Follow</button>
    </div>
  );
};

export default FollowerSuggestion;
