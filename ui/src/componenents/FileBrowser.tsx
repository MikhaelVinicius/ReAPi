import React from 'react';

// Sua entidade Files
interface GameFile {
  name: string;
  texto: string;
  autor: string;
  data: string;
}

interface FileBrowserProps {
  files: GameFile[];
}

const FileBrowser: React.FC<FileBrowserProps> = ({ files }) => {
  if (!files || files.length === 0) {
    return <p>Nenhum arquivo encontrado para este jogo.</p>;
  }

  return (
    <div className="file-browser">
      <h3>Arquivos do Jogo</h3>
      <table>
        <thead>
          <tr>
            <th>Nome</th>
            <th>Autor</th>
            <th>Data</th>
            <th>Descrição</th>
          </tr>
        </thead>
        <tbody>
          {files.map((file) => (
            <tr key={file.name}>
              <td>{file.name}</td>
              <td>{file.autor}</td>
              <td>{file.data}</td>
              <td>{file.texto}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default FileBrowser;