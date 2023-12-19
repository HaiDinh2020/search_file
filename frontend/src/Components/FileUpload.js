import React, { useState } from 'react';
import axios from 'axios';

function FileUpload() {

  const handleUpload = async (event) => {

    if (event.target.files[0]) {
      const formData = new FormData();
      formData.append('file', event.target.files[0]);

      try {
        console.log('Selected file:', event.target.files[0]);

        axios.post(`http://localhost:8080/api/upload`, formData, {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        }).then((response) => {
          if(response.status === 200) {
            console.log(response)
           alert(response.data)
          } else {
            alert("upload KHÔNG thành công")
          }
        })


      } catch (error) {
        console.error('Lỗi khi tải lên tệp:', error);
      }
    }
  };

  return (
    <div>
      <input type="file" accept=".pdf,.doc,.docx" onChange={handleUpload} />
    </div>
  );
}

export default FileUpload;
