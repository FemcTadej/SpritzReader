package com.hugiell.spritzreader;

import java.util.concurrent.TimeUnit;
import io.reactivex.*;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Hugiell on 1. 09. 2017.
 */
public class Reader {
    private static final Reader ourInstance = new Reader();
    private String mText;
    private int mWordsPerMinute;

    public static Reader getInstance(final String text, final int wordsPerMinute) {
        ourInstance.mText = text;
        ourInstance.mWordsPerMinute = wordsPerMinute;
        return ourInstance;
    }

    private Reader() {
    }

    public Observable<String> getReader() {
        String[] words = mText.split("[\\s']");
        Double delay = (double)60/mWordsPerMinute*1000;
        return Observable
                .interval(delay.longValue(), TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .map(i -> words[i.intValue()])
                .take(words.length);
    }

}