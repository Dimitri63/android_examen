package local.dgnex.examen.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import local.dgnex.examen.helper.ContactDBHelper;
import local.dgnex.examen.models.Contact;

public class ContactDAO extends DAO {

    public ContactDAO(Context context) {
        super(new ContactDBHelper(context));
    }

    public Contact find(Long id) {

        Contact contact = null;

        open();

        Cursor cursor = db
                .rawQuery(
                        "SELECT * FROM "
                                + ContactDBHelper.CONTACT_TABLE_NAME
                                + " WHERE " + ContactDBHelper.CONTACT_KEY
                                + " = ?",
                        new String[] { String.valueOf(id)});

        if (cursor != null && cursor.moveToFirst()) {
            contact = new Contact();
            contact.setId(cursor.getLong(ContactDBHelper.CONTACT_KEY_COLUMN_INDEX));
            contact.setName(cursor.getString(ContactDBHelper.CONTACT_NAME_COLUMN_INDEX));
            contact.setSurname(cursor.getString(ContactDBHelper.CONTACT_SURNAME_COLUMN_INDEX));
            contact.setCompany(cursor.getString(ContactDBHelper.CONTACT_COMPANY_COLUMN_INDEX));
            contact.setAddress(cursor.getString(ContactDBHelper.CONTACT_ADDRESS_COLUMN_INDEX));
            contact.setPhone(cursor.getString(ContactDBHelper.CONTACT_PHONE_COLUMN_INDEX));
            contact.setMail(cursor.getString(ContactDBHelper.CONTACT_MAIL_COLUMN_INDEX));
            contact.setSector(cursor.getString(ContactDBHelper.CONTACT_SECTOR_COLUMN_INDEX));
            contact.setFavorite(cursor.getInt(ContactDBHelper.CONTACT_FAVORITE_COLUMN_INDEX));

            cursor.close();
        }

        close();

        return contact;
    }

    public List<Contact> list() {
        List<Contact> contactList = new ArrayList<>();

        open();

        Cursor cursor = db.rawQuery("SELECT * FROM " + ContactDBHelper.CONTACT_TABLE_NAME, null);
        if (cursor != null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Contact contact = new Contact();
                contact.setId(cursor.getLong(ContactDBHelper.CONTACT_KEY_COLUMN_INDEX));
                contact.setName(cursor.getString(ContactDBHelper.CONTACT_NAME_COLUMN_INDEX));
                contact.setSurname(cursor.getString(ContactDBHelper.CONTACT_SURNAME_COLUMN_INDEX));
                contact.setCompany(cursor.getString(ContactDBHelper.CONTACT_COMPANY_COLUMN_INDEX));
                contact.setAddress(cursor.getString(ContactDBHelper.CONTACT_ADDRESS_COLUMN_INDEX));
                contact.setPhone(cursor.getString(ContactDBHelper.CONTACT_PHONE_COLUMN_INDEX));
                contact.setMail(cursor.getString(ContactDBHelper.CONTACT_MAIL_COLUMN_INDEX));
                contact.setSector(cursor.getString(ContactDBHelper.CONTACT_SECTOR_COLUMN_INDEX));
                contact.setFavorite(cursor.getInt(ContactDBHelper.CONTACT_FAVORITE_COLUMN_INDEX));

                contactList.add(contact);

                cursor.moveToNext();
            }

            cursor.close();
        }

        close();

        return contactList;
    }

    public Contact add(Contact contact) {
        open();

        ContentValues values = new ContentValues();

        values.put(ContactDBHelper.CONTACT_NAME, contact.getName());
        values.put(ContactDBHelper.CONTACT_SURNAME, contact.getSurname());
        values.put(ContactDBHelper.CONTACT_COMPANY, contact.getCompany());
        values.put(ContactDBHelper.CONTACT_ADDRESS, contact.getAddress());
        values.put(ContactDBHelper.CONTACT_PHONE, contact.getPhone());
        values.put(ContactDBHelper.CONTACT_MAIL, contact.getMail());
        values.put(ContactDBHelper.CONTACT_SECTOR, contact.getSector());
        values.put(ContactDBHelper.CONTACT_FAVORITE, contact.getFavorite());


        Long id = db.insert(ContactDBHelper.CONTACT_TABLE_NAME, null, values);

        contact.setId(id);

        close();

        return contact;
    }

    public void update(Contact contact) {

        open();

        ContentValues values = new ContentValues();

        values.put(ContactDBHelper.CONTACT_NAME, contact.getName());
        values.put(ContactDBHelper.CONTACT_SURNAME, contact.getSurname());
        values.put(ContactDBHelper.CONTACT_COMPANY, contact.getCompany());
        values.put(ContactDBHelper.CONTACT_ADDRESS, contact.getAddress());
        values.put(ContactDBHelper.CONTACT_PHONE, contact.getPhone());
        values.put(ContactDBHelper.CONTACT_MAIL, contact.getMail());
        values.put(ContactDBHelper.CONTACT_SECTOR, contact.getSector());
        values.put(ContactDBHelper.CONTACT_FAVORITE, contact.getFavorite());

        db.update(ContactDBHelper.CONTACT_TABLE_NAME, values, ContactDBHelper.CONTACT_KEY + " = ?",
                new String[]{ String.valueOf(contact.getId())});

        close();
    }

    public void delete(Contact contact) {

        open();

        db.delete(ContactDBHelper.CONTACT_TABLE_NAME, ContactDBHelper.CONTACT_KEY + " = ?",
                new String[]{ String.valueOf(contact.getId())});

        close();
    }

}
