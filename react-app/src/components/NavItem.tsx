import { ReactElement } from 'react';
import { Link } from 'react-router-dom';

interface Props {
  name: string;
  link: string;
  children: JSX.Element;
}

const NavItem = ({ name, link, children }: Props) => {
  return (
    <Link
      className="p-4 rounded-full hover:bg-gray-400 flex space-x-6"
      to={link}
    >
      {children}
      <span className="my-auto">{name}</span>
    </Link>
  );
};

export default NavItem;
