import React from 'react';
import { Link } from 'react-router-dom';
import { Game } from '../types';

interface GameListProps {
  games: Game[];
}

const GameList: React.FC<GameListProps> = ({ games = [] }) => {
  
  /**
   * 👇 A SOLUÇÃO ESTÁ AQUI
   * Esta versão melhorada da função resolve os dois problemas:
   * 1. Remove o erro "Function not implemented."
   * 2. Remove caracteres especiais (como ':') para criar uma URL válida.
   */
  const formatGameNameForUrl = (name: string): string => {
    if (!name) return '';
    return name
      .toLowerCase()                      // 1. Converte para minúsculas
      .replace(/:/g, '')                  // 2. Remove os dois-pontos (:)
      .replace(/\s+/g, '-');              // 3. Substitui espaços por hifens
  };

  if (games.length === 0) {
    return <p>Nenhum jogo encontrado.</p>;
  }

  return (
    <ul className="game-list">
      {games.map((game) => (
        <li key={game.name}>
          {/* A URL gerada agora será limpa e válida */}
          <Link to={`/game/${formatGameNameForUrl(game.name)}`}>
            {game.name} ({game.releaseYear})
          </Link>
        </li>
      ))}
    </ul>
  );
};

export default GameList;