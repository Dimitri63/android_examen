package local.dgnex.examen.adapter;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import local.dgnex.examen.R;
import local.dgnex.examen.models.Contact;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>{

    private List<Contact> contactList;
    private final OnItemClickListener listener;

    public ContactAdapter(List<Contact> contactList, OnItemClickListener listener) {
        this.contactList = contactList;
        this.listener = listener;
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        public TextView tvMainContactFullName;
        public Button btnMainContactDetail;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMainContactFullName = itemView.findViewById(R.id.tvMainContactFullName);
            btnMainContactDetail = itemView.findViewById(R.id.btnMainContactDetail);

        }
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_item, parent, false);

        return new ContactViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {

        Contact contact = contactList.get(position);

        Spannable fullName;
        fullName = new SpannableString(contact.getSurname() + " " + contact.getName());
        if (contact.getFavorite() == 1) {
            fullName.setSpan(
                    new ForegroundColorSpan(Color.GREEN),
                    0 ,
                    fullName.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            fullName.setSpan(
                    new ForegroundColorSpan(Color.BLACK),
                    0 ,
                    fullName.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        holder.tvMainContactFullName.setText(fullName);

        holder.btnMainContactDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(contact);
                Toast.makeText(view.getContext(), "Show details id= " + contact.getId(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public interface OnItemClickListener {
        void onItemClick(Contact contact);
    }
}
