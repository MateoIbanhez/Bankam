import React, { useState } from 'react';
import './HomeSection.css';

const HomeSection = () => {
    const [questions, setQuestions] = useState([
        { question: '¿De donde viene Bankam?', answer: 'El proyecto nace de los sueños de un grupo de programadores que busca ofrecer al cliente la solución definitiva para los problemas más usuales que ofrecen los bancos convencionales, y que nosotros solucionamos con gran eficacia.', isOpen: false },
        { question: '¿Cómo puedo abrir una cuenta en Bankam?', answer: 'Puedes abrir una cuenta en Bankam completando el formulario en línea en nuestro sitio web.', isOpen: false },
        { question: '¿Bankam cobra comisiones por realizar alguna operación?', answer: 'No, Bankam es libre de comisiones y condiciones a la hora de operar o abrirte una cuenta.', isOpen: false },
        { question: '¿Cómo puedo realizar una transferencia internacional?', answer: 'Para realizar una transferencia internacional, debes completar el formulario de transferencia en línea y proporcionar los detalles del destinatario y el monto a transferir. Esta función la tenemos en fase de BETA.', isOpen: false },
    ]);

    const toggleQuestion = (index) => {
        setQuestions(prevQuestions => {
            const updatedQuestions = prevQuestions.map((q, i) => {
                if (i === index) {
                    return { ...q, isOpen: !q.isOpen };
                } else {
                    return { ...q, isOpen: false };
                }
            });
            return updatedQuestions;
        });
    };

    return (
        <section className="flex flex-col items-center justify-center py-8 md:py-16 lg:py-24 space-y-8 md:space-y-12 lg:space-y-16">
            <h1 className="text-4xl md:text-6xl lg:text-7xl font-bold mb-8 text-center text-green-400">Bienvenido a Tu Banco Digital</h1>
            <h2 className="text-4xl md:text-6xl lg:text-7xl font-bold mb-8 text-center text-green-400">BANKAM</h2>
            <div className="grid grid-cols-1 md:grid-cols-3 gap-4 py-8 md:py-16 w-full max-w-screen-lg">
                {/* Bloques descriptivos */}
                <div className="p-6 rounded-lg text-center transition duration-300 ease-in-out transform hover:scale-105">
                    <h3 className="text-xl font-semibold mb-2 text-white">Sencillo e Intuitivo</h3>
                    <p className="text-lg text-white mb-4">Interfaz sencilla e intuitiva, para todos los usuarios</p>
                    <span className="text-4xl text-white" role="img" aria-label="Tick">✔️</span>
                </div>
                <div className="p-6 rounded-lg text-center transition duration-300 ease-in-out transform hover:scale-105">
                    <h3 className="text-xl font-semibold mb-2 text-white">Operaciones Globales</h3>
                    <p className="text-lg text-white mb-4">Envía y recibe dinero de forma Internacional</p>
                    <span className="text-4xl text-white" role="img" aria-label="World">🌐</span>
                </div>
                <div className="p-6 rounded-lg text-center transition duration-300 ease-in-out transform hover:scale-105">
                    <h3 className="text-xl font-semibold mb-2 text-white">Seguridad Garantizada</h3>
                    <p className="text-lg text-white mb-4">Dinero y datos blindados con nuestro sistema de seguridad</p>
                    <span className="text-4xl text-white" role="img" aria-label="Lock">🔒</span>
                </div>
            </div>
            <div className="py-8 md:py-16 w-full max-w-screen-lg">
                <h2 className="text-4xl md:text-6xl lg:text-7xl font-bold mb-8 text-center text-green-400 pb-10">¿Qué hace único a Bankam?</h2>
                {questions.map((q, index) => (
                    <div key={index} className="border-b-2 border-gray-400 py-4">
                        <h3 className="text-2xl font-semibold text-green-400 cursor-pointer transition duration-300 ease-in-out" onClick={() => toggleQuestion(index)}>{q.question}</h3>
                        <p className={`text-lg text-white mt-2 transition duration-300 ease-in-out ${q.isOpen ? 'opacity-100 max-h-96' : 'opacity-0 max-h-0 overflow-hidden'}`}>{q.answer}</p>
                    </div>
                ))}
            </div>
            <div className="py-8 w-full max-w-screen-lg">
                <h2 className="text-4xl md:text-6xl lg:text-7xl font-bold mb-8 text-center text-green-400">Únete a nuestra Familia</h2>
                <p className="text-lg md:text-xl lg:text-2xl font-semibold mb-8 text-center text-green-400">Disfruta de las ventajas de la tarjeta BlackAM</p>
                <div className="flex justify-center mt-8 space-x-12">
                    <div className='py-8'>
                        <ul className="text-white text-lg">
                            <li className="mb-4">
                                <span className="inline-block w-4 h-4 bg-green-400 rounded-full mr-2"></span> Operaciones sin comisión
                            </li>
                            <li className="mb-4">
                                <span className="inline-block w-4 h-4 bg-green-400 rounded-full mr-2"></span> Recibela en menos de 48 horas
                            </li>
                        </ul>
                    </div>
                    <div className='py-8'>
                        <ul className="text-white text-lg">
                            <li className="mb-4">
                                <span className="inline-block w-4 h-4 bg-green-400 rounded-full mr-2"></span> Compatibilidad con otras entidades
                            </li>
                            <li className="mb-4">
                                <span className="inline-block w-4 h-4 bg-green-400 rounded-full mr-2"></span> Personalizado con el sello de la entidad
                            </li>
                        </ul>
                    </div>
                </div>
                <div className="flex items-center justify-center mt-1">
                    <div className="contenedor-imagen">
                        <img 
                            src="src/img/tarjeta.png" 
                            alt="Imagen de Unete a nuestra Familia" 
                            className="tarjeta"
                        />
                    </div>
                </div>
            </div>
            <section className="py-1  w-full max-w-screen-lg">
                <h2 className="text-3xl md:text-4xl lg:text-5xl font-bold mb-4 md:mb-8 text-center text-green-400 py-4">Opinión de Clientes</h2>
                <div className="w-full max-w-screen-lg grid grid-cols-1 md:grid-cols-3 gap-4">
                    {/* Cliente 1 */}
                    <div className="cliente bg-gray-600 rounded-lg p-4 md:p-8">
                        <div className="mb-2 md:mb-4">
                            <p className="text-base md:text-lg lg:text-xl font-semibold text-green-400">Luz Maria Hernandez</p>
                            <p className="text-xs md:text-sm lg:text-base text-white">"Nunca he estado tan contenta con mi banco, sin duda es una apuesta segura"</p>
                        </div>
                        <div className="w-12 md:w-16 h-12 md:h-16 bg-gray-200 rounded-full flex items-center justify-center">
                            {/* Aquí puedes agregar la imagen del cliente */}
                            <img src="src/img/luz.jpg" alt="Cliente 1" className="w-full h-full object-cover rounded-full" />
                        </div>
                    </div>

                    {/* Cliente 2 */}
                    <div className="cliente bg-gray-600 rounded-lg p-4 md:p-8">
                        <div className="mb-2 md:mb-4">
                            <p className="text-base md:text-lg lg:text-xl font-semibold text-green-400">Francisco Munoz</p>
                            <p className="text-xs md:text-sm lg:text-base text-white">"Seguro y rapido, no se puede pedir mas, lo recomiendo encarecidamente"</p>
                        </div>
                        <div className="w-12 md:w-16 h-12 md:h-16 bg-gray-600 rounded-full flex items-center justify-center">
                            {/* Aquí puedes agregar la imagen del cliente */}
                            <img src="src/img/fran.jpg" alt="Cliente 2" className="w-full h-full object-cover rounded-full" />
                        </div>
                    </div>

                    {/* Cliente 3 */}
                    <div className="cliente bg-gray-600 rounded-lg p-4 md:p-8">
                        <div className="mb-2 md:mb-4">
                            <p className="text-base md:text-lg lg:text-xl font-semibold text-green-400">Ana Gonzalez</p>
                            <p className="text-xs md:text-sm lg:text-base text-white">"Las transferencias se hacen al instante, muy comodo y su interfaz muy completa"</p>
                        </div>
                        <div className="w-12 md:w-16 h-12 md:h-16 bg-gray-600 rounded-full flex items-center justify-center">
                            {/* Aquí puedes agregar la imagen del cliente */}
                            <img src="src/img/anuka.jpg" alt="Cliente 3" className="w-full h-full object-cover rounded-full" />
                        </div>
                    </div>
                </div>
            </section>
        </section>
    );
};

export default HomeSection;
