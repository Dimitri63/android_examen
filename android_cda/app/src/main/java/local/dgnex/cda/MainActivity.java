package local.dgnex.cda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import local.dgnex.cda.dao.StudentDAO;
import local.dgnex.cda.models.Student;
import local.dgnex.cda.services.StudentService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private StudentDAO studentDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = getApplicationContext();

       StudentService studentService = (StudentService) new Retrofit.Builder()
               .baseUrl("http://localhost:8000/students")
               .addConverterFactory(GsonConverterFactory.create())
               .build()
               .create(Student.class);

        TodoAsncTasks todoAsncTasks = new TodoAsncTasks();
        todoAsncTasks.execute();

    }

    public class TodoAsncTasks extends AsyncTask<String, String, List<Student>> {
        @Override
        protected List<Student> doInBackground(String... strings) {
            List<Student> students = new ArrayList<>();

            try {
                students = studentDAO.list();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return students;
        }

        @Override
        protected void onPostExecute(List<Student> students) {

            System.out.println(students);
        }
    }

}