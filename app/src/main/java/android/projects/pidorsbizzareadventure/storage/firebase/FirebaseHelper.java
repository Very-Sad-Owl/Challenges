package android.projects.pidorsbizzareadventure.storage.firebase;

import android.projects.pidorsbizzareadventure.pojo.Participator;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import static android.projects.pidorsbizzareadventure.storage.firebase.FBConstants.USER_UID;
import static android.projects.pidorsbizzareadventure.storage.firebase.FBConstants.ref;
import static android.projects.pidorsbizzareadventure.storage.firebase.FBConstants.refChallenges;

public class FirebaseHelper {

    public static class ChallengesInteraction {

        public static void getAllCurrent(final List<Zaruba> list, final ReadingCallback callback) {
            DatabaseReference reference = refChallenges;
            ValueEventListener listener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (!list.isEmpty()) list.clear();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Zaruba zaruba = ds.getValue(Zaruba.class);
                        Log.i("ALLCURR", zaruba.toString() + " " + USER_UID);
                        for (Participator participator : zaruba.getParticipators()) {
                            if (participator.getUid().equals(USER_UID)) {
                                list.add(zaruba);
                            }
                        }
                    }
                    callback.onSuccess();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };
            reference.addValueEventListener(listener);
        }

        public static void searchByUid(final String uid, final Zaruba found, final ReadingCallback callback){
            DatabaseReference reference = refChallenges;
            ValueEventListener listener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Zaruba zaruba = ds.getValue(Zaruba.class);
                        Log.i("found", zaruba.toString());
                        if(zaruba.getUid().equals(uid)){
                            found.setUid(zaruba.getUid());
                            found.setTitle(zaruba.getTitle());
                            found.setConditions(zaruba.getConditions());
                            found.setDescription(zaruba.getDescription());
                            found.setHost(zaruba.getHost());
                            found.setParticipators(zaruba.getParticipators());
                            found.setPunishment(zaruba.getPunishment());
                            found.setReward(zaruba.getReward());
                        }
                    }
                    callback.onSuccess();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };
            reference.addValueEventListener(listener);
        }

        public static void updateChallenge(Zaruba obj){
            refChallenges.child(obj.getUid()).setValue(obj);
        }

        public static void uploadChallenge(Zaruba obj){
            String uid = refChallenges.push().getKey();
            obj.setUid(uid);
            refChallenges.child(uid).setValue(obj);
        }
    }

    public static class UserInteraction {
        public void updatePenalty(){

        }
    }
}
