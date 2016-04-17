package cc.dc.demo.model.impl;

import java.util.ArrayList;
import java.util.List;

import cc.dc.demo.model.IMainModel;
import cc.dc.demo.ui.fragment.AllFragment;
import cc.dc.demo.ui.fragment.AndroidFragment;
import cc.dc.demo.ui.fragment.IOSFragment;
import cc.dc.demo.ui.fragment.base.BaseFragment;

/**
 * Created by dingcai on 2016/4/5.
 */
public class MainModeImpl implements IMainModel {
    @Override
    public List<String> getAllItemNames() {
        List<String> list = new ArrayList<>();
        list.add("Android");
        list.add("IOS");
        list.add("福利");
        return list;
    }

    @Override
    public List<BaseFragment> getAllFragments() {
        List<BaseFragment> list = new ArrayList<>();
        AndroidFragment fragment1 = new AndroidFragment();
        IOSFragment fragment2= new IOSFragment();
        AllFragment fragment3 = new AllFragment();
        list.add(fragment1);
        list.add(fragment2);
        list.add(fragment3);
        return list;
    }
}
