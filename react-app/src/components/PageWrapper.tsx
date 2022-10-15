import { ReactNode } from 'react';
import Feed from './Feed';
import NavMenu from './NavMenu';
import SideBar from './SideBar';

interface Props {
  children: ReactNode;
}

const PageWrapper = ({ children }: Props) => {
  return (
    <div className="h-full flex">
      <div>Logo</div>
      <NavMenu />
      <div>
        {children}
        <Feed />
      </div>
      <SideBar />
    </div>
  );
};

export default PageWrapper;
