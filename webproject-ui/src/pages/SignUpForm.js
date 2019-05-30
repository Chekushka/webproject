import React,{Component} from 'react';
import {Link} from 'react-router-dom';
import './cssPages/SignUpForm.css';
class SignUpForm extends Component{
    constructor() {
        super();
    
        this.state = {
            email: '',
            password: '',
            firstName: '',
            surname: '',
            phone: '',
            hasAgreed: false
        };
    
        this.handleChange = this.handleChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);

        console.log("[SignUpForm] constructor");
    }
    componentDidMount() {
      console.log("[SignUpForm] componentDidMount");
    }
  
    componentWillUnmount() {
      console.log("[SignUpForm] componentWillUnmount");
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
        fetch("http://localhost:8080/api/user/register", {
      method: "POST",
      headers: {
        "content-type" : "application/json"
      },
      body: JSON.stringify(this.state)
    });
    }
render(){
  console.log("[SignUpForm] render"); 
    return(
  <div className="signup">
    <form onSubmit={this.onSubmit}>
                <div>
                    <input className="signup" type="email" id="email" placeholder="Enter your full email" name="email" value={this.state.email} onChange={this.handleChange}/>
                </div>


                <div>
                    <input className="signup" type="password" id="password" placeholder="Enter your full password" name="password" value={this.state.password} onChange={this.handleChange}/>
                </div>
            
                
                <div >
                    <input className="signup" type="text" id="firstName"  placeholder="Enter your firstname" name="firstName" value={this.state.firstName} onChange={this.handleChange}/>
                </div>
                
                <div >
                    <input className="signup" type="text" id="surname"  placeholder="Enter your secondname" name="surname" value={this.state.surname} onChange={this.handleChange}/>
                </div>
                
                <div >
                    <input className="signup" type="text" id="phone"  placeholder="Enter your phone" name="phone" value={this.state.phone} onChange={this.handleChange}/>
                </div>

                <div >
                  <label >
                    <input  type="checkbox" name="hasAgreed" value={this.state.hasAgreed} onChange={this.handleChange}/>
                    I agreed with <a href="">this</a>
                  </label>
                </div>

                <h1></h1>
                
                <div>
                  <button className="Button">Sign up</button>
                  <hr></hr>
                  <Link to="/sign-in">I am already member</Link>
                </div>
    </form>
</div>
);
}
}
export default SignUpForm;