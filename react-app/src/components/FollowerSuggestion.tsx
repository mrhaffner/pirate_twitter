import User from '../types/User';
import Avatar from './Avatar';

interface Props {
  user: User;
}

const FollowerSuggestion = ({ user }: Props) => {
  return (
    <div>
      <div>
        <Avatar seed={user.id} />
      </div>
      <div>
        <p>@{user.handle}</p>
      </div>
      <button>Follow</button>
    </div>
  );
};

export default FollowerSuggestion;
