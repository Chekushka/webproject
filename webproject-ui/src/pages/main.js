import React from 'react';

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
        <div>
        EXBooks-book exchange platform
        <h5>in development...</h5>
        
        </div>);
    }

}

export default main; 