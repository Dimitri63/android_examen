package local.dgnex.cda.services;

import java.util.List;

import local.dgnex.cda.models.Student;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StudentService {

    public static final String ENDPOINT = "http://localhost:8000/students";

    @GET("getAllStudents")
    Call<List<Student>> getAllStudents();
}
