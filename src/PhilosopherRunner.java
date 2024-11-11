/***
 * 1. Пять безмолвных философов сидят вокруг круглого стола, перед каждым философом стоит тарелка спагетти.
 * 2. Вилки лежат на столе между каждой парой ближайших философов.
 * 3. Каждый философ может либо есть, либо размышлять.
 * 4. Философ может есть только тогда, когда держит две вилки — взятую справа и слева.
 * 5. Философ не может есть два раза подряд, не прервавшись на размышления (можно не учитывать)
 * Описать в виде кода такую ситуацию. Каждый философ должен поесть три раза
 */


public class PhilosopherRunner {
    public static void main(String[] args) {
        final int nCounter = 5;
        String[] names = {
                "Аристотель", "Платон", "Сократ", "Гераклит", "Кант", "Ксенофан", "Анаксимандр", "Диоген", "Порфирий"
        };

        Thread[] philosophers = new Thread[nCounter];
        Round round = new Round(nCounter);
        for (int i = 0; i < nCounter; ++i) {
            philosophers[i] = new Thread(
                    new Philosopher(names[i], i, nCounter, round, 1000 * (1 + i))
            );
        }
        System.out.println("Раунд " + round.getRound());
        for (Thread th : philosophers) {
            th.start();
        }

    }
}
