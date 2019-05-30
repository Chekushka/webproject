import React, { Component } from 'react';
import BookItem from './BookItem';
import './cssBooks/BookList.css'
class BookList extends Component {
      
    constructor() {
        super();
        console.log("[BookList] constructor");
        this.state = {
            items:[]
        };
    }

    componentDidMount() {
        console.log("[BookList] componentDidMount");
        let initialItems = [];
        fetch('http://localhost:8080/api/announce')
            .then(response => {
                return response.json();
                
            }).then(data => {
                console.log(data)
                initialItems = data.map((planet) => {
                return planet});

            this.setState({
                items: initialItems,
            });
        });
    }
    
    componentWillUnmount() {
        console.log("[BookList] componentWillUnmount");
    }

    render() {
        console.log("[BookList] render");

         

        return (
            <div className="BookList">
            <table>
                <thead>
                    <tr>
                        <th>User</th>
                        <th>Book</th>
                        <th>Date</th>
                        <th>Accept</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                {
                    
                    this.state.items.map(item => <BookItem item={ item } />)
                   
                }
                </tbody>
            </table>
            </div>
        );
    }
}

export default BookList;