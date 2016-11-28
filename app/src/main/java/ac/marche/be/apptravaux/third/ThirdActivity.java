package ac.marche.be.apptravaux.third;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;


import java.util.List;

import ac.marche.be.apptravaux.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ThirdActivity extends Activity implements ThirdView {

    @BindView(R.id.listThird)
    protected ListView listView;

    @BindView(R.id.progressThird)
    protected ProgressBar progressBar;

    ThirdPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        ButterKnife.bind(this);

        presenter = new ThirdPresenterImpl(this, new GetItemsUtilImpl());

    }

    /**
     * Override de l'activity
     * j'appel la methode onresume du presenter
     * qui va afficher la barre de progression
     * et charger les donnees
     */
    @Override
    protected void onResume() {
        presenter.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    /**
     * Depuis View
     */
    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        listView.setVisibility(View.INVISIBLE);
    }

    /**
     * Depuis View
     */
    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.VISIBLE);
    }

    /**
     * Depuis View
     */
    @Override
    public void setItems(List<String> items) {
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items));
    }

    /**
     * Depuis View
     */
    @Override
    public void showMessage(String message) {

    }
}
