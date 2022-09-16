import PirateMap from '../svg/PirateMap';
import PirateShip from '../svg/PirateShip';
import NavItem from './NavItem';
import TweetButton from './TweetButton';
import UserButton from './UserButton';

const NavMenu = () => {
  return (
    <nav className="w-88 py-4 pl-24 flex flex-col justify-between">
      <div className="space-y-3 mr-10">
        <div>
          <p>tweet logo</p>
        </div>
        <NavItem name={'Home'} link={''}>
          <PirateShip />
        </NavItem>
        <NavItem name={'Explore'} link={'explore'}>
          <PirateMap />
        </NavItem>
        <TweetButton />
      </div>
      <div>
        <UserButton />
      </div>
    </nav>
  );
};

export default NavMenu;
