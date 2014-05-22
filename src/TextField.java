import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
/**
 *
 * @author simonpedersen
 */

public class TextField extends MouseAdapter implements ActionListener
{
    JTextField textField;
    float[] hsbValues;
    int width;
    
    TextField(String text, int width, boolean enabled)
    {
        hsbValues = Color.RGBtoHSB(230, 230, 250, hsbValues);
        textField = new JTextField(width);
        textField.addActionListener(this);
        textField.addMouseListener(this);
        textField.setText(text);
        textField.setFont(new Font("SansSerif", Font.BOLD, ChangeComponent.font.getSize()));
        textField.setDisabledTextColor(Color.BLACK);
        textField.setBackground(Color.getHSBColor(hsbValues[0], hsbValues[1], hsbValues[2]));
        textField.setEnabled(enabled);
        
        if (enabled)
            textField.setBackground(Color.WHITE);
    }
    
    @Override
    public void mouseClicked(MouseEvent event)
    {
        if  (event.getButton() == 1)
        {
            textField.setBackground(Color.WHITE);
            textField.setEnabled(true);
            textField.requestFocus();
        }
    }
    
    public void actionPerformed(ActionEvent event)
    {
        textField.setBackground(Color.getHSBColor(hsbValues[0], hsbValues[1], hsbValues[2]));
        textField.setEnabled(false);
    }
}

