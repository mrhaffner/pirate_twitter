import User from '../types/User';
import FollowerSuggestion from './FollowerSuggestion';

interface Props {
  users: User[];
}

const FollowerSuggestions = ({ users }: Props) => {
  return (
    <div>
      <div>You might Like</div>
      <div>
        {users.map((user) => (
          <FollowerSuggestion user={user} />
        ))}
      </div>
    </div>
  );
};

export default FollowerSuggestions;
