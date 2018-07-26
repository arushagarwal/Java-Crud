package com.brainmentors.views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.brainmentors.items.Item;
import com.brainmentors.items.ItemOperations;

public class ItemCRUD extends JFrame {

	private JPanel contentPane;
	private JTextField id;
	private JTextField name;
	private JTextField price;
	private JTextField qt;
	private JTable table;
	JScrollPane scrollPane = new JScrollPane();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemCRUD frame = new ItemCRUD();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ItemCRUD() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 557);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblItemMaster = new JLabel("Item Master");
		lblItemMaster.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemMaster.setBounds(161, 17, 149, 29);
		contentPane.add(lblItemMaster);
		
		id = new JTextField();
		id.setBounds(171, 44, 188, 26);
		contentPane.add(id);
		id.setColumns(10);
		
		JLabel lblItemid = new JLabel("ItemId");
		lblItemid.setBounds(91, 49, 61, 16);
		contentPane.add(lblItemid);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(171, 92, 188, 26);
		contentPane.add(name);
		
		price = new JTextField();
		price.setColumns(10);
		price.setBounds(171, 142, 188, 26);
		contentPane.add(price);
		
		qt = new JTextField();
		qt.setColumns(10);
		qt.setBounds(171, 189, 188, 26);
		contentPane.add(qt);
		
		JLabel namelbl = new JLabel("name");
		namelbl.setBounds(91, 97, 61, 16);
		contentPane.add(namelbl);
		
		JLabel pricelbl = new JLabel("price");
		pricelbl.setBounds(91, 147, 61, 16);
		contentPane.add(pricelbl);
		
		JLabel qtlbl = new JLabel("quantity");
		qtlbl.setBounds(91, 194, 61, 16);
		contentPane.add(qtlbl);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			addNewItem();
			}
		});
		btnAdd.setIcon(new ImageIcon(ItemCRUD.class.getResource("/com/brainmentors/views/add.png")));
		btnAdd.setBounds(17, 227, 135, 84);
		contentPane.add(btnAdd);
		
		JButton printBt = new JButton("Print");
		printBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			showList();
			}
		});
		printBt.setBounds(158, 229, 129, 73);
		contentPane.add(printBt);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(17, 328, 494, 201);
		contentPane.add(scrollPane);
		
		table = new JTable(new MyTableModel());
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			searchItem();
			}
		});
		btnNewButton.setBounds(297, 227, 117, 65);
		contentPane.add(btnNewButton);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteRecord();
			}
		});
		btnDelete.setBounds(412, 227, 117, 29);
		contentPane.add(btnDelete);
		
		JButton btnSort = new JButton("Sort");
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			sortByPrice();
			}
		});
		btnSort.setBounds(372, 165, 117, 29);
		contentPane.add(btnSort);
		
		JButton btnSum = new JButton("Sum");
		btnSum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			sum();
			}
		});
		btnSum.setBounds(371, 111, 117, 29);
		contentPane.add(btnSum);
		
		
		lblTotal.setBounds(378, 65, 61, 16);
		contentPane.add(lblTotal);
	}
	JLabel lblTotal = new JLabel("Total");
	private void sum(){
			
		 lblTotal.setText(String.valueOf(ItemOperations.getInstance().sum()));
		
	}
	
	private void sortByPrice(){
		ItemOperations.getInstance().sort();
		table.setModel(new MyTableModel());
		
	}
	
	private void deleteRecord(){
		ItemOperations itemOperation = ItemOperations.getInstance();
		boolean result = itemOperation.remove(Integer.parseInt(id.getText()), name.getText());
		if(result){
			JOptionPane.showMessageDialog(this, "Record Deleted");
			table.setModel(new MyTableModel());
		}
		else{
			JOptionPane.showMessageDialog(this, "No record found...");
		}
	}
	
	private void searchItem(){
		String result = ItemOperations.
				getInstance().
				searchItem(Integer.parseInt(id.getText()),name.getText())>=0?"Found":"Not Found...";
	JOptionPane.showMessageDialog(this, result);
	}
	
	private void showList(){
		System.out.println(ItemOperations.getInstance().getItems().toString());
		//table = new JTable(new MyTableModel());
	}
	
	private void addNewItem(){
		int itemId  = Integer.parseInt(id.getText());
		String itemName = name.getText();
		double itemPrice = Double.parseDouble(price.getText());
		int quantity = Integer.parseInt(qt.getText());
		Item item = new Item(itemId, itemName,itemPrice,quantity);
		ItemOperations itemOperations = ItemOperations.getInstance();
		/*ItemOperations itemOperations2 = ItemOperations.getInstance();
		if(itemOperations==itemOperations2){
			System.out.println("Same Ref");
		}
		else{
			System.out.println("Not Same Ref");
		}*/
		String result = itemOperations.addItem(item);
		JOptionPane.showMessageDialog(this,result);
		table.setModel(new MyTableModel());
	}
}