import React, { useEffect, useState } from 'react'
import Tab from 'react-bootstrap/Tab';
import Tabs from 'react-bootstrap/Tabs';
import axios from 'axios';
import { MdDelete } from "react-icons/md";
import { FaEllipsisVertical } from "react-icons/fa6";
import { IoMdShareAlt } from "react-icons/io";
import { GoDownload } from "react-icons/go";

function HomePage() {

  const [allFile, setAllFile] = useState([]);

  const getAllFile = async () => {
    try {
      axios.get(`http://localhost:8080/api/allFile`
      ).then((response) => {
        if (response.status === 200) {
          setAllFile(response.data)
        } else {
          alert("getfile KHÔNG thành công")
        }
      })
    } catch (error) {
      console.error('Lỗi khi tải lên tệp:', error);
    }

  };

  useEffect(() => {
    getAllFile();
  }, [])

  return (
    <div className="container mt-5" style={{ backgroundColor: '#fff' }}>
      <h4>YOUR RECENT FILES</h4>

      <Tabs
        defaultActiveKey="all"
        id="fill-tab-example"
        className="mb-3"
      >
        <Tab eventKey="all" title="ALL">
          {
            allFile.map((file, index) => (
              <div key={index} className="row  border border-1 p-3 mb-3 mr-2">
                <div className='col-2 d-flex justify-content-center align-items-center '>
                  <MdDelete />
                  <FaEllipsisVertical />
                </div>
                <div className='col-7 '>
                  {file.fileName}
                </div>
                <div className='col-3 d-flex justify-content-center align-items-center'>
                  <button type="button" className="btn btn-danger btn-sm mr-2 d-flex justify-content-center align-items-center">
                    <IoMdShareAlt className='mr-1'/>
                    Share
                  </button>
                  <button type="button" className="btn btn-primary btn-sm d-flex justify-content-center align-items-center">
                    <GoDownload className='mr-1' />
                    Download
                  </button>
                </div>

              </div>
            ))
          }
        </Tab>
        <Tab eventKey="pdf" title="PDF">
          Tab content for Profile
        </Tab>
        <Tab eventKey="word" title="WORD">
          Tab content for Loooonger Tab
        </Tab>
      </Tabs>

    </div>
  )
}

export default HomePage