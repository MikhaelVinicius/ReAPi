// Dentro de src/types.ts

export interface GameFile {
  id: number;
  fileName: string;
  fileType: string;
  description: string;
  author: string;
}

export interface Game {
  id: number;
  name: string;
  releaseYear: string; // <-- A versão correta
  platforms: string;
  files: GameFile[];
}

export interface PagedGameResponse {
  content: Game[];
  totalPages: number;
}