import java.awt.GridLayout;
import javax.swing.JPanel;
/**
 *
 * @author simonpedersen
 */

public class ComponentLayout
{
    public static JPanel mainPane;
    public static CaseDatabase caseDB = new CaseDatabase();
    int panels = 0;
    
    ComponentLayout(JPanel mainPane)
    {
        this.mainPane = mainPane;
    }
    
    void InitComponent()
    {
        caseDB.CreateTables();
        caseDB.ReadSettings();
        panels = caseDB.ReadAmount();
        
        mainPane.setLayout(new GridLayout(panels, 1));
        
        for (int i = 0; i < panels; i++)
        {
            mainPane.add(new Panel(caseDB.ReadValues(i), false).panel);
        }
    }
}
