public class Philosopher implements Runnable {
    private final int maxPhilosofields;
    private int eatCounter;
    private final String name;
    private Round round;
    private int id;
    private int eatTime;

    public Philosopher(String name, int id, final int maxPhilosofields, final Round round, int eatTime) {
        this.name = name;
        this.round = round;
        this.maxPhilosofields = maxPhilosofields;
        this.id = id;
        this.eatTime = eatTime;
        eatCounter = 0;
    }

    @Override
    public void run() {
        final int variations = (int) Math.floor(maxPhilosofields / 2.0);
        while (true) {
            try {
                int nRound = round.getRound();
                for (int i = 0; i < variations; ++i) {
                    int nIndex = nRound + i * 2;
                    if (nIndex >= maxPhilosofields) {
                        nIndex -= maxPhilosofields;
                    }
                    if (id == nIndex) {
                        eat();
                    }
                }
                round.next();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void eat() throws InterruptedException {
        this.eatCounter += 1;
        System.out.println(name + " поел " + eatCounter + " раз(а)");
        if (eatTime > 0) {
            Thread.sleep(eatTime);
        }
    }
}
