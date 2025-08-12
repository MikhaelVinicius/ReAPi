import React, { useState, useEffect, useCallback } from 'react';
import axios from 'axios';
import GameList from '../components/GameList';
import SearchBar from '../components/SearchBar';
import { Game, PagedGameResponse } from '../types';
import styles from './HomePage.module.css';

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

            const response = await axios.get<PagedGameResponse>(`http://localhost:8080/api/v1/games?${params.toString()}`);
            
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

    return (
        <div className={styles.homeContainer}>
            <h2 className={styles.title}>System Archives // Game List</h2>
            
            {}
            <SearchBar onSearch={handleSearch} />
            
            {isLoading ? <p>Accessing data...</p> : <GameList games={games} />}
            
            <div className={styles.pagination}>
                <button 
                  onClick={() => setCurrentPage(currentPage - 1)} 
                  disabled={currentPage === 0 || isLoading}
                >
                  &lt;&lt; Anterior
                </button>
                <span>
                  Página {currentPage + 1} de {totalPages}
                </span>
                <button 
                  onClick={() => setCurrentPage(currentPage + 1)} 
                  disabled={currentPage >= totalPages - 1 || isLoading}
                >
                  Próxima &gt;&gt;
                </button>
            </div>
        </div>
    );
};

export default HomePage;