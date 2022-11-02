package guiRevamp;

import javax.swing.*;
import java.awt.*;

import static guiRevamp.constants.*;

public class SubPanels {

    final JPanel panelAboveAbove, panelAboveBelow, panelCentreBelow;

    SubPanels(){
        panelAboveAbove = new JPanel();
        panelAboveBelow = new JPanel();
        panelCentreBelow = new JPanel();

        panelAboveBelow.setLayout(new GridBagLayout());
        panelCentreBelow.setLayout(new GridBagLayout());

        panelAboveAbove.setPreferredSize(new Dimension(FRAME_WIDTH, PANEL_ABOVE_HEIGHT/4));
        panelAboveBelow.setPreferredSize(new Dimension(FRAME_WIDTH, 3*PANEL_ABOVE_HEIGHT/4));
        panelCentreBelow.setPreferredSize(new Dimension(FRAME_WIDTH, PANEL_CENTRE_HEIGHT/8));

        panelAboveAbove.setBackground(new Color(0x4A384D));
        panelAboveBelow.setBackground(new Color(0xDABFD2));
        panelCentreBelow.setBackground(new Color(0xECBFD0));
    }
}
