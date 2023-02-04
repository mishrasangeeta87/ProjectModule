import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//import retrofit2.con

public class JSONApi {
    Retrofit retrofit;

    public JSONApi(){
        retrofit =  new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com/posts")
                .build();
    }


}
