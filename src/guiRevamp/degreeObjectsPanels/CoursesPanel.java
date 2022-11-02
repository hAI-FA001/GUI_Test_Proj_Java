package guiRevamp.degreeObjectsPanels;

import degreeObjects.Course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class CoursesPanel extends GeneralDegreeObjectPanel {
    public Course[] courses;

    public AssignmentsPanel[] assignmentsPanels;
    public QuizzesPanel[] quizzesPanels;
    public JPanel[] goToAssignmentPanel, goToQuizPanel, containerForGoTo;
    JLabel assignmentInfoLabelOnHover, quizInfoLabelOnHover;
    public boolean clickedAssignmentPanel;

    CoursesPanel(int parentPanelNo){
        super();

        this.parentPanelNo = parentPanelNo;
    }
    @Override
    public void addDegreeObject(JFrame parentFrame, String labelText, int numAdded) {
        super.addDegreeObject(parentFrame, labelText, numAdded);

        for(int i= degreeObjectPanels.length - numAdded; i < degreeObjectPanels.length; i++){

            JLabel tempLabel1 = new JLabel("Assignments"), tempLabel2 = new JLabel("Quizzes");
            tempLabel1.setForeground(new Color(0xDDAAFF));
            tempLabel1.setHorizontalAlignment(SwingConstants.CENTER);
            tempLabel2.setForeground(new Color(0xDDAAFF));
            tempLabel2.setHorizontalAlignment(SwingConstants.CENTER);

            containerForGoTo[i].setName("goToContainer-"+i);
            containerForGoTo[i].setBackground(new Color(0x000000, true));
            containerForGoTo[i].setLayout(new BorderLayout());

            goToAssignmentPanel[i].setName("goToAssignment-"+i);
            goToAssignmentPanel[i].setBackground(new Color(0x000000, true));
            goToAssignmentPanel[i].setLayout(new BorderLayout());
            goToAssignmentPanel[i].addMouseListener((MouseListener) parentFrame);
            goToAssignmentPanel[i].setPreferredSize(new Dimension(100,61));
            goToAssignmentPanel[i].add(tempLabel1, BorderLayout.NORTH);

            goToQuizPanel[i].setName("goToQuiz-"+i);
            goToQuizPanel[i].setBackground(new Color(0x331144));
            goToQuizPanel[i].setBackground(new Color(0x000000, true));
            goToQuizPanel[i].addMouseListener((MouseListener) parentFrame);
            goToQuizPanel[i].setPreferredSize(new Dimension(100,61));
            goToQuizPanel[i].add(tempLabel2, BorderLayout.NORTH);

            containerForGoTo[i].add(goToAssignmentPanel[i], BorderLayout.WEST);
            containerForGoTo[i].add(goToQuizPanel[i], BorderLayout.EAST);

            degreeObjectPanels[i].add(containerForGoTo[i], BorderLayout.SOUTH);
        }

        validate();
    }

    @Override
    public void incrementSizeBy(int size) {
        super.incrementSizeBy(size);

        if(courses == null){
            courses = new Course[size];
            assignmentsPanels = new AssignmentsPanel[size];
            quizzesPanels = new QuizzesPanel[size];
            goToAssignmentPanel = new JPanel[size];
            goToQuizPanel = new JPanel[size];
            containerForGoTo = new JPanel[size];

            for(int i=0; i < courses.length; i++) {
                courses[i] = new Course(null, null, null, 0, null, null);
                assignmentsPanels[i] = new AssignmentsPanel(i);
                quizzesPanels[i] = new QuizzesPanel(i);
                goToAssignmentPanel[i] = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);

                        Graphics2D g2d = (Graphics2D) g;
                        GradientPaint gp = new GradientPaint(0, 0,
                                new Color(0xBBCC88AA, true), getWidth(), getHeight()/2,
                                new Color(0x55AA0077, true));
                        g2d.setPaint(gp);
                        g2d.fillRect(0, 0, getWidth(), getHeight());
                    }
                };
                goToQuizPanel[i] = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);

                        Graphics2D g2d = (Graphics2D) g;
                        GradientPaint gp = new GradientPaint(0, 0,
                                new Color(0x55AA0077, true), getWidth(), getHeight()/2,
                                new Color(0xBBCC88AA, true));
                        g2d.setPaint(gp);
                        g2d.fillRect(0, 0, getWidth(), getHeight());
                    }
                };
                containerForGoTo[i] = new JPanel();
            }
        }
        else {
            Course[] courses = new Course[this.courses.length + size];
            AssignmentsPanel[] assignmentsPanels = new AssignmentsPanel[this.assignmentsPanels.length + size];
            QuizzesPanel[] quizzesPanels = new QuizzesPanel[this.quizzesPanels.length + size];
            JPanel[] goToAssignmentPanel = new JPanel[this.goToAssignmentPanel.length + size];
            JPanel[] goToQuizPanel = new JPanel[this.goToQuizPanel.length + size];
            JPanel[] containerForGoTo = new JPanel[this.containerForGoTo.length + size];

            System.arraycopy(this.courses, 0, courses, 0, this.courses.length);
            System.arraycopy(this.assignmentsPanels, 0, assignmentsPanels, 0, this.assignmentsPanels.length);
            System.arraycopy(this.quizzesPanels, 0, quizzesPanels, 0, this.quizzesPanels.length);
            System.arraycopy(this.goToAssignmentPanel, 0, goToAssignmentPanel, 0, this.goToAssignmentPanel.length);
            System.arraycopy(this.goToQuizPanel, 0, goToQuizPanel, 0, this.goToQuizPanel.length);
            System.arraycopy(this.containerForGoTo, 0, containerForGoTo, 0, this.containerForGoTo.length);

            for(int i=this.courses.length; i < courses.length; i++) {
                courses[i] = new Course(null, null, null, 0, null, null);
                assignmentsPanels[i] = new AssignmentsPanel(i);
                quizzesPanels[i] = new QuizzesPanel(i);
                goToAssignmentPanel[i] = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);

                        Graphics2D g2d = (Graphics2D) g;
                        GradientPaint gp = new GradientPaint(0, 0,
                                new Color(0xBBCC88AA, true), getWidth(), getHeight()/2,
                                new Color(0x55AA0077, true));
                        g2d.setPaint(gp);
                        g2d.fillRect(0, 0, getWidth(), getHeight());
                    }
                };
                goToQuizPanel[i] = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);

                        Graphics2D g2d = (Graphics2D) g;
                        GradientPaint gp = new GradientPaint(0, 0,
                                new Color(0x55AA0077, true), getWidth(), getHeight()/2,
                                new Color(0xBBCC88AA, true));
                        g2d.setPaint(gp);
                        g2d.fillRect(0, 0, getWidth(), getHeight());
                    }
                };
                containerForGoTo[i] = new JPanel();
            }

            this.courses = courses;
            this.assignmentsPanels = assignmentsPanels;
            this.quizzesPanels = quizzesPanels;
            this.goToAssignmentPanel = goToAssignmentPanel;
            this.goToQuizPanel = goToQuizPanel;
            this.containerForGoTo = containerForGoTo;
        }
    }

    public Course[] getDegreeObjects(){
        return courses;
    }

    @Override
    public Object[] getInnerPanels() {
        return clickedAssignmentPanel? assignmentsPanels : quizzesPanels;
    }
    public Object[][] getBothInnerPanels(){
        return new Object[][] {assignmentsPanels, quizzesPanels};
    }

    @Override
    public void showInfo(int index){
        super.showInfo(index);

        assignmentInfoLabelOnHover = new JLabel((courses[index].getAssignments()!=null)?
                "   " + courses[index].getAssignments().length + " Assignments" : "   0 Assignments");

        quizInfoLabelOnHover = new JLabel((courses[index].getQuizzes()!=null)?
                "   " + courses[index].getQuizzes().length + " Quizzes" : "   0 Quizzes");

        infoLabelOnHover = new JLabel(
                String.format("<html><pre> %s<br/> %s</pre></html>",
                        courses[index].getCode(), courses[index].getCredit_hrs() + " Hrs")
        );

        Font font1 = new Font(Font.SERIF, Font.PLAIN, 20), font2 = new Font(Font.SERIF, Font.PLAIN, 12);
        infoLabelOnHover.setFont(font1);
        infoLabelOnHover.setForeground(new Color(0x774455));
        infoLabelOnHover.setHorizontalAlignment(SwingConstants.LEADING);
        infoLabelOnHover.setVerticalAlignment(SwingConstants.CENTER);
//        infoLabelOnHover.setPreferredSize(new Dimension(10, 40));

        assignmentInfoLabelOnHover.setFont(font2);
        assignmentInfoLabelOnHover.setForeground(new Color(0x556677));
        assignmentInfoLabelOnHover.setHorizontalAlignment(infoLabelOnHover.getHorizontalAlignment());
        assignmentInfoLabelOnHover.setVerticalAlignment(infoLabelOnHover.getVerticalAlignment());
//        assignmentInfoLabelOnHover.setPreferredSize(infoLabelOnHover.getPreferredSize());

        quizInfoLabelOnHover.setFont(font2);
        quizInfoLabelOnHover.setForeground(new Color(0x556677));
        quizInfoLabelOnHover.setHorizontalAlignment(infoLabelOnHover.getHorizontalAlignment());
        quizInfoLabelOnHover.setVerticalAlignment(infoLabelOnHover.getVerticalAlignment());
//        quizInfoLabelOnHover.setPreferredSize(infoLabelOnHover.getPreferredSize());

        degreeObjectPanels[index].add(infoLabelOnHover, BorderLayout.CENTER);
        goToAssignmentPanel[index].add(assignmentInfoLabelOnHover, BorderLayout.CENTER);
        goToQuizPanel[index].add(quizInfoLabelOnHover, BorderLayout.CENTER);
        validate();
    }

    @Override
    public void hideInfo(int index){
        super.hideInfo(index);
        degreeObjectPanels[index].remove(infoLabelOnHover);
        goToAssignmentPanel[index].remove(assignmentInfoLabelOnHover);
        goToQuizPanel[index].remove(quizInfoLabelOnHover);
        validate();
        repaint();
    }
}
