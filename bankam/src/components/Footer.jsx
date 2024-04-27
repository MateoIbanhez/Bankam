import React from 'react';
import { Link } from 'react-router-dom';

const Footer = () => {
  return (
    <footer className="bg-gray-900 text-white py-4 text-center">
      <div className="container mx-auto">
        <p>
          Aplicación creada por{' '}
          <Link to="/contacto" className="text-blue-500 hover:underline">
            Mateo y Aitor
          </Link>
        </p>
        <p>© Bankam 2024. Todos los derechos reservados.</p>
      </div>
    </footer>
  );
};

export default Footer;
