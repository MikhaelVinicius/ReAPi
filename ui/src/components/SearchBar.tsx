import React, { useState } from 'react';
import styles from './SearchBar.module.css';

interface SearchBarProps {
  onSearch: (query: string) => void;
}

const SearchBar: React.FC<SearchBarProps> = ({ onSearch }) => {
  const [query, setQuery] = useState('');

  const handleSearch = () => {
    onSearch(query);
  };

   return (
    <div className={styles.searchBar}>
      <input className={styles.searchInput} /* ...props... */ />
      <button className={styles.searchButton} /* ...props... */ >Search</button>
    </div>
  );
};

export default SearchBar;