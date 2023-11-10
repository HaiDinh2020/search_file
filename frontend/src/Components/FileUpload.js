import React, { useState } from 'react';
import axios from 'axios';
import './FileUpload.css';

function FileUpload({ onFileUpload }) {
  const [selectedFile, setSelectedFile] = useState(null);

  const handleFileChange = (e) => {
    setSelectedFile(e.target.files[0]);
  };

  const handleUpload = async () => {
    
    if (selectedFile) {
      const formData = new FormData();
      formData.append('file', selectedFile);

      try {
        // Gửi tệp lên máy chủ
        axios.post(`http://localhost:3000/upload`, { user: 'hellowworld' })
        .then(res => {
          console.log(res);
          console.log(res.data);
        })
        // Gọi hàm callback để thông báo thành công
        // onFileUpload(response.data);

        // Đặt lại trạng thái
        setSelectedFile(null);
      } catch (error) {
        console.error('Lỗi khi tải lên tệp:', error);
      }
    }
  };

  return (
    <div>
      <input type="file" accept=".pdf, .doc, .docx" onChange={handleFileChange} />
      <button onClick={handleUpload}>Tải lên</button>
    </div>
  );
}

export default FileUpload;
