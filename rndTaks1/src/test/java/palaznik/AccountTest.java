package palaznik;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void testAccountWith2BuyersAnd2Payers() throws Exception {
        Account account = new Account();
        List<Person> people = new ArrayList<>(4);
        people.add(new Buyer(account));
        people.add(new Buyer(account));
        people.add(new Payer(account));
        people.add(new Payer(account));
        for (Thread thread : people) {
            thread.start();
        }
        TimeUnit.SECONDS.sleep(1);

        for (Person person : people) {
            person.stopThread();
        }
        for (Person person : people) {
            person.join();
        }
    }
}