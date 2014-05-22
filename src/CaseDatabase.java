import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 *
 * @author simonpedersen
 */

public class CaseDatabase 
{
    Connection databaseConnection = null;
    Statement databaseStatement = null;
    boolean settingsFound = false;
    
    void ConnectToDB()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            databaseConnection = DriverManager.getConnection("jdbc:sqlite:CaseDatabase.db");       
        }
        catch (Exception e)
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    
    void CreateTables()
    {
        try
        {
            ConnectToDB();
            
            databaseStatement = databaseConnection.createStatement();
            
            String sql = "CREATE TABLE SETTINGS(TEXTSIZE INT)";
            
            databaseStatement.executeUpdate(sql);
            
                   sql = "CREATE TABLE CASES(TEXT1 TEXT,TEXT2 TEXT,TEXT3 TEXT,TEXT4 TEXT,TEXT5 TEXT,TEXT6 TEXT);";
                   
            databaseStatement.executeUpdate(sql);
                   
            databaseStatement.close();
            databaseConnection.close();
        }
        catch (Exception e)
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    
    void ReadSettings()
    {
        try
        {
            ConnectToDB();
            
            databaseStatement = databaseConnection.createStatement();
            
            String sql = "SELECT * FROM SETTINGS;";
           
            ResultSet queryResult = databaseStatement.executeQuery(sql);
            
            int TextSize = queryResult.getInt("TEXTSIZE");
            
            ChangeComponent.font = new Font("SansSerif", Font.BOLD, TextSize);
            
            settingsFound = true;
            
            queryResult.close();
            databaseStatement.close();
            databaseConnection.close();
        }
        catch (Exception e)
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    
    void SaveSettings()
    {
        try
        {
            ConnectToDB();
            
            databaseStatement = databaseConnection.createStatement();
            
            String sql; 
            
            if (settingsFound)
                sql = "UPDATE SETTINGS SET TEXTSIZE = " + ChangeComponent.font.getSize() + ";";
            else
                sql = "INSERT INTO SETTINGS(TEXTSIZE) VALUES(" + ChangeComponent.font.getSize() + ");";
           
            databaseStatement.executeUpdate(sql);
            
            databaseStatement.close();
            databaseConnection.close();
        }
        catch (Exception e)
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    
    int ReadAmount()
    {
        int amount = 0;
        try
        {
            ConnectToDB();
            
            databaseStatement = databaseConnection.createStatement();
            
            String sql = "SELECT * FROM CASES;";
           
            ResultSet queryResult = databaseStatement.executeQuery(sql);
            
            while(queryResult.next())
                amount++;
            
            queryResult.close();
            databaseStatement.close();
            databaseConnection.close();
        }
        catch (Exception e)
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return 0;
        }
        return amount;
    }
    
    String[] ReadValues(int index)
    {
        String[] TextFields = new String[6];
        int findIndex = 0;
        try
        {
            ConnectToDB();
            
            databaseStatement = databaseConnection.createStatement();
            
            String sql = "SELECT * FROM CASES ORDER BY TEXT1 ASC;";
           
            ResultSet queryResult = databaseStatement.executeQuery(sql);
            
            if (findIndex == index)
            {
                TextFields[0] = queryResult.getString("TEXT1");
                TextFields[1] = queryResult.getString("TEXT2");
                TextFields[2] = queryResult.getString("TEXT3");
                TextFields[3] = queryResult.getString("TEXT4");
                TextFields[4] = queryResult.getString("TEXT5");
                TextFields[5] = queryResult.getString("TEXT6");
            }
            
            while(queryResult.next())
            {
                if (findIndex == index)
                {
                    TextFields[0] = queryResult.getString("TEXT1");
                    TextFields[1] = queryResult.getString("TEXT2");
                    TextFields[2] = queryResult.getString("TEXT3");
                    TextFields[3] = queryResult.getString("TEXT4");
                    TextFields[4] = queryResult.getString("TEXT5");
                    TextFields[5] = queryResult.getString("TEXT6");
                    break;
                }
                findIndex++;
            }
            
            queryResult.close();
            databaseStatement.close();
            databaseConnection.close();
        }
        catch (Exception e)
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return TextFields = new String[] {"","","","","",""};
        }
        return TextFields;
    }
    
    void SaveValues()
    {
        try
        {
            ConnectToDB();
            
            databaseStatement = databaseConnection.createStatement();
            
            int amount = RetrieveAmount(ComponentLayout.mainPane);

            for (int i = 0; i < amount; i++)
            {
                String[] textFields = RetrieveValues(ComponentLayout.mainPane,i);
                
                String sql = "SELECT * FROM CASES WHERE TEXT1 = '" + textFields[0] + "';";
                
                ResultSet queryResult = databaseStatement.executeQuery(sql);
                
                if (queryResult.next())
                {
                    for (int x = 0; x < textFields.length; x++)
                    {
                        if (textFields[x].contains("'"))
                            textFields[x] = textFields[x].replaceAll("'", "''");
                    }
                    
                    sql = "UPDATE CASES SET TEXT2 = '" + textFields[1] + "',TEXT3 = '" + textFields[2] +
                          "',TEXT4 = '" + textFields[3] + "',TEXT5 = '" + textFields[4] + "',TEXT6 = '" +
                          textFields[5] + "' WHERE TEXT1 = '" + textFields[0] + "';";    
                }
                else
                {
                    sql = "INSERT INTO CASES(TEXT1,TEXT2,TEXT3,TEXT4,TEXT5,TEXT6) " +
                          "VALUES('" + textFields[0] + "','" + textFields[1] + "','" + textFields[2] + "','" +
                          textFields[3] + "','" + textFields[4] + "','" + textFields[5] + "');";
                }
                
                queryResult.close();
                databaseStatement.executeUpdate(sql);
            }
            
            databaseStatement.close();
            databaseConnection.close();
        }
        catch (Exception e)
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    
    int RetrieveAmount(JPanel mainPane)
    {
        Component[] components = mainPane.getComponents();
        
        return components.length;
    }
    
    String[] RetrieveValues(JPanel mainPane, int index)
    {
        String[] textFields = new String[6];
        
        Component[] components = mainPane.getComponents();

        if ((components[index] instanceof Container))
        {
            Component[] subComponents = ((Container)components[index]).getComponents();
              
            for (int x = 0; x < subComponents.length; x++)
            {
                if (subComponents[x] instanceof Container)
                {
                    textFields[x] = ((JTextField)subComponents[x]).getText();
                }
            }
        }
        return textFields;
    }
}
