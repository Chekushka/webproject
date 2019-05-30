import React from 'react';
import './cssPages/NotFound.css'
class NotFound extends React.Component {

    constructor() {
        super();
        console.log("[NotFound] constructor");
    }

    componentDidMount() {
        console.log("[NotFound] componentDidMount");
    }

    componentWillUnmount() {
        console.log("[NotFound] componentWillUnmount");
    }

    render() {
        console.log("[NotFound] render");        
        return (<div className="not">Page not found</div>);
    }

}

export default NotFound; 