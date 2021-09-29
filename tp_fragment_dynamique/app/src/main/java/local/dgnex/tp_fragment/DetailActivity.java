package local.dgnex.tp_fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    DetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailFragment = (DetailFragment)getSupportFragmentManager().findFragmentById(R.id.flDetail);

        if (detailFragment == null) {

            detailFragment = new DetailFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.flDetail, detailFragment)
                    .commit();
        }
    }
}