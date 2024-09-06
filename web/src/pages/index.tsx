import React from 'react';
import SlotMachine from '../components/SlotMachine';
import Link from 'next/link';

const Home = () => {
  return (
    <div style={{ height: '100vh', display: 'flex', flexDirection: 'column', justifyContent: 'center', alignItems: 'center', backgroundColor: '#282c34', color: 'white' }}>
      <h1 style={{
        fontSize: '4rem',
        fontWeight: 'bold',
        color: '#FFD700',
        textShadow: '2px 2px 4px #000000',
        marginBottom: '2rem',
        animation: 'glow 1.5s ease-in-out infinite alternate'
      }}>
        Primo!!
      </h1>
      <SlotMachine />
      <div style={{ marginTop: '20px' }}>
        <Link href="/history">View History</Link>
      </div>
      <style jsx global>{`
                @keyframes glow {
                    from {
                        text-shadow: 0 0 10px #fff, 0 0 20px #fff, 0 0 30px #e60073, 0 0 40px #e60073, 0 0 50px #e60073, 0 0 60px #e60073, 0 0 70px #e60073;
                    }
                    to {
                        text-shadow: 0 0 20px #fff, 0 0 30px #ff4da6, 0 0 40px #ff4da6, 0 0 50px #ff4da6, 0 0 60px #ff4da6, 0 0 70px #ff4da6, 0 0 80px #ff4da6;
                    }
                }
            `}</style>
    </div>
  );
};

export default Home;
