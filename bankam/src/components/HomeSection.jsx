import React, { useState } from 'react';

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
        <section className="flex flex-col items-center justify-center py-16 md:py-32">
            <h1 className="text-4xl md:text-6xl lg:text-7xl font-bold mb-8 text-center text-green-400">Bienvenido a Tu Banco Digital</h1>
            <h2 className="text-4xl md:text-6xl lg:text-7xl font-bold mb-8 text-center text-green-400">BANKAM</h2>
            <div className="grid grid-cols-1 md:grid-cols-3 gap-4 py-8 md:py-16 w-full max-w-screen-lg">
                {/* Bloque descriptivo 1 */}
                <div className="p-6 rounded-lg text-center transition duration-300 ease-in-out transform hover:scale-105 my-8">
                    <h3 className="text-xl font-semibold mb-2 text-green-400">Sencillo e Intuitivo</h3>
                    <p className="text-lg text-green-400 mb-4">Interfaz sencilla e intuitiva, para todos los usuarios</p>
                    <span className="text-4xl text-green-400" role="img" aria-label="Tick">✔️</span>
                </div>
                {/* Bloque descriptivo 2 */}
                <div className="p-6 rounded-lg text-center transition duration-300 ease-in-out transform hover:scale-105 my-8">
                    <h3 className="text-xl font-semibold mb-2 text-green-400">Operaciones Globales</h3>
                    <p className="text-lg text-green-400 mb-4">Envía y recibe dinero de forma Internacional</p>
                    <span className="text-4xl text-green-400" role="img" aria-label="World">🌐</span>
                </div>
                {/* Bloque descriptivo 3 */}
                <div className="p-6 rounded-lg text-center transition duration-300 ease-in-out transform hover:scale-105 my-8">
                    <h3 className="text-xl font-semibold mb-2 text-green-400">Seguridad Garantizada</h3>
                    <p className="text-lg text-green-400 mb-4">Dinero y datos blindados con nuestro sistema de seguridad</p>
                    <span className="text-4xl text-green-400" role="img" aria-label="Lock">🔒</span>
                </div>
            </div>
            <div className="py-8 md:py-16 w-full max-w-screen-lg">
                <h2 className="text-4xl md:text-6xl lg:text-7xl font-bold mb-8 text-center text-green-400">¿Qué hace único a Bankam?</h2>
                {questions.map((q, index) => (
                    <div key={index} className="border-b-2 border-gray-400 py-4">
                        <h3 className="text-2xl font-semibold text-green-400 cursor-pointer transition duration-300 ease-in-out" onClick={() => toggleQuestion(index)}>{q.question}</h3>
                        <p className={`text-lg text-white mt-2 transition duration-300 ease-in-out ${q.isOpen ? 'opacity-100 max-h-96' : 'opacity-0 max-h-0 overflow-hidden'}`}>{q.answer}</p>
                    </div>
                ))}
            </div>
        </section>
    );
};

export default HomeSection;
