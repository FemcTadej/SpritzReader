package com.hugiell.spritzreader;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Hugiell on 1. 09. 2017.
 */

public class WordTest {

    @Test
    public void testGetPivotLetterPosition() {
        assertTrue(new Word("At").getPivotLetterPosition() == 1);
        assertTrue(new Word("words").getPivotLetterPosition() == 1);
        assertTrue(new Word("increase").getPivotLetterPosition() == 2);
        assertTrue(new Word("increase").getPivotLetterPosition() == 2);
        assertTrue(new Word("textbook").getPivotLetterPosition() == 2);
        assertTrue(new Word("the").getPivotLetterPosition() == 1);
        assertTrue(new Word("even").getPivotLetterPosition() == 1);
        assertTrue(new Word("welcome").getPivotLetterPosition() == 2);
    }

}
