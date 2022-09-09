import User from '../types/User';
import Avatar from './Avatar';

interface Props {
  user: User;
}

const SearchSuggestion = ({ user }: Props) => {
  return (
    <a href="">
      <Avatar seed={user.id} />
      <div>
        <p>@{user.handle}</p>
        <div>
          <span>icon</span>
          <span>numfollowers K followers</span>
        </div>
      </div>
    </a>
  );
};

export default SearchSuggestion;
