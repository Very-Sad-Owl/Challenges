package android.projects.pidorsbizzareadventure.storage.firebase;

import android.projects.pidorsbizzareadventure.pojo.Participator;
import android.projects.pidorsbizzareadventure.pojo.User;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.projects.pidorsbizzareadventure.storage.firebase.FBConstants.*;

public class FirebaseHelper {

    public static class ChallengesInteraction {

        public static void getAllCurrent(final List<Zaruba> list, final ReadingCallback callback) {
            DatabaseReference reference = refChallenges;

            ValueEventListener listener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //if (!list.isEmpty()) list.clear();
                    list.clear();
                    Log.i("onDataChangebi", list.toString());
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Zaruba zaruba = ds.getValue(Zaruba.class);
                        Log.i("ALLCURR", zaruba.toString() + " " + auth.getCurrentUser().getUid());
                        for (Participator participator : zaruba.getParticipators()) {
                            if (participator.getUid().equals(auth.getCurrentUser().getUid())) {
                                list.add(zaruba);
                            }
                        }
                    }
                    Log.i("onDataChangeai", list.toString());
                    callback.onSuccess();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };
            reference.addValueEventListener(listener);

//            ChildEventListener listener = new ChildEventListener() {
//                @Override
//                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                    Log.i("childListener", "child added");
//                    if (!list.isEmpty()) list.clear();
//                        Zaruba zaruba = dataSnapshot.getValue(Zaruba.class);
//                        Log.i("ALLCURR", zaruba.toString() + " " + auth.getCurrentUser().getUid());
//                        for (Participator participator : zaruba.getParticipators()) {
//                            if (participator.getUid().equals(auth.getCurrentUser().getUid())) {
//                                list.add(zaruba);
//                            }
//                    }
//                    callback.onSuccess();
//                }
//
//                @Override
//                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                    Log.i("childListener", "child changed");
//                }
//
//                @Override
//                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//                    Log.i("childListener", "child removed");
//                }
//
//                @Override
//                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                    Log.i("childListener", "child moved");
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//                    Log.i("childListener", "child cancelled");
//                }
//            };
//            reference.addChildEventListener(listener);
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
            reference.addListenerForSingleValueEvent(listener);

        }

        public static void updateChallenge(Zaruba obj){
            //refChallenges.child(obj.getUid()).setValue(obj);
            Map<String, Object> map = new HashMap<>();
            map.put(obj.getUid(), obj);
            refChallenges.updateChildren(map);
        }

        public static void uploadChallenge(Zaruba obj){
            String uid = refChallenges.push().getKey();
            obj.setUid(uid);
            refChallenges.child(uid).setValue(obj);
        }

        public static void updateParticipators(Zaruba obj){
            Map<String, Object> map = new HashMap<>();
            map.put("participators", obj.getParticipators());
            refChallenges.child(obj.getUid()).updateChildren(map);
        }
    }

    public static class UserInteraction {

        public static void registerUser(User user){
            refUsers.child(user.getUid()).setValue(user);
        }

        public static void getUser(final User user, final String uid, final ReadingCallback callback){
            DatabaseReference reference = refUsers;
            ValueEventListener listener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        User found = ds.getValue(User.class);
                        Log.i("found", found.toString());
                        Log.i("uidd", found.getUid() + " " + uid + " " + USER_UID);
                        if(found.getUid().equals(uid)){
                            Log.i("found2", found.toString());
                            user.setEmail(found.getEmail());
                            user.setPassword(found.getPassword());
                            user.setNickname(found.getNickname());
                            user.setUid(found.getUid());
                        }
                    }
                    Log.i("found3", user.toString());
                    callback.onSuccess();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };
            reference.addValueEventListener(listener);
        }

    }
}
