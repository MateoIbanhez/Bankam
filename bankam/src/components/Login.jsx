import React, { useState } from 'react';

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleLogin = async (event) => {
        event.preventDefault(); // Evita el comportamiento predeterminado del formulario

        try {
            // Llama a la función handleLoginRequest con el nombre de usuario y contraseña
            await handleLoginRequest(username, password);
        } catch (error) {
            console.error('Error al iniciar sesión:', error);
        }
    };

    // Función para enviar una solicitud de inicio de sesión al servidor
    const handleLoginRequest = async (documento, password) => {
        try {
            // Construir el cuerpo de la solicitud
            const requestBody = {
                documento: documento,
                password: password
            };

            // Enviar la solicitud HTTP al servidor usando fetch
            const response = await fetch('http://localhost:443/api/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(requestBody)
            });

            // Procesar la respuesta del servidor
            const data = await response.json();
            const idUsuario = data;
            if (idUsuario !== 0) {
                console.log('Inicio de sesión exitoso. ID de usuario:', idUsuario);
            } else {
                console.log('Inicio de sesión fallido. Usuario o contraseña incorrectos.');
            }
        } catch (error) {
            console.error('Error al enviar la solicitud de inicio de sesión:', error);
        }
    };

    return (
        <div className="flex items-center justify-center h-screen">
            <div className='bg-slate-800 border border-slate-600 rounded-md p-8 shadow-lg backdrop-filter backdrop-blur-lg bg-opacity-30 relative'>
                <h1 className="text-4xl font-bold text-center mb-8">Iniciar Sesión</h1>
                <form onSubmit={handleLogin}> {/* Usa onSubmit en lugar de action */}
                    <div className='relative'>
                        <input type='text' className='block w-72 py-2.5 px-0 text-sm text-white bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:text-white focus:border-blue-600 peer' placeholder=''
                            value={username} onChange={(e) => setUsername(e.target.value)} /> {/* Captura y actualiza el valor del nombre de usuario */}
                        <label htmlFor='' className='absolute text-sm duration-300 transform -translate scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark-text-blue-500 peer-placeholder-shown-scale-100 peer-focus:scale-75 peer-focus:-translate-y-6'>Nombre de Usuario</label>
                    </div>
                    <div className='relative my-4'>
                        <input type='password' className='block w-72 py-2.5 px-0 text-sm text-white bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:text-white focus:border-blue-600 peer' placeholder=''
                            value={password} onChange={(e) => setPassword(e.target.value)} /> {/* Captura y actualiza el valor de la contraseña */}
                        <label htmlFor='' className='absolute text-sm duration-300 transform -translate scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark-text-blue-500 peer-placeholder-shown-scale-100 peer-focus:scale-75 peer-focus:-translate-y-6'>Contraseña</label>
                    </div>
                    <button type="submit" className="block w-full bg-blue-500 text-white py-2.5 rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-opacity-50">Iniciar Sesión</button>
                </form>
            </div>
        </div>
    );
};

export default Login;
