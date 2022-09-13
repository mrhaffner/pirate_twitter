import { ReactElement } from 'react';

interface Props {
  name: string;
  link: string;
  children: JSX.Element;
}

const NavItem = ({ name, link, children }: Props) => {
  return (
    <div className="p-4 rounded-full hover:bg-gray-400">
      <a href={link} className="flex space-x-6">
        {children}
        <span className="my-auto">{name}</span>
      </a>
    </div>
  );
};

export default NavItem;
