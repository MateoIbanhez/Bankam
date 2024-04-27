import React from 'react';

const Soporte = () => {
    return (
        <section className="flex flex-col items-center justify-center py-8 md:py-16 lg:py-24 space-y-8 md:space-y-12 lg:space-y-16">
            <h1 className="text-4xl md:text-6xl lg:text-7xl font-bold mb-8 text-center text-green-400">Soporte digital</h1>
            <div className="max-w-screen-lg px-4 md:px-0">
                <h3 className="text-2xl md:text-3xl font-semibold text-center text-green-400 mb-4">Problemas de conexión</h3>
                <p className="text-lg text-white mb-6">Si estás experimentando problemas de conexión, por favor sigue los siguientes pasos:</p>
                <ul className=" text-white list-disc list-inside mb-6">
                    <li>Reinicia tu router y dispositivo.</li>
                    <li>Verifica que estés conectado a una red estable.</li>
                    <li>Si el problema persiste, contacta a nuestro equipo de soporte técnico.</li>
                </ul>

                <h3 className="text-2xl md:text-3xl font-semibold text-center text-green-400 mb-4">Problemas de acceso</h3>
                <p className="text-lg text-white mb-6">Si no puedes acceder a tu cuenta, te recomendamos:</p>
                <ul className="text-white list-disc list-inside mb-6">
                    <li>Verifica que estés utilizando las credenciales correctas.</li>
                    <li>Restablece tu contraseña si es necesario.</li>
                    <li>Si el problema persiste, comunícate con nuestro equipo de soporte para obtener ayuda adicional.</li>
                </ul>
                <h3 className="text-2xl md:text-3xl font-semibold text-center text-green-400 mb-4">He sido victima de un robo de datos</h3>
                <p className="text-lg text-white mb-6">Si sospechas de que has sido victima de un robo de datos:</p>
                <ul className="text-white list-disc list-inside mb-6">
                    <li>Realiza un cambio urgente en tus credenciales de acceso.</li>
                    <li>Solicita un bloqueo temporal de tu cuenta.</li>
                    <li>Denuncia el hecho ante los cuerpos de seguridad del estado.</li>
                </ul>

                <h3 className="text-2xl md:text-3xl font-semibold text-center text-green-400 mb-4">He perdido mi tarjeta</h3>
                <p className="text-lg text-white mb-6">Si has extraviado tu tarjeta te recomendamos:</p>
                <ul className="text-white list-disc list-inside mb-6">
                    <li>Ponte en contacto con nuestro equipo de soporte para solicitar una nueva tarjeta.</li>
                    <li>Solicita el bloqueo de la tarjeta extraviada, por la seguridad de tu dinero.</li>
                    <li>Puedes agregar tu tarjeta en la Wallet de tu Smartphone, evitando llevar la fisica contigo.</li>
                </ul>

                <h3 className="text-2xl md:text-3xl font-semibold text-center text-green-400 mb-4">Otros problemas</h3>
                <p className="text-lg text-white mb-6">Si tienes algún otro problema o consulta, no dudes en contactarnos:</p>
                <ul className="text-white list-disc list-inside mb-6">
                    <li>Puedes enviar un correo electrónico a soporte@tubancodigital.com</li>
                    <li>Visita nuestra sección de preguntas frecuentes para obtener respuestas a preguntas comunes.</li>
                    <li>Nuestro equipo de soporte está disponible las 24 horas del día, los 7 días de la semana.</li>
                </ul>
            </div>
        </section>
    );
}

export default Soporte;