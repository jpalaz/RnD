package model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ComputerManagerTest {

    /**
     * Test without multithreadin just because 1) I have no time to write it, 2) I have no idea now how to write it.
     */
    @Test
    public void findComputers() throws Exception {
        ComputerManager manager = new ComputerManager();
        Computer computer = manager.addComputer("a", "a", "a", "a", "a");
        computer = manager.addComputer("b", "b", "b", "b", "b");
        computer = manager.addComputer("a", "a", "b", "b", "b");

        List<Computer> computers = manager.findComputers(new Computer(null, null, null, null, "a"));
        assertEquals(1, computers.size());

        computers = manager.findComputers(new Computer("a", "b", null, null, null));
        assertEquals(0, computers.size());

        computers = manager.findComputers(new Computer("a", "a", null, null, null));
        assertEquals(2, computers.size());
    }

}