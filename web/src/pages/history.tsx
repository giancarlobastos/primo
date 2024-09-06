import React, { useEffect, useState } from 'react';
import Link from 'next/link';
import styles from '../styles/History.module.css';

interface Play {
  userId: string;
  generatedNumber: number;
  prime: boolean;
  timestamp: number;
}

const History = () => {
  const [history, setHistory] = useState<Play[]>([]);
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    const fetchHistory = async () => {
      const response = await fetch('http://localhost:8080/history?userId=123');
      const data = await response.json();
      setHistory(data);
      setLoading(false);
    };

    fetchHistory();
  }, []);

  return (
    <div className={styles.container}>
      <h1 className={styles.title}>Game History</h1>
      {loading ? (
        <p>Loading...</p>
      ) : (
        <table className={styles.table}>
          <thead>
            <tr>
              <th>Number</th>
              <th>Prime</th>
              <th>Time</th>
            </tr>
          </thead>
          <tbody>
            {history.map((item, index) => (
              <tr key={index}>
                <td>{item.generatedNumber}</td>
                <td>{item.prime ? 'Yes' : 'No'}</td>
                <td>{new Date(item.timestamp).toLocaleString()}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
      <Link href="/" className={styles.link}>Return to Game</Link>
    </div>
  );
};

export default History;
