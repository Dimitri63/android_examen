package local.dgnex.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView tvDetailContactFullname;
    private TextView tvDetailLabelCompany;
    private TextView tvDetailContactCompany;
    private TextView tvDetailLabelAddress;
    private TextView tvDetailContactAddress;
    private TextView tvDetailLabelPhone;
    private TextView tvDetailContactPhone;
    private TextView tvDetailLabelMail;
    private TextView tvDetailContactMail;
    private TextView tvDetailLabelSector;
    private TextView tvDetailContactSector;

    private Button btnDetailCall;
    private Button btnDetailLocate;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    private void getElements() {
        this.tvDetailContactFullname = findViewById(R.id.tvDetailContactFullname);

        this.tvDetailLabelCompany = findViewById(R.id.tvDetailLabelCompany);
        this.tvDetailLabelCompany.setText(getString(R.string.detailPlaceholderCompany));
        this.tvDetailContactCompany = findViewById(R.id.tvDetailContactCompany);

        this.tvDetailLabelAddress = findViewById(R.id.tvDetailLabelAddress);
        this.tvDetailLabelAddress.setText(getString(R.string.detailPlaceholderAddress));
        this.tvDetailContactAddress = findViewById(R.id.tvDetailContactAddress);


        this.tvDetailLabelPhone = findViewById(R.id.tvDetailLabelPhone);
        this.tvDetailLabelPhone.setText(getString(R.string.detailPlaceholderPhone));
        this.tvDetailContactPhone = findViewById(R.id.tvDetailContactPhone);

        this.tvDetailLabelMail = findViewById(R.id.tvDetailLabelMail);
        this.tvDetailLabelMail.setText(getString(R.string.detailPlaceholderMail));
        this.tvDetailContactMail = findViewById(R.id.tvDetailContactMail);

        this.tvDetailLabelSector = findViewById(R.id.tvDetailLabelSector);
        this.tvDetailLabelSector.setText(getString(R.string.detailPlaceholderSector));
        this.tvDetailContactSector = findViewById(R.id.tvDetailContactSector);


        this.btnDetailCall = findViewById(R.id.btnDetailCall);
        this.btnDetailLocate = findViewById(R.id.btnDetailLocate);

    }
}