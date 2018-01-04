package com.beyonity.tamilaudiobook.presenter;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;

import com.beyonity.tamilaudiobook.R;
import com.beyonity.tamilaudiobook.adapter.ViewPagerAdapter;
import com.beyonity.tamilaudiobook.interactor.MainInteractor;
import com.beyonity.tamilaudiobook.interfaces.IMainPresenter;
import com.beyonity.tamilaudiobook.interfaces.IMainView;
import com.beyonity.tamilaudiobook.ui.activity.MainActivity;
import com.beyonity.tamilaudiobook.ui.fragment.AllAudioBookViewFragment;
import com.beyonity.tamilaudiobook.ui.fragment.FinishedAudioBookFragment;
import com.beyonity.tamilaudiobook.ui.fragment.NewAudioBookFragment;
import com.beyonity.tamilaudiobook.ui.fragment.OnGoingAudioBookFragment;
import com.beyonity.tamilaudiobook.utils.PermissionChecker;
import com.beyonity.tamilaudiobook.utils.PrefUtils;

/**
 * Created by HP on 22-07-2017.
 */

public class MainPresenter implements IMainPresenter {
    private final Context context;
    private final PrefUtils pref;
    private MainInteractor interactor;
    private IMainView view;
    private ViewPager viewPager;
    private AllAudioBookViewFragment allFrag;
    private NewAudioBookFragment newFrag;
    private OnGoingAudioBookFragment ongoingFrag;
    private FinishedAudioBookFragment finishedFrag;

    public MainPresenter(Context context) {
        this.view = (IMainView) context;
        this.context = context;
        this.interactor = new MainInteractor(context, this);
        pref = PrefUtils.getInstance(context);
        init();
    }

    private void init() {
        if (pref.getAudioBookFolderPath() == null) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setTitle(R.string.select_directory)
                    .setMessage(R.string.select_directory_dialog_msg)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (view != null)
                                if (PermissionChecker.requestForPermission(context,
                                        Manifest.permission.READ_EXTERNAL_STORAGE,
                                        R.string.read_permission_request, MainActivity.READ_PERMISSION_REQUEST))
                                    view.startFolderChooser();
                        }
                    })
                    .create().show();
        } else interactor.scanForNewAudioBooks();
    }

    public void setupViewPager(ViewPager viewPager, FragmentManager supportFragmentManager) {
        this.viewPager = viewPager;
        allFrag = new AllAudioBookViewFragment();
        newFrag = new NewAudioBookFragment();
        ongoingFrag = new OnGoingAudioBookFragment();
        finishedFrag = new FinishedAudioBookFragment();
        ViewPagerAdapter adapter = new ViewPagerAdapter(context, supportFragmentManager);
        adapter.addFragment(allFrag, R.string.all);
        adapter.addFragment(newFrag, R.string.new_txt);
        adapter.addFragment(ongoingFrag, R.string.ongoing);
        adapter.addFragment(finishedFrag, R.string.finished);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(interactor.getPageWithSomeData());
    }

    public void setTabLayout(TabLayout tabLayout) {
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void startFolderChooser() {
        if (view != null)
            view.startFolderChooser();
    }

    @Override
    public void onNewAudioBookLoaded() {
        allFrag.updateBookList();
        newFrag.updateBookList();
    }

    public MainInteractor getInteractor() {
        return interactor;
    }
}
