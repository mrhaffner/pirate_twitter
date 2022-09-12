import User from '../types/User';
import FollowerSuggestion from './FollowerSuggestion';

interface Props {
  users: User[];
}

const FollowerSuggestions = ({ users }: Props) => {
  return (
    <div className="bg-gray-400 p-5 space-y-3 rounded-xl">
      <div>You might Like</div>
      <div className="space-y-3">
        {users.map((user) => (
          <FollowerSuggestion user={user} />
        ))}
      </div>
    </div>
  );
};

export default FollowerSuggestions;
