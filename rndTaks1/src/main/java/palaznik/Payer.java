package palaznik;

import org.apache.logging.log4j.LogManager;

/**
 * Producer object
 */
public class Payer extends Person {
    private final static org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(Payer.class);


    public Payer(Account account) {
        super(account);
    }

    @Override
    public void run() {
        while (super.isRunning()) {
            try {
                account.deposit(moneySize.getMoneySize());
            } catch (InterruptedException e) {
                LOGGER.error("deposit error", e);
            }
        }
    }

}
