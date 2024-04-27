import React, { useState, useEffect } from 'react';

const Perfil = () => {
    // Estado para controlar la visibilidad de la tarjeta
    const [visible, setVisible] = useState(false);

    // Utiliza useEffect para cambiar el estado a visible después de un retraso
    useEffect(() => {
        const timeout = setTimeout(() => {
            setVisible(true);
        }, 500); // Cambia el tiempo según tus preferencias
        return () => clearTimeout(timeout);
    }, []);

    return (
        <section className={`flex flex-col items-center justify-center py-8 md:py-16 lg:py-24 space-y-8 md:space-y-12 lg:space-y-16 bg-black ${visible ? 'opacity-100' : 'opacity-0'}`} style={{ transition: 'opacity 0.5s ease-in-out' }}>
            <h1 className="text-4xl md:text-6xl lg:text-7xl font-bold mb-8 text-center text-green-400">Tu perfil</h1>
            <div className="bg-gray-900 p-8 rounded-lg flex flex-col items-center justify-center">
                <div className="flex items-center justify-center mb-8">
                    <img src="src/img/michael-scoot.jpg" alt="Foto de perfil" className="w-40 h-40 rounded-full object-cover hover:scale-110 transition-transform duration-300 ease-in-out" />
                </div>
                <h3 className="text-2xl md:text-3xl font-semibold text-center text-green-400 mb-4">Michael Scott</h3>
                <p className="text-lg text-white mb-6">Bienvenido, estos son tus datos:</p>
                <ul className="list-disc list-inside text-white mb-6">
                    <li><span className="font-semibold">Correo electrónico:</span> <a href="mailto:correo@example.com" className="text-white hover:text-green-500">michaelscott@scott.org</a></li>
                    <li><span className="font-semibold">Teléfono:</span> <a href="tel:+1234567890" className="text-white hover:text-green-500">+1234567890</a></li>
                    <li><span className="font-semibold">DNI:</span> 12345678Z</li>
                    <li><span className="font-semibold">Fecha de nacimiento:</span> 01/01/1990</li>
                </ul>
            </div>
        </section>
    );
}

export default Perfil;
