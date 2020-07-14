package android.projects.pidorsbizzareadventure.pojo;

import android.projects.pidorsbizzareadventure.adapters.UnitsAdapter;
import android.util.Log;
import android.widget.Adapter;

import androidx.annotation.NonNull;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class FirebaseHelper {

    private static final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private static final DatabaseReference reference = FirebaseDatabase.getInstance().getReference(user.getUid());

    public static class FBWriter {
        public static void writeToFB(String key, Zaruba object){
            String uid = reference.child(key).push().getKey();
            object.setUid(uid);
            reference.child(key).child(uid).setValue(object);
        }

       public static void updateData(String key, Zaruba obj){
           reference.child(key).child(obj.getUid()).setValue(obj);
       }
    }
     public static class FBReader {
        public static void readChallengesFromFB(final List<Zaruba> list, final String key, final UnitsAdapter adapter){
            ValueEventListener listener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(!list.isEmpty()) list.clear();
                    for( DataSnapshot ds : dataSnapshot.child(key).getChildren() ){
                        Zaruba zaruba = ds.getValue(Zaruba.class);
                        list.add(zaruba);
                        adapter.notifyDataSetChanged();
                        Log.i("READFB", zaruba.toString());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };
            reference.addValueEventListener(listener);
        }

         public static void readChallengesFromFB(final List<Zaruba> list, final String key){
             ValueEventListener listener = new ValueEventListener() {
                 @Override
                 public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                     if(!list.isEmpty()) list.clear();
                     for( DataSnapshot ds : dataSnapshot.child(key).getChildren() ){
                         Zaruba zaruba = ds.getValue(Zaruba.class);
                         list.add(zaruba);
                         Log.i("READFB", zaruba.toString());
                     }
                 }

                 @Override
                 public void onCancelled(@NonNull DatabaseError databaseError) {

                 }
             };
             reference.addValueEventListener(listener);
         }

         public static void readChallengesFromFBAsync(final List<Zaruba> list, final String key, final FBCallback callback){
             ValueEventListener listener = new ValueEventListener() {
                 @Override
                 public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                     if(!list.isEmpty()) list.clear();
                     for( DataSnapshot ds : dataSnapshot.child(key).getChildren() ){
                         Zaruba zaruba = ds.getValue(Zaruba.class);
                         list.add(zaruba);
                         Log.i("READFB", zaruba.toString());
                     }
                     callback.onCallback(list);
                 }

                 @Override
                 public void onCancelled(@NonNull DatabaseError databaseError) {

                 }
             };
             reference.addValueEventListener(listener);
         }

//         public static void find(){
//             DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
//             Query query = reference.child("C1GRoO4Fu4MEvdz4TQVrFBxs5Bo2").orderByChild("title").equalTo("ds");
//
//
//         }

         private interface FBCallback{
            void onCallback(List<Zaruba> list);
         }
     }



}
