import React from 'react';

const Contacto = () => {
    return (
        <section className="py-8 md:py-16 lg:py-24 bg-black">
            <div className="container mx-auto px-4 md:px-8 lg:px-16">
                <h1 className="text-4xl md:text-6xl lg:text-7xl font-bold mb-8 text-center text-green-400">Contacto</h1>
                <p className="text-lg text-center text-white mb-8">Agradecemos que hayas invertido tiempo en este proyecto tan especial para ambos, es nuestro primer salto al código "a gran escala" hecho solo por y para nosotros. Un saludo de parte de nuestro equipo.</p>
                <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
                    <div className="bg-gray-900 p-6 rounded-lg relative">
                        <div className="flex items-center justify-center mb-4">
                            <img src="src/img/mateoperfil.jpg" alt="Foto de perfil" className="w-16 h-16 rounded-full" />
                        </div>
                        <h3 className="text-2xl md:text-3xl font-semibold text-center text-green-400 mb-4">Mateo Ibanhez Soruco</h3>
                        <p className="text-lg text-white mb-6">Datos de contacto:</p>
                        <ul className="list-disc list-inside text-white mb-6">
                            <li><a href="mailto:mateoibanhezsoruco@gmail.com" className="text-white hover:text-green-500">mateoibanhezsoruco@gmail.com</a></li>
                            <li><a href="https://www.linkedin.com/in/mateo-ibanhez-soruco-bbb4ba27b/" target="_blank" rel="noopener noreferrer" className="text-white hover:text-green-500">LinkedIn</a></li>
                            <li><a href="tel:+34683623179" className="text-white hover:text-green-500">683 623 179</a></li>
                        </ul>
                    </div>
                    <div className="bg-gray-900 p-6 rounded-lg relative">
                        <div className="flex items-center justify-center mb-4">
                            <img src="/src/img/aitor.jpg" alt="Foto de perfil" className="w-16 h-16 rounded-full" />
                        </div>
                        <h3 className="text-2xl md:text-3xl font-semibold text-center text-green-400 mb-4">Aitor Vázquez García</h3>
                        <p className="text-lg text-white mb-6">Datos de contacto:</p>
                        <ul className="list-disc list-inside text-white mb-6">
                            <li><a href="mailto:aitorvazgar@gmail.com" className="text-white hover:text-green-500">aitorvazgar@gmail.com</a></li>
                            <li><a href="https://www.linkedin.com/in/mateo-ibanhez-soruco-bbb4ba27b/" target="_blank" rel="noopener noreferrer" className="text-white hover:text-green-500">LinkedIn</a></li>
                            <li><a href="tel:+34634583869" className="text-white hover:text-green-500">634 583 869</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </section>
    );
}

export default Contacto;
