package guiRevamp.degreeObjectsPanels;

import degreeObjects.Topic;

import javax.swing.*;
import java.awt.*;

public class TopicsPanel extends GeneralDegreeObjectPanel {
    Topic[] topics;

    TopicsPanel(int parentPanelNo){
        super();

        this.parentPanelNo = parentPanelNo;
    }

    @Override
    public void incrementSizeBy(int size) {
        super.incrementSizeBy(size);

        if(topics == null){
            topics = new Topic[size];

            for(int i=0; i < topics.length; i++){
                topics[i] = new Topic();
            }
        }
        else {
            Topic[] topics = new Topic[this.topics.length + size];

            System.arraycopy(this.topics, 0, topics, 0, this.topics.length);

            for(int i=this.topics.length; i < topics.length; i++){
                topics[i] = new Topic();
            }

            this.topics = topics;
        }
    }

    public Topic[] getDegreeObjects(){
        return topics;
    }

    @Override
    void setDegreeObjects(Object[] o) {
        if(this.topics.length == o.length)
            this.topics = (Topic[]) o;
        else {
            for(int i=this.topics.length-o.length; i < this.topics.length; i++)
                this.topics[i] = (Topic) o[i];
        }
    }

    @Override
    public void incrementSetAndAdd(Object[] o, JFrame parentFrame, String labelText){
        this.incrementSizeBy(o.length);
        this.setDegreeObjects(o);
        this.addDegreeObject(parentFrame, labelText, o.length);
    }

    @Override
    public void showInfo(int index){
        super.showInfo(index);
        infoLabelOnHover = new JLabel(
                String.format("<html><pre> %s</pre></html>",
                        topics[index].getDesc())
        );
        infoLabelOnHover.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        infoLabelOnHover.setForeground(new Color(0x774455));
        infoLabelOnHover.setHorizontalAlignment(SwingConstants.LEADING);
        infoLabelOnHover.setVerticalAlignment(SwingConstants.CENTER);
        infoLabelOnHover.setPreferredSize(new Dimension(250, 40));

        degreeObjectPanels[index].add(infoLabelOnHover, BorderLayout.CENTER);
        validate();
    }
}
