package com.beyonity.tamilaudiobook.interfaces;

/**
 * Created by Archit on 26-07-2017.
 */

public interface IPlayerView {

    void onBodyTextColorUpdate(int animatedValue);

    void onDominantColorLoad(int dominantColor);

    void onBackgroundColorUpdate(int animatedValue);

    void onTitleTextColorUpdate(int animatedValue);

    void onErrorLoadAudioBook();

    void onInit(long duration);

    void updateTimer(String time);
}
