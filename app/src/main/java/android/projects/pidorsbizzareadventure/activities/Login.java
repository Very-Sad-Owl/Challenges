package android.projects.pidorsbizzareadventure.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.projects.pidorsbizzareadventure.R;
import android.projects.pidorsbizzareadventure.ui.MainActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static android.projects.pidorsbizzareadventure.storage.firebase.FBConstants.*;

public class Login extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        user = auth.getCurrentUser();
        if(user != null && checkVerification()){
            Toast.makeText(this, "user ok", Toast.LENGTH_SHORT).show();
            startMainActivity();
        }else {
            Toast.makeText(this, "user not ok", Toast.LENGTH_SHORT).show();
        }
    }

    private void init(){
        email = findViewById(R.id.edEmail);
        password = findViewById(R.id.edPassword);
        auth = FirebaseAuth.getInstance();
    }

    public void onClickSignIn(View view){
        if(!TextUtils.isEmpty(email.getText().toString()) &&
                !TextUtils.isEmpty(password.getText().toString())) {
            auth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(Login.this, "signing in success", Toast.LENGTH_SHORT).show();
                            if (checkVerification()) startMainActivity();
                            else Toast.makeText(Login.this, "failed", Toast.LENGTH_SHORT).show();
                            startMainActivity();
                        }
                    });
        } else {
        Toast.makeText(this, "required fields are empty", Toast.LENGTH_SHORT).show();
    }
    }

    public void onClickSignUp(View view){
        if(!TextUtils.isEmpty(email.getText().toString()) &&
                !TextUtils.isEmpty(password.getText().toString())) {
            auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Login.this, "registered", Toast.LENGTH_SHORT).show();
                                writeToFB();
                                verify();
                                Log.i("loginUp", user.getUid());
                                startMainActivity();
                            } else {
                                Toast.makeText(Login.this, "registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(this, "required fields are empty", Toast.LENGTH_SHORT).show();
        }
    }

    private void verify(){
        //assert user != null : "user is null";
        Objects.requireNonNull(auth.getCurrentUser()).sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "check your email", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Login.this, "verification failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private boolean checkVerification(){
        //assert user != null : "user null";
        return user != null && user.isEmailVerified();
    }

    private void startMainActivity(){
        Log.i("to main", USER_UID);
        Intent start = new Intent(this, MainActivity.class);
        startActivity(start);
    }

    private void writeToFB(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(USER_PATH);
        Map<String, String> userMetadata = new HashMap<>();

        userMetadata.put(EMAIL_FIELD, email.getText().toString());
        userMetadata.put(PASSWORD_FIELD, password.getText().toString());

        String uid = user.getUid();
//
//        reference.child(uid).setValue(emailP);
//        reference.child(uid).setValue(passwordP);
        reference.child(uid).setValue(userMetadata);
    }
}
