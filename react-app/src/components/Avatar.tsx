interface Props {
  seed: number;
}

const Avatar = ({ seed }: Props) => {
  // generate random avatar based on mod seed
  return <div className="h-10 w-10 bg-black rounded-full "></div>;
};

export default Avatar;
