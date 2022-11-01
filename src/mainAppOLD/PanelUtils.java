package mainAppOLD;

import degreeObjectsPanels.*;

import java.awt.*;

import static mainAppOLD.App.strsForCardLayout;

public class PanelUtils {
    public static void goBackOneDepth(GeneralDegreeObjectPanel panel1, GeneralDegreeObjectPanel panel2,
                                      App parentFrame, boolean isInQuestionsPanel){

//        panel2.setVisible(false);
//        panel2.setEnabled(false);
//
//        panel1.setVisible(true);
//        panel1.setEnabled(true);
//

        switch(parentFrame.curPanelDepth){
            case 1: {
                parentFrame.optionsPanel.setBtnEnabled(true, false, false, false);
                ((CardLayout) parentFrame.cardsLayoutTest.getLayout()).show(
                        parentFrame.cardsLayoutTest, App.strsForCardLayout[0]
                );
                setInfoLabels(((DegreesPanel)panel1).degreePrograms[panel1.activePanelNo], parentFrame);
            }
            break;
            case 2: {
                parentFrame.optionsPanel.setBtnEnabled(false, true, false, true);
                ((CardLayout) parentFrame.cardsLayoutTest.getLayout()).show(
                        parentFrame.cardsLayoutTest, App.strsForCardLayout[1]
                );
                setInfoLabels(((SemestersPanel)panel1).semesters[panel1.activePanelNo], parentFrame);
            }
            break;
            case 3: {
                parentFrame.optionsPanel.setBtnsSubContainerForDegree();
                parentFrame.optionsPanel.setBtnEnabled(false, false, true, true);
                ((CardLayout) parentFrame.cardsLayoutTest.getLayout()).show(
                        parentFrame.cardsLayoutTest, App.strsForCardLayout[2]
                );
                setInfoLabels(((CoursesPanel)panel1).courses[panel1.activePanelNo], parentFrame);
            }
            break;
            case 4: {
                if(isInQuestionsPanel) {
                    parentFrame.optionsPanel.setBtnEnabled(true, false, false, true);
                    ((CardLayout) parentFrame.cardsLayoutTest.getLayout()).show(
                            parentFrame.cardsLayoutTest, App.strsForCardLayout[3]
                    );
                    setInfoLabels(((AssignmentsPanel) panel1).assignments[panel1.activePanelNo], parentFrame);
                }
                else {
                    parentFrame.optionsPanel.setBtnEnabled(false, true, false, true);
                    ((CardLayout) parentFrame.cardsLayoutTest.getLayout()).show(
                            parentFrame.cardsLayoutTest, App.strsForCardLayout[4]
                    );
                    setInfoLabels(((QuizzesPanel) panel1).quizzes[panel1.activePanelNo], parentFrame);
                }
            }
            break;
            default:
                System.out.println("Error! Unknown Panel Depth");
        }

        parentFrame.cardsLayoutTest.validate();
//        panel1.validate();
    }

    public static void goIntoNextDepth(GeneralDegreeObjectPanel panel1, GeneralDegreeObjectPanel panel2,
                                      App parentFrame, int index, boolean clickedAssignmentPanel){

        panel1.activePanelNo = index;
//        panel1.setVisible(false);
//        panel1.setEnabled(false);
//
//        panel2.setVisible(true);
//        panel2.setEnabled(true);

//        panel1.scrollPane.setViewportView(panel2);

        switch(parentFrame.curPanelDepth){
            case 1: {
                parentFrame.optionsPanel.setBtnEnabled(false, true, false, true);
                setInfoLabels(((DegreesPanel)panel1).degreePrograms[index], parentFrame);

                parentFrame.cardsLayoutTest.add(panel2.scrollPane, strsForCardLayout[1]);
                ((CardLayout) parentFrame.cardsLayoutTest.getLayout()).show(
                        parentFrame.cardsLayoutTest, strsForCardLayout[1]
                );
            }
            break;
            case 2: {
                parentFrame.optionsPanel.setBtnEnabled(false, false, true, true);
                setInfoLabels(((SemestersPanel)panel1).semesters[index], parentFrame);

                parentFrame.cardsLayoutTest.add(panel2.scrollPane, strsForCardLayout[2]);
                ((CardLayout) parentFrame.cardsLayoutTest.getLayout()).show(
                        parentFrame.cardsLayoutTest, strsForCardLayout[2]
                );
            }
            break;
            case 3: {
                parentFrame.optionsPanel.setBtnsSubContainerForCourse();

                if(clickedAssignmentPanel)
                    parentFrame.optionsPanel.setBtnEnabled(true, false, false, true);
                else
                    parentFrame.optionsPanel.setBtnEnabled(false, true, false, true);

                setInfoLabels(((CoursesPanel)panel1).courses[index], parentFrame);

                parentFrame.cardsLayoutTest.add(panel2.scrollPane, (clickedAssignmentPanel)?
                        strsForCardLayout[3] : strsForCardLayout[4]);

                ((CardLayout) parentFrame.cardsLayoutTest.getLayout()).show(
                        parentFrame.cardsLayoutTest, (clickedAssignmentPanel)?
                                strsForCardLayout[3] : strsForCardLayout[4]
                );
            }
            break;
            case 4: {
                if(clickedAssignmentPanel) {
                    parentFrame.optionsPanel.setBtnEnabled(false, false, true, true);
                    setInfoLabels(((AssignmentsPanel) panel1).assignments[index], parentFrame);

                    parentFrame.cardsLayoutTest.add(panel2.scrollPane, strsForCardLayout[5]);
                    ((CardLayout) parentFrame.cardsLayoutTest.getLayout()).show(
                            parentFrame.cardsLayoutTest, strsForCardLayout[5]
                    );
                }
                else {
                    parentFrame.optionsPanel.setBtnEnabled(false, false, true, true);
                    setInfoLabels(((QuizzesPanel)panel1).quizzes[index], parentFrame);

                    parentFrame.cardsLayoutTest.add(panel2.scrollPane, strsForCardLayout[6]);
                    ((CardLayout)parentFrame.cardsLayoutTest.getLayout()).show(
                            parentFrame.cardsLayoutTest, strsForCardLayout[6]
                    );
                }
            }
            break;
            default:
        }

        parentFrame.cardsLayoutTest.validate();
    }

    public static void setInfoLabels(Object obj, App parentFrame){
        parentFrame.optionsPanel.setViewInfoLabels(obj, parentFrame);
    }
}
