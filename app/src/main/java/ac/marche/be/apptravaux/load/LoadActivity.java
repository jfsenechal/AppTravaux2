package ac.marche.be.apptravaux.load;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import ac.marche.be.apptravaux.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadActivity extends Activity implements LoadView {

    @BindView(R.id.loadProgress)
    protected ProgressBar progressBar;

    LoadPresenter loadPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        ButterKnife.bind(this);
        loadPresenter = new LoadPresenterImpl(this);
    }

    @Override
    protected void onResume() {
        loadPresenter.onResume();
        super.onResume();
    }

    /**
     * override de la vue
     */
    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * override de la vue
     */
    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    /**
     * override de la vue
     */
    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
