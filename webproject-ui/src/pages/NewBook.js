import React,{Component} from 'react';
import './cssPages/NewBook.css';
class NewBook extends Component{
    constructor() {
        super();
    
        this.state = {
            name: '',
            genre: '',
            description: '',
            author: '',
            year: '',
        };
    
        this.handleChange = this.handleChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);

        console.log("[NewBook] constructor");
    }
    componentDidMount() {
      console.log("[NewBook] componentDidMount");
    }
  
    componentWillUnmount() {
      console.log("[NewBook] componentWillUnmount");
    }
    handleChange(e) {
        let target = e.target;
        let value = target.type === 'checkbox' ? target.checked : target.value;
        let name = target.name;
    
        this.setState({
          [name]: value
        });
    }
    
    onSubmit(e) {
        e.preventDefault();
    
        console.log('The form was submitted with the following data:');
        console.log(this.state);
        fetch("http://localhost:8080/api/book/register", {
      method: "POST",
      headers: {
        "content-type" : "application/json"
      },
      body: JSON.stringify(this.state)
    }
    );
    }
render(){
  console.log("[NewBook] render"); 
    return(
<div className="newBook" >
  <form onSubmit={this.onSubmit}>
    <div>
    <input className="newBook" type="text" id="name" placeholder="Enter name" name="name" value={this.state.name} onChange={this.handleChange}/>  
    </div> 

    <div>
    <input className="newBook" type="text" id="genre" placeholder="Enter genre" name="genre" value={this.state.genre} onChange={this.handleChange}/>  
    </div>  

    <div>
    <input className="newBook" type="text" id="description" placeholder="Enter short description" name="description" value={this.state.description} onChange={this.handleChange}/>  
    </div>  

    <div>
    <input className="newBook" type="text" id="author" placeholder="Enter author" name="author" value={this.state.author} onChange={this.handleChange}/>  
    </div>  

    <div>
    <input className="newBook" type="text" id="year" placeholder="Enter year" name="year" value={this.state.year} onChange={this.handleChange}/>  
    </div>  
      <br></br>
        <div>
              <button className="newBook">Add book</button>
        </div>
      <br></br>
  </form>
</div>
);
}
}
export default NewBook;