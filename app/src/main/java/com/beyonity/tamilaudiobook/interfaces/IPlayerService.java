package com.beyonity.tamilaudiobook.interfaces;

import com.beyonity.tamilaudiobook.bean.AudioBook;
import com.beyonity.tamilaudiobook.bean.BookChapter;

/**
 * Created by HP on 30-07-2017.
 */

public interface IPlayerService {
    void setSource(BookChapter audiobook);

    int play();

    void onSeekChange(int progress);
}
