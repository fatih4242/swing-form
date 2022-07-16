import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.*;

import org.json.*;

public class Main {
	static BufferedReader br;
	static URL url;
	public static void main(String[] args)  {
		JFrame frame = new JFrame();
		
		
		
		
		
		try {
			url = new URL("https://tokeryazilim.com/api/form.php");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			br = new BufferedReader(new InputStreamReader(url.openStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String line = "";
		StringBuffer response = new StringBuffer();
		try {
			while((line = br.readLine()) != null) {
				response.append(line);
				
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			//telefon, eposta, mesaj
			JSONArray a = new JSONArray(response.toString());
			String[][] value = new String[a.length()][3];
			String[] head = {"telefon","eposta", "mesaj"};
			int b = 0;
			for(int i = 0; i< a.length() ; i++) {
				String telefon = (String) a.getJSONObject(i).get("telefon");
				String eposta = (String) a.getJSONObject(i).get("eposta");
				String mesaj = (String) a.getJSONObject(i).get("mesaj");
				
				for(int j = 0; j< 3; j++) {
					
					
					if(j == 0) {
						value[b][j] = telefon;
					}else if(j==1){
						value[b][j] = eposta;
					}else if(j == 2) {
						value[b][j] = mesaj;
					}
					
					
				}
				b++;
				
				
				}
			
			JTable table = new JTable(value, head);
			
			table.setBounds(0,0, 800,700);
			JScrollPane scroll = new JScrollPane(table);
			
			JButton btn = new JButton("Choose");
			btn.setBounds(700,800, 100,100);
			
			btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					ArrayList<String> str = new ArrayList<>();  
					
					String txt = "";
					StringBuffer str2 = new StringBuffer();

					for(int i = 0; i <3; i++) {
						 //txt = table.getModel().getValueAt(table.getSelectedRow(), i).toString();
						if(i == 0) {
							 str2.append("Telefon : "+table.getModel().getValueAt(table.getSelectedRow(), i).toString()+"\n");

						}else if(i == 1) {
							 str2.append("Eposta : "+table.getModel().getValueAt(table.getSelectedRow(), i).toString()+"\n");

						}else if(i == 2) {
							 str2.append("Mesaj : "+table.getModel().getValueAt(table.getSelectedRow(), i).toString()+"\n");

						}
						//str.add(table.getModel().getValueAt(table.getSelectedRow(), i).toString());
						
					}
					JOptionPane.showMessageDialog(frame, str2.toString());
				}
				
			});
			
			
			frame.add(btn);
			frame.add(scroll);
			
			
			
			//JSONObject j = new JSONObject(response.toString());
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		frame.setSize(800,800);
		frame.setVisible(true);
	}

}
