public class Round {
    private volatile int round;
    /**
     * Счетчик определившихся философов
     */
    private volatile int counter;
    /**
     * Число философов
     */
    private final int maxCounter;

    /**
     * Констрктор
     *
     * @param maxCounter Число философов
     */
    public Round(int maxCounter) {
        this.round = 0;
        this.counter = 0;
        this.maxCounter = maxCounter;
    }

    /**
     * Вернуть номер раунда
     *
     * @return номер раунда
     */
    public int getRound() {
        return round;
    }

    /**
     * Следующий раунд
     *
     * @throws InterruptedException
     */
    public synchronized void next() throws InterruptedException {
        int nNow = round;
        int valNextRound = counter;
        if (valNextRound + 1 == maxCounter) {
            if (nNow + 1 < maxCounter) {
                round = nNow + 1;
            } else {
                round = 0;
            }
            System.out.println("Round " + nNow);
            counter = 0;
            notifyAll();
        } else {
            counter = valNextRound + 1;
            wait();
        }
    }
}
