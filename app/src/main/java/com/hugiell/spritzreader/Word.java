package com.hugiell.spritzreader;

public class Word {
    private String word;

    public Word(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public int getPivotLetterPosition() {
        int pivot = 0;
        switch (getWord().length())
        {
            case 0:
            case 1:
                pivot = 0;
                break;
            case 2:
            case 3:
            case 4:
            case 5:
                pivot = 1;
                break;
            case 6:
            case 7:
            case 8:
            case 9:
                pivot = 2;
                break;
            case 10:
            case 11:
            case 12:
            case 13:
                pivot = 3;
                break;
            default:
                pivot = 4;
        };
        return pivot;
    }
}
