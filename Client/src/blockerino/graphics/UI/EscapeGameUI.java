package blockerino.graphics.UI;


import blockerino.GamePanel;
import blockerino.Window;

import javax.swing.*;

class EscapeGameUI extends JPanel {

    private boolean isActive;
    private JPanel gamePanel;

    EscapeGameUI() {
        //gamePanel = Window.getInstance().getGamePanel();
        //^SINGLETON NOT WORKING
    }

    void manageUI() {
        if (!isActive) {
            createUI();
            System.out.println("Creating escape menu");
        } else {
            destroyUI();
            System.out.println("Destroying escape menu");
        }
    }

    private void createUI() {
//        JButton okButton = new JButton("OK");
//        this.add(okButton);
//        this.setLocation(20, 200);
//        isActive = true;
//        System.out.println(gamePanel.getWidth());
//        //gamePanel.add(okButton);
    }

    private void destroyUI() {
        isActive = false;
    }
}
