package com.beyonity.tamilaudiobook.interactor;

import android.content.Context;

import com.beyonity.tamilaudiobook.bean.AudioBook;
import com.beyonity.tamilaudiobook.bean.BookChapter;
import com.beyonity.tamilaudiobook.database.DBHelper;
import com.beyonity.tamilaudiobook.interfaces.IPlayerPresenter;
import com.beyonity.tamilaudiobook.interfaces.IPlayerService;
import com.beyonity.tamilaudiobook.utils.Utils;

/**
 * Created by HP on 30-07-2017.
 */

public class PlayerInteractor {

    private static PlayerInteractor instance;

    private final Context context;
    private final DBHelper db;
    private IPlayerPresenter presenter;
    private IPlayerService service;
    private Status status;
    private AudioBook audiobook;
    private int chapterNo = 0;

    public static PlayerInteractor getInstance(Context context) {
        if (instance == null)
            instance = new PlayerInteractor(context);
        return instance;
    }

    private PlayerInteractor(Context context) {
        this.context = context;
        this.db = DBHelper.getInstance(context);
    }

    public void setPresenterListener(IPlayerPresenter presenter) {
        this.presenter = presenter;
    }

    public void setServiceListener(IPlayerService service) {
        this.service = service;
    }

    public AudioBook loadBook(String albumId) {
        audiobook = db.getAudioBookViaId(albumId);
        if (service != null && audiobook != null)
            service.setSource(audiobook.getChapters().get(chapterNo));
        return audiobook;
    }

    public int playBook() {
        if (service != null)
            return service.play();
        else return -1;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public BookChapter getNextChapter() {
        if (audiobook != null && audiobook.getChapters().size() > chapterNo)
            return audiobook.getChapters().get(chapterNo);
        else return null;
    }

    public void askToPlay() {
        presenter.playSong();
    }

    public void updateSeek(int progress) {
        if (service!=null)
            service.onSeekChange(progress*1000);
    }

    public enum Status {
        PLAYING, STOPPED, PAUSED
    }
}
