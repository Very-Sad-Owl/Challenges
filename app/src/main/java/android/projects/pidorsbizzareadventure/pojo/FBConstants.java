package android.projects.pidorsbizzareadventure.pojo;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public interface FBConstants {
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    String CHALLENGE_PATH = "CHALLENGES";
    String USER_PATH = "USERS";
    String EMAIL_FIELD = "email";
    String PASSWORD_FIELD = "password";
    String NICKNAME_FIELD = "nickname";
    String USER_UID = FirebaseAuth.getInstance().getCurrentUser().getUid() != null ? FirebaseAuth.getInstance().getCurrentUser().getUid() : "" ;
}
