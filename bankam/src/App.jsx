import React from 'react';
import Header from './components/Header'
import { BrowserRouter as Router } from 'react-router-dom'; 
import HomeSection from './components/HomeSection';
import Login from './components/Login';


function App() {
  return (
    <>
    <Router>
      <Login/>
      <div className='w-full min-h-screen bg-gray-950'>
        <Header/>
        <HomeSection/>
      </div>
      </Router>
    </>
  );
}

export default App;