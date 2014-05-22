import java.awt.Color;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 *
 * @author simonpedersen
 */

public class Panel extends MouseAdapter
{
    JPanel panel = new JPanel();
        Color panelBackGroundColor;
    Panel()
    {
        panel.setLayout(new FlowLayout());
        
        panel.add(new TextField("BLA11111", 6, false).textField);
        panel.add(new TextField("BLA/BLA bla bla bla", 30, false).textField);
        panel.add(new TextField("MAIL RECIEVED", 9, false).textField);
        panel.add(new TextField("21-11-2013", 7, false).textField);
        panel.add(new TextField("Waiting for someone to reply and comment on something!", 35, false).textField);
        panel.add(new TextField("It's PENDING, \"Client\".", 20, false).textField);
    }
    
    @SuppressWarnings("LeakingThisInConstructor")
    Panel(String[] TextFields, boolean enabled)
    {
        panel.setLayout(new FlowLayout());
        panelBackGroundColor = panel.getBackground();
        panel.addMouseListener(this);
        panel.add(new TextField(TextFields[0], 6, enabled).textField);
        panel.add(new TextField(TextFields[1], 30, enabled).textField);
        panel.add(new TextField(TextFields[2], 9, enabled).textField);
        panel.add(new TextField(TextFields[3], 7, enabled).textField);
        panel.add(new TextField(TextFields[4], 35, enabled).textField);
        panel.add(new TextField(TextFields[5], 20, enabled).textField);
    }
    
    @Override
    public void mouseClicked(MouseEvent event)
    {
        if  (event.getButton() == 3)
        {
            if (panel.getBackground() == panelBackGroundColor)
                panel.setBackground(Color.GREEN);
            else if (panel.getBackground() == Color.GREEN)
                panel.setBackground(Color.RED);
            else
                panel.setBackground(panelBackGroundColor);
        }
    }
}
