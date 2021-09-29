package local.dgnex.examen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    public static final String KEY_CONTACT = "contactId";

    private Context context;
    private ContactDAO contactDAO;
    private ContactAdapter contactAdapter;
    private RecyclerView rvListContact;

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

        ContactAsncTasks contactAsncTasks = new ContactAsncTasks();
        contactAsncTasks.execute();
    }

    public class ContactAsncTasks extends AsyncTask<String, String, List<Contact>> {
        @Override
        protected List<Contact> doInBackground(String... strings) {
            List<Contact> contactList = new ArrayList<>();

            try {
                contactList = contactDAO.list();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return contactList;
        }

        @Override
        protected void onPostExecute(List<Contact> contactList) {

           for (Contact contact : contactList) {
               System.out.println("DEBUG ----------------- " + contact);
           }
           contactAdapter = new ContactAdapter(contactList, new ContactAdapter.OnItemClickListener() {
               @Override
               public void onItemClick(Contact contact) {
                   Intent intent = new Intent(context, DetailActivity.class);
                   intent.putExtra(KEY_CONTACT, contact.getId());
                   startActivity(intent);
               }
           });
            rvListContact.setAdapter(contactAdapter);

        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        ContactAsncTasks contactAsncTasks = new ContactAsncTasks();
        contactAsncTasks.execute();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private void getElements() {
        this.context = getApplicationContext();
        this.contactDAO = new ContactDAO(this.context);

        this.tvMainLabelListContact = findViewById(R.id.tvMainLabelListContact);
        this.tvMainLabelListContact.setText(getString(R.string.mainLabelListContact));

        this.btnMainAddContact = findViewById(R.id.btnMainAddContact);

        this.rvListContact = findViewById(R.id.rvListContact);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        this.rvListContact.setHasFixedSize(true);
        this.rvListContact.setLayoutManager(layoutManager);
    }
}