package com.beyonity.tamilaudiobook.interactor;

import android.content.Context;
import android.os.Handler;

import com.beyonity.tamilaudiobook.bean.AudioBook;
import com.beyonity.tamilaudiobook.database.DBHelper;
import com.beyonity.tamilaudiobook.interfaces.IAllAudioBookPresenter;

import java.util.ArrayList;

/**
 * Created by Archit on 25-07-2017.
 */

public class NewAudioBookInteractor {

    private final DBHelper dbHandler;
    private IAllAudioBookPresenter presenter;
    private ArrayList<AudioBook> books;

    public NewAudioBookInteractor(Context context, IAllAudioBookPresenter presenter) {
        this.presenter = presenter;
        this.dbHandler = DBHelper.getInstance(context);
    }

    public void loadNewAudioBooks() {
        books = new ArrayList<>();
        final Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                books = dbHandler.loadNewAudioBooks();
                if (presenter != null)
                    presenter.onAudioBooksLoaded(books);
            }
        };
        handler.post(r);
    }

    public void markAudioBookOnGoing(AudioBook item) {
        dbHandler.updateBookStatus(item.getAlbumId(), 1);
    }
}
