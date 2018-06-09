package tk.medlynk.patient.android;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import tk.medlynk.patient.android.Model.Answer;

/**
 * Created by Shahab on 6/7/2018.
 */

public class JsonConverter {

    private static JsonConverter jsonConverter;

    private JsonConverter(){

    }

    public static JsonConverter getInstance(){
        if( jsonConverter == null ){
            jsonConverter = new JsonConverter ();
        }
        return jsonConverter;
    }

    public String answersToAnswerJson(List<Answer> answers){
        Gson gson = new Gson();
        JsonElement element= gson.toJsonTree ( answers,new TypeToken<List<Answer>> () {}.getType() );

        return element.toString ();
    }

    public List<Answer> answerJsonToAnswers(String answerJson ){
        Gson gson = new Gson();
        List<Answer> answers = gson.fromJson ( answerJson,new TypeToken<List<Answer>>(){}.getType () );

        return answers;
    }

}
