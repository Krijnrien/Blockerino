package blockerino.graphics.UI;

import blockerino.GamePanel;

import javax.swing.*;

public class EscapeGameUI{

    EscapeGameUI() {

    }

    public void createUI() {
        JPanel jPanel = new JPanel();
        JButton okButton = new JButton("OK");
        jPanel.add(okButton);
        jPanel.setLocation(0, 0);

    }

    public void destroyUI() {

    }
}
