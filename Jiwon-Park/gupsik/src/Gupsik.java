import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Gupsik extends JFrame{
	/**
	 *
	 * MADE BY JIWON PARK
	 * 2607 ������ ��ü�������α׷��� ����
	 * 
	 */
	//���� ����
	private static final long serialVersionUID = -3116822371356086835L;
	int year, month, date;
	Calendar cal= Calendar.getInstance();
	int n=0;
	Toolkit tools = Toolkit.getDefaultToolkit();
	Dimension screen = tools.getScreenSize();
	Container c;
	//�̹����� �޾ƿɽô�.
	Image lefti = tools.getImage(getClass().getResource("left.png"));
	Image righti = tools.getImage(getClass().getResource("right.png"));
	ImageIcon L = new ImageIcon(lefti);
	ImageIcon R = new ImageIcon(righti);
	final int y= cal.get(Calendar.YEAR), m=cal.get(Calendar.MONTH)+1, d=cal.get(Calendar.DATE);
	public Gupsik(){
		//������
		year =cal.get(Calendar.YEAR);
		month=cal.get(Calendar.MONTH)+1;
		date=cal.get(Calendar.DATE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(screen.width-130, screen.height/2);
		c= getContentPane();
		c.setLayout(new GridLayout(1,5,0,0));
		this.getRootPane().setBackground(Color.white);
		c.setBackground(Color.white);
		this.setBackground(Color.white);
	}
	
	boolean dateinmonth(int month, int date){
		//�� ��¥�� ���� �޿� �ִ��� �˾Ƴ���.
		if(date<1)return false;
		if(month == 1||month == 3||month == 5||month == 7||month == 8||month == 10||month == 12){
			if(date>31)return false;
		}
		else if(month==2){
			//GregorianCalendar Ŭ������ ���� �����ϴ�.
			GregorianCalendar gr = new GregorianCalendar();
			if(gr.isLeapYear(cal.get(Calendar.YEAR))){
				if(date>29)return false;
			}
			else if(date>28)return false;
		}
		else{
			if(date>30)return false;
		}
		return true;
		//end of function
	}
	
	void run(){
		//�޾ƿ��ٰ� �ٸ� �޷� �Ѿ�� ��ƾߵ�
		if(!dateinmonth(month, date+n)){
			if(date+n<1){
				month--;
				cal.set(year, month-1, 1);
				date = cal.getActualMaximum(Calendar.DATE)-n;
				cal=Calendar.getInstance();
			}
			else {
				month++;
				date=1-n;
			}
		}
		//�޾ƿ� url
		String url="http://kwangju-s.hs.kr/xboard/board.php?mode=list&tbnum=44&sCat=0&"
				+ "page=1&keyset=&searchword=&mode1=&sYear="+
				year+"&sMonth="+mon(month)+"&sDay="+mon(date+n);
		
		JMenuItem r = new JMenuItem("��¥ �Է�");
		r.setFont(new Font("����", Font.PLAIN, 15));
		r.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String a = JOptionPane.showInputDialog(null, "���� : x�� x (���� �߿�)", "��¥�� �Է��ϼ���", JOptionPane.QUESTION_MESSAGE);
				try{
					int index=0;
					while(a.charAt(index)!='��')index++;
					int m = Integer.parseInt(a.substring(0, index));
					index+=2;
					int d = Integer.parseInt(a.substring(index));
					if(dateinmonth(m, d)){
						cal.set(year, m, d);
						date = d-n;
						month = m;
						c.removeAll();
						run();
					}
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, "��Ŀ� ���� �ʽ��ϴ�",null, JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		
		JMenuBar menu = new JMenuBar();
		r.setBackground(Color.white);
		r.setBorderPainted(false);
		menu.add(r);
		menu.setBackground(Color.WHITE);
		menu.setBorderPainted(false);
		setJMenuBar(menu);
		//���� ������ ��ư �����
		JButton left = new JButton((Icon) L);
		left.setActionCommand("left");
		left.setBorderPainted(false);
		left.setFocusPainted(false);
		left.addActionListener(new ButtonAction());
		left.setBackground(Color.white);
		JButton right = new JButton (R);
		right.setActionCommand("right");
		right.setBorderPainted(false);
		right.setFocusPainted(false);
		right.setBackground(Color.white);
		right.addActionListener(new ButtonAction());
		c.add(left);
		//�޽��� �޾ƿ���
		Vector<String> gupsik = getGupsik(url, month, date+n);
		
		//��ɾ��� ��ħ�������� ���п� ���๮�� �������� �������ϱ� ����
		for(int i=0; i<gupsik.size()-1; i++){
			if(gupsik.get(i).equals("") && gupsik.get(i+1).equals(""))
				gupsik.remove(i);
		}
		
		//�����̸� Ÿ��Ʋ�� ������ �޽�, �ƴϸ� �������� �޽�
		if(y==year && m==month && d ==date+n){
			this.setTitle("������ �޽� - "+Integer.toString(month)+"�� "+date+"��" + " (made by Jiwon Park)");
		}
		else{
			this.setTitle("�������� �޽� - "+Integer.toString(month)+"�� "+(date+n)+"��"+" (made by Jiwon Park)");
		}
		//�޽��� ���� ����?
		try {if(gupsik.get(0).equals("int")){
			c.removeAll();
			JLabel lb = new JLabel("���ͳ� ���� Ȯ������",JLabel.CENTER);
			lb.setFont(new Font("����", Font.BOLD, 100));
			c.add(lb);
			this.setVisible(true);
			return;
		}
		}
		catch (ArrayIndexOutOfBoundsException e){
			
		}
		if(gupsik.size()<3){
			JLabel label = new JLabel("<html>�� ���� �޽��� ���ų� ��ϵ��� �ʾҽ��ϴ�.</html>", JLabel.CENTER);
			label.setFont(new Font("���� ���", Font.BOLD, 40));
			add(label);
			add(right);
			setVisible(true);
			return;
		}
		
		// ���
		JPanel[] panel = new JPanel[3];
		for(int i=0; i<3; i++)
			panel[i]=new JPanel(new GridLayout(0,1, 10, 0));
		JLabel l = new JLabel("��ħ", JLabel.CENTER);
		l.setFont(new Font("����", Font.PLAIN, 30));
		l.setOpaque(true);
		l.setBackground(Color.white);
		panel[0].add(l);
		int k=0;
		//�޽� ���� ����� ��ħ�� ���� ���๮�� �޾Ƴ����� �� �����ؾߵ�
		while(gupsik.get(k)==""){
			gupsik.remove(k);
		}
		//���� ������ ������ for-each
		for(String element : gupsik){
			//��ħ ���� ������ ���๮�� �ΰ��� ���еǹǷ� �� ���̸� split�ϸ� �߰��� �� ���ڿ��� ������ �ȴ�.
			//���ڿ��� <html></html>�� �θ� �ڵ��ٹٲ��� �ȴ�.
			if(element.equals("")){
				k++;
				if(k==1){
					JLabel label = new JLabel("<html>����</html>", JLabel.CENTER);
					label.setFont(new Font("����", Font.PLAIN,30));
					label.setOpaque(true);
					label.setBackground(Color.white);
					panel[k].add(label);
				}
				else if (k==2){
					JLabel label = new JLabel("<html>����</html>", JLabel.CENTER);
					label.setFont(new Font("����", Font.PLAIN,30));
					label.setOpaque(true);
					label.setBackground(Color.white);
					panel[k].add(label);
				}
				
				
				continue;
			}
			//�޴� �߰�
			JLabel label = new JLabel("<html>"+element+"</html>", JLabel.CENTER);
			label.setFont(new Font("����", Font.PLAIN,30));
			label.setOpaque(true);
			label.setBackground(Color.white);
			panel[k].add(label);
		}
		if(k==1){
			//���Ḹ ���� ���� ��ǥ������ �����, �ƴ� ����Ϲۿ� ����.
			JLabel label = new JLabel ("<html>������ ������̶� ����</html>", JLabel.CENTER);
			label.setFont(new Font("����", Font.PLAIN, 30));
			label.setOpaque(true);
			label.setBackground(Color.white);
			panel[2].add(label);
		}
		add(panel[0]);
		add(panel[1]);
		add(panel[2]);
		c.add(right);
		setVisible(true);
		return;
		//end of function
	}
	
	public static void main(String[] args) {
		//���α׷��� ������
		Gupsik g= new Gupsik();
		g.run();
		return;
	}
	
	public String mon(int month){
		//5���� 05�� �ٲ۴�.
		if(month<10)return "0"+month;
		else return Integer.toString(month);
	}
	
	
    public Vector<String> getGupsik(String urlToRead, int month, int date) {
    	//�޽��� ����
    	this.setTitle("LOADING.....");
        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String line;
        Vector<String> result= new Vector<String>();
        int mode=0;
        //System.out.println("		"+ yoil + "(" + month + "/" + (date)+")");
        try {
            url = new URL(urlToRead);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            //Reading & Parsing
            while ((line = rd.readLine()) != null) {
            	if(line.equals("			</li>"))mode=0;
            	try{
            		int index =0;
            		while(line.charAt(index)!='(')index++;
            		int index1 = index;
            		while(line.charAt(index1)!=')')index1++;
            		String l = line.substring(index, index1+1);
            		if(l.equals("(" + month + "/" + (date)+")")){
            			mode=1;
            			//Ȩ������ ��Ŀ� ���� �Ľ�
            			rd.readLine();
            			rd.readLine();
            			rd.readLine();
            			rd.readLine();
            			rd.readLine();
            			rd.readLine();
            			rd.readLine();
            			line=rd.readLine();
            			int index2=0, index3=0;
            			while(line.charAt(index2++)!='>');
            			index3=index2;
            			while(line.charAt(index3++)!='<');
            			line=line.substring(index2, index3-1);
            			line = linetrace(line);
            			result.add(line);
            			continue;
            		}
            	}
            	catch(Exception e){}
            	
            	if(mode==1){
            		int index4=0;
            		while(line.charAt(index4++)!='<');
            		line=line.substring(0, index4-1);
            		line = linetrace(line);
            		result.add(line);
            	}
            }
            rd.close();
        } 
        //������ ���� ���� ������ ����ϰ� ���
        catch(StringIndexOutOfBoundsException e){
        	return new Vector<String>();
        }
        catch(NoRouteToHostException e){
        	Vector<String> a = new Vector<String>();
        	a.add("int");
        	return a;
        }
        catch(UnknownHostException e){
        	Vector<String> a = new Vector<String>();
        	a.add("int");
        	return a;
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        //�޽� �޴� ��ȯ
        return result;
        //end of function
    }
    
    String linetrace(String a){
    	//�����ڵ�� ���ϴ� ���� ex)'��' �� �ǵ�����.
    	//����� ������ ���ڴ� ����� ��ǻ�� ����
    	String result="";
    	for(int i=0; i<a.length(); i++){
    		if(a.charAt(i)=='&'){
    			int k=i;
    			if(a.charAt(i+1)!='#' || i+1>=a.length()){
    				result+=a.charAt(i);
    				continue;
    			}
    			while(a.charAt(k)!=';'){
    				if(k+1>=a.length())break;
    				k++;
    			}
    			//��ȯ
    			String temp = a.substring(i+2, k);
    			int intv = Integer.parseInt(temp);
    			result += (char)intv;
    			i=k+1;
    		}
    		else result +=a.charAt(i);
    	}
    	return result;
    	//end of function
    }
    
    class ButtonAction implements ActionListener{
		//������ ���� ��ư �������� Action
    	@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton btn = (JButton)arg0.getSource();
			if(btn.getActionCommand().equals("left")){
				n--;
				c.removeAll();
			}
			else{
				n++;
				c.removeAll();
			}
			run();
		}
    	//end of class
    } 
    //end of class
}//end of program
/*public String getyoil(int i){
//cal.get(Calendar.DAY_OF_WEEK) �� �ѱ��� ���Ϸ� �ٲ۴�.
if(i>=8){
	while(i>=8)i-=7;
}
if(i<1){
	while(i<1)i+=7;
}
switch(i){
case 1:
	return "��";
case 2:
	return "��";
case 3:
	return "ȭ";
case 4:
	return "��";
case 5:
	return "��";
case 6:
	return "��";
default:
	return "��";
}
}
*/