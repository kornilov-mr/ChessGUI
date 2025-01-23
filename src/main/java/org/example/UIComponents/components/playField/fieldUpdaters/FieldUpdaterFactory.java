package org.example.UIComponents.components.playField.fieldUpdaters;

import gameControl.gameController.AbstractGameController;
import gameControl.gameController.OnlineGameController;
import gameControl.gameController.TutorialGameController;
import gameControl.gameController.VisualGameController;
import org.example.UIComponents.components.playField.VisualGameField;

/**
 * creates fieldUpdater based on Game controller type
 */
public class FieldUpdaterFactory {
    private final VisualGameField visualGameField;

    public FieldUpdaterFactory(VisualGameField visualGameField) {
        this.visualGameField = visualGameField;
    }

    public IUpdateField createFieldUpdater(AbstractGameController abstractGameController ) {
        IUpdateField updateField = null;
        if(abstractGameController instanceof VisualGameController) {
            updateField = new BasicFieldUpdate(visualGameField);
        }else if(abstractGameController instanceof TutorialGameController) {
            updateField = new TutorialFieldUpdate(visualGameField);
        }else if(abstractGameController instanceof OnlineGameController) {
            updateField = new OnlineFieldUpdate(visualGameField);
        }
        if(updateField == null) throw new RuntimeException("Can't create field updater for "+abstractGameController.getClass().getName());
        return updateField;
    }
}
