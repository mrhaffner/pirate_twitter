import NavItem from './NavItem';
import TweetButton from './TweetButton';
import UserButton from './UserButton';

const Menu = () => {
  return (
    <div>
      <div>
        <div>logo</div>
        <NavItem name={'Home'} link={''} />
        <NavItem name={'Explore'} link={''} />
        <TweetButton />
      </div>
      <div>
        <UserButton />
      </div>
    </div>
  );
};

export default Menu;
