import React from 'react';
import { Link } from 'react-router-dom';

interface Game {
  name: string;
  year: string;
}

interface GameListProps {
  games: Game[];
}


const GameList: React.FC<GameListProps> = ({ games = [] }) => {


  if (games.length === 0) {
    return <p>Nenhum jogo encontrado.</p>;
  }

  const formatGameNameForUrl = (name: string) => {
    return name.toLowerCase().replace(/\s+/g, '-');
  };

  return (
    <ul className="game-list">
      {games.map((game) => (
        <li key={game.name}>
          <Link to={`/game/${formatGameNameForUrl(game.name)}`}>
            {game.name} ({game.year})
          </Link>
        </li>
      ))}
    </ul>
  );
};

export default GameList;