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

            btnDetailCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_CALL);
                    i.setData(Uri.parse("tel:" + contact.getPhone()));
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        startActivity(i);
                    } else {
                        requestPermissions(new String[]{CALL_PHONE}, 1);
                    }
                }
            });

            btnDetailMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", contact.getPhone(), null)));
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

    }
}