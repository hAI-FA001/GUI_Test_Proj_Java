package degreeObjectsPanels;

import degreeObjects.Quiz;

import javax.swing.*;
import java.awt.*;

public class QuizzesPanel extends GeneralDegreeObjectPanel{
    public Quiz[] quizzes;

    public TopicsPanel[] topicPanels;

    QuizzesPanel(int parentPanelNo){
        super();

        this.parentPanelNo = parentPanelNo;
    }

    @Override
    public void incrementSizeBy(int size) {
        super.incrementSizeBy(size);

        if(quizzes == null){
            quizzes = new Quiz[size];
            topicPanels = new TopicsPanel[size];

            for(int i=0; i < quizzes.length; i++){
                quizzes[i] = new Quiz();
                topicPanels[i] = new TopicsPanel(i);
            }
        }
        else {
            Quiz[] quizzes = new Quiz[this.quizzes.length + size];
            TopicsPanel[] topicPanels = new TopicsPanel[this.topicPanels.length + size];

            System.arraycopy(this.quizzes, 0, quizzes, 0, this.quizzes.length);
            System.arraycopy(this.topicPanels, 0, topicPanels, 0, this.topicPanels.length);

            for(int i=this.quizzes.length; i < quizzes.length; i++){
                quizzes[i] = new Quiz();
                topicPanels[i] = new TopicsPanel(i);
            }

            this.quizzes = quizzes;
            this.topicPanels = topicPanels;
        }
    }

    public Quiz[] getDegreeObjects(){
        return quizzes;
    }

    @Override
    public Object[] getInnerPanels() {
        return topicPanels;
    }

    @Override
    public void showInfo(int index){
        super.showInfo(index);
        infoLabelOnHover = new JLabel(
                String.format("<html><pre> %s<br/> %s</pre></html>",
                        "Due On " + quizzes[index].getDue_Date(),
                        (quizzes[index].getTopics()!=null)?
                                quizzes[index].getTopics().length + " Topics" : "0 Topics")
        );
        infoLabelOnHover.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        infoLabelOnHover.setForeground(new Color(0x774455));
        infoLabelOnHover.setHorizontalAlignment(SwingConstants.LEADING);
        infoLabelOnHover.setVerticalAlignment(SwingConstants.CENTER);
        infoLabelOnHover.setPreferredSize(new Dimension(250, 40));

        degreeObjectPanels[index].add(infoLabelOnHover, BorderLayout.CENTER);
        validate();
    }

    @Override
    public void hideInfo(int index){
        super.hideInfo(index);
        degreeObjectPanels[index].remove(infoLabelOnHover);
        validate();
        repaint();
    }
}
