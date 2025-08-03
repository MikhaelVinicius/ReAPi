import React, { useState, useEffect, useCallback } from 'react';
import axios from 'axios';
import GameList from '../componenents/GameList';
import SearchBar from '../componenents/SearchBar';


interface Game {
  name: string;
  year: string;
}


interface PagedResponse {
    content: Game[];
    totalPages: number;
    number: number; 
}

const HomePage: React.FC = () => {
  const [games, setGames] = useState<Game[]>([]);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [searchQuery, setSearchQuery] = useState('');
  const [isLoading, setIsLoading] = useState(false);

  const fetchGames = useCallback(async () => {
    setIsLoading(true);
    try {
  
      const params = new URLSearchParams({
        page: currentPage.toString(),
        size: '10',
        search: searchQuery,
      });

      
      const response = await axios.get<PagedResponse>(`http://localhost:8080/api/v1/games?${params.toString()}`);

      setGames(response.data.content);
      setTotalPages(response.data.totalPages);
    } catch (error) {
      console.error("Erro ao buscar jogos:", error);

    } finally {
        setIsLoading(false);
    }
  }, [currentPage, searchQuery]);

  useEffect(() => {
    fetchGames();
  }, [fetchGames]);

  const handleSearch = (query: string) => {
    setSearchQuery(query);
    setCurrentPage(0); 
  };

  const handlePageChange = (newPage: number) => {
    setCurrentPage(newPage);
  };

  return (
    <div>
      <h2>Biblioteca de Jogos</h2>
      <SearchBar onSearch={handleSearch} />
      {isLoading ? <p>Carregando...</p> : <GameList games={games} />}

      {}
      <div className="pagination">
        <button onClick={() => handlePageChange(currentPage - 1)} disabled={currentPage === 0}>
          Anterior
        </button>
        <span>Página {currentPage + 1} de {totalPages}</span>
        <button onClick={() => handlePageChange(currentPage + 1)} disabled={currentPage >= totalPages - 1}>
          Próxima
        </button>
      </div>
    </div>
  );
};

export default HomePage;