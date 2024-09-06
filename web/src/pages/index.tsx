import Link from 'next/link';
import SlotMachine from '../components/SlotMachine';
import styles from '../styles/Home.module.css';

const Home = () => {
  return (
    <div className={styles.container}>
      <h1 className={styles.title}>Primo!!</h1>
      <SlotMachine />
      <div className={styles.linkContainer}>
        <Link href="/history">View History</Link>
      </div>
    </div>
  );
};

export default Home;
