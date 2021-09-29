package local.dgnex.examen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import local.dgnex.examen.models.Contact;

public class AddActivity extends AppCompatActivity {

    private TextView tvAddContactLabelSurname;
    private EditText etAddContactSurname;
    private TextView tvAddContactLabelName;
    private EditText etAddContactName;
    private TextView tvAddContactLabelCompany;
    private EditText etAddContactCompany;
    private TextView tvAddContactLabelAddress;
    private EditText etAddContactAddress;
    private TextView tvAddContactLabelPhone;
    private EditText etAddContactPhone;
    private TextView tvAddContactLabelMail;
    private EditText etAddContactMail;
    private TextView tvAddContactLabelSector;
    private Spinner spAddContactSector;
    private Button btnAddContactConfirm;
    private Button btnAddContactCancel;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getElements();
        // Bar de retour
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Initializing a String Array
        final List<String> sectors = new ArrayList<>();

        sectors.add("Industrie");
        sectors.add("Informatique");
        sectors.add("Santé");

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,sectors);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        this.spAddContactSector.setAdapter(spinnerArrayAdapter);

        Contact contact = new Contact();
        this.etAddContactSurname.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() > 3) {
                    contact.setSurname(editable.toString());
                    System.out.println("DEBUG ----------------- " + contact);
                }
            }
        });
        this.etAddContactName.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() > 3) {
                    contact.setName(editable.toString());
                    System.out.println("DEBUG ----------------- " + contact);
                }
            }
        });
        this.etAddContactCompany.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() > 3) {
                    contact.setCompany(editable.toString());
                    System.out.println("DEBUG ----------------- " + contact);
                }
            }
        });
        this.etAddContactAddress.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() > 3) {
                    contact.setAddress(editable.toString());
                    System.out.println("DEBUG ----------------- " + contact);
                }
            }
        });
        this.etAddContactPhone.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() > 3) {
                    contact.setPhone(editable.toString());
                    System.out.println("DEBUG ----------------- " + contact);
                }
            }
        });
        this.etAddContactMail.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() > 3) {
                    contact.setMail(editable.toString());
                    System.out.println("DEBUG ----------------- " + contact);
                }
            }
        });

        this.btnAddContactConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contact.setFavorite(0);
                contact.setSector(spAddContactSector.getSelectedItem().toString());
                System.out.println("DEBUG ----------------- " + contact);
            }
        });

        this.btnAddContactCancel.setOnClickListener(View -> {
            Intent intent = new Intent(this.context, MainActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private void getElements() {
        this.context = getApplicationContext();

        this.tvAddContactLabelSurname = findViewById(R.id.tvAddContactLabelSurname);
        this.tvAddContactLabelSurname.setText(getString(R.string.addPlaceholderName));
        this.etAddContactSurname = findViewById(R.id.etAddContactSurname);

        this.tvAddContactLabelName = findViewById(R.id.tvAddContactLabelName);
        this.tvAddContactLabelName.setText(getString(R.string.addPlaceholderSurname));
        this.etAddContactName = findViewById(R.id.etAddContactName);

        this.tvAddContactLabelCompany = findViewById(R.id.tvAddContactLabelCompany);
        this.tvAddContactLabelCompany.setText(getString(R.string.addPlaceholderCompany));
        this.etAddContactCompany = findViewById(R.id.etAddContactCompany);

        this.tvAddContactLabelAddress = findViewById(R.id.tvAddContactLabelAddress);
        this.tvAddContactLabelAddress.setText(getString(R.string.addPlaceholderAddress));
        this.etAddContactAddress = findViewById(R.id.etAddContactAddress);

        this.tvAddContactLabelPhone = findViewById(R.id.tvAddContactLabelPhone);
        this.tvAddContactLabelPhone.setText(getString(R.string.addPlaceholderPhone));
        this.etAddContactPhone = findViewById(R.id.etAddContactPhone);

        this.tvAddContactLabelMail = findViewById(R.id.tvAddContactLabelMail);
        this.tvAddContactLabelMail.setText(getString(R.string.addPlaceholderMail));
        this.etAddContactMail = findViewById(R.id.etAddContactMail);

        this.tvAddContactLabelSector = findViewById(R.id.tvAddContactLabelSector);
        this.tvAddContactLabelSector.setText(getString(R.string.addPlaceholderSector));

        this.btnAddContactConfirm = findViewById(R.id.btnAddContactConfirm);
        this.btnAddContactConfirm.setText(getString(R.string.addPlaceholderBTN_Add));

        this.spAddContactSector = findViewById(R.id.spAddContactSector);

        this.btnAddContactCancel = findViewById(R.id.btnAddContactCancel);
        this.btnAddContactCancel.setText(getString(R.string.addPlaceholderBTN_Cancel));

    }
}