package android.projects.pidorsbizzareadventure.ui.creation;

import android.projects.pidorsbizzareadventure.pojo.Zaruba;

public interface StepByStepContract {

    void nextStep();
    void previousStep();
    void finishCreation(Zaruba result);
}
