package local.dgnex.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Context context;

    private TextView tvMainLabelListContact;
    private Button btnMainAddContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getElements();

        this.btnMainAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddActivity.class);
                startActivity(intent);
            }
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    private void getElements() {
        this.context = getApplicationContext();

        this.tvMainLabelListContact = findViewById(R.id.tvMainLabelListContact);
        this.tvMainLabelListContact.setText(getString(R.string.mainLabelListContact));

        this.btnMainAddContact = findViewById(R.id.btnMainAddContact);
    }
}