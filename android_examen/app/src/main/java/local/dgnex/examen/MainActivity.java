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
import android.widget.CompoundButton;
import android.widget.Switch;
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
    private Switch switchFilterByFavorite;


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
            // Comportement par défaut, affiche tous les contacts
            contactAdapter = new ContactAdapter(contactList, new ContactAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Contact contact) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(KEY_CONTACT, contact.getId());
                    startActivity(intent);
                }
            });
            rvListContact.setAdapter(contactAdapter);


            // En fonction du switch, affiche tous les contacts ou seulement les favoris
           switchFilterByFavorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (isChecked) {
                        List<Contact> contactFavorites = new ArrayList<>();
                        for (Contact contact : contactList) {
                            if (contact.getFavorite() == 1) {
                                contactFavorites.add(contact);
                            }
                        }
                        contactAdapter = new ContactAdapter(contactFavorites, new ContactAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(Contact contact) {
                                Intent intent = new Intent(context, DetailActivity.class);
                                intent.putExtra(KEY_CONTACT, contact.getId());
                                startActivity(intent);
                            }
                        });
                        rvListContact.setAdapter(contactAdapter);
                        Toast.makeText(context, "Afficher seulement les favoris", Toast.LENGTH_SHORT).show();
                    } else {
                        contactAdapter = new ContactAdapter(contactList, new ContactAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(Contact contact) {
                                Intent intent = new Intent(context, DetailActivity.class);
                                intent.putExtra(KEY_CONTACT, contact.getId());
                                startActivity(intent);
                            }
                        });
                        rvListContact.setAdapter(contactAdapter);
                        Toast.makeText(context, "Afficher tous les contacts", Toast.LENGTH_SHORT).show();
                    }
                }
           });



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

        this.switchFilterByFavorite = findViewById(R.id.switchFilterByFavorite);
    }
}