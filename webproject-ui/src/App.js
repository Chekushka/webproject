import React, { Component } from 'react';
import {BrowserRouter as Router, Route,NavLink,Switch} from 'react-router-dom';
import './App.css';
import SignUpForm from './pages/SignUpForm.js';
import SignInForm from './pages/SignInForm.js';
import NotFound from './pages/NotFound';
import main from './pages/main';
import ExpenceList from './pages/expences/ExpenceList';

class App extends Component {
  constructor(){
    super();
    console.log("[App] constructor");
  }
  componentDidMount() {
    console.log("[App] componentDidMount");
  }
  componentWillUnmount() {
    console.log("[App] componentWillUnmount");
  }
  render() {
  
    console.log("[App] render");
    return (
        <Router>
        <div className="App">
          <div>
            <h1>EXBooks</h1>
            <div>
            <NavLink exact to ="/">Main</NavLink>   <NavLink to ="/sign-in">Sign in</NavLink>   <NavLink to ="/sign-up">Sign up</NavLink>  <NavLink exact to ="/books">Books</NavLink> 
            </div>
            <Switch>

              <Route exact path="/" component={main}/>
              <Route  path="/sign-up" component={SignUpForm}/>
              <Route path="/sign-in" component={SignInForm}/> 
              <Route path="/books" component={ExpenceList}/> 
              <Route component={NotFound} /> 
            </Switch>
          </div>  
        </div>
      </Router>
    );
  }
}

export default App;
