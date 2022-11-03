package guiRevamp;

import degreeObjects.*;
import guiRevamp.degreeObjectsPanels.*;
import morePanels.AddPanelForStrings;
import morePanels.DatePanel;
import morePanels.GetInfoPanel;
import static guiRevamp.constants.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class App extends JFrame implements ActionListener, MouseListener {

        ViewInfoPanel viewInfoPanel;
        MainPanels mainPanels;
        static GetInfoPanel infoPanel = new GetInfoPanel();
        static DatePanel addDatePanel = null;
        static AddPanelForStrings addPanelForStrings = null;

        App(){
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            setLocationRelativeTo(null);
            setLayout(new BorderLayout());


            mainPanels = new MainPanels(this);
            viewInfoPanel = new ViewInfoPanel(this);


            mainPanels.panelBelow.add(viewInfoPanel, BorderLayout.CENTER);


            add(mainPanels.panelAbove, BorderLayout.NORTH);
            add(mainPanels.panelAtCentre, BorderLayout.CENTER);
            add(mainPanels.panelBelow, BorderLayout.SOUTH);

            pack();
            setVisible(true);
            validate();
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            GeneralDegreeObjectPanel panel, parentPanel;
            Object degObj;

            Object[] returned = mainPanels.subPanels.mainContainerForDegreePanels.getActivePanelsAndDegObj();

            panel = (GeneralDegreeObjectPanel) returned[0];

            if (panel == null)
                return;

            parentPanel = (GeneralDegreeObjectPanel) returned[1];
            degObj = returned[2];

            if (e.getSource() instanceof JButton) {

                if (e.getSource() == infoPanel.getDatesBtn()) {
                    PanelUtils.handleInputDatePanel(this);
                } else if (e.getSource() == mainPanels.optionButtons.btns[ADD_BTN]) {
                    panel.incrementSizeBy(1);
                    infoPanel = new GetInfoPanel(degObj, this);
                } else if (addDatePanel != null && e.getSource() == addDatePanel.getDoneBtn()) {
                    addDatePanel.getDateFrame().setVisible(false);
                    addDatePanel.getDateFrame().setEnabled(false);
                } else if (e.getSource() == infoPanel.getSetBtn()) {
                    PanelUtils.handleInputForStringsPanel(this);
                } else if (addPanelForStrings != null && e.getSource() == addPanelForStrings.getDoneBtn()) {
                    addPanelForStrings.getAdderFrame().setVisible(false);
                    addPanelForStrings.getAdderFrame().setEnabled(false);
                } else if (e.getSource() == viewInfoPanel.viewDatesBtn) {

                    PanelUtils.showDates((parentPanel != null &&
                            parentPanel.getDegreeObjects()[0] instanceof DegreeDateCommon) ? parentPanel : panel);
                } else if (e.getSource() == infoPanel.getSubmitBtn()) {
                    Object o = infoPanel.getObjectInfo();

                    if (o == null)
                        return;


                    StringBuilder textForLabel = new StringBuilder();

                    if (o instanceof DegreeProgram)
                        textForLabel.append(((DegreeProgram) o).getName());

                    else if (o instanceof Course)
                        textForLabel.append(((Course) o).getName());

                    else {
                        textForLabel.append(o.getClass().getSimpleName()).append("-");

                        if (!(o instanceof Question)) {
                            if (addDatePanel != null && addDatePanel.getDates() != null) {

                                Date[] dates = addDatePanel.getDates();

                                if (o instanceof Semester)
                                    ((Semester) o).setImportant_Dates(dates);
                                else if (o instanceof Assignment)
                                    ((Assignment) o).setDue_Date(dates[0]);
                                else if (o instanceof Quiz)
                                    ((Quiz) o).setDue_Date(dates[0]);

                                addDatePanel.getDateFrame().dispose();
                                addDatePanel = null;
                            }
                            if (addPanelForStrings != null && addPanelForStrings.getStoredStrings() != null) {

                                String[] stringsFromPanel = addPanelForStrings.getStoredStrings();

                                for (String s : stringsFromPanel)
                                    if (o instanceof Assignment)
                                        ((Assignment) o).addQuestion(new Question(s));
                                    else
                                        ((Quiz) o).addTopic(new Topic(s));

                                addPanelForStrings.getAdderFrame().dispose();
                                addPanelForStrings = null;

                                Object[] innerDegObj = o instanceof Assignment ? ((Assignment) o).getQuestion() :
                                        ((Quiz) o).getTopics();
                                String labelToUse = o instanceof Assignment ? "Question-" : "Topic-";

                                ((GeneralDegreeObjectPanel) panel.getInnerPanels()[panel.getDegreeObjects().length - 1])
                                        .incrementSetAndAdd(innerDegObj, this, labelToUse);
                            }

                        }
                    }

                    panel.getDegreeObjects()[panel.getDegreeObjects().length - 1] = o;

                    panel.addDegreeObject(this, textForLabel.toString(), 1);

                    if (!(o instanceof DegreeProgram))
                        ((DegreeObjectCommon) parentPanel.getDegreeObjects()[parentPanel.activePanelNo])
                                .setInnerDegreeObjectTo(panel.getDegreeObjects());
                } else if (e.getSource() == mainPanels.optionButtons.btns[GO_BACK]) {
                    mainPanels.subPanels.mainContainerForDegreePanels.goBackOneDepth();
                }
            } else System.out.println("test");
        }

        @Override
        public void mouseClicked(MouseEvent e) {

            mainPanels.subPanels.mainContainerForDegreePanels.handleClick(e);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

            int index;

            if(((JPanel) e.getSource()).getParent().getParent().getParent() instanceof CoursesPanel) {
                index = Integer.parseInt(((JPanel) e.getSource()).getName().split("-")[1]);
                ((CoursesPanel) ((JPanel) e.getSource()).getParent().getParent().getParent()).showInfo(index);
            }
            else {
                index = Integer.parseInt(((JPanel) e.getSource()).getName());
                ((GeneralDegreeObjectPanel)((JPanel) e.getSource()).getParent()).showInfo(index);
            }

            validate();
        }

        @Override
        public void mouseExited(MouseEvent e) {

            int index;

            if(((JPanel) e.getSource()).getParent().getParent().getParent() instanceof CoursesPanel) {
                index = Integer.parseInt(((JPanel) e.getSource()).getName().split("-")[1]);
                ((CoursesPanel) ((JPanel) e.getSource()).getParent().getParent().getParent())
                        .hideInfo(index);
            }
            else {
                index = Integer.parseInt(((JPanel) e.getSource()).getName());
                ((GeneralDegreeObjectPanel)((JPanel) e.getSource()).getParent())
                        .hideInfo(index);
            }

            validate();
        }

}
