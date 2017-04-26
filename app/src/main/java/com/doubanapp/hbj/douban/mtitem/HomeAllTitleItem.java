package com.doubanapp.hbj.douban.mtitem;

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.flipboard.bottomsheet.commons.MenuSheetView;

import me.drakeet.multitype.Item;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class HomeAllTitleItem implements Item {

    public String title;
    public BottomSheetLayout bottomSheet;
    public MenuSheetView menuSheetView;


    public HomeAllTitleItem(String title, BottomSheetLayout bottomSheet, MenuSheetView menuSheetView) {
        this.title = title;
        this.bottomSheet = bottomSheet;
        this.menuSheetView = menuSheetView;
    }
}
