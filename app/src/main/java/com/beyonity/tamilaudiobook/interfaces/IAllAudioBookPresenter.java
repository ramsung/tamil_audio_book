package com.beyonity.tamilaudiobook.interfaces;

import com.beyonity.tamilaudiobook.bean.AudioBook;

import java.util.ArrayList;

/**
 * Created by Archit on 25-07-2017.
 */

public interface IAllAudioBookPresenter {
    void onAudioBooksLoaded(ArrayList<AudioBook> books);
}
