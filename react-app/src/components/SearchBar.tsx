const SearchBar = () => {
  // needs to control dropdown
  return (
    <div className="bg-gray-400 rounded-full flex items-center">
      <div>icon</div>
      <input className="mx-4 my-3 w-56" />
      <button className="hidden">close button hidden</button>
    </div>
  );
};

export default SearchBar;
