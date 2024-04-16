import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { FaTimes, FaBars } from 'react-icons/fa';
import logo from './logo.png'; // Importa el logotipo

const Header = () => {
    const [navbar, setNavbar] = useState(false);
    const Navbar = [
        {
            name:'Inicio',
            link:"/"
        },
        {
            name:'Transferencias',
            link:"/transferencias"
        },
        {
            name:'Perfil',
            link:"/perfil"
        },
        {
            name:'Soporte',
            link:"/soporte"
        },
        {
            name:'Notificaciones',
            link:"/notificaciones"
        }
    ];

    return (
        <nav className="w-full h-auto bg-gray-800 lg:px-24 md:px-16 sm:px-14 px-12 py-2 shadow-md">
            <div className="justify-between mx-auto lg:w-full md:items-center md:flex">
                {/* Zona de la navbar perteneciente al logo */}
                <div>
                    <div className="flex items-center justify-between py-3 md:py-5 md:block">
                        {/* Logotipo */}
                        <Link to="/" className="flex items-center">
                            <img src={logo} alt="Logo" className="h-20 w-20 md:h-24 md:w-24" /> {/* Tamaño del logotipo */}
                        </Link>
                        {/* Toggle Button Section */}
                        <div className="md:hidden">
                            <button className="p-2 text-gray-700 rounded-md outline-none border border-transparent focus:border-gray-400 focus:border" onClick={() => setNavbar(!navbar)}>
                                {navbar ? (
                                    <FaTimes className="text-gray-400 cursor-pointer" size={24} />
                                ) : (
                                    <FaBars className="text-gray-400 cursor-pointer" size={24} />
                                )}
                            </button>
                        </div>
                    </div>
                </div>
                {/* Items del Navbar */}
                <div className={`flex justify-between items-center md:block ${navbar ? "block" : "hidden"}`}>
                    <ul className="list-none lg:flex md:flex sm:block block gap-x-5 gap-y-16">
                        {Navbar.map((item, index) => (
                            <li key={index}>
                                <Link to={item.link} className="text-gray-400 text-[1.15rem] font-medium tracking-wider hover:text-gray-200 ease-out duration-700">{item.name}</Link>
                            </li>
                        ))}
                    </ul>
                </div>
            </div>
        </nav>
    );
};

export default Header;