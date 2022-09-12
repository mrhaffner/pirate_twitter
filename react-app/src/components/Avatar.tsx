import PirateAvatar from '../svg/PirateAvatar';

interface Props {
  seed: number;
}

const Avatar = ({ seed }: Props) => {
  return (
    <div className="h-10 w-10 bg-white rounded-full overflow-hidden">
      <PirateAvatar seed={seed} />
    </div>
  );
};

export default Avatar;
