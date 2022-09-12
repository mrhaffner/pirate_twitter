interface Props {
  name: string;
  link: string;
}

const NavItem = ({ name, link }: Props) => {
  return (
    <div>
      <a href={link} className="flex space-x-6">
        <div>logo</div>
        <span>{name}</span>
      </a>
    </div>
  );
};

export default NavItem;
