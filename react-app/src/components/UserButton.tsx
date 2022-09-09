import Avatar from './Avatar';

const UserButton = () => {
  // probably get seed/username from logged in user id?
  return (
    <button>
      <div>
        <div>
          <Avatar seed={1} />
        </div>
        <div>@username</div>
      </div>
      <div>
        <span>dots</span>
      </div>
    </button>
  );
};

export default UserButton;
