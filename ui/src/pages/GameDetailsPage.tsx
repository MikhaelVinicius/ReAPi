import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import axios from 'axios';
import FileBrowser from '../componenents/FileBrowser';

interface GameFile {
  name: string;
  texto: string;
  autor: string;
  data: string;
}

interface Game {
  name: string;
  year: string;
  platarfoms: string[]; 
  files: GameFile[];
}

const GameDetailsPage: React.FC = () => {
  const [game, setGame] = useState<Game | null>(null);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  const { gameName } = useParams<{ gameName: string }>();

  useEffect(() => {
    if (!gameName) return;

    const fetchGameDetails = async () => {
      try {
        
        const response = await axios.get<Game>(`http://localhost:8080/api/v1/games/${gameName}`);
        setGame(response.data);
      } catch (err) {
        setError("Não foi possível carregar os detalhes do jogo.");
        console.error(err);
      } finally {
        setIsLoading(false);
      }
    };

    fetchGameDetails();
  }, [gameName]);

  if (isLoading) return <p>Carregando detalhes do jogo...</p>;
  if (error) return <p style={{ color: 'red' }}>{error}</p>;
  if (!game) return <p>Jogo não encontrado.</p>;

  return (
    <div>
      <Link to="/">&larr; Voltar para a lista</Link>
      <h2>{game.name} ({game.year})</h2>
      <p>Plataformas: {game.platarfoms.join(', ')}</p>

      <FileBrowser files={game.files} />
    </div>
  );
};

export default GameDetailsPage;