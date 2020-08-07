package android.projects.pidorsbizzareadventure.storage.firebase;

import android.projects.pidorsbizzareadventure.pojo.User;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public interface FBConstants {
    String CHALLENGE_PATH = "CHALLENGES";
    String USER_PATH = "USERS";
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();
    String USER_UID = user != null ?
            user.getUid() != null ? user.getUid() : "" : "";
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference refChallenges = FirebaseDatabase.getInstance().getReference(CHALLENGE_PATH);
    DatabaseReference refUsers = FirebaseDatabase.getInstance().getReference(USER_PATH);
    User CURRENT_USER = new User();

}
