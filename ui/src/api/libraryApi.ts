import axios from 'axios';
import { Game, GameFile } from '../types'; // Supondo que você criou um types.ts

const apiClient = axios.create({
    baseURL: 'http://localhost:8080/api/v1',
});

// ... sua função getGames paginada continua aqui ...

// Função para buscar os detalhes COMPLETOS de um jogo, incluindo os arquivos
export const getGameDetailsByName = (gameName: string) => {
    return apiClient.get<Game>(`/games/${gameName}`);
};

// Função para DELETAR um arquivo pelo ID
export const deleteFileById = (fileId: number) => {
    return apiClient.delete(`/files/${fileId}`);
};

// Interface para os dados de criação de um novo arquivo
interface NewFileData {
    fileName: string;
    fileType: string;
    description: string;
    author: string;
}

// Função para CRIAR um novo arquivo para um jogo
export const createFileForGame = (gameId: number, fileData: NewFileData) => {
    // Usamos o endpoint que espera o gameId na URL
    return apiClient.post<GameFile>(`/files/game/${gameId}`, fileData);
};