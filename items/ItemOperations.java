package com.brainmentors.items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemOperations {
	private ItemOperations(){}
	private ArrayList<Item> itemList = new ArrayList<>();
	private static ItemOperations itemOperations;
	
	public String addItem(Item item){
		this.itemList.add(item);
		return "Item Added...";
	}
	
	void makesync() {
//		synchronized (itemList) {
//			itemList.add(new Item());
//		}
		List<Item>itemList2=Collections.synchronizedList(itemList) ;
	}
	
	public double sum() {
		double sum = 0.0;
//		for(Item item : itemList){
//			 sum += item.getPrice();
//		}
		return itemList.stream().mapToDouble(item->item.getPrice()).sum();
	}
	
	public void sort() {
//		Collections.sort(itemList,new SortByName());						//By creating other class
//		Collections.sort(itemList,new Comparator<Item>(){					//Anonymous class
//		@Override
//		public int compare(Item one, Item two){
//		return one.getName().compareToIgnoreCase(two.getName());
//		}
//    		return two.getName().compareToIgnoreCase(one.getName());
//			}
//		});
		Collections.sort(itemList,(one,two)->one.getName().compareTo(two.getName()));   //Lambda 
	}
	
	public boolean remove(int id,String name) {
		int index=this.searchItem(id, name);
		if(index>=0) {
			itemList.remove(index);
			return true;
		}
		return false;
	}
	
	public int searchItem(int id,String name) {
		Item item = new Item();
		item.setId(id);
		item.setName(name);
		return itemList.indexOf(item);
	}
	
	public ArrayList<Item> getItems(){
		return itemList;
	}
	
	public static ItemOperations getInstance(){
		if(itemOperations==null){
			itemOperations = new ItemOperations();
		}
		return itemOperations;
	}
}
