import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from './components/Header';
import HomeSection from './components/HomeSection';
import Login from './components/Login';
import Transferencias from './components/Transferencias';

function App() {
  return (
    <Router>
      <div className='text-white h-screen flex items-center justify-center bg-cover' style={{"backgroundImage": "url('../src/img/Login-bg.jpg')"}}>
        <Login/>
      </div>
      <div className='w-full min-h-screen bg-gray-950'>
        <Header/>
        <Routes>
          <Route path="/transferencias" element={<Transferencias />} />
          <Route path="/" element={<HomeSection />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;