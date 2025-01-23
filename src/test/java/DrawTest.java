import logic.BoardController;
import logic.BoardControllerBuilder;
import logic.pieceDisposition.BoardDispositionSetting;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class DrawTest {

    @Test
    public void testDraw() {
        BoardControllerBuilder builder = new BoardControllerBuilder();
        builder.setBoardDispositionSetting(new BoardDispositionSetting(new File("src/test/java/DispositionsForTesting/TableWithDraw.sp")));
        BoardController boardController = builder.build();
        Assert.assertTrue(boardController.isGameInDraw());
    }

}
