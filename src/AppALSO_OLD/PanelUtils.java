package AppALSO_OLD;

import degreeObjects.Date;
import degreeObjects.DegreeDateCommon;
import degreeObjectsPanels.*;
import morePanels.AddPanelForStrings;
import morePanels.DatePanel;

import javax.swing.*;
import java.awt.*;

import static AppALSO_OLD.App.strsForCardLayout;

public class PanelUtils {
    public static void goBackOneDepth(GeneralDegreeObjectPanel panel1, GeneralDegreeObjectPanel panel2,
                                      App parentFrame, boolean isInQuestionsPanel){

        int indexForCardLayout;

        switch(parentFrame.curPanelDepth){
            case 1: {
                parentFrame.optionsPanel.setBtnEnabled(true, false, false, false);

                indexForCardLayout = 0;
            }
            break;
            case 2: {
                parentFrame.optionsPanel.setBtnEnabled(false, true, false, true);

                indexForCardLayout = 1;
            }
            break;
            case 3: {
                parentFrame.optionsPanel.setBtnsSubContainerForDegree();
                parentFrame.optionsPanel.setBtnEnabled(false, false, true, true);

                indexForCardLayout = 2;
            }
            break;
            case 4: {
                if(isInQuestionsPanel)
                    parentFrame.optionsPanel.setBtnEnabled(true, false, false, true);
                else
                    parentFrame.optionsPanel.setBtnEnabled(false, true, false, true);

                indexForCardLayout = isInQuestionsPanel? 3 : 4;
            }
            break;
            default:
                System.out.println("Error! Unknown Panel Depth");
                return;
        }

        setInfoLabels(panel1.getDegreeObjects()[panel1.activePanelNo], parentFrame);
        ((CardLayout) parentFrame.cardsLayoutTest.getLayout())
                .show(parentFrame.cardsLayoutTest, App.strsForCardLayout[indexForCardLayout]);

        parentFrame.cardsLayoutTest.validate();
    }

    public static void goIntoNextDepth(GeneralDegreeObjectPanel panel1, GeneralDegreeObjectPanel panel2,
                                       App parentFrame, int index, boolean clickedAssignmentPanel){

        panel1.activePanelNo = index;
        int indexForCardLayout;

        switch(parentFrame.curPanelDepth){
            case 1: {
                parentFrame.optionsPanel.setBtnEnabled(false, true, false, true);

                indexForCardLayout = 1;
            }
            break;
            case 2: {
                parentFrame.optionsPanel.setBtnEnabled(false, false, true, true);

                indexForCardLayout = 2;
            }
            break;
            case 3: {
                parentFrame.optionsPanel.setBtnsSubContainerForCourse();
                JButton first, second;

                if(clickedAssignmentPanel) {
                    first = parentFrame.optionsPanel.addTopicBtn;
                    second = parentFrame.optionsPanel.addQuestionBtn;
                    parentFrame.optionsPanel.setBtnEnabled(true, false, false, true);
                }
                else {
                    first = parentFrame.optionsPanel.addQuestionBtn;
                    second = parentFrame.optionsPanel.addTopicBtn;
                    parentFrame.optionsPanel.setBtnEnabled(false, true, false, true);
                }

                parentFrame.optionsPanel.swapFirstWithSecondBtn(first, second);
                indexForCardLayout = clickedAssignmentPanel? 3 : 4;
            }
            break;
            case 4: {
                if(clickedAssignmentPanel)
                    parentFrame.optionsPanel.setBtnEnabled(false, false, true, true);
                else
                    parentFrame.optionsPanel.setBtnEnabled(false, false, true, true);

                indexForCardLayout = clickedAssignmentPanel? 5 : 6;
            }
            break;
            default:
                return;
        }

        setInfoLabels(panel1.getDegreeObjects()[index], parentFrame);
        parentFrame.cardsLayoutTest.add(panel2.scrollPane, strsForCardLayout[indexForCardLayout]);

        ((CardLayout) parentFrame.cardsLayoutTest.getLayout())
                .show(parentFrame.cardsLayoutTest, strsForCardLayout[indexForCardLayout]);

        parentFrame.cardsLayoutTest.validate();
    }

    public static void setInfoLabels(Object obj, App parentFrame){
        parentFrame.optionsPanel.setViewInfoLabels(obj, parentFrame);
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
