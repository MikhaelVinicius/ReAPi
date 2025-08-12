import React from 'react';
import { GameFile } from '../types';
import styles from './FileBrowser.module.css';
interface FileBrowserProps {
    files: GameFile[];
    onDeleteFile: (fileId: number) => void; 
}

const FileBrowser: React.FC<FileBrowserProps> = ({ files, onDeleteFile }) => {
    if (!files || files.length === 0) {
        return <p>Nenhum arquivo encontrado para este jogo.</p>;
    }

    return (
    <div className={styles.fileBrowser}>
      <h3 className={styles.title}>Evidence Locker // Files</h3>
      <div className={styles.fileGrid}>
        {files.map((file) => (
          <div key={file.id} className={styles.fileCard}>
            <div>
              <p className={styles.fileName}>{file.fileName}</p>
              <p className={styles.fileAuthor}>Author: {file.author}</p>
            </div>
            <button className={styles.deleteButton} onClick={() => onDeleteFile(file.id)}>
              Delete
            </button>
          </div>
        ))}
      </div>
    </div>
  );

};

export default FileBrowser;