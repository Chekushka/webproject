import React, { Component } from 'react';
import {BrowserRouter as Router, Route,NavLink,Switch} from 'react-router-dom';
import './App.css';
import SignUpForm from './pages/SignUpForm.js';
import SignInForm from './pages/SignInForm.js';
import NotFound from './pages/NotFound';
import main from './pages/main';
import BookList from './pages/books/BookList';
import NewBook from './pages/NewBook';
import Details from './pages/books/Details';
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
            <h1 className="app">Book Exchange</h1>
            <div  >
            <NavLink  className="app" exact to ="/">Main</NavLink>   <NavLink className="app" to ="/sign-in">Sign in</NavLink>   <NavLink className="app" to ="/sign-up">Sign up</NavLink>  <NavLink className="app" exact to ="/books">Books</NavLink>   <NavLink className="app" exact to ="/newbook">Add Book</NavLink>
            </div>
            <Switch>
              <Route exact path="/" component={main}/>
              <Route  path="/sign-up" component={SignUpForm}/>
              <Route path="/sign-in" component={SignInForm}/> 
              <Route path="/books" component={BookList}/> 
              <Route path="/newbook" component={NewBook}/> 
              <Route path='/details/:Id' component={Details} />
              <Route component={NotFound} /> 
            </Switch>
          </div>  
        </div>
      </Router>
    );
  }
}

export default App;
