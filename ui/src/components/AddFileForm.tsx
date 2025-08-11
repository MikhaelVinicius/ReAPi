import React, { useState } from 'react';

interface AddFileFormProps {
    onAddFile: (fileData: { fileName: string; fileType: string; author: string; description: string; }) => void;
}

const AddFileForm: React.FC<AddFileFormProps> = ({ onAddFile }) => {
    const [fileName, setFileName] = useState('');
    const [fileType, setFileType] = useState('TXT');
    const [author, setAuthor] = useState('');
    const [description, setDescription] = useState('');

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        if (!fileName || !author) {
            alert("Nome do arquivo e autor são obrigatórios.");
            return;
        }
        onAddFile({ fileName, fileType, author, description });
        
        setFileName('');
        setFileType('TXT');
        setAuthor('');
        setDescription('');
    };

    return (
        <div className="add-file-form">
            <h4>Adicionar Novo Arquivo</h4>
            <form onSubmit={handleSubmit}>
                <input value={fileName} onChange={e => setFileName(e.target.value)} placeholder="Nome do Arquivo" />
                <input value={author} onChange={e => setAuthor(e.target.value)} placeholder="Autor" />
                <input value={fileType} onChange={e => setFileType(e.target.value)} placeholder="Tipo (TXT, PDF...)" />
                <textarea value={description} onChange={e => setDescription(e.target.value)} placeholder="Descrição"></textarea>
                <button type="submit">Adicionar</button>
            </form>
        </div>
    );
};

export default AddFileForm;