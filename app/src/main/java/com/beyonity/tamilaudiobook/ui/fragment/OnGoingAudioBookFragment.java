package com.beyonity.tamilaudiobook.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beyonity.tamilaudiobook.R;
import com.beyonity.tamilaudiobook.bean.AudioBook;
import com.beyonity.tamilaudiobook.presenter.OnGoingAudioBookPresenter;
import com.beyonity.tamilaudiobook.interfaces.IAudioBookView;
import com.beyonity.tamilaudiobook.ui.activity.PlayerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HP on 18-07-2017.
 */

public class OnGoingAudioBookFragment extends Fragment implements IAudioBookView {
    private View mainView;
    private Context context;
    private boolean viewDestroyed = true;
    private OnGoingAudioBookPresenter presenter;
    @BindView(R.id.rv)
    RecyclerView rv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_audiobook_ongoing, container, false);
        context = mainView.getContext();
        init();
        return mainView;
    }

    private void init() {
        ButterKnife.bind(this, mainView);
        presenter = new OnGoingAudioBookPresenter(context, this);
        presenter.setRecycler(rv);
        viewDestroyed = false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewDestroyed = true;
    }

    public void updateBookList() {
        if (!viewDestroyed)
            presenter.updateBookList();
    }

}
