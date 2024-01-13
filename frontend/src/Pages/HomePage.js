import React, { useEffect, useState } from 'react'
import Tab from 'react-bootstrap/Tab';
import Tabs from 'react-bootstrap/Tabs';
import axios from 'axios';
import Item from '../Components/Item/Item';
import SearchBar from '../Components/SearchBar';


function HomePage() {

  const [allFile, setAllFile] = useState([{}, {}]);
  const [allPdf, setAllPdf] = useState([{}, {}]);
  const [allWord, setAllWord] = useState([{}, {}]);

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

  const getPdfFile = async () => {
    try {
      axios.get(`http://localhost:8080/search/pdf`
      ).then((response) => {
        if (response.status === 200) {
          setAllPdf(response.data)
        } else {
          alert("getfile KHÔNG thành công")
        }
      })
    } catch (error) {
      console.error('Lỗi khi tải lên tệp:', error);
    }
  }

  const getWordFile = async () => {
    try {
      axios.get(`http://localhost:8080/search/word`
      ).then((response) => {
        if (response.status === 200) {
          setAllWord(response.data)
        } else {
          alert("getfile KHÔNG thành công")
        }
      })
    } catch (error) {
      console.error('Lỗi khi tải lên tệp:', error);
    }
  }

  const searchExac = async (word) => {
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

  const searchAny = async (word1, word2) => {
    console.log("ssearcj");
    try {
      const response = await axios.get(`http://localhost:8080/search/multiword?word1=${word1}&word2=${word2}`);
      console.log(response);
      if (response.status === 200) {
          console.log(response.data);
          setAllFile(response.data);
      } else {
          alert("getfile KHÔNG thành công");
      }
    } catch (error) {
        console.error(error);
    }
  }

  const handleSearch = async (searchTerm) => {
    if (searchTerm.includes('|')) {
      var arrayWord = searchTerm.split('|');
      searchAny(arrayWord[0], arrayWord[1]);
    } else if(searchTerm !== ""){
      searchExac(searchTerm);
    } else {
      getAllFile();
    }
  };

  useEffect(() => {
    getAllFile();
    getPdfFile();
    getWordFile();
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
          <Item data={allFile} getAllFile={getAllFile}/>
        </Tab>
        <Tab eventKey="pdf" title="PDF">
          <Item data={allPdf} />
        </Tab>
        <Tab eventKey="word" title="WORD">
          <Item data={allWord} />
        </Tab>
      </Tabs>

    </div>
  )
}

export default HomePage