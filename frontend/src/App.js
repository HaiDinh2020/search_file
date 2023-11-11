// App.js
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import ProductsPage from './Pages/ProductsPage.js';
import HomePage from './Pages/HomePage';
import Navbar from './Navigation/Navbar.js';
import Sidebar from './Navigation/Sidebar.js';
import { Container, Row, Col } from 'react-bootstrap';
import './App.css'

const App = () => {
  return (
    <Router>
      <div>
        <Navbar />
        <Container fluid className="g-0">
                <Row >
                    <Col xs={2} style={{backgroundColor: '#f7f9fc'}}>
                        <Sidebar />
                    </Col>
                    <Col >
                        <Routes>
                            <Route path="/" element={<HomePage />} />
                            <Route path="/important" element={<ProductsPage />} />
                            
                        </Routes>
                    </Col>
                </Row>
            </Container>
      </div>
    </Router>
  );
};

export default App;
