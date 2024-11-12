public class Philosopher implements Runnable {
    private final int maxPhilosofields;
    private int eatCounter;
    private final String name;
    private Round round;
    private int id;
    private int eatTime;
    private boolean canEat;

    public Philosopher(String name, int id, final int maxPhilosofields, final Round round, int eatTime) {
        this.name = name;
        this.round = round;
        this.maxPhilosofields = maxPhilosofields;
        this.id = id;
        this.eatTime = eatTime;
        eatCounter = 0;
        canEat = true;
    }

    @Override
    public void run() {
        final int variations = (int) Math.floor(maxPhilosofields / 2.0);
        while (true) {
            try {
                int nRound = round.getRound();
                int nIndex;
                    for (int i = 0; i < variations; ++i) {
                        nIndex = nRound + i * 2;
                        if (nIndex >= maxPhilosofields) {
                            nIndex -= maxPhilosofields;
                        }
                        if (id == nIndex) {
                            if (allowToEat()) {
                                eat();
                            } else {
                                System.out.println(name + " наелся");
                            }
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
        if (eatCounter == 3) {
            canEat = false;
        }
        if (eatTime > 0) {
            Thread.sleep(eatTime);
        }
    }

    public boolean allowToEat() {
        return this.canEat;
    }
}
