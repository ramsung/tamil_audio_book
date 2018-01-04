package com.beyonity.tamilaudiobook.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.beyonity.tamilaudiobook.adapter.AudioBookAdapter;
import com.beyonity.tamilaudiobook.adapter.IAdapterItemClick;
import com.beyonity.tamilaudiobook.bean.AudioBook;
import com.beyonity.tamilaudiobook.interactor.AllAudioBookInteractor;
import com.beyonity.tamilaudiobook.interfaces.IAllAudioBookPresenter;
import com.beyonity.tamilaudiobook.interfaces.IAudioBookView;
import com.beyonity.tamilaudiobook.ui.activity.PlayerActivity;
import com.beyonity.tamilaudiobook.utils.PrefUtils;
import com.beyonity.tamilaudiobook.utils.Utils;

import java.util.ArrayList;

/**
 * Created by HP on 23-07-2017.
 */

public class AllAudioBookPresenter implements IAdapterItemClick<AudioBook>, IAllAudioBookPresenter {
    private final PrefUtils pref;
    private final AllAudioBookInteractor interactor;
    private IAudioBookView view;
    private Context context;
    private AudioBookAdapter adapter;

    public AllAudioBookPresenter(Context context, IAudioBookView view) {
        this.view = view;
        this.context = context;
        this.pref = PrefUtils.getInstance(context);
        this.interactor = new AllAudioBookInteractor(context, this);
    }

    public void setRecycler(RecyclerView rv) {
        LinearLayoutManager lm = new LinearLayoutManager(context);
        rv.setLayoutManager(lm);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(),
                lm.getOrientation());
        rv.addItemDecoration(dividerItemDecoration);
        ArrayList<AudioBook> items = new ArrayList<>();
        adapter = new AudioBookAdapter(context, items, this);
        rv.setAdapter(adapter);
        interactor.loadAllAudioBooks();
    }

    @Override
    public void onItemSelected(int position, AudioBook item) {
        Intent intent = new Intent(context, PlayerActivity.class);
        intent.putExtra("id", item.getAlbumId());
        context.startActivity(intent);
    }

    @Override
    public void onAudioBooksLoaded(ArrayList<AudioBook> books) {
        if (adapter != null)
            adapter.updateList(books);
    }

    public void updateBookList() {
        interactor.loadAllAudioBooks();
    }
}
