interface Props {
  seed: number;
}

const PirateAvatar = ({ seed }: Props) => {
  const piratePath = `/pirates/pirate${seed % 9}.jpg`;
  return <img src={piratePath} alt="Pirate avatar" className="scale-150" />;
};

export default PirateAvatar;
