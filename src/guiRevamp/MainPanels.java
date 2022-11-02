package guiRevamp;

import javax.swing.*;
import java.awt.*;

import static guiRevamp.constants.*;

public class MainPanels {


    final JPanel panelAbove, panelAtCentre, panelBelow;
    SubPanels subPanels;
    OptionButtons optionButtons;

    MainPanels(JFrame parentFrame){
        panelAbove = new JPanel();
        panelAtCentre = new JPanel();
        panelBelow = new JPanel();

        panelAbove.setLayout(new BorderLayout());
        panelAtCentre.setLayout(new BorderLayout());
        panelBelow.setLayout(new BorderLayout());

        panelAbove.setPreferredSize(new Dimension(FRAME_WIDTH, PANEL_ABOVE_HEIGHT));
        panelAtCentre.setPreferredSize(new Dimension(FRAME_WIDTH, PANEL_CENTRE_HEIGHT));
        panelBelow.setPreferredSize(new Dimension(FRAME_WIDTH, PANEL_BELOW_HEIGHT));

        panelAbove.setBackground(new Color(0x771144));
        panelAtCentre.setBackground(new Color(0xAD1A97));
        panelBelow.setBackground(new Color(0xAD528C));

        subPanels = new SubPanels();
        optionButtons = new OptionButtons(parentFrame);

        addStuff();
    }

    void addStuff(){

        addBtns();

        panelAbove.add(subPanels.panelAboveAbove, BorderLayout.NORTH);
        panelAbove.add(subPanels.panelAboveBelow, BorderLayout.CENTER);
        panelAtCentre.add(subPanels.panelCentreBelow, BorderLayout.SOUTH);
    }

    private void addBtns() {

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(PANEL_ABOVE_HEIGHT/8, (int) Math.ceil(1.3*FRAME_WIDTH/100),
                PANEL_ABOVE_HEIGHT/8, (int) Math.ceil(1.3*FRAME_WIDTH/100));
        c.gridwidth = FRAME_WIDTH/3;
        c.ipadx = FRAME_WIDTH/16;
        c.ipady = FRAME_HEIGHT/16;
        c.weighty = 1;
        c.weightx = 1;

        c.anchor = GridBagConstraints.LINE_START;
        subPanels.panelAboveBelow.add(optionButtons.btns[GO_BACK], c);
        c.anchor = GridBagConstraints.CENTER;
        subPanels.panelAboveBelow.add(optionButtons.btns[SHOW_FAVS], c);
        c.anchor = GridBagConstraints.LINE_END;
        subPanels.panelAboveBelow.add(optionButtons.btns[SEARCH], c);

        GridBagConstraints c2 = new GridBagConstraints();
        c2.insets = new Insets(PANEL_CENTRE_HEIGHT/64, (int) Math.ceil(1.3*FRAME_WIDTH/100),
                PANEL_CENTRE_HEIGHT/64, (int) Math.ceil(1.3*FRAME_WIDTH/100));
        c2.gridwidth = FRAME_WIDTH/4;
        c2.ipadx = FRAME_WIDTH/16;
        c2.ipady = PANEL_CENTRE_HEIGHT/16;
        c2.weighty = 1;
        c2.weightx = 1;

        subPanels.panelCentreBelow.add(optionButtons.btns[EDIT], c2);
        subPanels.panelCentreBelow.add(optionButtons.btns[ADD_TO_FAV], c2);
        subPanels.panelCentreBelow.add(optionButtons.btns[ADD_NOTE], c2);
        subPanels.panelCentreBelow.add(optionButtons.btns[ADD_BTN], c2);
    }
}
