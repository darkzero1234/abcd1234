import javax.swing.JTable;


public class TicketTable extends JTable{
	public static JTable createTable(String[][]contents, String[] header)
	{
		JTable table = new JTable(contents, header);
		return table;
	}
}
