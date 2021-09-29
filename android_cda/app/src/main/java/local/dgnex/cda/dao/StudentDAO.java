package local.dgnex.cda.dao;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import local.dgnex.cda.helper.StudentBDHelper;
import local.dgnex.cda.models.Student;

public class StudentDAO extends DAO {

    public StudentDAO(Context context) {
        super(new StudentBDHelper(context));
    }

    public Student find(Long id) {

        Student student = null;

        open();

        Cursor cursor = db
                .rawQuery(
                        "SELECT * FROM "
                                + StudentBDHelper.VIDEO_TABLE_NAME
                                + " WHERE " + StudentBDHelper.STUDENT_KEY
                                + " = ?",
                        new String[] { String.valueOf(id)});

        if (cursor != null && cursor.moveToFirst()) {
            student = new Student();
            student.setId(cursor.getLong(StudentBDHelper.STUDENT_KEY_COLUMN_INDEX));
            student.setFirstName(cursor.getString(StudentBDHelper.STUDENT_FIRSTNAME_COLUMN_INDEX));
            student.setLastName(cursor.getString(StudentBDHelper.STUDENT_LASTNAME_COLUMN_INDEX));
            student.setAge(cursor.getInt(StudentBDHelper.STUDENT_AGE_COLUMN_INDEX));
            student.setPower(cursor.getString(StudentBDHelper.STUDENT_POWER_COLUMN_INDEX));
            student.setPhoto(cursor.getString(StudentBDHelper.STUDENT_PHOTO_COLUMN_INDEX));

            cursor.close();
        }

        close();

        return student;
    }

    public List<Student> list() {
        List<Student> students = new ArrayList<>();

        open();

        Cursor cursor = db.rawQuery("SELECT * FROM " + StudentBDHelper.VIDEO_TABLE_NAME, null);
        if (cursor != null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Student student = new Student();
                student.setId(cursor.getLong(StudentBDHelper.STUDENT_KEY_COLUMN_INDEX));
                student.setFirstName(cursor.getString(StudentBDHelper.STUDENT_FIRSTNAME_COLUMN_INDEX));
                student.setLastName(cursor.getString(StudentBDHelper.STUDENT_LASTNAME_COLUMN_INDEX));
                student.setAge(cursor.getInt(StudentBDHelper.STUDENT_AGE_COLUMN_INDEX));
                student.setPower(cursor.getString(StudentBDHelper.STUDENT_POWER_COLUMN_INDEX));
                student.setPhoto(cursor.getString(StudentBDHelper.STUDENT_PHOTO_COLUMN_INDEX));

                students.add(student);

                cursor.moveToNext();
            }

            cursor.close();
        }

        close();

        return students;
    }

}
