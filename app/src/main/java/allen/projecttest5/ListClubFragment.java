package allen.projecttest5;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import allen.projecttest5.model.ClubObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListClubFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<List<ClubObject>> {

    public static final int LOADER_ID = 1;
    public static final String URL = "https://api.myjson.com/bins/usgc";
    private TextView tv;

    public ListClubFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_club, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv = (TextView) view.findViewById(R.id.tv);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<List<ClubObject>> onCreateLoader(int id, Bundle args) {
        DownloadStringLoader loader = new DownloadStringLoader(getActivity().getApplicationContext(), URL);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<List<ClubObject>> loader, List<ClubObject> data) {
        if (data != null)
            tv.setText(data.get(0).getName());
        else tv.setText("Data null");
    }

    @Override
    public void onLoaderReset(Loader<List<ClubObject>> loader) {

    }
}
