import React, { useState } from 'react';

const Login = () => {
    return (
        <div className="flex items-center justify-center h-screen">
            <div className='bg-slate-800 border border-slate-600 rounded-md p-8 shadow-lg backdrop-filter backdrop-blur-lg bg-opacity-30 relative'>
                <h1 className="text-4xl font-bold text-center mb-8">Iniciar Sesión</h1>
                <form action="">
                    <div className='relative'>
                        <input type='text' className='block w-72 py-2.5 px-0 text-sm text-white bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:text-white focus:border-blue-600 peer' placeholder=''/>
                        <label htmlFor=''className='absolute text-sm duration-300 transform -translate scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark-text-blue-500 peer-placeholder-shown-scale-100 peer-focus:scale-75 peer-focus:-translate-y-6'>Nombre de Usuario</label>
                    </div>
                    <div className='relative my-4'>
                        <input type='password' className='block w-72 py-2.5 px-0 text-sm text-white bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:text-white focus:border-blue-600 peer' placeholder=''/>
                        <label htmlFor='' className='absolute text-sm duration-300 transform -translate scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark-text-blue-500 peer-placeholder-shown-scale-100 peer-focus:scale-75 peer-focus:-translate-y-6'>Contraseña</label>
                    </div>
                    <button className="block w-full bg-blue-500 text-white py-2.5 rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-opacity-50">Iniciar Sesión</button>
                </form>
            </div>
        </div>
    );
};

export default Login;