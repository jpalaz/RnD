package palaznik;

import org.apache.logging.log4j.LogManager;

import java.util.concurrent.TimeUnit;

/**
 * Shared data
 */
public class Account {
    private int balance;
    private final static org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(Account.class);

    public synchronized void withdraw(int sum) throws InterruptedException {
        while (balance - sum < 0) {
            LOGGER.info("withdraw ${}, WAIT, current balance: {}", sum, balance);
            wait();
        }
        balance -= sum;
        LOGGER.info("withdraw ${}, SUCCESS, current balance: {}", sum, balance);
        notifyAll();
    }

    public synchronized void deposit(int sum) throws InterruptedException {
        balance += sum;
        LOGGER.info("deposit ${}, SUCCESS, current balance: {}", sum, balance);
        TimeUnit.MICROSECONDS.sleep(10);
        notifyAll();
    }
}
