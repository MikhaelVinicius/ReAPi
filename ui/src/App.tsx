import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './App.css';


interface Game {
  id: number;
  title: string;
}

function App() {
  
  const [games, setGames] = useState<Game[]>([]);

  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
   
    const apiUrl = 'http://localhost:8080/api/v1/games';

    axios.get<Game[]>(apiUrl)
      .then(response => {
    
        setGames(response.data);
      })
      .catch(error => {
      
        console.error("Houve um erro ao buscar os dados!", error);
        setError('Falha ao conectar com o servidor. Verifique se o backend está rodando e tente novamente.');
      });
  }, []); 

  return (
    <div className="App">
      <header className="App-header">
        <h1>Resident Evil Library</h1>
        <h2>Lista de Jogos</h2>
        
        {error && <p style={{ color: 'red' }}>{error}</p>}
        
        <ul>
          {games.map(game => (
            <li key={game.id}>{game.title}</li>
          ))}
        </ul>
      </header>
    </div>
  );
}

export default App;