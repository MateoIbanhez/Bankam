import React from 'react';
import Header from './components/Header'
import { BrowserRouter as Router } from 'react-router-dom'; 
import HomeSection from './components/HomeSection';
import Login from './components/Login';

function App() {
  return (
    <>
    <Router>
    <div class='text-white h-[100vh] flex-items-center justify-center bg-cover' style={{"backgroundImage": "url('../src/img/Login-bg.jpg')"}}>
      <Login/>
      </div>
      <div className='w-full min-h-screen bg-gray-950'>
        <Header/>
        <HomeSection/>
      </div>
      </Router>
    </>
  );
}

export default App;