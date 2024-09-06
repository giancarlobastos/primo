import React, { useEffect, useState } from 'react';
import Link from 'next/link';

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
    <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh', flexDirection: 'column' }}>
      <h1 style={{ marginBottom: '20px' }}>Game History</h1>
      {loading ? (
        <p>Loading...</p>
      ) : (
        <table style={{ borderCollapse: 'collapse', width: '80%', maxWidth: '600px' }}>
          <thead>
            <tr>
              <th style={{ border: '1px solid black', padding: '8px', backgroundColor: '#f2f2f2' }}>Number</th>
              <th style={{ border: '1px solid black', padding: '8px', backgroundColor: '#f2f2f2' }}>Prime</th>
              <th style={{ border: '1px solid black', padding: '8px', backgroundColor: '#f2f2f2' }}>Time</th>
            </tr>
          </thead>
          <tbody>
            {history.map((item, index) => (
              <tr key={index}>
                <td style={{ border: '1px solid black', padding: '8px', textAlign: 'center' }}>{item.generatedNumber}</td>
                <td style={{ border: '1px solid black', padding: '8px', textAlign: 'center' }}>{item.prime ? 'Yes' : 'No'}</td>
                <td style={{ border: '1px solid black', padding: '8px', textAlign: 'center' }}>{new Date(item.timestamp).toLocaleString()}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
      <Link href="/" style={{ marginTop: '20px', textDecoration: 'none', color: 'blue', fontWeight: 'bold' }}>Return to Game</Link>
    </div>
  );
};

export default History;
