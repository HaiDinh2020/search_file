import React, { useEffect, useState } from 'react'
import Table from 'react-bootstrap/Table';
import Tab from 'react-bootstrap/Tab';
import Tabs from 'react-bootstrap/Tabs';
import axios from 'axios';
import Item from '../Components/Item/Item';
import SearchBar from '../Components/SearchBar';


function HomePage() {

  const [allFile, setAllFile] = useState([{}, {}]);

  const getAllFile = async () => {
    try {
      axios.get(`http://localhost:8080/search`
      ).then((response) => {
        if (response.status === 200) {
          setAllFile(response.data.content)
        } else {
          alert("getfile KHÔNG thành công")
        }
      })
    } catch (error) {
      console.error('Lỗi khi tải lên tệp:', error);
    }

  };

  const searchExac = async (word) => {
    try {
      const response = await axios.get(`http://localhost:8080/search/exac/${word}`);
      if (response.status === 200) {
        console.log(response.data)
        setAllFile(response.data);
      } else {
        alert("getfile KHÔNG thành công");
      }
    } catch (error) {
      console.error('Lỗi khi tải lên tệp:', error);
    }
  }

  const searchAny = async (word) => {
    try {
      const response = await axios.get(`http://localhost:8080/search/${word}`);
      if (response.status === 200) {
        console.log(response.data)
        setAllFile(response.data);
      } else {
        alert("getfile KHÔNG thành công");
      }
    } catch (error) {
      console.error('Lỗi khi tải lên tệp:', error);
    }
  }

  const handleSearch = async (searchTerm) => {
    if (searchTerm.includes('|')) {
      const modifiedString = searchTerm.split('|').join('');
      searchAny(modifiedString)
    } else {
      searchExac(searchTerm);
    }
  };

  useEffect(() => {
    getAllFile();
  }, [])

  return (
    <div className="container mt-5" style={{ backgroundColor: '#fff' }}>
      <SearchBar onSearch={handleSearch} />
      <h4>YOUR RECENT FILES</h4>
      <Tabs
        defaultActiveKey="all"
        id="fill-tab-example"
        className="mb-3"
      >
        <Tab eventKey="all" title="ALL">
          <Item data={allFile} />
        </Tab>
        <Tab eventKey="pdf" title="PDF">
          <Item data={allFile} />
        </Tab>
        <Tab eventKey="word" title="WORD">
          Tab content for Loooonger Tab
        </Tab>
      </Tabs>

    </div>
  )
}

export default HomePage