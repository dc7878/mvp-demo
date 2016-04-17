package cc.dc.demo.view;

import java.util.List;

import cc.dc.demo.ui.fragment.base.BaseFragment;

/**
 * Created by dingcai on 2016/4/5.
 */
public interface IMainView {
    void initItemNames(List<String> names);
    void initFragments(List<BaseFragment> list);
    void changeSelectPosition(int position);
}
