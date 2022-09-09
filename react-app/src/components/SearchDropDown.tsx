import User from '../types/User';
import SearchSuggestion from './SearchSuggestion';

interface Props {
  users: User[];
}

const SearchDropDown = ({ users }: Props) => {
  return (
    <div>
      {users.map((user) => (
        <SearchSuggestion user={user} />
      ))}
    </div>
  );
};

export default SearchDropDown;
