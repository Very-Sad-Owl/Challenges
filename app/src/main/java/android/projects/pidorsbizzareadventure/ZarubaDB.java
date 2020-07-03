package android.projects.pidorsbizzareadventure;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

public abstract class ZarubaDB extends RoomDatabase {

    public static ZarubaDB database;
    public static final String DB_NAME = "challenges.db";

    public static ZarubaDB getInstance(Context context) {
        if(database == null){
            synchronized (ZarubaDB.class){
                if (database == null){
                    database = Room.databaseBuilder(context, ZarubaDB.class, DB_NAME).build();
                }
            }
        }
        return database;
    }

    public abstract ZarubasDao zarubasDao();

}
