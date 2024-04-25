import React from 'react';

const HomeSection = () => {
    return (
        <section className="flex flex-col items-center justify-center py-60">
            <h1 className="text-4xl md:text-6xl lg:text-7xl font-bold mb-8 text-center text-green-400">Bienvenido a Tu Banco Digital</h1>
            <h2 className="text-4xl md:text-6xl lg:text-7xl font-bold mb-8 text-center  text-green-400">BANKAM</h2>
            <div className="grid grid-cols-1 md:grid-cols-3 gap-4 py-20">
                {/* Bloque descriptivo 1 */}
                <div className="bg-gray-800 p-6 rounded-lg text-center">
                    <h3 className="text-xl font-semibold mb-4">Sencillo e Intuitivo</h3>
                    <p className="text-lg">Descripción de la ventaja 1 aquí...</p>
                </div>
                {/* Bloque descriptivo 2 */}
                <div className="bg-gray-800 p-6 rounded-lg text-center">
                    <h3 className="text-xl font-semibold mb-4">Operaciones a nivel Internacional</h3>
                    <p className="text-lg">Descripción de la ventaja 2 aquí...</p>
                </div>
                {/* Bloque descriptivo 3 */}
                <div className="bg-gray-800 p-6 rounded-lg text-center">
                    <h3 className="text-xl font-semibold mb-4">Almacena tu dinero de forma segura</h3>
                    <p className="text-lg">Descripción de la ventaja 3 aquí...</p>
                </div>
            </div>
        </section>
    );
};

export default HomeSection;