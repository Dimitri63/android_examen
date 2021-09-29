package local.dgnex.tp_fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity implements MainFragment.OnClickListener {

    MainFragment mainFragment;
    DetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment = (MainFragment)getSupportFragmentManager().findFragmentById(R.id.flMain);

        if (mainFragment == null) {
            mainFragment = new MainFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.flMain, mainFragment)
                    .commit();
        }

        detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.flDetail);

        if (detailFragment == null && findViewById(R.id.flDetail) != null) {
            detailFragment = new DetailFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.flDetail, detailFragment)
                    .commit();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle saveInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onClick() {
        if (detailFragment == null || !detailFragment.isVisible()){
            startActivity(new Intent(this, DetailActivity.class));
        }
    }
}