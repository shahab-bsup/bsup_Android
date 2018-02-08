package tk.medlynk.patient.android.Activity.Login;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

/**
 * Created by Shahab on 1/11/2018.
 */

public class LoginViewModel extends ViewModel {

    private ObservableField<String> email;
    private ObservableField<String> password;
    private ObservableField<String> emailError;
    private Context mContext;

    public LoginViewModel() {
        email = new ObservableField<> (  );
        password = new ObservableField<> (  );
        emailError = new ObservableField<> (  );
    }

    public LoginViewModel(@NonNull Context context) {
        email = new ObservableField<> (  );
        password = new ObservableField<> (  );
        mContext = context;
    }

    public ObservableField<String> getEmail() {
        return email;
    }

    public ObservableField<String> getPassword() {
        return password;
    }

    public void submitLogin(){
        System.out.println ("submit!");
//        getEmailError (email.get ());
    }

}