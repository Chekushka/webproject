import React from 'react';
import './cssPages/main.css';
class main extends React.Component {

    constructor() {
        super();
        console.log("[Main] constructor");
    }

    componentDidMount() {
        console.log("[Main] componentDidMount");
    }

    componentWillUnmount() {
        console.log("[Main] componentWillUnmount");
    }

    render() {
        console.log("[Main] render");        
        return (
        <div className="main">
        Book Exchange Platform <br></br>
        </div>
        
        );
    }

}

export default main; 