package guiRevamp;

import degreeObjects.Date;
import degreeObjects.DegreeDateCommon;
import guiRevamp.degreeObjectsPanels.GeneralDegreeObjectPanel;
import morePanels.AddPanelForStrings;
import morePanels.DatePanel;

import javax.swing.*;
import java.awt.*;

public class PanelUtils {
    public static void goBackOneDepth(GeneralDegreeObjectPanel panel1,
                                      DegreeContainerPanel degreeContainerPanel, boolean isInQuestionsPanel){

        int indexForCardLayout = degreeContainerPanel.curPanelDepth-1;

        if(indexForCardLayout >= 3 && !isInQuestionsPanel)
            indexForCardLayout++;

        setInfoLabels(panel1.getDegreeObjects()[panel1.activePanelNo], degreeContainerPanel.parentApp);

        ((CardLayout) degreeContainerPanel.getLayout())
                .show(degreeContainerPanel, DegreeContainerPanel.strsForCardLayout[indexForCardLayout]);

        degreeContainerPanel.validate();
    }

    public static void goIntoNextDepth(GeneralDegreeObjectPanel panel1, GeneralDegreeObjectPanel panel2,
                                       DegreeContainerPanel degreeContainerPanel, int index, boolean clickedAssignmentPanel){

        panel1.activePanelNo = index;
        int indexForCardLayout;

        switch(degreeContainerPanel.curPanelDepth){
            case 1:
            case 2:
                indexForCardLayout = degreeContainerPanel.curPanelDepth;
            break;
            case 3:
                indexForCardLayout = clickedAssignmentPanel?
                        degreeContainerPanel.curPanelDepth : degreeContainerPanel.curPanelDepth+1;
            break;
            case 4:
                indexForCardLayout = clickedAssignmentPanel?
                        degreeContainerPanel.curPanelDepth+1 : degreeContainerPanel.curPanelDepth+2;
            break;
            default:
                return;
        }

        setInfoLabels(panel1.getDegreeObjects()[index], degreeContainerPanel.parentApp);

        degreeContainerPanel.add(panel2.scrollPane, DegreeContainerPanel.strsForCardLayout[indexForCardLayout]);

        ((CardLayout) degreeContainerPanel.getLayout())
                .show(degreeContainerPanel, DegreeContainerPanel.strsForCardLayout[indexForCardLayout]);

        degreeContainerPanel.validate();
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
