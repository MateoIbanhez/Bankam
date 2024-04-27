import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from './components/Header';
import HomeSection from './components/HomeSection';
import Transferencias from './components/Transferencias';
import Soporte from './components/Soporte'
import Contacto from './components/Contacto'
import Perfil from './components/Perfil'


function App() {
  return (
    <Router>
      <div className='w-full min-h-screen bg-gray-950'>
        <Header/>
        <Routes>
          <Route path="/transferencias" element={<Transferencias />} />
          <Route path="/soporte" element={<Soporte />} />
          <Route path="/contacto" element={<Contacto />} />
          <Route path="/" element={<HomeSection />} />
          <Route path="/perfil" element={<Perfil />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;