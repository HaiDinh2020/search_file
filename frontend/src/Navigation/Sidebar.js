// Sidebar.js
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import './Sidebar.css';
import FileUpload from '../Components/FileUpload';

const Sidebar = () => {

  const [isPopupVisible, setPopupVisible] = useState(false);

  const handleButtonClick = () => {
    setPopupVisible(true);
  };




  return (
    <div className="sidebar" style={{ backgroundColor: '#f7f9fc', justifyContent: 'center', alignItems: 'center' }}>

      <div className="d-flex justify-content-center align-items-center">
        <button
          className="btn custom-button"
          onClick={handleButtonClick}
        >
          + Mới
        </button>
        {isPopupVisible && (
        <div className="popup">
          <FileUpload />
          <button onClick={() => setPopupVisible(false)}>Đóng</button>
        </div>
      )}
      </div>

      <ul className="nav flex-column">
        <li className='nav-item'>
          <Link to="/" className='nav-link'>File</Link>
        </li>
        <li className="nav-item">
          <Link to="/important" className="nav-link">Important</Link>
        </li>
        <li className="nav-item">
          <Link to="/archives" className="nav-link">Archives</Link>
        </li>
        <li className="nav-item">
          <Link to="/deleted" className="nav-link">Deleted</Link>
        </li>
        <li className="nav-item">
          <Link to="/friends" className="nav-link">Friends</Link>
        </li>

      </ul>
    </div>
  );
};

export default Sidebar;
