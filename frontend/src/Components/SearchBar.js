import React from 'react';

function SearchBar({ onSearch }) {
  const handleSearch = (e) => {
    onSearch(e.target.value);
  };

  return (
    <div>
      <input
        type="text"
        placeholder="Tìm kiếm tệp..."
        onChange={handleSearch}
      />
    </div>
  );
}

export default SearchBar;
