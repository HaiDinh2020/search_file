import React, { useState } from 'react';
import { Form, Button } from 'react-bootstrap';
import { BsSearch } from 'react-icons/bs';

function SearchBar({ onSearch }) {

  const [searchTerm, setSearchTerm] = useState('');

  const handleSearch = async () => {
    try {
      onSearch(searchTerm);
    } catch (error) {
      console.error('Lỗi khi tải lên tệp:', error);
    }
  };
  
  return (
    <Form className="d-flex">
      <Form.Control
        type="search"
        placeholder="Search"
        className="me-2"
        aria-label="Search"
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
      />
      <Button variant="outline-dark" onClick={handleSearch}>
        <BsSearch />
      </Button>
    </Form>
  );
}

export default SearchBar;
