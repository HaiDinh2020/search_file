import React, { useState } from 'react'
import { MdDelete } from "react-icons/md";
import { FaEllipsisVertical } from "react-icons/fa6";
import { IoMdShareAlt } from "react-icons/io";
import { GoDownload } from "react-icons/go";
import './Item.css'
import axios from 'axios';
// import magic from '../../magic.pdf';
// import { Document, Page, pdfjs } from 'react-pdf';
// pdfjs.GlobalWorkerOptions.workerSrc = `//unpkg.com/pdfjs-dist@${pdfjs.version}/legacy/build/pdf.worker.min.js`;

function Item({ data, getAllFile }) {

    const [dataRender, setDataRender] = useState(data);
    const [numPages, setNumPages] = useState(null);
    const [pageNumber, setPageNumber] = useState(1);

    const handleDelete = async (fileName) => {
        try {
            const response = await axios.delete(`http://localhost:8080/search/delete`, {
                data: {
                    "fileName": fileName
                }
            });
            if (response.status === 200) {
                getAllFile();
                alert(`xóa file ${fileName} thành công`);
            } else {
                alert("xóa KHÔNG thành công", response.data);
            }
        } catch (error) {
            console.error('Lỗi khi xóa tệp:', error);
        }
    };


    // function onDocumentLoadSuccess({ numPages }) {
    //     setNumPages(numPages);
    //     setPageNumber(1);
    // }

    // function changePage(offset) {
    //     setPageNumber(prevPageNumber => prevPageNumber + offset);
    // }

    // function previousPage() {
    //     changePage(-1);
    // }

    // function nextPage() {
    //     changePage(1);
    // }


    return (
        <div>
            {
                data.map((file, index) => (
                    <div key={index} className="row  border border-1 p-3 mb-3 mr-2">
                        <div className='col-2 d-flex justify-content-center align-items-center '>

                            <MdDelete className='mr-4' onClick={() => handleDelete(file.fileName)} />

                            <FaEllipsisVertical />
                            <div className="vertical-line"></div>
                        </div>
                        <div className='col-7 '>
                            {file.fileName}
                        </div>
                        <div className='col-3 d-flex justify-content-center align-items-center'>
                            <button type="button" className="btn btn-danger btn-sm mr-2 d-flex justify-content-center align-items-center">
                                <IoMdShareAlt className='mr-1' />
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

            {/* <Document
                file={magic}
                options={{ workerSrc: "/pdf.worker.js" }}
                onLoadSuccess={onDocumentLoadSuccess}
            >
                <Page pageNumber={pageNumber} />
            </Document>
            <div>
                <p>
                    Page {pageNumber || (numPages ? 1 : "--")} of {numPages || "--"}
                </p>
                <button type="button" disabled={pageNumber <= 1} onClick={previousPage}>
                    Previous
                </button>
                <button
                    type="button"
                    disabled={pageNumber >= numPages}
                    onClick={nextPage}
                >
                    Next
                </button>
            </div> */}
        </div>
    )
}

export default Item;