package guiRevamp;

import degreeObjects.Date;
import degreeObjects.DegreeDateCommon;
import guiRevamp.degreeObjectsPanels.GeneralDegreeObjectPanel;
import morePanels.AddPanelForStrings;
import morePanels.DatePanel;

import javax.swing.*;
import java.awt.*;

import static guiRevamp.App.strsForCardLayout;

public class PanelUtils {
    public static void goBackOneDepth(GeneralDegreeObjectPanel panel1, GeneralDegreeObjectPanel panel2,
                                      App parentFrame, boolean isInQuestionsPanel){

        int indexForCardLayout = isInQuestionsPanel? parentFrame.curPanelDepth - 1 : parentFrame.curPanelDepth;

        setInfoLabels(panel1.getDegreeObjects()[panel1.activePanelNo], parentFrame);

        ((CardLayout) parentFrame.mainContainerForDegreePanels.getLayout())
                .show(parentFrame.mainContainerForDegreePanels, App.strsForCardLayout[indexForCardLayout]);

        parentFrame.mainContainerForDegreePanels.validate();
    }

    public static void goIntoNextDepth(GeneralDegreeObjectPanel panel1, GeneralDegreeObjectPanel panel2,
                                       App parentFrame, int index, boolean clickedAssignmentPanel){

        panel1.activePanelNo = index;
        int indexForCardLayout;

        switch(parentFrame.curPanelDepth){
            case 1:
            case 2:
                indexForCardLayout = parentFrame.curPanelDepth;
            break;
            case 3:
                indexForCardLayout = clickedAssignmentPanel? parentFrame.curPanelDepth : parentFrame.curPanelDepth+1;
            break;
            case 4:
                indexForCardLayout = clickedAssignmentPanel? parentFrame.curPanelDepth+1 : parentFrame.curPanelDepth+2;
            break;
            default:
                return;
        }

        setInfoLabels(panel1.getDegreeObjects()[index], parentFrame);

        parentFrame.mainContainerForDegreePanels.add(panel2.scrollPane, strsForCardLayout[indexForCardLayout]);

        ((CardLayout) parentFrame.mainContainerForDegreePanels.getLayout())
                .show(parentFrame.mainContainerForDegreePanels, strsForCardLayout[indexForCardLayout]);

        parentFrame.mainContainerForDegreePanels.validate();
    }

    public static void setInfoLabels(Object obj, App parentFrame){
        parentFrame.viewInfoPanel.setViewInfoLabels(obj, parentFrame);
    }

    public static void handleInputForStringsPanel(JFrame parentFrame){

        if (App.addPanelForStrings == null)
            App.addPanelForStrings = new AddPanelForStrings(parentFrame, "Question");
        else {
            App.addPanelForStrings.getAdderFrame().setVisible(true);
            App.addPanelForStrings.getAdderFrame().setEnabled(true);
        }
    }
    public static void handleInputDatePanel(JFrame parentFrame){

        if (App.addDatePanel == null)
            App.addDatePanel = new DatePanel(parentFrame);
        else {
            App.addDatePanel.getDateFrame().setVisible(true);
            App.addDatePanel.getDateFrame().setEnabled(true);
        }
    }
    public static void showDates(GeneralDegreeObjectPanel panel){
        Object o = ((DegreeDateCommon)panel.getDegreeObjects()[panel.activePanelNo]).getDateObject();
        Date[] date;

        if(o instanceof Date)
            date = new Date[] {(Date)o};
        else
            date = (Date[]) o;

        new DatePanel(date);
    }
}
