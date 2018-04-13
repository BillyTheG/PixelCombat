package pixelCombat.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import pixelCombat.view.gamephases.GamePlayView;



public class Console extends BorderPane {
    protected final TextArea textArea = new TextArea();
    
    protected final List<String> history = new ArrayList<>();
    protected int historyPointer = 0;

    public Console() {
    	setLayoutX(0);
        textArea.setEditable(false);
        textArea.setMaxSize(GamePlayView.SCREEN_WIDTH, GamePlayView.SCREEN_HEIGHT);
        textArea.setMinSize(GamePlayView.SCREEN_WIDTH, GamePlayView.SCREEN_HEIGHT);
        textArea.setStyle(""
                + "-fx-font-size: 14px;"
                + "-fx-font-style: consolas;"
                + "-fx-font-weight: bold;"
                + "-fx-font-family: consolas;"
                + "-fx-text-fill: white;"
                + "-fx-control-inner-background: black");
        setCenter(textArea);
        
    }

    @Override
    public void requestFocus() {
        super.requestFocus();
       
    }

    public void setOnMessageReceivedHandler(final Consumer<String> onMessageReceivedHandler) {
    }

    public void clear() {
    	GUIUtils.runSafe(() -> textArea.clear());
    }

    public synchronized void print(final String text) {
        Objects.requireNonNull(text, "text");
        GUIUtils.runSafe(() -> textArea.appendText(text));
    }

    public synchronized void println(final String text) {
        Objects.requireNonNull(text, "text");
        GUIUtils.runSafe(() -> textArea.appendText(text + System.lineSeparator()));
    }

    public synchronized void println() {
    	GUIUtils.runSafe(() -> textArea.appendText(System.lineSeparator()));
    }
    
    @Override
    public boolean equals(Object obj)
    {
    	if(obj instanceof Console)
    		return this.historyPointer == ((Console)obj).historyPointer;
    	
    	return false;
    }
    
    @Override
    public int hashCode()
    {
    	return historyPointer;
    }
   
    
    
}
