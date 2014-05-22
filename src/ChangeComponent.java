import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 *
 * @author simonpedersen
 */

public class ChangeComponent 
{
    public static Font font = new Font("SansSerif", Font.BOLD, 10);
    
    public static void AddNew(JPanel mainPane)
    {
        String[] textFields = new String[6];
        
        textFields[0] = "BLA";

        mainPane.add(new Panel(textFields, true).panel);
        
        GridLayout gridlayout = (GridLayout)mainPane.getLayout();
        
        mainPane.setLayout(new GridLayout(gridlayout.getRows() + 1, gridlayout.getColumns()));
        
        Component[] components = mainPane.getComponents();
        
        if ((components[components.length - 1] instanceof Container))
        {
            Component[] subComponents = ((Container)components[components.length - 1]).getComponents();
            
            if (subComponents[0] instanceof Container)
                ((JTextField)subComponents[0]).requestFocus();
        }
    }
    
    public static void AddNew(JPanel mainPane, String[] textFields)
    {
        mainPane.add(new Panel(textFields, false).panel);
        
        GridLayout gridlayout = (GridLayout)mainPane.getLayout();
        
        mainPane.setLayout(new GridLayout(gridlayout.getRows() + 1, gridlayout.getColumns()));
    }
    
    public static void EnlargeText(JPanel mainPane)
    {
        Component[] components = mainPane.getComponents();

        for (int i = 0; i < components.length; ++i) 
        {
            if ((components[i] instanceof Container))
            {
                Component[] subComponents = ((Container)components[i]).getComponents();
                
                for (int x = 0; x < subComponents.length; x++)
                {
                    if (subComponents[x] instanceof Container)
                    {
                        font = subComponents[x].getFont();
                        subComponents[x].setFont(new Font(font.getFontName(), Font.BOLD, font.getSize() + 1));
                        font = subComponents[x].getFont();
                    }
                }
            }
        }
    }
    
    public static void MinimizeText(JPanel mainPane)
    {
        Component[] components = mainPane.getComponents();

        for (int i = 0; i < components.length; ++i) 
        {
            if ((components[i] instanceof Container))
            {
                Component[] subComponents = ((Container)components[i]).getComponents();
                
                for (int x = 0; x < subComponents.length; x++)
                {
                    if (subComponents[x] instanceof Container)
                    {
                        font = subComponents[x].getFont();
                        subComponents[x].setFont(new Font("SansSerif", Font.BOLD, font.getSize() - 1));
                        font = subComponents[x].getFont();
                    }
                }
            }
        }
    }
}
