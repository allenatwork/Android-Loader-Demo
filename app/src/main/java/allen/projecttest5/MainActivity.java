package allen.projecttest5;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentByTag("ListClub");
        if (fragment == null) {
            manager.beginTransaction().add(R.id.frame_content, new ListClubFragment(), "ListClub").commit();
        }
    }

    public void showDialogRequesting() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getResources().getString(R.string.waiting));
            mProgressDialog.setCancelable(false);
        }

        mProgressDialog.show();
    }

    public void hideDialogRequesting() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }
}
