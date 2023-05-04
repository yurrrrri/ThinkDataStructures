package com.yuri.thinkTest;

import com.yuri.think.WikiPhilosophy;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class WikiPhilosophyTest {

    @Test
    void testMain() {
        String[] args = {};

        try {
            WikiPhilosophy.main(args);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }
}
