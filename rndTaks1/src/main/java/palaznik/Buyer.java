package palaznik;

import org.apache.logging.log4j.LogManager;

/**
 * Consumer object
 */
public class Buyer extends Person {
    private final static org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(Buyer.class);

    public Buyer(Account account) {
        super(account);
    }

    @Override
    public void run() {
        while (super.isRunning()) {
            try {
                account.withdraw(moneySize.getMoneySize());
            } catch (InterruptedException e) {
                LOGGER.error("withdraw error", e);
            }
        }
    }
}
