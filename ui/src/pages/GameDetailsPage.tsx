import React, { useState, useEffect, useCallback } from 'react';
import { useParams, Link } from 'react-router-dom';
import { getGameDetailsByName, createFileForGame, deleteFileById } from '../api/libraryApi';
import { Game, GameFile } from '../types';
import FileBrowser from '../components/FileBrowser';
import AddFileForm from '../components/AddFileForm'; 
import styles from './GameDetailsPage.module.css';
const GameDetailsPage: React.FC = () => {
    const [game, setGame] = useState<Game | null>(null);
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    const { gameName } = useParams<{ gameName: string }>();

    const fetchGameDetails = useCallback(async () => {
        if (!gameName) return;
        setIsLoading(true);
        try {
            const response = await getGameDetailsByName(gameName);
            setGame(response.data);
        } catch (err) {
            setError("Não foi possível carregar os detalhes do jogo.");
        } finally {
            setIsLoading(false);
        }
    }, [gameName]);

    useEffect(() => {
        fetchGameDetails();
    }, [fetchGameDetails]);

  
    const handleAddFile = async (fileData: { fileName: string; fileType: string; author: string; description: string; }) => {
        if (!game) return;
        try {
            await createFileForGame(game.id, fileData);
            
            fetchGameDetails();
        } catch (error) {
            console.error("Erro ao adicionar arquivo:", error);
            alert("Falha ao adicionar arquivo.");
        }
    };

    
    const handleDeleteFile = async (fileId: number) => {
        const originalFiles = game?.files || [];
 
        if (game) {
            setGame({
                ...game,
                files: game.files.filter(f => f.id !== fileId),
            });
        }
        
        try {
            await deleteFileById(fileId);
        } catch (error) {
            console.error("Erro ao deletar arquivo:", error);
            alert("Falha ao deletar arquivo. Restaurando lista.");
           
            if (game) setGame({ ...game, files: originalFiles });
        }
    };

    if (isLoading) return <p>Carregando detalhes do jogo...</p>;
    if (error) return <p style={{ color: 'red' }}>{error}</p>;
    if (!game) return <p>Jogo não encontrado.</p>;

     return (
    <div className={styles.detailsContainer}>
      <Link to="/" className={styles.backLink}>&lt;&lt; RETURN TO ARCHIVES</Link>
      <header className={styles.header}>
        <h2 className={styles.gameTitle}>{game.name}</h2>
        <p>CASE FILE YEAR: {game.releaseYear} // SYSTEMS: {game.platforms}</p>
      </header>
      
      <AddFileForm onAddFile={handleAddFile} />
      <FileBrowser files={game.files} onDeleteFile={handleDeleteFile} />
    </div>
  );
};

export default GameDetailsPage;