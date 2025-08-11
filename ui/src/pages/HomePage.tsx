import React, { useState, useEffect, useCallback } from 'react';
import axios from 'axios';
import GameList from '../components/GameList';
import SearchBar from '../components/SearchBar';
import { Game, PagedGameResponse } from '../types'; // <-- A IMPORTAÇÃO FICA

// APAGUE TODO O BLOCO DE DEFINIÇÃO DE INTERFACES ABAIXO.
// Elas agora vêm do arquivo 'types.ts'.

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

            // 👇 A CORREÇÃO ESTÁ AQUI: Adicione <PagedGameResponse>
            const response = await axios.get<PagedGameResponse>(`http://localhost:8080/api/v1/games?${params.toString()}`);
            
            // Agora o TypeScript sabe que response.data tem .content e .totalPages
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

    // ... o resto do seu componente ...

    return (
        <div>
          <h2>Biblioteca de Jogos</h2>
          <SearchBar onSearch={(query) => { setSearchQuery(query); setCurrentPage(0); }} />
          {isLoading ? <p>Carregando...</p> : <GameList games={games} />}
          
          <div className="pagination">
            <button onClick={() => setCurrentPage(currentPage - 1)} disabled={currentPage === 0}>
              Anterior
            </button>
            <span>Página {currentPage + 1} de {totalPages}</span>
            <button onClick={() => setCurrentPage(currentPage + 1)} disabled={currentPage >= totalPages - 1}>
              Próxima
            </button>
          </div>
        </div>
      );
};

export default HomePage;