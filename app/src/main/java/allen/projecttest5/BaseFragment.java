package allen.projecttest5;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;


/**
 * Created by Allen on 19-Apr-16.
 */
public class BaseFragment extends Fragment {
    public void showPage(Fragment fragment, boolean isAddtoBackstack) {
        if (isAdded()) {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.frame_content, fragment);
            if (isAddtoBackstack) transaction.addToBackStack(fragment.getClass().toString());
            transaction.commit();
        }
    }

    public void showLoading() {
        Activity activity = getActivity();
        if (activity != null && activity instanceof MainActivity)
            ((MainActivity) getActivity()).showDialogRequesting();
    }

    public void hideLoading() {
        Activity activity = getActivity();
        if (activity != null && activity instanceof MainActivity)
            ((MainActivity) getActivity()).hideDialogRequesting();
    }

    public String getName() {
        return "Base Fragment";
    }

    public void showLog(String msg) {
        Log.d(getName(), msg);
    }

    @Override
    public void onPause() {
        showLog("onPause");
        super.onPause();
    }

    @Override
    public void onResume() {
        showLog("onResume");
        super.onResume();
    }

    @Override
    public void onDestroy() {
        showLog("onDestroy");
        super.onDestroy();
    }

}
