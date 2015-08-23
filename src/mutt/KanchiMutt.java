package mutt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;


import net.sf.jcarrierpigeon.WindowPosition;
import net.sf.jtelegraph.Telegraph;
import net.sf.jtelegraph.TelegraphQueue;
import net.sf.jtelegraph.TelegraphType;

import org.freixas.jcalendar.JCalendar;
import org.freixas.jcalendar.JCalendarCombo;

import com.ibm.icu.text.NumberFormat;
import com.ibm.icu.text.RuleBasedNumberFormat;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class KanchiMutt extends JFrame implements ActionListener {

	/**
	 * @param args
	 */
	JPanel panel = new JPanel();
	JPanel new_panel = new JPanel();
	JPanel view_panel = new JPanel();
	JPanel edit_panel = new JPanel();
	JPanel renew_panel = new JPanel();
	JPanel bill_panel = new JPanel();
	JTabbedPane tab_pane = new JTabbedPane();
	JLabel donation_type, pay_mode, pay_num, dated, bank_nam, branch, date, receipt;
	JLabel addr_line_11, addr_line_21, city1, addr_line_12, addr_line_22, city2, addr_line_13, addr_line_23, city3, addr_line_14, addr_line_24, city4;
	
	JTextField payment_num, bank_name, branch_nam, issue_dat, receipt_no;
	JComboBox don_type, payment_mode, duration, durationCombo, durationCombo1;
	JCalendarCombo cal, cal2;
	JLabel no_p1, name_p1, add_p1, ph_p1, duration1, start_date1, end_date1, amt_p1, no_p2, name_p2, add_p2, ph_p2, duration2, start_date2, end_date2, amt_p2, no_p3, name_p3, add_p3, ph_p3, amt_p3, duration3, start_date3, end_date3,no_p4, name_p4, add_p4, ph_p4, amt_p4, duration4;
	JTextField num_p1, cand_nam_p1,cand_ph_p1, cand_amt_p1, dur1, s_date1, e_date1, num_p2, cand_nam_p2, cand_ph_p2, cand_amt_p2, dur2, s_date2, e_date2, num_p3, cand_nam_p3, cand_ph_p3, cand_amt_p3, dur3, s_date3, e_date3, num_p4, cand_nam_p4, cand_ph_p4, cand_amt_p4, dur4;
	JTextField addr_11, addr_21, city_town1, addr_12, addr_22, city_town2, addr_13, addr_23, city_town3, addr_14, addr_24, city_town4;
	
	//JTextArea cand_add_p1, cand_add_p2, cand_add_p3;
	JButton save;
	JButton reset, reset1, reset2;
	JButton retrive;
	JButton find, find1;
	JButton edit, renew;
	JCalendar calendar1;
	JButton delete;
	JButton proceed;
	JFileChooser chose = new JFileChooser();
	JFileChooser gen_pdf = new JFileChooser();
	FileNameExtensionFilter csv_files = new FileNameExtensionFilter("csv files", "csv");
	JMenuBar menu = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenuItem Import = new JMenuItem("Import");
	JMenuItem export = new JMenuItem("Export");
	JMenuItem exit = new JMenuItem("Exit");
	JMenuItem pdf = new JMenuItem("Generate Pdf");
	JMenuItem refresh = new JMenuItem("Refresh");
	JMenu bill = new JMenu("Bill");
	JMenuItem cancel = new JMenuItem("Cancel Payment");
	JMenuItem statement = new JMenuItem("Statement");
	JMenuItem stmt_full = new JMenuItem("Complete Statement");
	JMenuItem recep = new JMenuItem("Receipt");
	JMenu help = new JMenu("Help");
	JMenuItem about = new JMenuItem("About");
	JButton view = new JButton("Refresh");
	DefaultTableModel model = new DefaultTableModel();
	DefaultTableModel print_mod = new DefaultTableModel();
	JTable edit_table = new JTable(model);
	JScrollPane jsp;
	Dimension dim;
	JToolBar view_tool = new JToolBar();
	ImageIcon import_img = new ImageIcon(this.getClass().getResource("import.png"));
	ImageIcon export_img = new ImageIcon(this.getClass().getResource("export.png"));
	ImageIcon refresh_img = new ImageIcon(this.getClass().getResource("refresh.png"));
	ImageIcon pdf_img = new ImageIcon(this.getClass().getResource("pdf.png"));
	ImageIcon about_img = new ImageIcon(this.getClass().getResource("about.png"));
	ImageIcon exit_img = new ImageIcon(this.getClass().getResource("exit.png"));
	ImageIcon cancel_img = new ImageIcon(this.getClass().getResource("cancel.png"));
	ImageIcon st_img = new ImageIcon(this.getClass().getResource("document.png"));
	ImageIcon stmt_img = new ImageIcon(this.getClass().getResource("complete-file.png"));
	ImageIcon rece_img = new ImageIcon(this.getClass().getResource("pay.png"));
	//ImageIcon header = new ImageIcon(this.getClass().getResource("header.png"));
	Font f;
	KanchiMutt(){
		super("Kanchi Mutt");
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle rec = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		int taskht = dim.height - rec.height;
		setSize(dim.width, dim.height-taskht);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//JLabel head = new JLabel(header);
		//add(head);
		
		
		//head.setBounds(0, 0, dim.width, 150);
		panel.setLayout(new BorderLayout());
		getContentPane().add(panel);
		//panel.setLayout(new GridBagLayout());
		//add(view);
		//view.addActionListener(this);
		//view.setBounds(10, 10, 100, 20);
		//setLayout(null);
		
		
		chose.addChoosableFileFilter(csv_files);
		f = new Font("Arial", Font.PLAIN, 12);
		new_interior();
		
		view_interior();
		edit_interior();
		renew_interior();
		bill_interior();
		tab_pane.addTab("New Entry", new_panel);
		tab_pane.addTab("View", view_panel);
		tab_pane.addTab("Update", edit_panel);
		tab_pane.addTab("Renewal", renew_panel);
		tab_pane.addTab("Billing", bill_panel);
		panel.add(tab_pane, BorderLayout.CENTER);
		//interior();
		//add(panel);
		setVisible(true);
	}
	
	public void new_interior(){
		file.setMnemonic(KeyEvent.VK_F);
		bill.setMnemonic(KeyEvent.VK_B);
		help.setMnemonic(KeyEvent.VK_H);
		Import.setMnemonic(KeyEvent.VK_I);
		export.setMnemonic(KeyEvent.VK_E);
		exit.setMnemonic(KeyEvent.VK_X);
		pdf.setMnemonic(KeyEvent.VK_P);
		refresh.setMnemonic(KeyEvent.VK_R);
		cancel.setMnemonic(KeyEvent.VK_C);
		statement.setMnemonic(KeyEvent.VK_S);
		
		about.setMnemonic(KeyEvent.VK_A);
		file.add(Import);
		Import.setIcon(import_img);
		Import.addActionListener(this);
		Import.setAccelerator(KeyStroke.getKeyStroke('I', ActionEvent.CTRL_MASK));
		file.add(export);
		export.setIcon(export_img);
		export.addActionListener(this);
		export.setAccelerator(KeyStroke.getKeyStroke('E', ActionEvent.CTRL_MASK));
		file.add(refresh);
		refresh.setIcon(refresh_img);
		refresh.addActionListener(this);
		refresh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		file.add(pdf);
		pdf.setIcon(pdf_img);
		pdf.addActionListener(this);
		pdf.setAccelerator(KeyStroke.getKeyStroke('P', ActionEvent.CTRL_MASK));
		file.add(exit);
		exit.setIcon(exit_img);
		exit.addActionListener(this);
		exit.setAccelerator(KeyStroke.getKeyStroke('X', ActionEvent.ALT_MASK));
		bill.add(cancel);
		cancel.addActionListener(this);
		cancel.setIcon(cancel_img);
		bill.add(statement);
		statement.addActionListener(this);
		statement.setIcon(st_img);
		bill.add(stmt_full);
		stmt_full.addActionListener(this);
		stmt_full.setIcon(stmt_img);
		bill.add(recep);
		recep.addActionListener(this);
		recep.setIcon(rece_img);
		help.add(about);
		about.setIcon(about_img);
		about.addActionListener(this);
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		menu.add(file);
		menu.add(bill);
		menu.add(help);
		setJMenuBar(menu);
		
		String[] dur = {"1 Year", "3 Year", "Life Time"};
		no_p1 = new JLabel("Mz No");
		
		name_p1 = new JLabel("Name");
		//add_p1 = new JLabel("Address");
		addr_line_11 = new JLabel("Address Line 1");
		addr_line_21 = new JLabel("Address Line 2");
		city1 = new JLabel("City");
		ph_p1 = new JLabel("Phone Num");
		duration1 = new JLabel("Duration");
		start_date1 = new JLabel("Start Date");
		end_date1 = new JLabel("End Date");
		amt_p1 = new JLabel("Amount");
		num_p1 = new JTextField(15);
		cand_nam_p1 = new JTextField(15);
		//cand_add_p1 = new JTextArea(4, 15);
		addr_11 = new JTextField(15);
		addr_21 = new JTextField(15);
		city_town1 = new JTextField(15);
		cand_ph_p1 = new JTextField(15);
		cand_amt_p1 = new JTextField(15);
		duration = new JComboBox(dur);
		duration.addActionListener(this);
		//dur1 = new JTextField(15);
		s_date1 = new JTextField(15);
		e_date1 = new JTextField(15);		
		save = new JButton("Save");
		reset = new JButton("Reset");	
		cal = new JCalendarCombo(JCalendar.DISPLAY_DATE, true);		
		
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		cal.setDateFormat(f);
		
		/*cal.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(e.getSource() == cal){
					if(duration.getSelectedIndex() == 0){													
							e_date1.setText(cal.getSelectedItem().toString());
						
					}
					
				}
			}
		}
				); */
		//cand_add_p1.setLineWrap(true);
		//cand_add_p1.setWrapStyleWord(true);
		new_panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		num_p1.setBorder(border);
		cand_nam_p1.setBorder(border);
		addr_11.setBorder(border);
		addr_21.setBorder(border);
		city_town1.setBorder(border);
		//cand_add_p1.setBorder(border);
		cand_ph_p1.setBorder(border);
		//dur1.setBorder(border);
		s_date1.setBorder(border);
		e_date1.setBorder(border);
		cand_amt_p1.setBorder(border);
		save.addActionListener(this);
		reset.addActionListener(this);
		save.setDefaultCapable(true);
		getRootPane().setDefaultButton(save);
		c.insets = new Insets(15, 15, 10, 10);
		
		c.gridx = 0; c.gridy = 0; 
		new_panel.add(no_p1, c);
		c.gridx = 1; c.gridy = 0;
		new_panel.add(num_p1, c);
		c.gridx = 0; c.gridy = 1;
		new_panel.add(name_p1, c);
		c.gridx = 1; c.gridy = 1;
		new_panel.add(cand_nam_p1, c);
		c.gridx = 0; c.gridy = 2;
		new_panel.add(addr_line_11, c);
		c.gridx = 1; c.gridy = 2;
		new_panel.add(addr_11, c);
		c.gridx = 0; c.gridy = 3;
		new_panel.add(addr_line_21, c);
		c.gridx = 1; c.gridy = 3;
		new_panel.add(addr_21, c);
		c.gridx = 0; c.gridy = 4;
		new_panel.add(city1, c);
		c.gridx = 1; c.gridy = 4;
		new_panel.add(city_town1, c);
		//panel.add(new JScrollPane(cand_add, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		c.gridx = 0; c.gridy = 5;
		new_panel.add(ph_p1, c);
		c.gridx = 1; c.gridy = 5;
		new_panel.add(cand_ph_p1, c);
		c.gridx = 0; c.gridy = 6;
		new_panel.add(duration1, c);
		c.gridx = 1; c.gridy = 6;
		new_panel.add(duration, c);
		c.gridx = 0; c.gridy = 7;
		new_panel.add(start_date1, c);
		c.gridx = 1; c.gridy = 7;
		new_panel.add(cal, c);
		//c.gridx = 0; c.gridy = 8;
		//new_panel.add(end_date1, c);
		//c.gridx = 1; c.gridy = 8;
		//new_panel.add(e_date1, c);
		c.gridx = 0; c.gridy = 8;
		new_panel.add(amt_p1, c);
		c.gridx = 1; c.gridy = 8;
		new_panel.add(cand_amt_p1, c);
		c.gridx = 1; c.gridy = 9;
		new_panel.add(save, c);
		c.gridx = 2; c.gridy = 9;
		new_panel.add(reset, c);
		
	/*	cand_add_p1.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_TAB){
					if (e.getModifiers() > 0){
						cand_add_p1.transferFocusBackward();
					} else {
						cand_add_p1.transferFocus();
					}
					e.consume();
				}
			}
		}); */
	}
	
	public void view_interior(){
	/*	no_p2 = new JLabel("No");
		name_p2 = new JLabel("Name");
		add_p2 = new JLabel("Address");
		amt_p2 = new JLabel("Amount");
		num_p2 = new JTextField(15);
		cand_nam_p2 = new JTextField(15);
		cand_add_p2 = new JTextArea(4, 15);
		cand_amt_p2 = new JTextField(15);
		first = new JButton("First");
		last = new JButton("Last");
		prev = new JButton("Previous");
		next = new JButton("Next");
		cand_add_p2.setLineWrap(true);
		cand_add_p2.setWrapStyleWord(true);
		view_panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		num_p2.setBorder(border);
		cand_nam_p2.setBorder(border);
		cand_add_p2.setBorder(border);
		cand_amt_p2.setBorder(border);
		first.addActionListener(this);
		last.addActionListener(this);
		prev.addActionListener(this);
		next.addActionListener(this);
		//save.addActionListener(this);
		//reset.addActionListener(this);
		c.gridx = 0; c.gridy = 0; c.insets = new Insets(15, 15, 10, 10);
		view_panel.add(no_p2, c);
		c.gridx = 1; c.gridy = 0;
		view_panel.add(num_p2, c);
		c.gridx = 0; c.gridy = 1;
		view_panel.add(name_p2, c);
		c.gridx = 1; c.gridy = 1;
		view_panel.add(cand_nam_p2, c);
		c.gridx = 0; c.gridy = 2;
		view_panel.add(add_p2, c);
		c.gridx = 1; c.gridy = 2;
		view_panel.add(cand_add_p2, c);
		//panel.add(new JScrollPane(cand_add, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		c.gridx = 0; c.gridy = 3;
		view_panel.add(amt_p2, c);
		c.gridx = 1; c.gridy = 3;
		view_panel.add(cand_amt_p2, c);
		c.gridx = 0; c.gridy = 4;
		view_panel.add(first, c);
		c.gridx = 1; c.gridy = 4;
		view_panel.add(prev, c);
		c.gridx = 2; c.gridy = 4;
		view_panel.add(next, c);
		c.gridx = 3; c.gridy = 4;
		view_panel.add(last, c); */
		view_panel.setLayout(new BorderLayout());
		//print = new JButton("Print");
		//view_panel.add(print);
		//view_panel.add(view);
		
		//view.setBounds(100, 300, 100, 20);
		//view_tool.add(view);
		//view_panel.add(view_tool);
		//view.addActionListener(this);
		//print.addActionListener(this);
		
		
		edit_table.setRowHeight(30);
		Font ff = new Font("Arial", Font.PLAIN, 16);
		edit_table.setFont(ff);
		edit_table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
		edit_table.setEnabled(false);
		//edit_table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Connection conn = null;
		try {
			conn = DriverManager.
			    getConnection("jdbc:h2:~/kanchiMutt", "sa", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // add application code here
		Statement stm;
		try {
			stm = conn.createStatement();
			//String st = "create table if not exists details(no int, name varchar(50), address varchar(200))";
			String st = "select * from details";
			ResultSet rs = stm.executeQuery(st);
			model.addColumn("Mz NO");
			model.addColumn("Name");
			model.addColumn("Address Line 1");
			model.addColumn("Address Line 2");
			model.addColumn("City");
			model.addColumn("Phone Num");
			model.addColumn("Amount");
		/*	while (rs.next()){
				String no = rs.getString(1);
				String nam = rs.getString(2);
				String addr = rs.getString(3);
				Float amount = rs.getFloat(4);
				model.addRow(new Object[] {no, nam, addr, amount});
			} */
			view_tab_data();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        jsp = new JScrollPane(edit_table);
        view_panel.add(jsp);
        
	}
	
	void view_tab_data(){
		
		try{
			Connection conn = DriverManager.
				    getConnection("jdbc:h2:~/kanchiMutt", "sa", "");
			Statement stm = conn.createStatement();
			String st = "select * from details";
			ResultSet rs = stm.executeQuery(st);
		
		while (rs.next()){
			String no = rs.getString(1);
			String nam = rs.getString(2);
			String addr1 = rs.getString(3);
			String addr2 = rs.getString(4);
			String city1 = rs.getString(5);
			String num1 = rs.getString(6);
			Float amount = rs.getFloat(10);
			model.addRow(new Object[] {no, nam, addr1, addr2, city1, num1, amount});
			
		}
		edit_table.getColumnModel().getColumn(0).setMaxWidth(100);
		edit_table.getColumnModel().getColumn(1).setMaxWidth(200);
		edit_table.getColumnModel().getColumn(2).setMaxWidth(400);
		edit_table.getColumnModel().getColumn(3).setMaxWidth(350);
		edit_table.getColumnModel().getColumn(4).setMaxWidth(200);
		edit_table.getColumnModel().getColumn(5).setMaxWidth(100);
		edit_table.getColumnModel().getColumn(6).setMaxWidth(100);
		((JLabel)edit_table.getDefaultRenderer(String.class)).setHorizontalAlignment(JLabel.LEFT);
	}
		catch(Exception e){
			System.err.print(e);
		}
	}
	
	void edit_interior(){
		
		String[] dur1 = {"1 Year", "3 Year", "Life Time"};
		
		no_p3 = new JLabel("Enter Mz No");
		name_p3 = new JLabel("Name");
		//add_p3 = new JLabel("Address");
		addr_line_13 = new JLabel("Address Line 1");
		addr_line_23 = new JLabel("Address Line 2");
		city3 = new JLabel("City");
		ph_p3 = new JLabel("Phone Num");
		duration3 = new JLabel("Duration");
		start_date3 = new JLabel("Start Date");
		amt_p3 = new JLabel("Amount");
		num_p3 = new JTextField(15);
		cand_nam_p3 = new JTextField(15);
		//cand_add_p3 = new JTextArea(4, 15);
		addr_13 = new JTextField(15);
		addr_23 = new JTextField(15);
		city_town3 = new JTextField(15);
		cand_ph_p3 = new JTextField(15);
		durationCombo = new JComboBox(dur1);
		
		cal2 = new JCalendarCombo(JCalendar.DISPLAY_DATE, true);		
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		cal2.setDateFormat(f);
		cand_amt_p3 = new JTextField(15);
		//save = new JButton("Save");
		//reset = new JButton("Reset");
		find = new JButton("Find");
		edit = new JButton("Update");
		delete = new JButton("Delete");
		reset = new JButton("Reset");
		//cand_add_p3.setLineWrap(true);
		//cand_add_p3.setWrapStyleWord(true);
		edit_panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		num_p3.setBorder(border);
		cand_nam_p3.setBorder(border);
		//cand_add_p3.setBorder(border);
		addr_13.setBorder(border);
		addr_23.setBorder(border);
		city_town3.setBorder(border);
		cand_ph_p3.setBorder(border);
		cand_amt_p3.setBorder(border);
		cand_nam_p3.setEditable(false);
		addr_13.setEditable(false);
		addr_23.setEditable(false);
		city_town3.setEditable(false);
		cand_ph_p3.setEditable(false);
		//cand_add_p3.setEditable(false);
		durationCombo.setEnabled(false);
		cal2.setEnabled(false);
		cand_amt_p3.setEditable(false);
		//save.addActionListener(this);
		//reset.addActionListener(this);
		find.addActionListener(this);
		edit.addActionListener(this);
		delete.addActionListener(this);
		reset.addActionListener(this);
		c.gridx = 0; c.gridy = 0; c.insets = new Insets(15, 15, 10, 10);
		edit_panel.add(no_p3, c);
		c.gridx = 1; c.gridy = 0;
		edit_panel.add(num_p3, c);
		c.gridx = 2; c.gridy = 0;
		edit_panel.add(find, c);
		c.gridx = 0; c.gridy = 1;
		edit_panel.add(name_p3, c);
		c.gridx = 1; c.gridy = 1;
		edit_panel.add(cand_nam_p3, c);
		c.gridx = 0; c.gridy = 2;
		edit_panel.add(addr_line_13, c);
		c.gridx = 1; c.gridy = 2;
		edit_panel.add(addr_13, c);
		c.gridx = 0; c.gridy = 3;
		edit_panel.add(addr_line_23, c);
		c.gridx = 1; c.gridy = 3;
		edit_panel.add(addr_23, c);
		c.gridx = 0; c.gridy = 4;
		edit_panel.add(city3, c);
		c.gridx = 1; c.gridy = 4;
		edit_panel.add(city_town3, c);
		
		//panel.add(new JScrollPane(cand_add, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		c.gridx = 0; c.gridy = 5;
		edit_panel.add(ph_p3, c);
		c.gridx = 1; c.gridy = 5;
		edit_panel.add(cand_ph_p3, c);
		c.gridx = 0; c.gridy = 6;
		edit_panel.add(duration3, c);
		c.gridx = 1; c.gridy = 6;
		edit_panel.add(durationCombo, c);
		c.gridx = 0; c.gridy = 7;
		edit_panel.add(start_date3, c);
		c.gridx = 1; c.gridy = 7;
		edit_panel.add(cal2, c);
		c.gridx = 0; c.gridy = 8;
		edit_panel.add(amt_p3, c);
		c.gridx = 1; c.gridy = 8;
		edit_panel.add(cand_amt_p3, c);		
		c.gridx = 1; c.gridy = 9;
		edit_panel.add(edit, c);
		c.gridx = 2; c.gridy = 9;
		edit_panel.add(delete, c);
		c.gridx = 3; c.gridy = 9;
		edit_panel.add(reset, c);
	/*	cand_add_p3.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_TAB){
					if (e.getModifiers() > 0){
						cand_add_p3.transferFocusBackward();
					} else {
						cand_add_p3.transferFocus();
					}
					e.consume();
				}
			}
		}); */
		
	}
	
	public void renew_interior(){
		String[] dur2 = {"1 Year", "3 Year", "Life Time"};
		
		no_p4 = new JLabel("Enter Mz No");
		name_p4 = new JLabel("Name");
		//add_p3 = new JLabel("Address");
		addr_line_14 = new JLabel("Address Line 1");
		addr_line_24 = new JLabel("Address Line 2");
		city4 = new JLabel("City");
		ph_p4 = new JLabel("Phone Num");
		duration4 = new JLabel("Duration");
		//start_date3 = new JLabel("Start Date");
		amt_p4 = new JLabel("Amount");
		num_p4 = new JTextField(15);
		cand_nam_p4 = new JTextField(15);
		//cand_add_p3 = new JTextArea(4, 15);
		addr_14 = new JTextField(15);
		addr_24 = new JTextField(15);
		city_town4 = new JTextField(15);
		cand_ph_p4 = new JTextField(15);
		durationCombo1 = new JComboBox(dur2);
		
		//cal2 = new JCalendarCombo(JCalendar.DISPLAY_DATE, true);		
		//DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		//cal2.setDateFormat(f);
		cand_amt_p4 = new JTextField(15);
		//save = new JButton("Save");
		//reset = new JButton("Reset");
		find1 = new JButton("Find ");
		renew = new JButton("Renew");
		//delete = new JButton("Delete");
		reset2 = new JButton("Reset ");
		//cand_add_p3.setLineWrap(true);
		//cand_add_p3.setWrapStyleWord(true);
		renew_panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		num_p4.setBorder(border);
		cand_nam_p4.setBorder(border);
		//cand_add_p3.setBorder(border);
		addr_14.setBorder(border);
		addr_24.setBorder(border);
		city_town4.setBorder(border);
		cand_ph_p4.setBorder(border);
		cand_amt_p4.setBorder(border);
		cand_nam_p4.setEditable(false);
		addr_14.setEditable(false);
		addr_24.setEditable(false);
		city_town4.setEditable(false);
		cand_ph_p4.setEditable(false);
		//cand_add_p3.setEditable(false);
		durationCombo1.setEnabled(false);
		//cal2.setEnabled(false);
		//cand_amt_p3.setEditable(false);
		//save.addActionListener(this);
		//reset.addActionListener(this);
		find1.addActionListener(this);
		renew.addActionListener(this);
		//delete.addActionListener(this);
		reset2.addActionListener(this);
		c.gridx = 0; c.gridy = 0; c.insets = new Insets(15, 15, 10, 10);
		renew_panel.add(no_p4, c);
		c.gridx = 1; c.gridy = 0;
		renew_panel.add(num_p4, c);
		c.gridx = 2; c.gridy = 0;
		renew_panel.add(find1, c);
		c.gridx = 0; c.gridy = 1;
		renew_panel.add(name_p4, c);
		c.gridx = 1; c.gridy = 1;
		renew_panel.add(cand_nam_p4, c);
		c.gridx = 0; c.gridy = 2;
		renew_panel.add(addr_line_14, c);
		c.gridx = 1; c.gridy = 2;
		renew_panel.add(addr_14, c);
		c.gridx = 0; c.gridy = 3;
		renew_panel.add(addr_line_24, c);
		c.gridx = 1; c.gridy = 3;
		renew_panel.add(addr_24, c);
		c.gridx = 0; c.gridy = 4;
		renew_panel.add(city4, c);
		c.gridx = 1; c.gridy = 4;
		renew_panel.add(city_town4, c);
		
		//panel.add(new JScrollPane(cand_add, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		c.gridx = 0; c.gridy = 5;
		renew_panel.add(ph_p4, c);
		c.gridx = 1; c.gridy = 5;
		renew_panel.add(cand_ph_p4, c);
		c.gridx = 0; c.gridy = 6;
		renew_panel.add(duration4, c);
		c.gridx = 1; c.gridy = 6;
		renew_panel.add(durationCombo1, c);
		
		c.gridx = 0; c.gridy = 7;
		renew_panel.add(amt_p4, c);
		c.gridx = 1; c.gridy = 7;
		renew_panel.add(cand_amt_p4, c);		
		c.gridx = 1; c.gridy = 8;
		renew_panel.add(renew, c);
		
		c.gridx = 2; c.gridy = 8;
		renew_panel.add(reset2, c);
	}
	
	public void bill_interior(){
		String[] pay = {"CORPUS","REVENUE"};
		String[] acc = {"Cash","Cheque","D.D"};
		calendar1 = new JCalendar(JCalendar.DISPLAY_DATE, false);
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date d = new java.util.Date();
		final String dat = f.format(d);
		
		date = new JLabel(dat);
		date.setToolTipText("Not right click to change date");
		date.addMouseListener(new MouseListener(){
			
			public void mouseClicked(MouseEvent e){
				String newdat = JOptionPane.showInputDialog(null, "Enter date", dat);
				if (newdat != null){
					date.setText(newdat);
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				date.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		receipt = new JLabel("Receipt No");
		no_p2 = new JLabel("Enter NS no");
		
		name_p2 = new JLabel("Name");
		//add_p2 = new JLabel("Address");
		addr_line_12 = new JLabel("Address Line 1");
		addr_line_22 = new JLabel("Address Line 2");
		city2 = new JLabel("City");
		amt_p2 = new JLabel("Amount");
		donation_type = new JLabel("Type of Donation");
		pay_mode = new JLabel("Mode of Payment");
		pay_num = new JLabel("cheque/D.D.No");
		dated = new JLabel("Dated of Issue");
		bank_nam = new JLabel("Name of the Bank");
		branch = new JLabel("Branch");
		receipt_no = new JTextField(15);
		num_p2 = new JTextField(15);
		cand_nam_p2 = new JTextField(15);
		//cand_add_p2 = new JTextArea(4, 15);
		addr_12 = new JTextField(15);
		addr_22 = new JTextField(15);
		city_town2 = new JTextField(15);
		cand_amt_p2 = new JTextField(15);
		payment_num = new JTextField(15);
		bank_name = new JTextField(15);
		branch_nam = new JTextField(15);
		issue_dat = new JTextField(15);
		don_type = new JComboBox(pay);
		payment_mode = new JComboBox(acc);
		don_type.addActionListener(this);
		payment_mode.addActionListener(this);
		retrive = new JButton("Retrive");
		proceed = new JButton("Proceed");
		reset1 = new JButton("Reset");
		retrive.addActionListener(this);
		proceed.addActionListener(this);
		reset1.addActionListener(this);
		
		//cand_add_p2.setLineWrap(true);
		//cand_add_p2.setWrapStyleWord(true);
		bill_panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		receipt_no.setBorder(border);
		num_p2.setBorder(border);
		cand_nam_p2.setBorder(border);
		//cand_add_p2.setBorder(border);
		addr_12.setBorder(border);
		addr_22.setBorder(border);
		city_town2.setBorder(border);
		cand_amt_p2.setBorder(border);
		payment_num.setBorder(border);
		bank_name.setBorder(border);
		issue_dat.setBorder(border);
		branch_nam.setBorder(border);
		//don_type.setBorder(border);
		//payment_mode.setBorder(border);
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 15; c.gridy = 0;
		bill_panel.add(date, c);
		c.gridx = 0; c.gridy = 1;
		bill_panel.add(receipt, c);
		c.gridx = 1; c.gridy = 1;
		bill_panel.add(receipt_no, c);
		c.gridx = 0; c.gridy = 2;
		bill_panel.add(no_p2, c);
		c.gridx = 1; c.gridy = 2;
		bill_panel.add(num_p2, c);
		c.gridx = 2; c.gridy = 2;
		bill_panel.add(retrive, c);
		c.gridx = 0; c.gridy = 3;
		bill_panel.add(name_p2, c);
		c.gridx = 1; c.gridy = 3;
		bill_panel.add(cand_nam_p2, c);
		c.gridx = 0; c.gridy = 4;
		bill_panel.add(addr_line_12, c);
		c.gridx = 1; c.gridy = 4;
		bill_panel.add(addr_12, c);
		c.gridx = 0; c.gridy = 5;
		bill_panel.add(addr_line_22, c);
		c.gridx = 1; c.gridy = 5;
		bill_panel.add(addr_22, c);
		c.gridx = 0; c.gridy = 6;
		bill_panel.add(city2, c);
		c.gridx = 1; c.gridy = 6;
		bill_panel.add(city_town2, c);
		//panel.add(new JScrollPane(cand_add, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		c.gridx = 0; c.gridy = 7;
		bill_panel.add(donation_type, c);
		c.gridx = 1; c.gridy = 7;
		bill_panel.add(don_type, c);
		c.gridx = 0; c.gridy = 8;
		bill_panel.add(amt_p2, c);
		c.gridx = 1; c.gridy = 8;
		bill_panel.add(cand_amt_p2, c);
		c.gridx = 0; c.gridy = 9;
		bill_panel.add(pay_mode, c);
		c.gridx = 1; c.gridy = 9;
		bill_panel.add(payment_mode, c);
		c.gridx = 0; c.gridy = 10;
		bill_panel.add(pay_num, c);
		c.gridx = 1; c.gridy = 10;
		bill_panel.add(payment_num, c);
		c.gridx = 0; c.gridy = 11;
		bill_panel.add(dated, c);
		c.gridx = 1; c.gridy = 11;
		bill_panel.add(issue_dat, c);
		c.gridx = 0; c.gridy = 12;
		bill_panel.add(bank_nam, c);
		c.gridx = 1; c.gridy = 12;
		bill_panel.add(bank_name, c);
		c.gridx = 0; c.gridy = 13;
		bill_panel.add(branch, c);
		c.gridx = 1; c.gridy = 13;
		bill_panel.add(branch_nam, c);
		c.gridx = 1; c.gridy = 14;
		bill_panel.add(proceed, c);
		c.gridx = 2; c.gridy = 14;
		bill_panel.add(reset1, c);
		
		if (payment_mode.getSelectedItem().equals("Cash")){
			payment_num.setEditable(false);
			bank_name.setEditable(false);
			branch_nam.setEditable(false);
			issue_dat.setEditable(false);
		}
		
		try{
			Class.forName("org.h2.Driver");
   			Connection conn=DriverManager.getConnection("jdbc:h2:~/vrnt","sa","");
			Statement stm=conn.createStatement();
			String st = "select max(receipt) from bill";
			ResultSet rs = stm.executeQuery(st);
			rs.next();
			int s = rs.getInt(1);		
			if (s == 0){
				receipt_no.setEditable(true);
			} else {
				receipt_no.setText(String.valueOf(s+1));
				receipt_no.setEditable(false);
			}
			conn.close();
		}
		catch (ClassNotFoundException e)
		{
			Telegraph tele = new Telegraph("Warning!", "Please Check Your Database Driver", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
			TelegraphQueue que = new TelegraphQueue();
			que.add(tele);
			//JOptionPane.showMessageDialog(null,"Please Check Your Database Driver", "Warning!",JOptionPane.WARNING_MESSAGE);
		}
		catch(SQLException ee)
		{
			System.err.println(ee);
			
			Telegraph tele = new Telegraph("Warning!", "Some problem might be occured in Database", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
			TelegraphQueue que = new TelegraphQueue();
			que.add(tele);
			//JOptionPane.showMessageDialog(null,"Some problem might be occured in Database","Warning!",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void print_interior(){
		JFrame print_frame = new JFrame("Receipt");
		print_frame.setSize(dim.width, dim.height);
		print_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		String h = new String("SRI GURUBHYO NAMAHA");
		
		String pan = new String("PAN No.: AAATV3147P");
		String vrnt = new String("VEDA RAKSHNA NIDHI TRUST (Regd.)");
		String vrnt_add = new String("No.64/31, SUBRAMANIYAN STREET, WEST MAMBALAM, CHENNAI - 600 033.");
		String mail = new String("e-mail: vrnt@vsnl.net");
		String ph = new String("Tel: 044-24740549");
		JTable tab = new JTable(print_mod);
		print_frame.add(tab);
		print_mod.addColumn("No");
		//print_mod.addColumn("Name");
		
		print_mod.addRow(new Object[] {"SRI GURUBHYO NAMAHA", "PAN No.: AAATV3147P", "VEDA RAKSHNA NIDHI TRUST (Regd.)", "No.64/31, SUBRAMANIYAN STREET, WEST MAMBALAM, CHENNAI - 600 033.", "e-mail: vrnt@vsnl.net", "Tel: 044-24740549"});
		print_mod.addRow(new Object[] {"PAN No.: AAATV3147P", "VEDA RAKSHNA NIDHI TRUST (Regd.)", "No.64/31, SUBRAMANIYAN STREET, WEST MAMBALAM, CHENNAI - 600 033.", "e-mail: vrnt@vsnl.net", "Tel: 044-24740549"});
		
		print_frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Connection conn = null;
		try {
			conn = DriverManager.
			    getConnection("jdbc:h2:~/kanchiMutt", "sa", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // add application code here
		Statement stm;
		try {
			stm = conn.createStatement();
			
			String st = "create table if not exists details(no varchar(10) primary key, name varchar(100), addr_1 varchar(150), addr_2 varchar(100), city varchar(100), phone_num varchar(20), duration varchar(10), start_date date, end_date date, amount double)";
			//String s = "create table if not exists bill(receipt number(10), dat date, no varchar(10), name varchar(100), addr_1 varchar(150), addr_2 varchar(100), city varchar(100), type_donatn varchar(10), amt double, pay_mode varchar(10), chqno number(20), issue_date varchar(10), bank varchar(100), branch varchar(100), status varchar(10))";
			stm.executeUpdate(st);
			//stm.executeUpdate(s);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       // NumberFormat formatter = 
       //     new RuleBasedNumberFormat(RuleBasedNumberFormat.SPELLOUT);
       // String result = formatter.format(num);
        

		KanchiMutt km = new KanchiMutt();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getActionCommand().equals("Import")){
			int ret = chose.showOpenDialog(this);
			File s = null;
			if (ret == JFileChooser.APPROVE_OPTION){
				s = chose.getSelectedFile();
				String fi = s.toString();
			}
			Connection conn = null;
			try{
				Class.forName("org.h2.Driver");
				conn = DriverManager.
					    getConnection("jdbc:h2:~/kanchiMutt", "sa", "");
				Statement stm = conn.createStatement();
				String st = "insert into details (select * from csvread('"+s+"'))";
				stm.executeUpdate(st);
				Telegraph tele = new Telegraph("Success", "Imported successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
				TelegraphQueue que = new TelegraphQueue();
				que.add(tele);
				//JOptionPane.showMessageDialog(null, "Imported Successfully...", "Success", JOptionPane.INFORMATION_MESSAGE);
			} catch(SQLException e6){
				System.err.println(e6);
				if (e6.getErrorCode() == 23505){
					Telegraph tele = new Telegraph("Data already exist", "Duplicate entry found...", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue que = new TelegraphQueue();
					que.add(tele);
					//JOptionPane.showMessageDialog(null, "Duplicate Entry Found...", "Data already exists", JOptionPane.ERROR_MESSAGE);
				} else {
					Telegraph tele = new Telegraph("Error", "Some problem occured...", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue que = new TelegraphQueue();
					que.add(tele);
				}
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		else if (e.getActionCommand().equals("Export")){
			int ret = chose.showSaveDialog(this);
			File s;
			if(ret == JFileChooser.APPROVE_OPTION){
				s = chose.getSelectedFile();
				String file_name = s.toString();
				if (!file_name.toLowerCase().endsWith(".csv")){
					s = new File(file_name+".csv");
				}
				Connection conn = null;
				try{
					Class.forName("org.h2.Driver");
					conn = DriverManager.
						    getConnection("jdbc:h2:~/kanchiMutt", "sa", "");
					Statement stm = conn.createStatement();
					String st = "call csvwrite('"+s+"', 'select * from details')";
					stm.executeUpdate(st);
					Telegraph tele = new Telegraph("Success", "Successfully Exported...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue que = new TelegraphQueue();
					que.add(tele);
					//JOptionPane.showMessageDialog(null, "Successfully Exported...", "Success", JOptionPane.INFORMATION_MESSAGE);
				} catch(Exception e7){
					System.err.println(e7);
				}
			}
		}
		
		else if (e.getActionCommand().equals("Save")){
			TelegraphQueue que = new TelegraphQueue();
			if(num_p1.getText().length() == 0){
				Telegraph tele = new Telegraph("Enter Mz No.", "Mz Number can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
				//JOptionPane.showMessageDialog(null, "NS number can't be empty...", "Error", JOptionPane.ERROR_MESSAGE);
				num_p1.setFocusable(true);
			} else if (cand_nam_p1.getText().length() == 0){
					Telegraph tele = new Telegraph("Enter Name", "Name can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
					que.add(tele);
					//JOptionPane.showMessageDialog(null, "Name can't be empty...", "Error", JOptionPane.ERROR_MESSAGE);
					cand_nam_p1.setFocusable(true);
			} else if (addr_11.getText().length() == 0){
						Telegraph tele = new Telegraph("Enter Address", "Address line 1 can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
						que.add(tele);
						//JOptionPane.showMessageDialog(null, "Address can't be empty...", "Error", JOptionPane.ERROR_MESSAGE);
						addr_11.setFocusable(true);
			} else if (city_town1.getText().length() == 0){
					Telegraph tele = new Telegraph("Enter City", "City can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
					que.add(tele);
					//JOptionPane.showMessageDialog(null, "Address can't be empty...", "Error", JOptionPane.ERROR_MESSAGE);
					city_town1.setFocusable(true);
			} else {
				try {
					Class.forName("org.h2.Driver");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        Connection conn = null;
				try {
					conn = DriverManager.
					    getConnection("jdbc:h2:~/kanchiMutt", "sa", "");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        // add application code here
		        try {
					Statement stm = conn.createStatement();
					int dur = duration.getSelectedIndex();
					int days = 0;
					if(dur==0)
						days = 365;
					else if(dur==1)
						days = 365*2;
					else if(dur==2)
						days = 365*5;
					String st = "insert into details values("+"'"+num_p1.getText()+"'"+","+"'"+cand_nam_p1.getText()+"'"+","+"'"+addr_11.getText()+"'"+", '"+addr_21.getText()+"', '"+city_town1.getText()+"', '"+cand_ph_p1.getText()+"', '"+duration.getSelectedItem().toString()+"', '"+cal.getSelectedItem().toString()+"', DATEADD('DAY',"+days+", '"+cal.getSelectedItem().toString()+"'), "+cand_amt_p1.getText()+")";
					stm.executeUpdate(st);
					Telegraph tele = new Telegraph("Success", "Saved successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue quee = new TelegraphQueue();
					quee.add(tele);
					//JOptionPane.showMessageDialog(null, "Saved Successfully...", "Success", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
					if (e2.getErrorCode() == 23505){
						Telegraph tele = new Telegraph("Data already exist", "The Mz Number You Entered Is Already Registered...", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);
						TelegraphQueue quee = new TelegraphQueue();
						quee.add(tele);
					//JOptionPane.showMessageDialog(null, "NS number "+num_p1.getText()+" already found...", "Duplicate entry found", JOptionPane.ERROR_MESSAGE);
					}
				}
				num_p1.setText("");
				cand_nam_p1.setText("");
				addr_11.setText("");
				addr_21.setText("");
				city_town1.setText("");
				cand_ph_p1.setText("");
				cand_amt_p1.setText("");
				try {
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}
			}
					
				
			
			
			
		} 
		
		else if (e.getActionCommand().equals("Reset")){
			num_p1.setText("");
			cand_nam_p1.setText("");
			addr_11.setText("");
			addr_21.setText("");
			city_town1.setText("");
			cand_ph_p1.setText("");
			duration.setSelectedIndex(0);			
			cand_amt_p1.setText("");
		} 
		
		else if (e.getActionCommand().equals("Refresh")){
			model.setRowCount(0);
			view_tab_data();
		} 
		
		else if (e.getActionCommand().equals("Generate Pdf")){
			int x = 0, y = 0;
			PdfWriter writer = null;
			int ret = gen_pdf.showSaveDialog(this);
			File s = null;
			if (ret == JFileChooser.APPROVE_OPTION){
				s = gen_pdf.getSelectedFile();
				String stm = s.toString();
				if(!stm.endsWith(".pdf")){
					s = new File(stm+".pdf");
				}
			}
			Document doc = new Document();
			try {
				writer = PdfWriter.getInstance(doc, new FileOutputStream(s));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			doc.setPageSize(PageSize.A4);
			doc.setMargins(30, 30, 10, 10);
			doc.setMarginMirroring(true);
			doc.open();
			com.itextpdf.text.Font n = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 11);
			PdfPTable table = new PdfPTable(3);
			table.setComplete(true);
			table.setWidthPercentage(110);
			
			//Paragraph para = new Paragraph();
			//para.setAlignment(Element.RECTANGLE);
			try{
				Class.forName("org.h2.Driver");
		        Connection conn = DriverManager.
		            getConnection("jdbc:h2:~/kanchiMutt", "sa", "");
		        Statement stm = conn.createStatement();
		        String st = "select * from details where NOW() <= end_date";
		        ResultSet rs = stm.executeQuery(st);
		        PdfContentByte canvas = writer.getDirectContentUnder();
		        while(rs.next()){
		        	Phrase h = null;
		        	
		        	 if (rs.getString(4) != null && rs.getString(5) != null){
		        	//para.add(rs.getString(1)+rs.getString(2)+rs.getString(3));
		        	h = new Phrase("Mz NO "+rs.getString(1)+"\n"+rs.getString(2)+"\n"+rs.getString(3)+"\n"+rs.getString(4)+"\n"+rs.getString(5), n);
		        	//ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, h, 10, 800-x, 0);
		        	//x+=10;
		        	 } else if (rs.getString(4) == null && rs.getString(5) != null) {
		        		 h = new Phrase("Mz NO "+rs.getString(1)+"\n"+rs.getString(2)+"\n"+rs.getString(3)+"\n"+rs.getString(5), n);
		        	 
		        	 } else if (rs.getString(4) != null && rs.getString(5) == null){
		        		 h = new Phrase("Mz NO "+rs.getString(1)+"\n"+rs.getString(2)+"\n"+rs.getString(3)+"\n"+rs.getString(4), n);
		        		 
		        	 }
		        	 PdfPCell cell = new PdfPCell(h);
		        	 cell.setFixedHeight(100f);
		        	table.addCell(cell);
		        	
		        }
		        
		        table.addCell("");
		        table.addCell("");
		        //table.addCell("");
			}
			catch(Exception e4){
				System.err.println(e4);
			}
			try {
				doc.add(table);
				
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			doc.close();
			//System.out.println("sucess");
			Telegraph tele = new Telegraph("Success", "Pdf generated successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
			TelegraphQueue que = new TelegraphQueue();
			que.add(tele);
			//JOptionPane.showMessageDialog(null, "Pdf generated successfully...", "Success", JOptionPane.INFORMATION_MESSAGE);
		} 
		
		else if (e.getActionCommand().equals("Exit")){
			System.exit(1);
		}
		
		else if (e.getActionCommand().equals("About")){
			JOptionPane.showMessageDialog(null, "Content created by vignesh c", "About", JOptionPane.INFORMATION_MESSAGE);
		}
		
		if (e.getActionCommand().equals("Find")){
			Statement stm = null;
			Connection conn = null;
			try{
				Class.forName("org.h2.Driver");
		        conn = DriverManager.
		            getConnection("jdbc:h2:~/kanchiMutt", "sa", "");
		        stm = conn.createStatement();
			}
			catch(Exception e1){
				System.err.println(e1);
			}
			
			try{
		        String st = "select * from details where no = "+"'"+num_p3.getText()+"'";
		        ResultSet rs = stm.executeQuery(st);
		        
		        
		        
		        if(rs.first()){
		        	String s = rs.getString(1);
		        	String d = num_p3.getText();
		        	//System.out.println(s+d);
		        	//if (s.equals(d)){
		        		//System.out.println("in");
		        		cand_nam_p3.setText(rs.getString(2));
		        		addr_13.setText(rs.getString(3));
		        		addr_23.setText(rs.getString(4));
		        		city_town3.setText(rs.getString(5));
		        		
		        		cand_ph_p3.setText(rs.getString(6));
		        		durationCombo.setSelectedItem(rs.getString(7));
		        		cal2.setSelectedItem(rs.getString(8));
		        		Double f = rs.getDouble(10);
		        		//System.out.println(rs.getString(2));
		        		cand_amt_p3.setText(f.toString());
		        		num_p3.setEditable(false);
		        		cand_nam_p3.setEditable(true);
		        		//cand_add_p3.setEditable(true);
		        		addr_13.setEditable(true);
		        		addr_23.setEditable(true);
		        		city_town3.setEditable(true);
		        		cand_ph_p3.setEditable(true);
		        		durationCombo.setEnabled(true);
		        		cal2.setEnabled(true);
		        		cand_amt_p3.setEditable(true);
		        		//break;
		        	} 
		        	else{
		        		//System.out.println("not found");
		        		Telegraph tele = new Telegraph("Error", "Entrie not found...", TelegraphType.NOTIFICATION_ERROR, WindowPosition.BOTTOMRIGHT, 4000);
		    			TelegraphQueue que = new TelegraphQueue();
		    			que.add(tele);
		        		//JOptionPane.showMessageDialog(null, "Entrie not found...", "Error", JOptionPane.ERROR_MESSAGE);
		        	}
		       // }
		        // add application code here
		        conn.close();
			} catch(Exception e6){
				System.err.println(e6);
				
			}
			
		} 
		
		else if(e.getActionCommand().equals("Update")){
			TelegraphQueue que = new TelegraphQueue();
			if (cand_nam_p3.getText().length() == 0){
				Telegraph tele = new Telegraph("Enter Name", "Name can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
				//JOptionPane.showMessageDialog(null, "Name can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
				cand_nam_p3.setFocusable(true);
			}else if(addr_13.getText().length() == 0){
					Telegraph tele = new Telegraph("Enter Address Line 1", "Address Line 1 can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
					que.add(tele);
					//JOptionPane.showMessageDialog(null, "Address can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
					addr_13.setFocusable(true);
			} else if(city_town3.getText().length() == 0){
					Telegraph tele = new Telegraph("Enter City", "City can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
					que.add(tele);
					//JOptionPane.showMessageDialog(null, "Address can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
					city_town3.setFocusable(true);
			} else {
				try{
					Class.forName("org.h2.Driver");
			        Connection conn = DriverManager.
			            getConnection("jdbc:h2:~/kanchiMutt", "sa", "");
			        Statement stm = conn.createStatement();
			        int cnt = durationCombo.getSelectedIndex();
			        int days = 0;
			        if(cnt==0)
			        	days = 365;
			        if(cnt==1)
			        	days = 365*3;
			        if(cnt==2)
			        	days = 365*5;
			        String st = "update details set name = "+"'"+cand_nam_p3.getText()+"'"+", "+"addr_1 = "+"'"+addr_13.getText()+"'"+", addr_2 = '"+addr_23.getText()+"', city = '"+city_town3.getText()+"', "+"phone_num = '"+cand_ph_p3.getText()+"', "+"duration = '"+durationCombo.getSelectedItem().toString()+"', "+"start_date = '"+cal2.getSelectedItem().toString()+"', end_date = "+" DATEADD('DAY',"+days+", '"+cal2.getSelectedItem().toString()+"'), "+"amount = "+cand_amt_p3.getText()+" where no = "+"'"+num_p3.getText()+"'";
			        stm.executeUpdate(st);
			        Telegraph tele = new Telegraph("Success", "Updated successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue quee = new TelegraphQueue();
					quee.add(tele);
			        //JOptionPane.showMessageDialog(null, "Updated Successfully...", "Success", JOptionPane.INFORMATION_MESSAGE);
			        num_p3.setText("");
			        cand_nam_p3.setText("");
			       // cand_add_p3.setText("");
			        addr_13.setText("");
			        addr_23.setText("");
			        city_town3.setText("");
			        cand_ph_p3.setText("");
			        cand_amt_p3.setText("");
			        num_p3.setEditable(true);
			        cand_nam_p3.setEditable(false);
			        //cand_add_p3.setEditable(false);
			        addr_13.setEditable(false);
			        addr_23.setEditable(false);
			        city_town3.setEditable(false);
			        cand_ph_p3.setEditable(false);
			        durationCombo.setEnabled(false);
			        cal2.setEnabled(false);
			        cand_amt_p3.setEditable(false);
				}
				catch(Exception e2){
					System.err.println(e2);
				}
			}
			
		} 
		
		else if (e.getActionCommand().equals("Delete")){
			int t = JOptionPane.showConfirmDialog(null, "Are you sure want to delete "+cand_nam_p3.getText(), "Confirm delete", JOptionPane.YES_NO_OPTION);
			if (t == JOptionPane.YES_OPTION){
			try{
				Class.forName("org.h2.Driver");
		        Connection conn = DriverManager.
		            getConnection("jdbc:h2:~/kanchiMutt", "sa", "");
		        Statement stm = conn.createStatement();
		        String st = "delete from details where no = "+"'"+num_p3.getText()+"'";
		        stm.executeUpdate(st);
		        Telegraph tele = new Telegraph("Success", "Deleted successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
				TelegraphQueue quee = new TelegraphQueue();
				quee.add(tele);
		        //JOptionPane.showMessageDialog(null, "Deleted Successfully...", "Success", JOptionPane.INFORMATION_MESSAGE);
		        num_p3.setText("");
		        cand_nam_p3.setText("");
		       // cand_add_p3.setText("");
		        addr_13.setText("");
		        addr_23.setText("");
		        city_town3.setText("");
		        cand_ph_p3.setText("");
		        cand_amt_p3.setText("");
		        num_p3.setEditable(true);
		        cand_nam_p3.setEditable(false);
		        //cand_add_p3.setEditable(false);
		        addr_13.setEditable(false);
		        addr_23.setEditable(false);
		        city_town3.setEditable(false);
		        cand_ph_p3.setEditable(false);
		        cand_amt_p3.setEditable(false);
			} catch(Exception e5){
				System.err.println(e5);
			}
		  }
		} 
		
		else if (e.getActionCommand().equals("Reset")){
			num_p3.setText("");
			cand_nam_p3.setText("");
			//cand_add_p3.setText("");
			addr_13.setText("");
	        addr_23.setText("");
	        city_town3.setText("");
	        cand_ph_p3.setText("");
	        durationCombo.setSelectedIndex(0);	        
			cand_amt_p3.setText("");
			num_p3.setEditable(true);
			cand_nam_p3.setEditable(false);
			//cand_add_p3.setEditable(false);
			 addr_13.setEditable(false);
		     addr_23.setEditable(false);
		     city_town3.setEditable(false);
		     cand_ph_p3.setEditable(false);
		     durationCombo.setEnabled(false);
		     cal2.setEnabled(false);
		     cand_amt_p3.setEditable(false);
		}
		
		if (e.getActionCommand().equals("Find ")){
			Statement stm = null;
			Connection conn = null;
			try{
				Class.forName("org.h2.Driver");
		        conn = DriverManager.
		            getConnection("jdbc:h2:~/kanchiMutt", "sa", "");
		        stm = conn.createStatement();
			}
			catch(Exception e1){
				System.err.println(e1);
			}
			
			try{
		        String st = "select * from details where no = "+"'"+num_p4.getText()+"'";
		        ResultSet rs = stm.executeQuery(st);
		        
		        
		        
		        if(rs.first()){
		        	String s = rs.getString(1);
		        	String d = num_p4.getText();
		        	//System.out.println(s+d);
		        	//if (s.equals(d)){
		        		//System.out.println("in");
		        		cand_nam_p4.setText(rs.getString(2));
		        		addr_14.setText(rs.getString(3));
		        		addr_24.setText(rs.getString(4));
		        		city_town4.setText(rs.getString(5));
		        		
		        		cand_ph_p4.setText(rs.getString(6));
		        		durationCombo1.setSelectedItem(rs.getString(7));
		        		//cal2.setSelectedItem(rs.getString(8));
		        		//Double f = rs.getDouble(10);
		        		//System.out.println(rs.getString(2));
		        		//cand_amt_p4.setText(f.toString());
		        		num_p4.setEditable(false);
		        		cand_nam_p4.setEditable(true);
		        		//cand_add_p3.setEditable(true);
		        		addr_14.setEditable(true);
		        		addr_24.setEditable(true);
		        		city_town4.setEditable(true);
		        		cand_ph_p4.setEditable(true);
		        		durationCombo1.setEnabled(true);
		        		//cal2.setEnabled(true);
		        		cand_amt_p4.setEditable(true);
		        		//break;
		        	} 
		        	else{
		        		//System.out.println("not found");
		        		Telegraph tele = new Telegraph("Error", "Entrie not found...", TelegraphType.NOTIFICATION_ERROR, WindowPosition.BOTTOMRIGHT, 4000);
		    			TelegraphQueue que = new TelegraphQueue();
		    			que.add(tele);
		        		//JOptionPane.showMessageDialog(null, "Entrie not found...", "Error", JOptionPane.ERROR_MESSAGE);
		        	}
		       // }
		        // add application code here
		        conn.close();
			} catch(Exception e6){
				System.err.println(e6);
				
			}
			
		}
		
		else if(e.getActionCommand().equals("Renew")){
			TelegraphQueue que = new TelegraphQueue();
			if (cand_nam_p4.getText().length() == 0){
				Telegraph tele = new Telegraph("Enter Name", "Name can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
				//JOptionPane.showMessageDialog(null, "Name can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
				cand_nam_p4.setFocusable(true);
			}else if(addr_14.getText().length() == 0){
					Telegraph tele = new Telegraph("Enter Address Line 1", "Address Line 1 can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
					que.add(tele);
					//JOptionPane.showMessageDialog(null, "Address can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
					addr_14.setFocusable(true);
			} else if(city_town4.getText().length() == 0){
					Telegraph tele = new Telegraph("Enter City", "City can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
					que.add(tele);
					//JOptionPane.showMessageDialog(null, "Address can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
					city_town4.setFocusable(true);
			} else {
				try{
					Class.forName("org.h2.Driver");
			        Connection conn = DriverManager.
			            getConnection("jdbc:h2:~/kanchiMutt", "sa", "");
			        Statement stm = conn.createStatement();
			        int cnt = durationCombo1.getSelectedIndex();
			        int days = 0;
			        if(cnt==0)
			        	days = 365;
			        if(cnt==1)
			        	days = 365*3;
			        if(cnt==2)
			        	days = 365*5;
			        String st1 = "select end_date,amount from details where no = "+"'"+num_p4.getText()+"'";
			        ResultSet rs = stm.executeQuery(st1);
			        
			        rs.first();
			        
			        Date end = rs.getDate(1);
			        double amt = rs.getDouble(2);
			        Date today = new Date();
			        Calendar fin = Calendar.getInstance();
			        
			        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			        df.format(today);			        
			        //df.format(fin.getTime());
			        
			        if(today.after(end))
			        	fin.setTime(today);
			        else if(today.before(end))
			        	fin.setTime(end);
			        else if(today.equals(end))
			        	fin.setTime(today);
			        double total = amt + Double.parseDouble(cand_amt_p4.getText());
			        String st = "update details set name = "+"'"+cand_nam_p4.getText()+"'"+", "+"addr_1 = "+"'"+addr_14.getText()+"'"+", addr_2 = '"+addr_24.getText()+"', city = '"+city_town4.getText()+"', "+"phone_num = '"+cand_ph_p4.getText()+"', "+"duration = '"+durationCombo1.getSelectedItem().toString()+"',  end_date = "+" DATEADD('DAY',"+days+", '"+df.format(fin.getTime())+"'), "+"amount = "+total+" where no = "+"'"+num_p4.getText()+"'";
			        stm.executeUpdate(st);
			        Telegraph tele = new Telegraph("Success", "Renewed successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue quee = new TelegraphQueue();
					quee.add(tele);
			        //JOptionPane.showMessageDialog(null, "Updated Successfully...", "Success", JOptionPane.INFORMATION_MESSAGE);
			        num_p4.setText("");
			        cand_nam_p4.setText("");
			       // cand_add_p3.setText("");
			        addr_14.setText("");
			        addr_24.setText("");
			        city_town4.setText("");
			        cand_ph_p4.setText("");
			        cand_amt_p4.setText("");
			        num_p4.setEditable(true);
			        cand_nam_p4.setEditable(false);
			        //cand_add_p3.setEditable(false);
			        addr_14.setEditable(false);
			        addr_24.setEditable(false);
			        city_town4.setEditable(false);
			        cand_ph_p4.setEditable(false);
			        durationCombo1.setEnabled(false);
			        //cal2.setEnabled(false);
			        cand_amt_p4.setEditable(false);
				}
				catch(Exception e2){
					System.err.println(e2);
				}
			}
			
		}
		
		else if (e.getActionCommand().equals("Reset ")){
			num_p4.setText("");
			cand_nam_p4.setText("");
			//cand_add_p3.setText("");
			addr_14.setText("");
	        addr_24.setText("");
	        city_town4.setText("");
	        cand_ph_p4.setText("");
	        durationCombo1.setSelectedIndex(0);	        
			cand_amt_p4.setText("");
			num_p4.setEditable(true);
			cand_nam_p4.setEditable(false);
			//cand_add_p3.setEditable(false);
			 addr_14.setEditable(false);
		     addr_24.setEditable(false);
		     city_town4.setEditable(false);
		     cand_ph_p4.setEditable(false);
		     durationCombo1.setEnabled(false);
		     //cal2.setEnabled(false);
		     cand_amt_p4.setEditable(false);
		}
		
		if (e.getActionCommand().equals("Retrive")){
			Statement stm = null;
			Connection conn = null;
			try{
				Class.forName("org.h2.Driver");
		        conn = DriverManager.
		            getConnection("jdbc:h2:~/kanchiMutt", "sa", "");
		        stm = conn.createStatement();
			}
			catch(Exception e1){
				System.err.println(e1);
			}
			
			try{
		        String st = "select * from details where no = "+"'"+num_p2.getText()+"'";
		        ResultSet rs = stm.executeQuery(st);
		        
		        
		        
		        if(rs.first()){
		        	String s = rs.getString(1);
		        	String d = num_p2.getText();
		        	//System.out.println(s+d);
		        	//if (s.equals(d)){
		        		//System.out.println("in");
		        		cand_nam_p2.setText(rs.getString(2));
		        		addr_12.setText(rs.getString(3));
		        		addr_22.setText(rs.getString(4));
		        		city_town2.setText(rs.getString(5));
		        		Float f = rs.getFloat(7);
		        		//System.out.println(rs.getString(2));
		        		//cand_amt_p3.setText(f.toString());
		        		num_p2.setEditable(false);
		        		//cand_nam_p3.setEditable(true);
		        		//cand_add_p3.setEditable(true);
		        		//cand_amt_p3.setEditable(true);
		        		//break;
		        	} 
		        	else{
		        		//System.out.println("not found");
		        		Telegraph tele = new Telegraph("Error", "Entrie not found...", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
						TelegraphQueue quee = new TelegraphQueue();
						quee.add(tele);
		        		//JOptionPane.showMessageDialog(null, "Entrie not found...", "Error", JOptionPane.ERROR_MESSAGE);
		        	}
		       // }
		        // add application code here
		        conn.close();
			} catch(Exception e6){
				System.err.println(e6);
				
			}
		}
		if (e.getSource().equals(payment_mode)){
			if (payment_mode.getSelectedItem().equals("Cash")){
				payment_num.setEditable(false);
				bank_name.setEditable(false);
				branch_nam.setEditable(false);
				issue_dat.setEditable(false);
			}
			else {
				payment_num.setEditable(true);
				bank_name.setEditable(true);
				branch_nam.setEditable(true);
				issue_dat.setEditable(true);
			}
		}
		
		if (e.getSource() == reset1){
			num_p2.setEditable(true);
			num_p2.setText("");
			cand_nam_p2.setText("");
			addr_12.setText("");
			addr_22.setText("");
			city_town2.setText("");
			don_type.setSelectedIndex(0);
			cand_amt_p2.setText("");
			payment_mode.setSelectedIndex(0);
			payment_num.setText("");
			issue_dat.setText("");
			bank_name.setText("");
			branch_nam.setText("");
			
			
		}
		
		if (e.getActionCommand().equals("Proceed")){
			TelegraphQueue que = new TelegraphQueue();
			if (cand_nam_p2.getText().length() == 0){
				Telegraph tele = new Telegraph("Enter Name", "Name can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
			} else if(addr_12.getText().length() == 0){
				Telegraph tele = new Telegraph("Enter Address", "Address can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
			} else if(city_town2.getText().length() == 0){
				Telegraph tele = new Telegraph("Enter City", "City can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
			} else if(cand_amt_p2.getText().length() == 0){
				Telegraph tele = new Telegraph("Enter Amount", "Amount can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
			} else if(payment_mode.getSelectedIndex() != 0) {
				if (payment_num.getText().length() == 0){
					Telegraph tele = new Telegraph("Enter Cheque/D.D No.", "Cheque/D.D number can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
					que.add(tele);
				} 
			} else {
				try {
					Class.forName("org.h2.Driver");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        Connection conn = null;
				try {
					conn = DriverManager.
					    getConnection("jdbc:h2:~/kanchiMutt", "sa", "");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        // add application code here
				String pay_num;
				if (payment_num.getText().length() == 0){
					pay_num = null;
				} else {
					pay_num = payment_num.getText();
				}
		        try {
					Statement stm = conn.createStatement();
					String st = "insert into bill values("+receipt_no.getText()+", '"+date.getText()+"', '"+num_p2.getText()+"', '"+cand_nam_p2.getText()+"'"+","+"'"+addr_12.getText()+"'"+", '"+addr_22.getText()+"', '"+city_town2.getText()+"', '"+don_type.getSelectedItem()+"', "+cand_amt_p2.getText()+", '"+payment_mode.getSelectedItem()+"', "+pay_num+", '"+issue_dat.getText()+"', '"+bank_name.getText()+"', '"+branch_nam.getText()+"', 'cleared'"+")";
					stm.executeUpdate(st);
					if(num_p2.getText().length() != 0){
						String st1 = "select amount from details where no = '"+num_p2.getText()+"'";
						ResultSet rs2 = stm.executeQuery(st1);
						rs2.next();
						double amt = rs2.getDouble(1);
						
						
						amt += Integer.parseInt(cand_amt_p2.getText());
						String st2 = "update details set amount = "+amt+"where no = '"+num_p2.getText()+"'";
						stm.executeUpdate(st2);
					}
					Telegraph tele = new Telegraph("Success", "Receipt generated successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue quee = new TelegraphQueue();
					quee.add(tele);
					//JOptionPane.showMessageDialog(null, "Saved Successfully...", "Success", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
					
				}
		        
				try {
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}
				
			PdfWriter writer = null;
			
			File s = null;
			try {
				s = File.createTempFile("vrnt_bill", ".pdf");
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			Document doc = new Document();
			try {
				writer = PdfWriter.getInstance(doc, new FileOutputStream(s));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			doc.setPageSize(PageSize.A5);
			//doc.setMargins(36, 72, 108, 180);
			doc.setMarginMirroring(true);
			doc.open();
			com.itextpdf.text.Font fi = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 16);
			com.itextpdf.text.Font n = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 10);
			com.itextpdf.text.Font nb = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD);
			com.itextpdf.text.Font si = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 8);
			com.itextpdf.text.Font ni = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 6);
			PdfPTable tab = new PdfPTable(3);
			PdfPCell a, b, c, d, f, g;
			//tab.setWidths(new int[] {80, 60, 60});
			tab.setWidthPercentage(100);
			
			NumberFormat formatter = new RuleBasedNumberFormat(RuleBasedNumberFormat.SPELLOUT);
			String result = formatter.format(Integer.parseInt(cand_amt_p2.getText()));
			Phrase h = new Phrase("SRI GURUBHYO NAMAHA", ni);
			
			Phrase pan = new Phrase("PAN No.: AAATV3147P", ni);
			Phrase vrnt = new Phrase("VEDA RAKSHNA NIDHI TRUST (Regd.)", fi);
			Phrase vrnt_add = new Phrase("No.64/31, SUBRAMANIYAN STREET, WEST MAMBALAM, CHENNAI - 600 033.", ni);
			Phrase mail = new Phrase("e-mail: vrnt@vsnl.net \t Tel: 044-24740549", si);
			Phrase donat = new Phrase(don_type.getSelectedItem()+" Donation", si);
			//Phrase ph = new Phrase("Tel: 044-24740549");
			a = new PdfPCell(h);
			b = new PdfPCell(pan);
			c = new PdfPCell(vrnt);
			d = new PdfPCell(vrnt_add);
			f = new PdfPCell(mail);
			g = new PdfPCell(donat);
			//g = new PdfPCell(ph);
			a.setColspan(2);
			//b.setColspan(3);
			c.setColspan(3);
			d.setColspan(3);
			f.setColspan(3);
			g.setColspan(3);
			a.setHorizontalAlignment(Element.ALIGN_RIGHT);
			b.setHorizontalAlignment(Element.ALIGN_RIGHT);
			c.setHorizontalAlignment(Element.ALIGN_CENTER);
			d.setHorizontalAlignment(Element.ALIGN_CENTER);
			f.setHorizontalAlignment(Element.ALIGN_CENTER);
			g.setHorizontalAlignment(Element.ALIGN_CENTER);
			//g.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			Phrase t = new Phrase("To", si);
			PdfPCell to = new PdfPCell(t);
			to.setColspan(3);
			
			Phrase nam = new Phrase("     "+cand_nam_p2.getText(), si);
			Phrase addr1 = new Phrase("       "+addr_12.getText(), ni);
			Phrase addr2 = null;
			Phrase city = null;
			if (addr_22.getText().length() != 0){
			addr2 = new Phrase("       "+addr_22.getText(), ni);
			city = new Phrase("       "+city_town2.getText(), ni);
			} else{
				addr2 =  new Phrase("       "+city_town2.getText(), ni);
				city = new Phrase("\0", ni);
			}
			
			Phrase rece = new Phrase("Receipt No: "+receipt_no.getText(), si);
			Phrase dat = new Phrase("Date: "+date.getText(), si);
			Phrase nsno = new Phrase("N.S No: "+num_p2.getText(), si);
			Phrase amt = new Phrase("Donation towards "+don_type.getSelectedItem()+" fund", si);
			Phrase rs = new Phrase("Rs. "+cand_amt_p2.getText(), si);
			Phrase rup = new Phrase("Rupees "+result+" only", si);
			Phrase pay = new Phrase("Payment Details", si);
			Phrase mod = new Phrase("Mode Of Payment: "+payment_mode.getSelectedItem(), si);
			Phrase chq = new Phrase("Cheque / DD No: "+payment_num.getText(), si);
			Phrase dated = new Phrase("Dated: "+issue_dat.getText(), si);
			Phrase bank = new Phrase("Bank: "+bank_name.getText(), si);
			Phrase branch = new Phrase("Branch: "+branch_nam.getText(), si);
			Phrase emp = new Phrase("\0");
			Phrase sign_don = new Phrase("Signature of Donor", si);
			Phrase sign_rec = new Phrase("Signature of Receiver", si);
			Phrase trust = new Phrase("Exe. Trustee/Treasurer", si);
			Phrase ins1 = new Phrase("1. Donations to the trust are exempt from income Tax Section 80-G of income Tax Act.", ni);
			Phrase ins2 = new Phrase("2. Please quote your N.S No. given above in future correspondence with us.", ni);
			PdfPCell nam_c = new PdfPCell(nam);
			PdfPCell addr1_c = new PdfPCell(addr1);
			PdfPCell addr2_c = new PdfPCell(addr2);
			PdfPCell city_c = new PdfPCell(city);
			PdfPCell rece_c = new PdfPCell(rece);
			PdfPCell dat_c = new PdfPCell(dat);
			PdfPCell nsno_c = new PdfPCell(nsno);
			PdfPCell amt_c = new PdfPCell(amt);
			PdfPCell rs_c = new PdfPCell(rs);
			PdfPCell rup_c = new PdfPCell(rup);
			PdfPCell pay_c = new PdfPCell(pay);
			PdfPCell mod_c = new PdfPCell(mod);
			PdfPCell chq_c = new PdfPCell(chq);
			PdfPCell dated_c = new PdfPCell(dated);
			PdfPCell bank_c = new PdfPCell(bank);
			PdfPCell branch_c = new PdfPCell(branch);
			PdfPCell empty = new PdfPCell(emp);
			PdfPCell sign_don_c = new PdfPCell(sign_don);
			PdfPCell sign_rec_c = new PdfPCell(sign_rec);
			PdfPCell trust_c = new PdfPCell(trust);
			PdfPCell ins1_c = new PdfPCell(ins1);
			PdfPCell ins2_c = new PdfPCell(ins2);
			empty.setFixedHeight(6);
			nam_c.setColspan(2);
			addr1_c.setColspan(2);
			addr2_c.setColspan(2);
			city_c.setColspan(3);
			amt_c.setColspan(2);
			rup_c.setColspan(3);
			pay_c.setColspan(3);
			chq_c.setColspan(2);
			bank_c.setColspan(2);
			branch_c.setColspan(3);
			empty.setColspan(3);
			ins1_c.setColspan(3);
			ins2_c.setColspan(3);
			a.setBorder(0);
			b.setBorder(0);
			c.setBorder(0);
			d.setBorder(0);
			f.setBorder(0);
			g.setBorder(0);
			empty.setBorder(0);
			to.setBorder(0);
			nam_c.setBorder(0);
			rece_c.setBorder(0);
			addr1_c.setBorder(0);
			dat_c.setBorder(0);
			addr2_c.setBorder(0);
			nsno_c.setBorder(0);
			city_c.setBorder(0);
			amt_c.setBorder(0);
			rs_c.setBorder(0);
			rup_c.setBorder(0);
			pay_c.setBorder(0);
			mod_c.setBorder(0);
			chq_c.setBorder(0);
			dated_c.setBorder(0);
			bank_c.setBorder(0);
			branch_c.setBorder(0);
			sign_don_c.setBorder(0);
			sign_rec_c.setBorder(0);
			trust_c.setBorder(0);
			ins1_c.setBorder(0);
			ins2_c.setBorder(0);
			PdfContentByte cb = writer.getDirectContent();
			cb.roundRectangle(20, doc.getPageSize().getHeight()/2+5, doc.getPageSize().getWidth()-40, doc.getPageSize().getHeight()/2-40, 20);
			cb.roundRectangle(20, 40, doc.getPageSize().getWidth()-40, doc.getPageSize().getHeight()/2-40, 20);
			
			//cb.closePath();
			cb.stroke();
			com.itextpdf.text.Image donor_img = null;
			com.itextpdf.text.Image off_img = null;
			try {
				donor_img = Image.getInstance(this.getClass().getResource("donr.png"));
				off_img = Image.getInstance(this.getClass().getResource("off.png"));
				donor_img.scaleAbsolute(150, 150);
				
				off_img.scaleAbsolute(150, 150);
				donor_img.setAbsolutePosition(120, doc.getPageSize().getHeight()/2+50);
				off_img.setAbsolutePosition(120, 90);
				
			} catch (BadElementException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			} catch (MalformedURLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			} catch (IOException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			} 
			for (int i = 0; i < 2; i++){
			tab.addCell(a);
			tab.addCell(b);
			tab.addCell(c);
			tab.addCell(d);
			tab.addCell(f);
			tab.addCell(g);
			tab.addCell(empty);
			tab.addCell(to);			
			tab.addCell(nam_c);
			tab.addCell(rece_c);
			tab.addCell(addr1_c);
			tab.addCell(dat_c);
			tab.addCell(addr2_c);
			tab.addCell(nsno_c);
			tab.addCell(city_c);
			tab.addCell(empty);
			tab.addCell(amt_c);
			tab.addCell(rs_c);
			tab.addCell(rup_c);
			tab.addCell(empty);
			tab.addCell(pay_c);
			tab.addCell(mod_c);
			tab.addCell(chq_c);
			tab.addCell(dated_c);
			tab.addCell(bank_c);
			tab.addCell(branch_c);
			tab.addCell(empty);
			tab.addCell(sign_don_c);
			tab.addCell(sign_rec_c);
			tab.addCell(trust_c);
			tab.addCell(empty);
			tab.addCell(ins1_c);
			tab.addCell(ins2_c);
			if (i == 0){
				tab.addCell(empty);
				//tab.addCell(empty);
			}
			}
			//tab.addCell(g);
			try {
				doc.add(tab);
				doc.add(donor_img);
				doc.add(off_img);
			} catch (DocumentException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			num_p2.setEditable(true);
			num_p2.setText("");
			cand_nam_p2.setText("");
			addr_12.setText("");
			addr_22.setText("");
			city_town2.setText("");
			don_type.setSelectedIndex(0);
			cand_amt_p2.setText("");
			payment_mode.setSelectedIndex(0);
			payment_num.setText("");
			issue_dat.setText("");
			bank_name.setText("");
			branch_nam.setText("");
			try{
				Class.forName("org.h2.Driver");
	   			Connection conn1=DriverManager.getConnection("jdbc:h2:~/vrnt","sa","");
				Statement stm1=conn1.createStatement();
				String st = "select max(receipt) from bill";
				
				ResultSet rs1 = stm1.executeQuery(st);
				rs1.next();
				int s1 = rs1.getInt(1);		
				if (s1 == 0){
					receipt_no.setEditable(true);
				} else {
					receipt_no.setText(String.valueOf(s1+1));
					receipt_no.setEditable(false);
				}
				
				
				conn1.close();
			}
			catch (ClassNotFoundException e1)
			{
				Telegraph tele = new Telegraph("Warning!", "Please Check Your Database Driver", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				TelegraphQueue quee = new TelegraphQueue();
				quee.add(tele);
				//JOptionPane.showMessageDialog(null,"Please Check Your Database Driver", "Warning!",JOptionPane.WARNING_MESSAGE);
			}
			catch(SQLException ee)
			{
				System.err.println(ee);
				
				Telegraph tele = new Telegraph("Warning!", "Some problem might be occured in Database", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				TelegraphQueue quee = new TelegraphQueue();
				quee.add(tele);
				//JOptionPane.showMessageDialog(null,"Some problem might be occured in Database","Warning!",JOptionPane.WARNING_MESSAGE);
			}
			try {
				Desktop.getDesktop().open(s);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			s.deleteOnExit();
			doc.close(); 
			//print_interior();
		  }
		} 
		
		if (e.getSource() == cancel){
			String rece = JOptionPane.showInputDialog(null, "Enter Receipt No.");
			if (rece != null){
				try{
					Class.forName("org.h2.Driver");
		   			Connection conn1=DriverManager.getConnection("jdbc:h2:~/vrnt","sa","");
					Statement stm1=conn1.createStatement();
					String st = "select no,amt,status from bill where receipt = "+Integer.parseInt(rece);
					
					ResultSet rs1 = stm1.executeQuery(st);
					rs1.next();
					String s1 = rs1.getString(1);		
					double s2 = rs1.getDouble(2);
					String s3 = rs1.getString(3);
					if (s3.equals("cleared")){
					if (s1.length() != 0){
						String st1 = "select amount from details where no = '"+s1+"'";
						ResultSet rs2 = stm1.executeQuery(st1);
						rs2.next();
						double d1 = rs2.getDouble(1);
						double tot = d1 - s2;
						String st2 = "update details set amount = "+tot+" where no = '"+s1+"'";
						stm1.executeUpdate(st2);
					} 
					
						String st3 = "update bill set status = 'cancelled' where receipt = "+Integer.parseInt(rece);
						stm1.executeUpdate(st3);
					} else{
						Telegraph tele = new Telegraph("Warning!", "Receipt is already cancelled...", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
						TelegraphQueue quee = new TelegraphQueue();
						quee.add(tele);
					}
					Telegraph tele = new Telegraph("Success", "Receipt cancelled successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);				
					TelegraphQueue quee = new TelegraphQueue();
					quee.add(tele);
					conn1.close();
				}
				catch (ClassNotFoundException e1)
				{
					System.err.println(e1);
					
					Telegraph tele = new Telegraph("Warning!", "Please Check Your Database Driver", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
					TelegraphQueue quee = new TelegraphQueue();
					quee.add(tele);
					//JOptionPane.showMessageDialog(null,"Please Check Your Database Driver", "Warning!",JOptionPane.WARNING_MESSAGE);
				}
				catch(SQLException ee)
				{
					System.err.println(ee);
					if (ee.getErrorCode() == 2000){
						Telegraph tele = new Telegraph("Warning!", "Receipt not found...", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
						TelegraphQueue quee = new TelegraphQueue();
						quee.add(tele);
					}else {
						Telegraph tele = new Telegraph("Warning!", "Some problem might be occured in Database", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
						TelegraphQueue quee = new TelegraphQueue();
						quee.add(tele);
					}
					//JOptionPane.showMessageDialog(null,"Some problem might be occured in Database","Warning!",JOptionPane.WARNING_MESSAGE);
				}
			}
		}
			
			if (e.getSource() == statement){
				String strtdate = JOptionPane.showInputDialog(null, "Enter starting date", date.getText());
				if(strtdate != null){
					String enddate = JOptionPane.showInputDialog(null, "Enter ending date", date.getText());
				if(enddate != null){
				
				PdfWriter writer = null;
				
				//Paragraph para = new Paragraph();
				//para.setAlignment(Element.RECTANGLE);
				try{
					Class.forName("org.h2.Driver");
			        Connection conn = DriverManager.
			            getConnection("jdbc:h2:~/kanchiMutt", "sa", "");
			        Statement stmt = conn.createStatement();
			        String st = "select receipt,dat,no,name,amt,pay_mode,chqno from bill where dat between '"+strtdate+"' and '"+enddate+"' and status = 'cleared'";
			        ResultSet rs = stmt.executeQuery(st);
			       if(rs.isBeforeFirst()){
			    	   int ret = gen_pdf.showSaveDialog(this);
						File s = null;
						if (ret == JFileChooser.APPROVE_OPTION){
							s = gen_pdf.getSelectedFile();
							String stm = s.toString();
							if(!stm.endsWith(".pdf")){
								s = new File(stm+".pdf");
							}
						
						Document doc = new Document();
						try {
							writer = PdfWriter.getInstance(doc, new FileOutputStream(s));
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (DocumentException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						doc.setPageSize(PageSize.A4);
						//doc.setMargins(36, 72, 108, 180);
						doc.setMarginMirroring(true);
						doc.open();
						com.itextpdf.text.Font n = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 10);
						PdfPTable table = new PdfPTable(6);
						PdfPTable tab = new PdfPTable(2);
						table.setComplete(true);
						table.setWidthPercentage(100);
						table.addCell(new Phrase("Receipt No"));
						table.addCell(new Phrase("Date"));
						table.addCell(new Phrase("N.S No"));
						table.addCell(new Phrase("Name"));
						table.addCell(new Phrase("Chque/D.D No"));
						table.addCell(new Phrase("Amount"));
						table.setHeaderRows(6);
						double cashtot=0, chqtot=0, ddtot=0, total, amt;
			        while(rs.next()){
			        	 table.addCell(new Phrase(rs.getString(1), n));
			        	 table.addCell(new Phrase(String.valueOf(rs.getDate(2)), n));
			        	 table.addCell(new Phrase(rs.getString(3), n));
			        	 table.addCell(new Phrase(rs.getString(4), n));
			        	 table.addCell(new Phrase(String.valueOf(rs.getInt(7)), n));
			        	 amt = rs.getDouble(5);
			        	 table.addCell(new Phrase(String.valueOf(amt), n));
			        	 String sss = rs.getString(6);
			        	 if (sss.equals("Cash")){
			        		 cashtot += amt;
			        	 } else if(sss.equals("Cheque")){
			        		 chqtot += amt;
			        	 } else if(sss.equals("D.D")){
			        		 ddtot += amt;
			        	 }
			        	
			        	
			        }
			        total = cashtot+chqtot+ddtot;
			        try {
			        	
			        	doc.add(new Phrase("Statement From "+strtdate+" To "+enddate));
						doc.add(table);
						PdfPCell e1 = new PdfPCell(new Phrase("\0"));
						e1.setColspan(2);
						e1.setBorder(0);
						tab.addCell(e1);
						PdfPCell p2 = new PdfPCell(new Phrase("Total Amount By Cash "));
						p2.setHorizontalAlignment(Element.ALIGN_RIGHT);
						p2.setBorder(0);
						tab.addCell(p2);
						PdfPCell p3 = new PdfPCell(new Phrase(String.valueOf(cashtot)));
						p3.setHorizontalAlignment(Element.ALIGN_RIGHT);
						p3.setBorder(0);
						tab.addCell(p3);
						PdfPCell p5 = new PdfPCell(new Phrase("Total Amount By Cheque "));
						p5.setHorizontalAlignment(Element.ALIGN_RIGHT);
						p5.setBorder(0);
						tab.addCell(p5);
						PdfPCell p4 = new PdfPCell(new Phrase(String.valueOf(chqtot)));
						p4.setHorizontalAlignment(Element.ALIGN_RIGHT);
						p4.setBorder(0);
						tab.addCell(p4);
						PdfPCell p6 = new PdfPCell(new Phrase("Total Amount By D.D "));
						p6.setHorizontalAlignment(Element.ALIGN_RIGHT);
						p6.setBorder(0);
						tab.addCell(p6);
						PdfPCell p7 = new PdfPCell(new Phrase(String.valueOf(ddtot)));
						p7.setHorizontalAlignment(Element.ALIGN_RIGHT);
						p7.setBorder(0);
						tab.addCell(p7);
						PdfPCell p8 = new PdfPCell(new Phrase("Total "));
						p8.setHorizontalAlignment(Element.ALIGN_RIGHT);
						p8.setBorder(0);
						tab.addCell(p8);
						PdfPCell p9 = new PdfPCell(new Phrase(String.valueOf(total)));
						p9.setHorizontalAlignment(Element.ALIGN_RIGHT);
						p9.setBorder(0);
						tab.addCell(p9);
						tab.addCell(e1);
						Phrase p = new Phrase("*** End Of Statement ***");
						PdfPCell p1 = new PdfPCell(p);
						p1.setHorizontalAlignment(Element.ALIGN_CENTER);
						p1.setColspan(2);
						p1.setBorder(0);
						tab.addCell(p1);
						doc.add(tab);
						
					} catch (DocumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					doc.close();
					//System.out.println("sucess");
					Telegraph tele = new Telegraph("Success", "Statement generated successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue que = new TelegraphQueue();
					que.add(tele);
						}} else{
			        	Telegraph tele = new Telegraph("Invalid Date Range", "The Date Range you entered is not valid...", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);
						TelegraphQueue que = new TelegraphQueue();
						que.add(tele);
			        }
			       }
				catch(Exception e4){
					System.err.println(e4);
				}
				
			  
			 }
			}
		  }
		
			
			if (e.getSource() == stmt_full){
				String startdate = JOptionPane.showInputDialog(null, "Enter starting date", date.getText());
				if(startdate != null){
					String endate = JOptionPane.showInputDialog(null, "Enter ending date", date.getText());
				if(endate != null){
				
				
				//Paragraph para = new Paragraph();
				//para.setAlignment(Element.RECTANGLE);
				try{
					Class.forName("org.h2.Driver");
			        Connection conn = DriverManager.
			            getConnection("jdbc:h2:~/kanchiMutt", "sa", "");
			        Statement stmt = conn.createStatement();
			        String st = "select * from bill where dat between '"+startdate+"' and '"+endate+"' and status = 'cleared'";
			        ResultSet rs = stmt.executeQuery(st);
			        String aa,ab,a1,a2,a3,a4,a5,a6,a7;
			        
			      // System.out.println(rs.isBeforeFirst());
			        if (rs.isBeforeFirst()){
			        	PdfWriter writer = null;
						int ret = gen_pdf.showSaveDialog(this);
						File s = null;
						if (ret == JFileChooser.APPROVE_OPTION){
							s = gen_pdf.getSelectedFile();
							String stm = s.toString();
							if(!stm.endsWith(".pdf")){
								s = new File(stm+".pdf");
							}
						
						Document doc = new Document();
						try {
							writer = PdfWriter.getInstance(doc, new FileOutputStream(s));
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (DocumentException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						doc.setPageSize(PageSize.A2);
						//doc.setMargins(36, 72, 108, 180);
						doc.setMarginMirroring(true);
						doc.open();
						com.itextpdf.text.Font n = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 10);
						PdfPTable table = new PdfPTable(9);
						PdfPTable tab = new PdfPTable(2);
						table.setComplete(true);
						table.setWidthPercentage(100);
						table.addCell(new Phrase("Receipt No"));
						table.addCell(new Phrase("Date"));
						table.addCell(new Phrase("N.S No"));
						table.addCell(new Phrase("Name"));
						table.addCell(new Phrase("Address"));
					//	table.addCell(new Phrase("Address Line 2"));
					//	table.addCell(new Phrase("City"));
						table.addCell(new Phrase("Donation Type"));				
						table.addCell(new Phrase("Amount"));
						table.addCell(new Phrase("Payment Mode"));
						table.addCell(new Phrase("Payment Details"));
					//	table.addCell(new Phrase("Date Of Issue"));
				//		table.addCell(new Phrase("Bank"));
				//		table.addCell(new Phrase("Branch"));
						
						table.setHeaderRows(9);
						double cashtot=0, chqtot=0, ddtot=0, total, amt;
			        while(rs.next()){
			        	
			        	 table.addCell(new Phrase(rs.getString(1), n));
			        	 table.addCell(new Phrase(String.valueOf(rs.getDate(2)), n));
			        	 table.addCell(new Phrase(rs.getString(3), n));
			        	 table.addCell(new Phrase(rs.getString(4), n));
			        	// table.addCell(new Phrase(rs.getString(5), n));
			        	// table.addCell(new Phrase(rs.getString(6), n));
			        	// table.addCell(new Phrase(rs.getString(7), n));
			        	 table.addCell(new Phrase(rs.getString(5)+"\n"+rs.getString(6)+"\n"+rs.getString(7), n));
			        	
			        	
			        	
			        	 table.addCell(new Phrase(rs.getString(8), n));
			        	// System.out.println(rs.getString(1)+aa);
			        	 amt = rs.getDouble(9);
			        	 table.addCell(new Phrase(String.valueOf(amt), n));
			        	 String sss = rs.getString(10);
			        	 table.addCell(new Phrase(sss, n));
			        	// table.addCell(new Phrase(String.valueOf(rs.getInt(11)), n));
			        	// table.addCell(new Phrase(rs.getString(12), n));
			        	// table.addCell(new Phrase(rs.getString(13), n));
			        	// table.addCell(new Phrase(rs.getString(14), n));
			        	table.addCell(new Phrase(String.valueOf(rs.getInt(11))+"\n"+rs.getString(12)+"\n"+rs.getString(13)+"\n"+rs.getString(14), n));
			        	
			        	 if (sss.equals("Cash")){
			        		 cashtot += amt;
			        	 } else if(sss.equals("Cheque")){
			        		 chqtot += amt;
			        	 } else if(sss.equals("D.D")){
			        		 ddtot += amt;
			        	 }
			        	 
			        }
			        total = cashtot+chqtot+ddtot;
			        try {
			        	 doc.add(new Phrase("Statement From "+startdate+" To "+endate));
		        		 doc.add(table);
		        		 PdfPCell e1 = new PdfPCell(new Phrase("\0"));
							e1.setColspan(2);
							e1.setBorder(0);
							tab.addCell(e1);
							PdfPCell p2 = new PdfPCell(new Phrase("Total Amount By Cash "));
							p2.setHorizontalAlignment(Element.ALIGN_RIGHT);
							p2.setBorder(0);
							tab.addCell(p2);
							PdfPCell p3 = new PdfPCell(new Phrase(String.valueOf(cashtot)));
							p3.setHorizontalAlignment(Element.ALIGN_RIGHT);
							p3.setBorder(0);
							tab.addCell(p3);
							PdfPCell p5 = new PdfPCell(new Phrase("Total Amount By Cheque "));
							p5.setHorizontalAlignment(Element.ALIGN_RIGHT);
							p5.setBorder(0);
							tab.addCell(p5);
							PdfPCell p4 = new PdfPCell(new Phrase(String.valueOf(chqtot)));
							p4.setHorizontalAlignment(Element.ALIGN_RIGHT);
							p4.setBorder(0);
							tab.addCell(p4);
							PdfPCell p6 = new PdfPCell(new Phrase("Total Amount By D.D "));
							p6.setHorizontalAlignment(Element.ALIGN_RIGHT);
							p6.setBorder(0);
							tab.addCell(p6);
							PdfPCell p7 = new PdfPCell(new Phrase(String.valueOf(ddtot)));
							p7.setHorizontalAlignment(Element.ALIGN_RIGHT);
							p7.setBorder(0);
							tab.addCell(p7);
							PdfPCell p8 = new PdfPCell(new Phrase("Total "));
							p8.setHorizontalAlignment(Element.ALIGN_RIGHT);
							p8.setBorder(0);
							tab.addCell(p8);
							PdfPCell p9 = new PdfPCell(new Phrase(String.valueOf(total)));
							p9.setHorizontalAlignment(Element.ALIGN_RIGHT);
							p9.setBorder(0);
							tab.addCell(p9);
							tab.addCell(e1);
							Phrase p = new Phrase("*** End Of Statement ***");
							PdfPCell p1 = new PdfPCell(p);
							p1.setHorizontalAlignment(Element.ALIGN_CENTER);
							p1.setColspan(2);
							p1.setBorder(0);
							tab.addCell(p1);
							doc.add(tab);
						} catch (DocumentException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					doc.close();
					Telegraph tele = new Telegraph("Success", "Complete Statement generated successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue que = new TelegraphQueue();
					que.add(tele);
			        }} else{
			        	Telegraph tele = new Telegraph("Invalid Date Range", "The Date Range you entered is not valid...", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);
						TelegraphQueue que = new TelegraphQueue();
						que.add(tele);
			        }
				}
				catch(Exception e4){
					System.err.println(e4);
				}
				
				//System.out.println("sucess");
				
				
			 
			}
		  }
		}
			
			if (e.getSource() == recep){
				String r = JOptionPane.showInputDialog(null, "Enter Receipt No.");
				if (r != null){
					try {
						Class.forName("org.h2.Driver");
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        Connection conn = null;
					try {
						conn = DriverManager.
						    getConnection("jdbc:h2:~/kanchiMutt", "sa", "");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        // add application code here
					String re = null, dates = null, nums= null, name = null, addrs1 = null, addrs2 = null, citys = null, types = null, mods = null, dats = null, ban = null, bran = null, stat = null;
					int chqs = 0;
					double amts = 0;
			        try {
						Statement stm = conn.createStatement();
						String st = "select * from bill where receipt = "+Integer.parseInt(r);
						ResultSet rs = stm.executeQuery(st);
						rs.next();
						re = rs.getString(1);
						dates = String.valueOf(rs.getDate(2));
						nums = rs.getString(3);
						name = rs.getString(4);
						addrs1 = rs.getString(5);
						addrs2 = rs.getString(6);
						citys = rs.getString(7);
						types = rs.getString(8);
						amts = rs.getDouble(9);
						mods = rs.getString(10);
						chqs = rs.getInt(11);
						dats = rs.getString(12);
						ban = rs.getString(13);
						bran = rs.getString(14);
						stat = rs.getString(15);
						
						//JOptionPane.showMessageDialog(null, "Saved Successfully...", "Success", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
						if(e2.getErrorCode() == 2000){
							Telegraph tele = new Telegraph("Receipt Not Found", "The Receipt Number you entered is not found...", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);
							TelegraphQueue que = new TelegraphQueue();
							que.add(tele);
						}
						
					}
			        if (stat.equals("cleared")){
			        
					try {
						conn.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						
					}
					
				PdfWriter writer = null;
				
				File s = null;
				try {
					s = File.createTempFile("vrnt_bill", ".pdf");
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				Document doc = new Document();
				try {
					writer = PdfWriter.getInstance(doc, new FileOutputStream(s));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				doc.setPageSize(PageSize.A4);
				//doc.setMargins(36, 72, 108, 180);
				doc.setMarginMirroring(true);
				doc.open();
				com.itextpdf.text.Font fi = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 16);
				com.itextpdf.text.Font n = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 10);
				com.itextpdf.text.Font nb = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD);
				com.itextpdf.text.Font si = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 8);
				com.itextpdf.text.Font ni = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 6);
				PdfPTable tab = new PdfPTable(3);
				PdfPCell a, b, c, d, f, g;
				//tab.setWidths(new int[] {80, 60, 60});
				tab.setWidthPercentage(100);
				
				NumberFormat formatter = new RuleBasedNumberFormat(RuleBasedNumberFormat.SPELLOUT);
				String result = formatter.format(amts);
				Phrase h = new Phrase("SRI GURUBHYO NAMAHA", ni);
				
				Phrase pan = new Phrase("PAN No.: AAATV3147P", ni);
				Phrase vrnt = new Phrase("VEDA RAKSHNA NIDHI TRUST (Regd.)", fi);
				Phrase vrnt_add = new Phrase("No.64/31, SUBRAMANIYAN STREET, WEST MAMBALAM, CHENNAI - 600 033.", ni);
				Phrase mail = new Phrase("e-mail: vrnt@vsnl.net \t Tel: 044-24740549", si);
				Phrase donat = new Phrase(types+" Donation", si);
				//Phrase ph = new Phrase("Tel: 044-24740549");
				a = new PdfPCell(h);
				b = new PdfPCell(pan);
				c = new PdfPCell(vrnt);
				d = new PdfPCell(vrnt_add);
				f = new PdfPCell(mail);
				g = new PdfPCell(donat);
				//g = new PdfPCell(ph);
				a.setColspan(2);
				//b.setColspan(3);
				c.setColspan(3);
				d.setColspan(3);
				f.setColspan(3);
				g.setColspan(3);
				a.setHorizontalAlignment(Element.ALIGN_RIGHT);
				b.setHorizontalAlignment(Element.ALIGN_RIGHT);
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
				d.setHorizontalAlignment(Element.ALIGN_CENTER);
				f.setHorizontalAlignment(Element.ALIGN_CENTER);
				g.setHorizontalAlignment(Element.ALIGN_CENTER);
				//g.setHorizontalAlignment(Element.ALIGN_CENTER);
				
				Phrase t = new Phrase("To", si);
				PdfPCell to = new PdfPCell(t);
				to.setColspan(3);
				
				Phrase nam = new Phrase("     "+name, si);
				Phrase addr1 = new Phrase("       "+addrs1, ni);
				Phrase addr2 = null;
				Phrase city = null;
				if (addrs2.length() != 0){
				addr2 = new Phrase("       "+addrs2, ni);
				city = new Phrase("       "+citys, ni);
				} else{
					addr2 =  new Phrase("       "+citys, ni);
					city = new Phrase("\0", ni);
				}
				
				Phrase rece = new Phrase("Receipt No: "+re, si);
				Phrase dat = new Phrase("Date: "+dates, si);
				Phrase nsno = new Phrase("N.S No: "+nums, si);
				Phrase amt = new Phrase("Donation towards "+types+" fund", si);
				Phrase rs = new Phrase("Rs. "+amts, si);
				Phrase rup = new Phrase("Rupees "+result+" only", si);
				Phrase pay = new Phrase("Payment Details", si);
				Phrase mod = new Phrase("Mode Of Payment: "+mods, si);
				Phrase chq = new Phrase("Cheque / DD No: "+chqs, si);
				Phrase dated = new Phrase("Dated: "+dats, si);
				Phrase bank = new Phrase("Bank: "+ban, si);
				Phrase branch = new Phrase("Branch: "+bran, si);
				Phrase emp = new Phrase("\0");
				Phrase sign_don = new Phrase("Signature of Donor", si);
				Phrase sign_rec = new Phrase("Signature of Receiver", si);
				Phrase trust = new Phrase("Exe. Trustee/Treasurer", si);
				Phrase ins1 = new Phrase("1. Donations to the trust are exempt from income Tax Section 80-G of income Tax Act.", ni);
				Phrase ins2 = new Phrase("2. Please quote your N.S No. given above in future correspondence with us.", ni);
				PdfPCell nam_c = new PdfPCell(nam);
				PdfPCell addr1_c = new PdfPCell(addr1);
				PdfPCell addr2_c = new PdfPCell(addr2);
				PdfPCell city_c = new PdfPCell(city);
				PdfPCell rece_c = new PdfPCell(rece);
				PdfPCell dat_c = new PdfPCell(dat);
				PdfPCell nsno_c = new PdfPCell(nsno);
				PdfPCell amt_c = new PdfPCell(amt);
				PdfPCell rs_c = new PdfPCell(rs);
				PdfPCell rup_c = new PdfPCell(rup);
				PdfPCell pay_c = new PdfPCell(pay);
				PdfPCell mod_c = new PdfPCell(mod);
				PdfPCell chq_c = new PdfPCell(chq);
				PdfPCell dated_c = new PdfPCell(dated);
				PdfPCell bank_c = new PdfPCell(bank);
				PdfPCell branch_c = new PdfPCell(branch);
				PdfPCell empty = new PdfPCell(emp);
				PdfPCell sign_don_c = new PdfPCell(sign_don);
				PdfPCell sign_rec_c = new PdfPCell(sign_rec);
				PdfPCell trust_c = new PdfPCell(trust);
				PdfPCell ins1_c = new PdfPCell(ins1);
				PdfPCell ins2_c = new PdfPCell(ins2);
				empty.setFixedHeight(6);
				nam_c.setColspan(2);
				addr1_c.setColspan(2);
				addr2_c.setColspan(2);
				city_c.setColspan(3);
				amt_c.setColspan(2);
				rup_c.setColspan(3);
				pay_c.setColspan(3);
				chq_c.setColspan(2);
				bank_c.setColspan(2);
				branch_c.setColspan(3);
				empty.setColspan(3);
				ins1_c.setColspan(3);
				ins2_c.setColspan(3);
				a.setBorder(0);
				b.setBorder(0);
				c.setBorder(0);
				d.setBorder(0);
				f.setBorder(0);
				g.setBorder(0);
				empty.setBorder(0);
				to.setBorder(0);
				nam_c.setBorder(0);
				rece_c.setBorder(0);
				addr1_c.setBorder(0);
				dat_c.setBorder(0);
				addr2_c.setBorder(0);
				nsno_c.setBorder(0);
				city_c.setBorder(0);
				amt_c.setBorder(0);
				rs_c.setBorder(0);
				rup_c.setBorder(0);
				pay_c.setBorder(0);
				mod_c.setBorder(0);
				chq_c.setBorder(0);
				dated_c.setBorder(0);
				bank_c.setBorder(0);
				branch_c.setBorder(0);
				sign_don_c.setBorder(0);
				sign_rec_c.setBorder(0);
				trust_c.setBorder(0);
				ins1_c.setBorder(0);
				ins2_c.setBorder(0);
				PdfContentByte cb = writer.getDirectContent();
				cb.roundRectangle(20, doc.getPageSize().getHeight()/2+5, doc.getPageSize().getWidth()-40, doc.getPageSize().getHeight()/2-40, 20);
				cb.roundRectangle(20, 40, doc.getPageSize().getWidth()-40, doc.getPageSize().getHeight()/2-40, 20);
				
				//cb.closePath();
				cb.stroke();
				com.itextpdf.text.Image donor_img = null;
				com.itextpdf.text.Image off_img = null;
				try {
					donor_img = Image.getInstance(this.getClass().getResource("donr.png"));
					off_img = Image.getInstance(this.getClass().getResource("off.png"));
					donor_img.scaleAbsolute(150, 150);
					
					off_img.scaleAbsolute(150, 150);
					donor_img.setAbsolutePosition(120, doc.getPageSize().getHeight()/2+50);
					off_img.setAbsolutePosition(120, 90);
					
				} catch (BadElementException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				} catch (MalformedURLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				} 
				for (int i = 0; i < 2; i++){
				tab.addCell(a);
				tab.addCell(b);
				tab.addCell(c);
				tab.addCell(d);
				tab.addCell(f);
				tab.addCell(g);
				tab.addCell(empty);
				tab.addCell(to);			
				tab.addCell(nam_c);
				tab.addCell(rece_c);
				tab.addCell(addr1_c);
				tab.addCell(dat_c);
				tab.addCell(addr2_c);
				tab.addCell(nsno_c);
				tab.addCell(city_c);
				tab.addCell(empty);
				tab.addCell(amt_c);
				tab.addCell(rs_c);
				tab.addCell(rup_c);
				tab.addCell(empty);
				tab.addCell(pay_c);
				tab.addCell(mod_c);
				tab.addCell(chq_c);
				tab.addCell(dated_c);
				tab.addCell(bank_c);
				tab.addCell(branch_c);
				tab.addCell(empty);
				tab.addCell(sign_don_c);
				tab.addCell(sign_rec_c);
				tab.addCell(trust_c);
				tab.addCell(empty);
				tab.addCell(ins1_c);
				tab.addCell(ins2_c);
				if (i == 0){
					tab.addCell(empty);
					//tab.addCell(empty);
				}
				}
				//tab.addCell(g);
				try {
					doc.add(tab);
					doc.add(donor_img);
					doc.add(off_img);
				} catch (DocumentException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				Telegraph tele = new Telegraph("Success", "Receipt generated successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
				TelegraphQueue quee = new TelegraphQueue();
				quee.add(tele);
				
				try {
					Desktop.getDesktop().open(s);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				s.deleteOnExit();
				doc.close(); 
			        } else {
			        	Telegraph tele = new Telegraph("Receipt Cancelled", "The Receipt is cancelled", TelegraphType.NOTIFICATION_ERROR, WindowPosition.BOTTOMRIGHT, 4000);
						TelegraphQueue quee = new TelegraphQueue();
						quee.add(tele);
			        }
				}
			}
		
	}


}
