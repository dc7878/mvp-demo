package cc.dc.demo.model;

import java.util.List;

import cc.dc.demo.ui.fragment.base.BaseFragment;

/**
 * Created by dingcai on 2016/4/5.
 */
public interface IMainModel {
    List<String> getAllItemNames();
    List<BaseFragment> getAllFragments();
}
