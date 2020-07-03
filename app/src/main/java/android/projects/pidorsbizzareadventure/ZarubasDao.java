package android.projects.pidorsbizzareadventure;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ZarubasDao {
    @Query("SELECT * FROM challenge ORDER BY id")
    List<Zaruba> getAllChallenges();

    @Insert
    void insertChallenge();

    @Delete
    void deleteChallenge(Zaruba zaruba);

    @Query("DELETE FROM challenge")
    void deleteAllChallenges();
}
