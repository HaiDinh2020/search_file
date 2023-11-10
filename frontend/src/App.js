import React, { useState } from 'react';
import './App.css';
import FileUpload from './Components/FileUpload';
import SearchBar from './Components/SearchBar';
import FileList from './Components/FileList';

function App() {
  const [uploadedFiles, setUploadedFiles] = useState([]);

  const handleFileUpload = (file) => {
    setUploadedFiles([...uploadedFiles, file]);
  };

  const handleSearch = (value) => {

  }

  return (
    <div className="app-container">
      <h1>Ứng dụng Tải lên Tệp và Tìm kiếm</h1>

      <FileUpload onFileUpload={handleFileUpload} />
      <SearchBar onSearch={handleSearch} />
      <FileList files={uploadedFiles} />
    </div>
  );
}

export default App;
