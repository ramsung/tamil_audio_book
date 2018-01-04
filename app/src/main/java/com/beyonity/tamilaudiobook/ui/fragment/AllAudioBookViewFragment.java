package com.beyonity.tamilaudiobook.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beyonity.tamilaudiobook.R;
import com.beyonity.tamilaudiobook.bean.AudioBook;
import com.beyonity.tamilaudiobook.presenter.AllAudioBookPresenter;
import com.beyonity.tamilaudiobook.interfaces.IAudioBookView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HP on 18-07-2017.
 */

public class AllAudioBookViewFragment extends Fragment implements IAudioBookView {
    private View mainView;
    private Context context;
    private boolean viewDestroyed = true;
    private AllAudioBookPresenter presenter;
    @BindView(R.id.rv)
    RecyclerView rv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_audiobook_all, container, false);
        context = mainView.getContext();
        init();
        return mainView;
    }

    private void init() {
        ButterKnife.bind(this, mainView);
        presenter = new AllAudioBookPresenter(context, this);
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
