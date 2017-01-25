package palaznik;

import java.util.Random;

public class MoneySizeGenerator {

    private static final int MAX_MONEY_SIZE = 100;
    private Random moneyGenerator;

    public MoneySizeGenerator() {
        this.moneyGenerator = new Random();
    }

    public int getMoneySize() {
        return moneyGenerator.nextInt(MAX_MONEY_SIZE + 1);
    }
}
