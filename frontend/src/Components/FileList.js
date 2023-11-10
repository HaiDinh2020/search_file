import React, { useState } from 'react';

function FileList({ files }) {
  const [searchTerm, setSearchTerm] = useState('');

 

  const filteredFiles = files.filter((file) =>
    file.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div>
      <ul>
        {filteredFiles.map((file) => (
          <li key={file.id}>{file.name}</li>
        ))}
      </ul>
    </div>
  );
}

export default FileList;
