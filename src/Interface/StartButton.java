package Interface;

import javax.swing.*;

public class StartButton {
    public static JButton createHideButton(){
        JButton hideButton=new JButton();
        hideButton.setVisible(false);
        return hideButton;
    }
}
