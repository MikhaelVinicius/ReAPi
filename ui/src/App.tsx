import React from 'react';
import { Routes, Route } from 'react-router-dom';
import HomePage from './pages/HomePage';
import GameDetailsPage from './pages/GameDetailsPage';
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <h1>Resident Evil Library</h1>
      </header>
      <main>
        <Routes>
          {/* Rota para a página inicial */}
          <Route path="/" element={<HomePage />} />

          {/* Rota para a página de detalhes de um jogo */}
          {/* O ":gameName" é um parâmetro dinâmico */}
          <Route path="/game/:gameName" element={<GameDetailsPage />} />
        </Routes>
      </main>
    </div>
  );
}

export default App;