interface Props {
  name: string;
  link: string;
}

const NavItem = ({ name, link }: Props) => {
  return (
    <a href={link}>
      <div>logo</div>
      <span>{name}</span>
    </a>
  );
};

export default NavItem;
