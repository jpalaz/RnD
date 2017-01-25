package palaznik;

/**
 * Combined part of Buyer and Payer
 */
public abstract class Person extends Thread {
    protected MoneySizeGenerator moneySize;
    private boolean isRunning;
    protected Account account;

    public Person(Account account) {
        this.moneySize = new MoneySizeGenerator();
        this.account = account;
        this.isRunning = true;
    }

    abstract public void run();

    public boolean isRunning() {
        return isRunning;
    }

    public synchronized void stopThread() {
        this.isRunning = false;
        this.notify();
    }
}
