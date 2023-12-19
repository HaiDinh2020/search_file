import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { Link } from 'react-router-dom';
import { AiOutlineQuestionCircle, AiOutlineSetting } from 'react-icons/ai';
import SearchBar from '../Components/SearchBar';


function Navibar() {
    return (
      <Navbar bg="secondary" variant="dark" sticky="top">
        <Container fluid>
          <Navbar.Brand as={Link} to="/trang-chu">
            Driver
          </Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav" className="justify-content-center">
            <Nav>
              {/* <SearchBar /> */}
            </Nav>
          </Navbar.Collapse>
          <Nav className="align-items-center">
            <Navbar.Collapse id="basic-navbar-nav">
              <Nav className="mr-auto">
                <Nav.Link href="/question">
                  <AiOutlineQuestionCircle />
                </Nav.Link>
                <Nav.Link href="/setting">
                  <AiOutlineSetting />
                </Nav.Link>
              </Nav>
              <Navbar className="me-2">
                <img
                  src={''}
                  alt=""
                  width="30"
                  height="30"
                  style={{ borderRadius: 50 }}
                />
              </Navbar>
            </Navbar.Collapse>
          </Nav>
        </Container>
      </Navbar>
    );
  }

export default Navibar;
