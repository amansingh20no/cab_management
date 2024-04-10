
package cab;

import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class Driver_Report  extends JFrame
{
Driver_Report()
{
Vector columnNames = new Vector();
Vector data = new Vector();
try
{
  DriverManager.registerDriver(new com.mysql.jdbc.Driver());
  Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3325/busdb?user=root&password=");

String sql = "Select * from drivertb";
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery( sql );
ResultSetMetaData md = rs.getMetaData();
int columns = md.getColumnCount();
for (int i = 1; i <= columns; i++)
{
columnNames.addElement( md.getColumnName(i) );
}
while (rs.next())
{
Vector row = new Vector(columns);
for (int i = 1; i <= columns; i++){
row.addElement( rs.getObject(i) );
}
data.addElement( row );
}
rs.close();
stmt.close();
}
catch(Exception e)
{
System.out.println(e);
}
JTable table = new JTable(data, columnNames);
TableColumn col;
for (int i = 0; i < table.getColumnCount(); i++)
{
col = table.getColumnModel().getColumn(i);
col.setMaxWidth(400);
}
JScrollPane scrollPane = new JScrollPane( table );
add( scrollPane );
}

}
