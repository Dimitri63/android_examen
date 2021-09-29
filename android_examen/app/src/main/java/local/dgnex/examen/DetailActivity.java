package local.dgnex.examen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import static android.Manifest.permission.CALL_PHONE;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import local.dgnex.examen.adapter.ContactAdapter;
import local.dgnex.examen.dao.ContactDAO;
import local.dgnex.examen.models.Contact;

public class DetailActivity extends AppCompatActivity {

    private ContactDAO contactDAO;
    private Long contactId;
    private Context context;

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
    private Button btnDetailMessage;

    private ImageButton btnDetailContactFavoriteOn;
    private ImageButton btnDetailContactFavoriteOff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getElements();

        Intent intent = getIntent();
        this.contactId = (Long) intent.getSerializableExtra(MainActivity.KEY_CONTACT);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ContactAsncTasks contactAsncTasks = new ContactAsncTasks();
        contactAsncTasks.execute();
    }

    public class ContactAsncTasks extends AsyncTask<String, String, List<Contact>> {
        @Override
        protected List<Contact> doInBackground(String... strings) {
            List<Contact> contactList = new ArrayList<>();

            try {
                Contact contact = contactDAO.find(contactId);
                if (contact != null) {
                    contactList.add(contact);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return contactList;
        }

        @Override
        protected void onPostExecute(List<Contact> contactList) {

            Contact contact = contactList.get(0);
            System.out.println("DEBUG ----------------- " + contact);
            tvDetailContactFullname.setText(contact.getSurname() + " " + contact.getName());
            tvDetailContactCompany.setText(contact.getCompany());
            tvDetailContactAddress.setText(contact.getAddress());
            tvDetailContactPhone.setText(contact.getPhone());
            tvDetailContactMail.setText(contact.getMail());
            tvDetailContactSector.setText(contact.getSector());
            if (contact.getFavorite() == 0) {
                btnDetailContactFavoriteOn.setVisibility(View.VISIBLE);
                btnDetailContactFavoriteOff.setVisibility(View.INVISIBLE);
            } else {
                btnDetailContactFavoriteOn.setVisibility(View.INVISIBLE);
                btnDetailContactFavoriteOff.setVisibility(View.VISIBLE);
            }


            btnDetailCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + contact.getPhone()));
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        startActivity(intent);
                    } else {
                        requestPermissions(new String[]{CALL_PHONE}, 1);
                    }
                }
            });

            btnDetailMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("sms:" + contact.getPhone()));
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        startActivity(intent);
                    } else {
                        requestPermissions(new String[]{CALL_PHONE}, 1);
                    }
                }
            });

            btnDetailLocate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Create a Uri from an intent string. Use the result to create an Intent.
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + contact.getAddress());

                    // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    // Make the Intent explicit by setting the Google Maps package
                    mapIntent.setPackage("com.google.android.apps.maps");

                    // Attempt to start an activity that can handle the Intent
                    startActivity(mapIntent);
                }
            });

            btnDetailContactFavoriteOn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    contact.setFavorite(1);
                    contactDAO.update(contact);
                    System.out.println("DEBUG ----------------- " + contact);
                    finish();
                    startActivity(getIntent());
                }
            });

            btnDetailContactFavoriteOff.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    contact.setFavorite(0);
                    contactDAO.update(contact);
                    System.out.println("DEBUG ----------------- " + contact);
                    finish();
                    startActivity(getIntent());
                }
            });
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private void getElements() {
        this.context = getApplicationContext();
        this.contactDAO = new ContactDAO(this.context);

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
        this.btnDetailMessage = findViewById(R.id.btnDetailMessage);

        this.btnDetailContactFavoriteOn = findViewById(R.id.btnDetailContactFavoriteOn);
        this.btnDetailContactFavoriteOff = findViewById(R.id.btnDetailContactFavoriteOff);

    }
}