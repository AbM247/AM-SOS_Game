package sprint_Test;

import javafx.application.Platform;
import sprint.GUI;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

public class TestSelectMode {

    @BeforeAll
    public static void initJFX() throws Exception {
        // Initialize JavaFX toolkit using Platform.startup
        Platform.startup(() -> {
            // JavaFX initialization logic if needed
        });
    }

    @Test
    public void testGetModeSimple() {
        // Run the test in the JavaFX thread
        Platform.runLater(() -> {
            // Simulate the selection of "Simple" mode
            GUI.Smode.setSelected(true);  // Select the Simple mode
            assertEquals("Simple", GUI.getMode());
        });
    }

    @Test
    public void testGetModeGeneral() {
        // Run the test in the JavaFX thread
        Platform.runLater(() -> {
            // Simulate the selection of "General" mode
            GUI.Gmode.setSelected(true);  // Select the General mode
            assertEquals("General", GUI.getMode());
        });
    }
}
