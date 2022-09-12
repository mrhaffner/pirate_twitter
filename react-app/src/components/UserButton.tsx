import Avatar from './Avatar';

const UserButton = () => {
  // probably get seed/username from logged in user id?
  return (
    <button className="flex justify-between w-full pr-8 mb-4">
      <div className="flex space-x-2">
        <div>
          <Avatar seed={1} />
        </div>
        <div>@username</div>
      </div>
      <div className="my-auto">
        <div>dots</div>
      </div>
    </button>
  );
};

export default UserButton;
