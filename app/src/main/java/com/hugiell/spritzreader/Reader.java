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

    public static Reader getInstance() {
        return ourInstance;
    }

    private Reader() {
    }

    public static Observable<String> getWordReader(final String text, final int wordsPerMinute) {
        String[] words = text.split("[\\s']");
        Double delay = (double)60/wordsPerMinute*1000;
        return Observable
                .interval(delay.longValue(), TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .map(i -> words[i.intValue()])
                .take(words.length);
    }

}