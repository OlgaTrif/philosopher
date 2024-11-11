public class Philosopher implements Runnable {
    /**
     * Число философов
     */
    private final int maxPhilosofields;
    /**
     * Количество приемов пищи
     */
    private int eatCounter;
    /**
     * Имя философа
     */
    private final String name;
    /**
     * Раунд
     */
    private Round round;
    /**
     * Номер философа
     */
    private int id;
    /**и го
     * Время нужное философу для приема пищи
     */
    private int eatTime;

    /**
     * Конструктор
     *
     * @param name             имя философа
     * @param id               номер философа
     * @param maxPhilosofields всего философов
     * @param round            раунд
     * @param eatTime          время для еды
     */
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

    /**
     * Прием пищи
     *
     * @throws InterruptedException
     */
    private void eat() throws InterruptedException {
        this.eatCounter += 1;
        System.out.println(name + " поел " + eatCounter + " раз(а)");
        if (eatTime > 0) {
            Thread.sleep(eatTime);
        }
    }
}
