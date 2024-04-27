import React, { useState } from 'react';

const Transferencias = () => {
    const [formDataHacerTransferencia, setFormDataHacerTransferencia] = useState({
        nombre: '',
        idUsuario: '',
        iban: '',
        concepto: '',
        cantidad: ''
    });

    const [formDataConsultarTransferencia, setFormDataConsultarTransferencia] = useState({
        consultar_idUsuario: '',
        consultar_fechaInicio: '',
        consultar_fechaFinal: ''
    });

    const handleChangeHacerTransferencia = (e) => {
        const { name, value } = e.target;
        setFormDataHacerTransferencia({
            ...formDataHacerTransferencia,
            [name]: value
        });
    };

    const handleChangeConsultarTransferencia = (e) => {
        const { name, value } = e.target;
        setFormDataConsultarTransferencia({
            ...formDataConsultarTransferencia,
            [name]: value
        });
    };

    const handleSubmitHacerTransferencia = (e) => {
        e.preventDefault();
        console.log(formDataHacerTransferencia);
        // Aquí puedes agregar la lógica para enviar los datos a tu backend o hacer cualquier otro procesamiento necesario
    };

    const handleSubmitConsultarTransferencia = (e) => {
        e.preventDefault();
        console.log(formDataConsultarTransferencia);
        // Aquí puedes agregar la lógica para enviar los datos a tu backend o hacer cualquier otro procesamiento necesario
    };

    return (
        <section className="flex flex-col items-center justify-center py-8 md:py-16 lg:py-24 space-y-8 md:space-y-12 lg:space-y-16">
            <h1 className="text-4xl md:text-6xl lg:text-7xl font-bold mb-8 text-center text-green-400">Bienvenido a tus Transferencias</h1>
            <div className="text-center text-white text-lg lg:text-2xl xl:text-1xl mb-10">
    <p>En esta sección puedes hacer transferencias, solicitar un historial de las que ya has realizado y cancelar una transferencia si es necesario.</p>
</div>
            <div className="grid grid-cols-1 md:grid-cols-2 gap-8 w-full max-w-4xl">
                <div className="bg-gray-800 p-8 rounded-lg text-center">
                    <h3 className="text-xl font-semibold mb-6 text-white">Hacer Transferencia</h3>
                    <form onSubmit={handleSubmitHacerTransferencia} className="max-w-md mx-auto">
                        <div className="mb-6">
                            <label htmlFor="nombre" className="block text-white mb-2">Nombre:</label>
                            <input type="text" id="nombre" name="nombre" value={formDataHacerTransferencia.nombre} onChange={handleChangeHacerTransferencia} className="w-full px-4 py-2 rounded-md bg-gray-700 text-white focus:outline-none focus:ring focus:ring-green-400" />
                        </div>
                        <div className="mb-6">
                            <label htmlFor="idUsuario" className="block text-white mb-2">ID de Usuario:</label>
                            <input type="text" id="idUsuario" name="idUsuario" value={formDataHacerTransferencia.idUsuario} onChange={handleChangeHacerTransferencia} className="w-full px-4 py-2 rounded-md bg-gray-700 text-white focus:outline-none focus:ring focus:ring-green-400" />
                        </div>
                        <div className="mb-6">
                            <label htmlFor="iban" className="block text-white mb-2">IBAN:</label>
                            <input type="text" id="iban" name="iban" value={formDataHacerTransferencia.iban} onChange={handleChangeHacerTransferencia} className="w-full px-4 py-2 rounded-md bg-gray-700 text-white focus:outline-none focus:ring focus:ring-green-400" />
                        </div>
                        <div className="mb-6">
                            <label htmlFor="concepto" className="block text-white mb-2">Concepto:</label>
                            <input type="text" id="concepto" name="concepto" value={formDataHacerTransferencia.concepto} onChange={handleChangeHacerTransferencia} className="w-full px-4 py-2 rounded-md bg-gray-700 text-white focus:outline-none focus:ring focus:ring-green-400" />
                        </div>
                        <div className="mb-6">
                            <label htmlFor="cantidad" className="block text-white mb-2">Cantidad:</label>
                            <input type="text" id="cantidad" name="cantidad" value={formDataHacerTransferencia.cantidad} onChange={handleChangeHacerTransferencia} className="w-full px-4 py-2 rounded-md bg-gray-700 text-white focus:outline-none focus:ring focus:ring-green-400" />
                        </div>
                        <button type="submit" className="bg-green-400 text-white py-2 px-4 rounded-md hover:bg-green-500 focus:outline-none focus:ring focus:ring-green-400">Enviar</button>
                    </form>
                </div>
                <div className="bg-gray-800 p-8 rounded-lg text-center mt-8 md:mt-0">
                    <h3 className="text-xl font-semibold mb-6 text-white">Consultar Transferencia</h3>
                    <form onSubmit={handleSubmitConsultarTransferencia} className="max-w-md mx-auto">
                        <div className="mb-6">
                            <label htmlFor="consultar_idUsuario" className="block text-white mb-2">ID de Usuario:</label>
                            <input type="text" id="consultar_idUsuario" name="consultar_idUsuario" value={formDataConsultarTransferencia.consultar_idUsuario} onChange={handleChangeConsultarTransferencia} className="w-full px-4 py-2 rounded-md bg-gray-700 text-white focus:outline-none focus:ring focus:ring-green-400" />
                        </div>
                        <div className="mb-6">
                            <label htmlFor="consultar_fechaInicio" className="block text-white mb-2">Fecha Inicio:</label>
                            <input type="text" id="consultar_fechaInicio" name="consultar_fechaInicio" value={formDataConsultarTransferencia.consultar_fechaInicio} onChange={handleChangeConsultarTransferencia} className="w-full px-4 py-2 rounded-md bg-gray-700 text-white focus:outline-none focus:ring focus:ring-green-400" />
                        </div>
                        <div className="mb-6">
                            <label htmlFor="consultar_fechaFinal" className="block text-white mb-2">Fecha Final:</label>
                            <input type="text" id="consultar_fechaFinal" name="consultar_fechaFinal" value={formDataConsultarTransferencia.consultar_fechaFinal} onChange={handleChangeConsultarTransferencia} className="w-full px-4 py-2 rounded-md bg-gray-700 text-white focus:outline-none focus:ring focus:ring-green-400" />
                        </div>
                        <button type="submit" className="bg-green-400 text-white py-2 px-4 rounded-md hover:bg-green-500 focus:outline-none focus:ring focus:ring-green-400">Enviar</button>
                    </form>
                </div>
            </div>
        </section>
    );
};

export default Transferencias;
