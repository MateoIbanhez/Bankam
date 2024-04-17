import React, { useState } from 'react';

const Login = () => {
    
    return(
        <div>
            <div className='bg-slate-800 border border-slate-600 rounded-md p-8 shadow-lg backdrop-fill'>
                <h1 className="text-4xl font-bold text-center">Iniciar Sesion</h1>
                <form action="">
                    <div>
                        <input type='text'/>
                        <label htmlFor=''>Nombre de Usuario</label>
                    </div>
                    <div>
                        <input type='password'/>
                        <label htmlFor=''>Contrasena</label>
                    </div>
                </form>
            </div>
        </div>

    );
};

export default  Login;