import React from 'react';
import { GameFile } from '../types';

interface FileBrowserProps {
    files: GameFile[];
    onDeleteFile: (fileId: number) => void; // <-- Nova prop para a função de deletar
}

const FileBrowser: React.FC<FileBrowserProps> = ({ files, onDeleteFile }) => {
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
                        <th>Tipo</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    {files.map((file) => (
                        <tr key={file.id}>
                            <td>{file.fileName}</td>
                            <td>{file.author}</td>
                            <td>{file.fileType}</td>
                            <td>
                                <button className="delete-button" onClick={() => onDeleteFile(file.id)}>
                                    Deletar
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default FileBrowser;