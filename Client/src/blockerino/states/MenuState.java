package blockerino.states;

import blockerino.util.KeyHandler;
import blockerino.util.MouseHandler;
import blockerino.Window;

import javax.swing.*;
import java.awt.*;

public class MenuState extends GameState {

    public MenuState(GameStateManager _gameStateManager) {
        super(_gameStateManager);
        //TODO Create controls, add to window
    }

    private JPanel createMenuControls() {
        JPanel jPanel = new JPanel();
        JButton okButton = new JButton("OK");
        jPanel.add(okButton);
        jPanel.setLocation(0, 0);
        return jPanel;
    }

    @Override
    public void update() {
        System.out.println("menu");
    }

    @Override
    public void input(MouseHandler _mouse, KeyHandler _key) {
        //TODO Selected control up/down by keyboard input
        //TODO Some fancy effect on mouse trail?
    }

    @Override
    public void render(Graphics2D _graphics2D) {
        //Unused
    }


    /*b.addActionListener(new ActionListener() {

    public void actionPerformed(ActionEvent e)
    {
        System.out.println("You clicked button "+e.getSource().toString());
    }
});*/
}
