package guiRevamp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class OptionButtons {

    JButton[] btns;

    OptionButtons(JFrame parentFrame){

        btns = new JButton[]{new JButton("Back"), new JButton("Show Favourites"), new JButton("Search"),
            new JButton("Edit"), new JButton("Add To Favourite"), new JButton("Add Note"),
            new JButton("Add")};


        for(JButton btn : btns) {
            btn.setFocusable(false);
            btn.addActionListener((ActionListener) parentFrame);

            btn.setBackground(new Color(0xFFBBCC));
            btn.setForeground(new Color(0x330033));
            btn.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
        }
    }
}
