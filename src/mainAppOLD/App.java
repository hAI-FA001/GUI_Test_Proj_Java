package mainAppOLD;

import degreeObjects.*;
import degreeObjectsPanels.*;
import morePanels.DatePanel;
import morePanels.GetInfoPanel;
import morePanels.AddPanelForStrings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class App extends JFrame implements ActionListener, MouseListener {

    DegreesPanel degreesPanel;
    OptionsPanel optionsPanel;
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

        //////////////////////////////////////////////////////////////////////////////////////
        optionsPanel = new OptionsPanel(getWidth(), getHeight(), this);
        degreesPanel = new DegreesPanel();



        cardsLayoutTest = new JPanel();
        cardsLayoutTest.setLayout(new CardLayout());
        cardsLayoutTest.add(degreesPanel.scrollPane, strsForCardLayout[0]);

//        add(degreesPanel.scrollPane, BorderLayout.CENTER);
        add(cardsLayoutTest, BorderLayout.CENTER);
        add(optionsPanel, BorderLayout.WEST);

        pack();

        setVisible(true);

        validate();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == optionsPanel.addDegreeBtn){
            degreesPanel.incrementSizeBy(1);
            infoPanel = new GetInfoPanel(
                    new DegreeProgram(null, 0, null, null),
                    this);
        }
        else if(e.getSource() == optionsPanel.addSemesterBtn){
            degreesPanel.semestersPanels[degreesPanel.activePanelNo].incrementSizeBy(1);
            infoPanel = new GetInfoPanel(
                    new Semester(null, 0, null, null),
                    this);
        }
        else if(e.getSource() == optionsPanel.addCourseBtn){
            SemestersPanel semesterPanel = degreesPanel.semestersPanels[degreesPanel.activePanelNo];
            semesterPanel.coursesPanels[semesterPanel.activePanelNo].incrementSizeBy(1);
            infoPanel = new GetInfoPanel(
                    new Course("","","",0,null,null),
                    this);
        }
        else if(e.getSource() == optionsPanel.addAssignmentBtn){
            CoursesPanel coursePanel = degreesPanel.semestersPanels[degreesPanel.activePanelNo]
                    .coursesPanels[degreesPanel.semestersPanels[degreesPanel.activePanelNo].activePanelNo];

            coursePanel.assignmentsPanels[coursePanel.activePanelNo].incrementSizeBy(1);
            infoPanel = new GetInfoPanel(
                    new Assignment(),
                    this
            );
        }
        else if(e.getSource() == optionsPanel.addQuizBtn){
            CoursesPanel coursePanel = degreesPanel.semestersPanels[degreesPanel.activePanelNo]
                    .coursesPanels[degreesPanel.semestersPanels[degreesPanel.activePanelNo].activePanelNo];

            coursePanel.quizzesPanels[coursePanel.activePanelNo].incrementSizeBy(1);
            infoPanel = new GetInfoPanel(
                    new Quiz(),
                    this
            );
        }
        else if(e.getSource() == optionsPanel.addQuestionBtn){
            AssignmentsPanel assignmentPanel = degreesPanel.semestersPanels[degreesPanel.activePanelNo]
                    .coursesPanels[degreesPanel.semestersPanels[degreesPanel.activePanelNo].activePanelNo]
                    .assignmentsPanels[degreesPanel.semestersPanels[degreesPanel.activePanelNo]
                    .coursesPanels[degreesPanel.semestersPanels[degreesPanel.activePanelNo].activePanelNo].activePanelNo];

            assignmentPanel.questionsPanels[assignmentPanel.activePanelNo].incrementSizeBy(1);

            infoPanel = new GetInfoPanel(
                    new Question(),
                    this
            );
        }
        else if(e.getSource() == infoPanel.getDatesBtn()){
            if(addDatePanel == null)
                addDatePanel = new DatePanel(this);
            else {
                addDatePanel.getDateFrame().setVisible(true);
                addDatePanel.getDateFrame().setEnabled(true);
            }
        }
        else if(addDatePanel != null && e.getSource() == addDatePanel.getDoneBtn()){
            addDatePanel.getDateFrame().setVisible(false);
            addDatePanel.getDateFrame().setEnabled(false);
        }
        else if(e.getSource() == infoPanel.getSetBtn()){
            if(addPanelForStrings == null)
                addPanelForStrings = new AddPanelForStrings(this, "Question");
            else {
                addPanelForStrings.getAdderFrame().setVisible(true);
                addPanelForStrings.getAdderFrame().setEnabled(true);
            }
        }
        else if(addPanelForStrings != null && e.getSource() == addPanelForStrings.getDoneBtn()){
            addPanelForStrings.getAdderFrame().setVisible(false);
            addPanelForStrings.getAdderFrame().setEnabled(false);
        }
        else if(e.getSource() == optionsPanel.viewDatesBtn){
            new DatePanel(
                    degreesPanel.semestersPanels[degreesPanel.activePanelNo]
                            .semesters[degreesPanel.semestersPanels[degreesPanel.activePanelNo].activePanelNo]
                            .getImportant_Dates()
            );
        }
        else if(e.getSource() == infoPanel.getSubmitBtn()){
            Object o = infoPanel.getObjectInfo();

            if(o == null)
                return;

            switch(o.getClass().getSimpleName()) {
                case "DegreeProgram": {
                    degreesPanel.degreePrograms[degreesPanel.degreePrograms.length - 1]
                            = (DegreeProgram) o;
                    degreesPanel.addDegreeObject(this, degreesPanel.degreePrograms[degreesPanel.degreePrograms.length-1].getName(), 1);
                }
                break;
                case "Semester": {
                    SemestersPanel semesterPanel = degreesPanel.semestersPanels[degreesPanel.activePanelNo];

                    semesterPanel.semesters[semesterPanel.semesters.length-1] = (Semester) o;

                    if(addDatePanel != null && addDatePanel.getDates() != null && addDatePanel.getDates().length > 0) {
                        (semesterPanel.semesters[semesterPanel.semesters.length - 1])
                                .setImportant_Dates(addDatePanel.getDates());
                        addDatePanel.getDateFrame().dispose();
                        addDatePanel = null;
                    }

                    semesterPanel.addDegreeObject(this, "Semester-" +
                            String.valueOf((semesterPanel.semesters[semesterPanel.semesters.length-1]).getSemester_No()), 1);

                    degreesPanel.degreePrograms[degreesPanel.activePanelNo].setSemesters(semesterPanel.semesters);

                    validate();
                }
                break;
                case "Course": {
                    CoursesPanel coursePanel = degreesPanel.semestersPanels[degreesPanel.activePanelNo]
                    .coursesPanels[degreesPanel.semestersPanels[degreesPanel.activePanelNo].activePanelNo];

                    coursePanel.courses[coursePanel.courses.length-1] = (Course) o;

                    coursePanel.addDegreeObject(this, coursePanel.courses[coursePanel.courses.length-1].getName(), 1);

                    degreesPanel.semestersPanels[degreesPanel.activePanelNo]
                            .semesters[degreesPanel.semestersPanels[degreesPanel.activePanelNo].activePanelNo]
                            .setCourses(coursePanel.courses);

                    validate();
                }
                break;
                case "Assignment": {
                    CoursesPanel coursePanel = degreesPanel.semestersPanels[degreesPanel.activePanelNo]
                            .coursesPanels[degreesPanel.semestersPanels[degreesPanel.activePanelNo].activePanelNo];

                    if(addPanelForStrings != null && addPanelForStrings.getStoredStrings() != null
                            && addPanelForStrings.getStoredStrings().length > 0) {

                        Question[] questions = new Question[addPanelForStrings.getStoredStrings().length];

                        for(int i=0; i < questions.length; i++)
                            questions[i] = new Question(addPanelForStrings.getStoredStrings()[i]);

                        coursePanel.assignmentsPanels[coursePanel.activePanelNo]
                                .assignments[coursePanel.assignmentsPanels[coursePanel.activePanelNo].activePanelNo]
                                .setQuestion(questions);

                        addPanelForStrings.getAdderFrame().dispose();
                        addPanelForStrings = null;
                    }
                    if(addDatePanel != null && addDatePanel.getDates() != null && addDatePanel.getDates().length > 0) {

                        coursePanel.assignmentsPanels[coursePanel.activePanelNo]
                                .assignments[coursePanel.assignmentsPanels[coursePanel.activePanelNo].activePanelNo]
                                .setDue_Date(addDatePanel.getDates()[0]);

                        addDatePanel.getDateFrame().dispose();
                        addDatePanel = null;
                    }

                    coursePanel.assignmentsPanels[coursePanel.activePanelNo]
                            .addDegreeObject(this, "Assignment-" +
                                    coursePanel.assignmentsPanels[coursePanel.activePanelNo]
                                            .assignments.length, 1);

                    coursePanel.courses[coursePanel.activePanelNo].setAssignments(
                            coursePanel.assignmentsPanels[coursePanel.activePanelNo].assignments
                    );

                    validate();
                }
                break;
                case "Quiz": {

                    CoursesPanel coursePanel = degreesPanel.semestersPanels[degreesPanel.activePanelNo]
                            .coursesPanels[degreesPanel.semestersPanels[degreesPanel.activePanelNo].activePanelNo];

                    if(addPanelForStrings != null && addPanelForStrings.getStoredStrings() != null
                            && addPanelForStrings.getStoredStrings().length > 0) {

                        String[] strs = addPanelForStrings.getStoredStrings();

                        Topic[] topics = new Topic[strs.length];

                        for(int i=0; i < strs.length; i++)
                            topics[i] = new Topic(strs[i]);

                        coursePanel.quizzesPanels[coursePanel.activePanelNo]
                                .quizzes[coursePanel.assignmentsPanels[coursePanel.activePanelNo].activePanelNo]
                                .setTopics(topics);

                        addPanelForStrings.getAdderFrame().dispose();
                        addPanelForStrings = null;
                    }
                    if(addDatePanel != null && addDatePanel.getDates() != null && addDatePanel.getDates().length > 0) {

                        coursePanel.quizzesPanels[coursePanel.activePanelNo]
                                .quizzes[coursePanel.assignmentsPanels[coursePanel.activePanelNo].activePanelNo]
                                .setDue_Date(addDatePanel.getDates()[0]);

                        addDatePanel.getDateFrame().dispose();
                        addDatePanel = null;
                    }

                    coursePanel.addDegreeObject(this, "Quiz-"+
                            coursePanel.quizzesPanels[coursePanel.activePanelNo].quizzes.length, 1
                    );

                    coursePanel.courses[coursePanel.activePanelNo].setQuizzes(
                            coursePanel.quizzesPanels[coursePanel.activePanelNo].quizzes
                    );

                    validate();
                }
                break;
                case "Question": {
                    CoursesPanel coursePanel = degreesPanel.semestersPanels[degreesPanel.activePanelNo]
                            .coursesPanels[degreesPanel.semestersPanels[degreesPanel.activePanelNo].activePanelNo];

                    QuestionsPanel questionPanel = coursePanel.assignmentsPanels[coursePanel.activePanelNo]
                            .questionsPanels[coursePanel.assignmentsPanels[coursePanel.activePanelNo].activePanelNo];

                    questionPanel.questions[questionPanel.activePanelNo] = (Question) o;

                    questionPanel.addDegreeObject(this, "Question-"+
                            questionPanel.questions.length, 1);

                    coursePanel.courses[coursePanel.activePanelNo]
                            .getAssignments()[coursePanel.activePanelNo].setQuestion(
                                    questionPanel.questions
                    );

                    validate();
                }
                break;
                default:
                    System.out.println("Unknown object returned");
            }
        }
        else if(e.getSource() == optionsPanel.goBackBtn){

            if(curPanelDepth == 1) {

                PanelUtils.goBackOneDepth(degreesPanel, degreesPanel.semestersPanels[degreesPanel.activePanelNo],
                        this, false);
                validate();
                curPanelDepth = 0;
                //scrollPane.setViewportView(degreesPanel);
            }
            else if(curPanelDepth == 2){
                SemestersPanel semesterPanel = degreesPanel.semestersPanels[degreesPanel.activePanelNo];
                CoursesPanel coursePanel = semesterPanel.coursesPanels[semesterPanel.activePanelNo];

                PanelUtils.goBackOneDepth(semesterPanel, coursePanel, this, false);
                validate();
                curPanelDepth = 1;
                //scrollPane.setViewportView(semesterPanel);
            }
            else if(curPanelDepth == 3){

                SemestersPanel semesterPanel = degreesPanel.semestersPanels[degreesPanel.activePanelNo];

                CoursesPanel coursePanel = semesterPanel.coursesPanels[semesterPanel.activePanelNo];
                GeneralDegreeObjectPanel assignmentOrQuizPanel = (coursePanel.clickedAssignmentPanel)?
                        coursePanel.assignmentsPanels[coursePanel.activePanelNo] :
                        coursePanel.quizzesPanels[coursePanel.activePanelNo];

                PanelUtils.goBackOneDepth(coursePanel, assignmentOrQuizPanel, this, false);
                validate();
                curPanelDepth = 2;
                //scrollPane.setViewportView(coursePanel);
            }
            else if(curPanelDepth == 4){

                SemestersPanel semesterPanel = degreesPanel.semestersPanels[degreesPanel.activePanelNo];
                CoursesPanel coursePanel = semesterPanel.coursesPanels[semesterPanel.activePanelNo];

                GeneralDegreeObjectPanel assignmentOrQuizPanel,
                                         questionOrTopicPanel;
                boolean isInQuestionsPanel;

                if(coursePanel.clickedAssignmentPanel) {
                    assignmentOrQuizPanel = coursePanel.assignmentsPanels[coursePanel.activePanelNo];
                    questionOrTopicPanel = ((AssignmentsPanel)assignmentOrQuizPanel)
                            .questionsPanels[assignmentOrQuizPanel.activePanelNo];
                    isInQuestionsPanel = true;
                }
                else {
                    assignmentOrQuizPanel = coursePanel.quizzesPanels[coursePanel.activePanelNo];
                    questionOrTopicPanel = ((QuizzesPanel)assignmentOrQuizPanel)
                            .topicPanels[assignmentOrQuizPanel.activePanelNo];
                    isInQuestionsPanel = false;
                }

                PanelUtils.goBackOneDepth(assignmentOrQuizPanel, questionOrTopicPanel,
                        this, isInQuestionsPanel);
                validate();
                curPanelDepth = 3;
                //scrollPane.setViewportView(assignmentPanel);
            }
        }
        else System.out.println("test");
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        JPanel panelSrc = (JPanel) e.getSource();

        JPanel panelParent = (JPanel) panelSrc.getParent();

        String panelParentName = panelParent.getClass().getSimpleName().toLowerCase();
        String panelGrandParentName = panelParent.getParent().getClass().getSimpleName().toLowerCase();
        String panelGreatGrandParentName = panelParent.getParent().getParent().getClass().getSimpleName().toLowerCase();


        if(panelGrandParentName.contains("viewport")){
            if(panelParentName.contains("deg")){
                curPanelDepth = 1;
                PanelUtils.goIntoNextDepth((DegreesPanel)panelParent,
                        ((DegreesPanel)panelParent).semestersPanels[Integer.parseInt(panelSrc.getName())],
                        this, Integer.parseInt(panelSrc.getName()), false);
                validate();
            }
            else if(panelParentName.contains("sem")){
                curPanelDepth = 2;
                PanelUtils.goIntoNextDepth((SemestersPanel)panelParent,
                        ((SemestersPanel)panelParent).coursesPanels[Integer.parseInt(panelSrc.getName())],
                        this, Integer.parseInt(panelSrc.getName()), false);
                validate();
            }
            else if(panelParentName.contains("assign")){
                curPanelDepth = 4;
                PanelUtils.goIntoNextDepth((AssignmentsPanel)panelParent,
                        ((AssignmentsPanel)panelParent).questionsPanels[Integer.parseInt(panelSrc.getName())],
                        this, Integer.parseInt(panelSrc.getName()), true);
                validate();
            }
            else if(panelParentName.contains("quiz")){
                curPanelDepth = 4;
                PanelUtils.goIntoNextDepth((QuizzesPanel)panelParent,
                        ((QuizzesPanel)panelParent).topicPanels[Integer.parseInt(panelSrc.getName())],
                        this, Integer.parseInt(panelSrc.getName()), false);
                validate();
            }
            else if(panelParentName.contains("ques")){
                System.out.println("question panel");
            }
            else if(panelParentName.contains("top")){
                System.out.println("topics panel");
            }
        }
        else if(panelGreatGrandParentName.contains("course")){

            curPanelDepth = 3;

            if(panelSrc.getName().toLowerCase().contains("assignment")) {
                ((CoursesPanel)panelParent.getParent().getParent()).clickedAssignmentPanel = true;
                PanelUtils.goIntoNextDepth((CoursesPanel) panelParent.getParent().getParent(),
                        ((CoursesPanel) panelParent.getParent().getParent()).assignmentsPanels[
                                Integer.parseInt(panelSrc.getName().split("-")[1])],
                        this,
                        Integer.parseInt(panelSrc.getName().split("-")[1]),
                        true);
            }
            else {
                ((CoursesPanel)panelParent.getParent().getParent()).clickedAssignmentPanel = false;
                PanelUtils.goIntoNextDepth((CoursesPanel) panelParent.getParent().getParent(),
                        ((CoursesPanel) panelParent.getParent().getParent()).quizzesPanels[
                                Integer.parseInt(panelSrc.getName().split("-")[1])],
                        this,
                        Integer.parseInt(panelSrc.getName().split("-")[1]),
                        false);
            }

            validate();
        }
        else
            System.out.println("Unknown? component clicked\n"+panelGrandParentName+"\n"+panelParentName);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

        JPanel panelSrc = (JPanel) e.getSource();

        JPanel panelParent = (JPanel) panelSrc.getParent();

        String panelParentName = panelParent.getClass().getSimpleName().toLowerCase();
        String panelGrandParentName = panelParent.getParent().getClass().getSimpleName().toLowerCase();
        String panelGreatGrandParentName = panelParent.getParent().getParent().getClass().getSimpleName().toLowerCase();


        if(panelGrandParentName.contains("viewport")){
            ((GeneralDegreeObjectPanel)panelParent).showInfo(Integer.parseInt(panelSrc.getName()));
            validate();
        }
        else if(panelGreatGrandParentName.contains("course")){
            ((GeneralDegreeObjectPanel)panelParent.getParent().getParent()).showInfo(Integer.parseInt(
                    panelSrc.getName().split("-")[1]
            ));
            validate();
        }
        else
            System.out.println("Unknown? component entered\n"+panelGrandParentName+"\n"+panelParentName);
    }

    @Override
    public void mouseExited(MouseEvent e) {

        JPanel panelSrc = (JPanel) e.getSource();

        JPanel panelParent = (JPanel) panelSrc.getParent();

        String panelParentName = panelParent.getClass().getSimpleName().toLowerCase();
        String panelGrandParentName = panelParent.getParent().getClass().getSimpleName().toLowerCase();
        String panelGreatGrandParentName = panelParent.getParent().getParent().getClass().getSimpleName().toLowerCase();


        if(panelGrandParentName.contains("viewport")){
            ((GeneralDegreeObjectPanel)panelParent).hideInfo(Integer.parseInt(panelSrc.getName()));
            validate();
        }
        else if(panelGreatGrandParentName.contains("course")){
            ((GeneralDegreeObjectPanel)panelParent.getParent().getParent()).hideInfo(Integer.parseInt(
                    panelSrc.getName().split("-")[1]
            ));
            validate();
        }
        else
            System.out.println("Unknown? component exited\n"+panelGrandParentName+"\n"+panelParentName);
    }
}
