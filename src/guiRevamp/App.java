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

        DegreesPanel degreesPanel;
        OptionsPanel optionsPanel;
        ViewInfoPanel viewInfoPanel;
        JButton[] btns = new JButton[] {new JButton("Back"), new JButton("Favourites"), new JButton("Search"),
                        new JButton("Edit"), new JButton("Favourite"), new JButton("Add Note"),
                        new JButton("Add")};
        static GetInfoPanel infoPanel = new GetInfoPanel();
        static DatePanel addDatePanel = null;
        static AddPanelForStrings addPanelForStrings = null;
        public JPanel cardsLayoutTest;
        static String[] strsForCardLayout = {"degree", "semester", "course", "assignment", "quiz", "question", "topics"};
        int curPanelDepth;


        App(){
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(new Dimension(750, 750));
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());


            optionsPanel = new OptionsPanel(getWidth(), getHeight(), this);
            degreesPanel = new DegreesPanel();



            cardsLayoutTest = new JPanel();
            cardsLayoutTest.setLayout(new CardLayout());
            cardsLayoutTest.add(degreesPanel.scrollPane, strsForCardLayout[0]);

            add(cardsLayoutTest, BorderLayout.CENTER);
            add(optionsPanel, BorderLayout.WEST);

            pack();

            setVisible(true);

            validate();
        }

        App(int paramForNoReason){
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            final float[] PANEL_RATIOS = {3F/32, 9F/16, 11F/32};
            final int PANEL_ABOVE_HEIGHT = (int) (PANEL_RATIOS[0]*FRAME_HEIGHT),
                    PANEL_CENTRE_HEIGHT = (int) (PANEL_RATIOS[1]*FRAME_HEIGHT),
                    PANEL_BELOW_HEIGHT = (int) (PANEL_RATIOS[2]*FRAME_HEIGHT);
            ////////////////////////////////////////////////////////////////////////
            JPanel panelAbove = new JPanel(), panelAtCentre = new JPanel(), panelBelow = new JPanel();

            panelAbove.setLayout(new BorderLayout());
            panelAtCentre.setLayout(new BorderLayout());
            panelBelow.setLayout(new BorderLayout());

            panelAbove.setPreferredSize(new Dimension(FRAME_WIDTH, PANEL_ABOVE_HEIGHT));
            panelAtCentre.setPreferredSize(new Dimension(FRAME_WIDTH, PANEL_CENTRE_HEIGHT));
            panelBelow.setPreferredSize(new Dimension(FRAME_WIDTH, PANEL_BELOW_HEIGHT));

            panelAbove.setBackground(new Color(0x771144));
            panelAtCentre.setBackground(new Color(0xAD1A97));
            panelBelow.setBackground(new Color(0xAD528C));

            ////////////////////////////////////////////////////////////////////////
            JPanel panelAboveAbove = new JPanel(), panelAboveBelow = new JPanel(),
                    panelCentreBelow = new JPanel();

            panelAboveBelow.setLayout(new GridBagLayout());
            panelCentreBelow.setLayout(new GridBagLayout());

            panelAboveAbove.setPreferredSize(new Dimension(FRAME_WIDTH, PANEL_ABOVE_HEIGHT/4));
            panelAboveBelow.setPreferredSize(new Dimension(FRAME_WIDTH, 3*PANEL_ABOVE_HEIGHT/4));
            panelCentreBelow.setPreferredSize(new Dimension(FRAME_WIDTH, PANEL_CENTRE_HEIGHT/8));

            panelAboveAbove.setBackground(new Color(0x4A384D));
            panelAboveBelow.setBackground(new Color(0xDABFD2));
            panelCentreBelow.setBackground(new Color(0xECBFD0));


            ////////////////////////////////////////////////////////////////////////

            for(JButton btn : btns)
                btn.addActionListener(this);

            ////////////////////////////////////////////////////////////////////////
            optionsPanel = new OptionsPanel(getWidth(), getHeight(), this);
            degreesPanel = new DegreesPanel();

            viewInfoPanel = new ViewInfoPanel(this);


            cardsLayoutTest = new JPanel();
            cardsLayoutTest.setLayout(new CardLayout());
            cardsLayoutTest.add(degreesPanel.scrollPane, strsForCardLayout[0]);

//            add(cardsLayoutTest, BorderLayout.CENTER);
//            add(optionsPanel, BorderLayout.WEST);


            ////////////////////////////////////////////////////////////////////////

            GridBagConstraints c = new GridBagConstraints();
            c.insets = new Insets(PANEL_ABOVE_HEIGHT/8, (int) Math.ceil(1.3*FRAME_WIDTH/100),
                    PANEL_ABOVE_HEIGHT/8, (int) Math.ceil(1.3*FRAME_WIDTH/100));
            c.gridwidth = FRAME_WIDTH/3;
            c.ipadx = FRAME_WIDTH/16;
            c.ipady = FRAME_HEIGHT/16;
            c.weighty = 1;
            c.weightx = 1;

            c.anchor = GridBagConstraints.LINE_START;
            panelAboveBelow.add(btns[GO_BACK], c);
            c.anchor = GridBagConstraints.CENTER;
            panelAboveBelow.add(btns[FAVS], c);
            c.anchor = GridBagConstraints.LINE_END;
            panelAboveBelow.add(btns[SEARCH], c);

            GridBagConstraints c2 = new GridBagConstraints();
            c2.insets = new Insets(PANEL_CENTRE_HEIGHT/64, (int) Math.ceil(1.3*FRAME_WIDTH/100),
                    PANEL_CENTRE_HEIGHT/64, (int) Math.ceil(1.3*FRAME_WIDTH/100));
            c2.gridwidth = FRAME_WIDTH/4;
            c2.ipadx = FRAME_WIDTH/16;
            c2.ipady = PANEL_CENTRE_HEIGHT/16;
            c2.weighty = 1;
            c2.weightx = 1;

            panelCentreBelow.add(btns[EDIT], c2);
            panelCentreBelow.add(btns[FAV], c2);
            panelCentreBelow.add(btns[ADD_NOTE], c2);
            panelCentreBelow.add(btns[ADD_BTN], c2);

            panelAbove.add(panelAboveAbove, BorderLayout.NORTH);
            panelAbove.add(panelAboveBelow, BorderLayout.CENTER);
            panelAtCentre.add(cardsLayoutTest, BorderLayout.CENTER);
            panelAtCentre.add(panelCentreBelow, BorderLayout.SOUTH);
            panelBelow.add(viewInfoPanel, BorderLayout.CENTER);



            ////////////////////////////////////////////////////////////////////////
            add(panelAbove, BorderLayout.NORTH);
            add(panelAtCentre, BorderLayout.CENTER);
            add(panelBelow, BorderLayout.SOUTH);

            pack();

            setVisible(true);

            validate();
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            GeneralDegreeObjectPanel panel = null, parentPanel = null;
            Object degObj = null;

            SemestersPanel semPanel = null;
            CoursesPanel courPanel = null;
            if(degreesPanel.semestersPanels != null) {
                    semPanel = degreesPanel.semestersPanels[degreesPanel.activePanelNo];

                if (semPanel.coursesPanels != null)
                    courPanel = semPanel.coursesPanels[semPanel.activePanelNo];
            }

            switch (curPanelDepth){
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
                    degObj = courPanel.clickedAssignmentPanel? new Assignment() : new Quiz();
                    break;
                case 4:
                    GeneralDegreeObjectPanel assignmentOrQuizPanel =
                            (GeneralDegreeObjectPanel) courPanel.getInnerPanels()[courPanel.activePanelNo];

                    parentPanel = assignmentOrQuizPanel;
                    panel = (GeneralDegreeObjectPanel) assignmentOrQuizPanel
                            .getInnerPanels()[assignmentOrQuizPanel.activePanelNo];
                    degObj = courPanel.clickedAssignmentPanel? new Question() : new Topic();
                    break;
                default:
                    System.out.println("Error! Unknown depth. Cannot exceed 5.");
            }

            if(panel == null)
                return;

            if(e.getSource() instanceof  JButton) {
                String btnText = ((JButton) e.getSource()).getText().toLowerCase();
                 if (e.getSource() == infoPanel.getDatesBtn()) {
                    PanelUtils.handleInputDatePanel(this);
                }
                 else if (e.getSource() == btns[ADD_BTN]) {
                     panel.incrementSizeBy(1);
                     infoPanel = new GetInfoPanel(degObj, this);
                 }
                 else if (addDatePanel != null && e.getSource() == addDatePanel.getDoneBtn()) {
                    addDatePanel.getDateFrame().setVisible(false);
                    addDatePanel.getDateFrame().setEnabled(false);
                }
                 else if (e.getSource() == infoPanel.getSetBtn()) {
                    PanelUtils.handleInputForStringsPanel(this);
                }
                 else if (addPanelForStrings != null && e.getSource() == addPanelForStrings.getDoneBtn()) {
                    addPanelForStrings.getAdderFrame().setVisible(false);
                    addPanelForStrings.getAdderFrame().setEnabled(false);
                }
                 else if (e.getSource() == optionsPanel.viewDatesBtn) {

                     PanelUtils.showDates((parentPanel != null &&
                             parentPanel.getDegreeObjects()[0] instanceof DegreeDateCommon)? parentPanel : panel);
                }
                 else if (e.getSource() == infoPanel.getSubmitBtn()) {
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

                                 Object[] innerDegObj = o instanceof Assignment? ((Assignment) o).getQuestion() :
                                         ((Quiz) o).getTopics();
                                 String labelToUse = o instanceof Assignment? "Question-" : "Topic-";

                                 ((GeneralDegreeObjectPanel) panel.getInnerPanels()[panel.getDegreeObjects().length-1])
                                         .incrementSetAndAdd(innerDegObj, this, labelToUse);
                             }

                         }
                     }

                     panel.getDegreeObjects()[panel.getDegreeObjects().length - 1] = o;

                     panel.addDegreeObject(this, textForLabel.toString(), 1);

                     if (!(o instanceof DegreeProgram))
                         ((DegreeObjectCommon) parentPanel.getDegreeObjects()[parentPanel.activePanelNo])
                                 .setInnerDegreeObjectTo(panel.getDegreeObjects());
                 }
                 else if (e.getSource() == btns[GO_BACK]) {

                    if (curPanelDepth >= 1) {
                        GeneralDegreeObjectPanel toGoTo, toGoFrom;

                        if (curPanelDepth >= 3)
                            courPanel = semPanel.coursesPanels[semPanel.activePanelNo];

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

                        toGoFrom = (GeneralDegreeObjectPanel) toGoTo.getInnerPanels()[toGoTo.activePanelNo];

                        PanelUtils.goBackOneDepth(toGoTo, toGoFrom, this,
                                courPanel != null && courPanel.clickedAssignmentPanel);
                        validate();
                        curPanelDepth--;
                    }
                } else System.out.println("test");
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {

            GeneralDegreeObjectPanel toGoFrom, toGoTo;
            boolean clickedAssignmentPanel;
            int index;

            ++curPanelDepth;


            if(((JPanel) e.getSource()).getParent().getParent().getParent() instanceof CoursesPanel){

                toGoFrom = (GeneralDegreeObjectPanel) ((JPanel) e.getSource()).getParent().getParent().getParent();
                index = Integer.parseInt(((JPanel) e.getSource()).getName().split("-")[1]);

                ((CoursesPanel) toGoFrom).clickedAssignmentPanel =
                        e.getSource() == ((CoursesPanel) toGoFrom).goToAssignmentPanel[index];

                toGoTo = (GeneralDegreeObjectPanel) toGoFrom.getInnerPanels()[index];
                clickedAssignmentPanel = ((CoursesPanel) toGoFrom).clickedAssignmentPanel;
            }
            else {
                index = Integer.parseInt(((JPanel) e.getSource()).getName());
                clickedAssignmentPanel = ((JPanel) e.getSource()).getParent() instanceof AssignmentsPanel;

                toGoFrom = (GeneralDegreeObjectPanel) ((JPanel) e.getSource()).getParent();
                toGoTo = (GeneralDegreeObjectPanel) toGoFrom.getInnerPanels()[index];
            }

            PanelUtils.goIntoNextDepth(toGoFrom, toGoTo, this,
                    index, clickedAssignmentPanel);

            validate();
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
