package guiRevamp;

import degreeObjects.*;
import guiRevamp.degreeObjectsPanels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class DegreeContainerPanel extends JPanel {

    DegreesPanel degreesPanel;
    static String[] strsForCardLayout = {"degree", "semester", "course", "assignment", "quiz", "question", "topics"};
    int curPanelDepth;
    final int MAX_ALLOWED_PANEL_DEPTH = 4;
    final App parentApp;

    DegreeContainerPanel(App parentApp) {
        this.parentApp = parentApp;

        this.setLayout(new CardLayout());

        degreesPanel = new DegreesPanel();

        this.add(degreesPanel.scrollPane, strsForCardLayout[0]);
    }

    SemestersPanel getActiveSemesterPanel() {
        return degreesPanel.semestersPanels == null ? null : degreesPanel.semestersPanels[degreesPanel.activePanelNo];
    }

    CoursesPanel getActiveCoursePanel() {

        if (getActiveSemesterPanel() == null || getActiveSemesterPanel().coursesPanels == null)
            return null;

        return degreesPanel.semestersPanels[degreesPanel.activePanelNo]
                .coursesPanels[degreesPanel.semestersPanels[degreesPanel.activePanelNo].activePanelNo];
    }

    Object[] getActivePanelsAndDegObj() {
        GeneralDegreeObjectPanel panel, parentPanel = null;
        Object degObj;

        SemestersPanel semPanel = null;
        CoursesPanel courPanel = null;

        if (degreesPanel.semestersPanels != null) {
            semPanel = degreesPanel.semestersPanels[degreesPanel.activePanelNo];

            if (semPanel.coursesPanels != null)
                courPanel = semPanel.coursesPanels[semPanel.activePanelNo];
        }

        switch (curPanelDepth) {
            case 0:
                panel = degreesPanel;
                degObj = new DegreeProgram();
                break;
            case 1:
                parentPanel = degreesPanel;
                panel = semPanel;
                degObj = new Semester();
                break;
            case 2:
                parentPanel = semPanel;
                panel = courPanel;
                degObj = new Course();
                break;
            case 3:
                parentPanel = courPanel;
                panel = (GeneralDegreeObjectPanel) courPanel.getInnerPanels()[courPanel.activePanelNo];
                degObj = courPanel.clickedAssignmentPanel ? new Assignment() : new Quiz();
                break;
            case 4:
                GeneralDegreeObjectPanel assignmentOrQuizPanel =
                        (GeneralDegreeObjectPanel) courPanel.getInnerPanels()[courPanel.activePanelNo];

                parentPanel = assignmentOrQuizPanel;
                panel = (GeneralDegreeObjectPanel) assignmentOrQuizPanel
                        .getInnerPanels()[assignmentOrQuizPanel.activePanelNo];
                degObj = courPanel.clickedAssignmentPanel ? new Question() : new Topic();
                break;
            default:
                System.out.println("Error! Unknown depth. Cannot exceed 5.");
                return new Object[]{null};
        }

        return new Object[]{panel, parentPanel, degObj};
    }

    void handleClick(MouseEvent e){

        GeneralDegreeObjectPanel src;
        int index;
        boolean isPanelHighlighted;

        if(((JPanel) e.getSource()).getParent().getParent().getParent() instanceof CoursesPanel) {
            src = (GeneralDegreeObjectPanel) ((JPanel) e.getSource()).getParent().getParent().getParent();
            index = Integer.parseInt(((JPanel) e.getSource()).getName().split("-")[1]);
        }
        else {
            src = (GeneralDegreeObjectPanel) ((JPanel) e.getSource()).getParent();
            index = Integer.parseInt(((JPanel) e.getSource()).getName());
        }
        isPanelHighlighted = src.isPanelAtIndexHighlighted(index);

        if(isPanelHighlighted && curPanelDepth < MAX_ALLOWED_PANEL_DEPTH)
            goIntoNextDepth(e, index, src);
        else
            src.highlightPanelAt(index);
    }

    void goBackOneDepth() {
        SemestersPanel semPanel = getActiveSemesterPanel();
        CoursesPanel courPanel = getActiveCoursePanel();

        if (curPanelDepth >= 1) {
            GeneralDegreeObjectPanel toGoTo;

            switch (curPanelDepth) {
                case 1:
                    toGoTo = degreesPanel;
                    break;
                case 2:
                    toGoTo = semPanel;
                    break;
                case 3:
                    toGoTo = courPanel;
                    break;
                case 4:
                    if (courPanel.clickedAssignmentPanel) {
                        toGoTo = courPanel.assignmentsPanels[courPanel.activePanelNo];
                    } else {
                        toGoTo = courPanel.quizzesPanels[courPanel.activePanelNo];
                    }
                    break;
                default:
                    System.out.println("Error! Unknown panel depth. Cannot exceed 4.");
                    return;
            }

            PanelUtils.goBackOneDepth(toGoTo, this,
                    courPanel != null && courPanel.clickedAssignmentPanel);
            curPanelDepth--;
            validate();
        }
    }

    void goIntoNextDepth(MouseEvent e, int index, GeneralDegreeObjectPanel src){

        GeneralDegreeObjectPanel toGoFrom, toGoTo;
        boolean clickedAssignmentPanel;

        ++curPanelDepth;

        if(src instanceof CoursesPanel) {
            ((CoursesPanel) src).clickedAssignmentPanel = e.getSource() == ((CoursesPanel) src).goToAssignmentPanel[index];
            clickedAssignmentPanel = ((CoursesPanel) src).clickedAssignmentPanel;
        }
        else
            clickedAssignmentPanel = src instanceof AssignmentsPanel;

        toGoFrom = src;
        toGoTo = (GeneralDegreeObjectPanel) toGoFrom.getInnerPanels()[index];

        PanelUtils.goIntoNextDepth(toGoFrom, toGoTo, this,
                index, clickedAssignmentPanel);

        validate();
    }
}
