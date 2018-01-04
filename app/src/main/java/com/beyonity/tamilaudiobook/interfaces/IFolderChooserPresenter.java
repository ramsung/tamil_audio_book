package com.beyonity.tamilaudiobook.interfaces;

import com.beyonity.tamilaudiobook.bean.Folder;

import java.util.ArrayList;

/**
 * Created by HP on 23-07-2017.
 */

public interface IFolderChooserPresenter {
    void foldersLoaded(String currFolder, ArrayList<Folder> folders);
}
