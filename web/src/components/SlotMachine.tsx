import { motion } from 'framer-motion';
import { Howl } from 'howler';
import { useEffect, useRef, useState } from 'react';
import styles from '../styles/SlotMachine.module.css';

const SlotMachine = () => {
    const [numbers, setNumbers] = useState<number[]>([]);
    const [currentIndex, setCurrentIndex] = useState<number>(-1);
    const [winningNumber, setWinningNumber] = useState<number | null>(null);
    const [winningMessage, setWinningMessage] = useState<string>('');
    const [showMessage, setShowMessage] = useState<boolean>(false);
    const intervalRef = useRef<NodeJS.Timeout | null>(null);

    const spinSound = new Howl({
        src: ['69723__lukaso__stop_5_0.wav'],
        html5: true
    });

    const winSound = new Howl({
        src: ['717771__1bob__victory-chime.wav'],
        html5: true
    });

    const loseSound = new Howl({
        src: ['331912__kevinvg207__wrong-buzzer.wav'],
        html5: true
    });

    useEffect(() => {
        setNumbers(shuffleArray([...Array(20).keys()].map(x => x + 1)));
    }, []);

    const shuffleArray = (array: number[]): number[] => {
        for (let i = array.length - 1; i > 0; i--) {
            const j = Math.floor(Math.random() * (i + 1));
            [array[i], array[j]] = [array[j], array[i]];
        }
        return array;
    };

    const spin = async () => {
        setShowMessage(false);
        const response = await fetch('http://localhost:8080/spin?userId=123&clientSeed=1234', {
            method: 'POST',
        });
        const data = await response.json();
        setWinningNumber(data.generatedNumber);
        setWinningMessage(data.prime ? "You won!!" : "You lose!");

        intervalRef.current = setInterval(() => {
            setCurrentIndex(prevIndex => {
                const newIndex = (prevIndex + 1) % numbers.length;
                spinSound.play();
                return newIndex;
            });
        }, 100);

        setTimeout(() => {
            clearInterval(intervalRef.current!);
            setCurrentIndex(numbers.findIndex(num => num === data.generatedNumber));
            if (data.prime) {
                winSound.play();
            } else {
                loseSound.play();
            }
            setShowMessage(true);
        }, 3000);
    };

    return (
        <div className={styles.container}>
            <div>
                {currentIndex !== -1 && (
                    <motion.div
                        animate={{ y: [0, -30, 0] }}
                        transition={{ repeat: Infinity, duration: 1 }}
                        className={styles.numberDisplay}
                    >
                        {numbers[currentIndex]}
                    </motion.div>
                )}
                <div className={styles.buttonContainer}>
                    <button onClick={spin} className={styles.spinButton}>Spin</button>
                </div>
                {showMessage && (
                    <div
                        className={`${styles.message} ${winningMessage.includes("won") ? styles.winMessage : styles.loseMessage
                            }`}
                    >
                        {winningMessage}
                    </div>
                )}
            </div>
        </div>
    );
};

export default SlotMachine;
