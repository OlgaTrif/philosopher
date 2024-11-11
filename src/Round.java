public class Round {
    private volatile int round;
    private volatile int counter;
    private final int maxCounter;

    public Round(int maxCounter) {
        this.round = 0;
        this.counter = 0;
        this.maxCounter = maxCounter;
    }

    public int getRound() {
        return round;
    }

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
