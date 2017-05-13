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
	 * 2607 박지원 객체지향프로그래밍 과제
	 * 
	 */
	//사전 변수
	private static final long serialVersionUID = -3116822371356086835L;
	int year, month, date;
	Calendar cal= Calendar.getInstance();
	int n=0;
	Toolkit tools = Toolkit.getDefaultToolkit();
	Dimension screen = tools.getScreenSize();
	Container c;
	//이미지를 받아옵시다.
	Image lefti = tools.getImage(getClass().getResource("left.png"));
	Image righti = tools.getImage(getClass().getResource("right.png"));
	ImageIcon L = new ImageIcon(lefti);
	ImageIcon R = new ImageIcon(righti);
	final int y= cal.get(Calendar.YEAR), m=cal.get(Calendar.MONTH)+1, d=cal.get(Calendar.DATE);
	public Gupsik(){
		//생성자
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
		//이 날짜가 현재 달에 있는지 알아낸다.
		if(date<1)return false;
		if(month == 1||month == 3||month == 5||month == 7||month == 8||month == 10||month == 12){
			if(date>31)return false;
		}
		else if(month==2){
			//GregorianCalendar 클래스는 정말 좋습니다.
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
		//받아오다가 다른 달로 넘어가면 잡아야됨
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
		//받아올 url
		String url="http://kwangju-s.hs.kr/xboard/board.php?mode=list&tbnum=44&sCat=0&"
				+ "page=1&keyset=&searchword=&mode1=&sYear="+
				year+"&sMonth="+mon(month)+"&sDay="+mon(date+n);
		
		JMenuItem r = new JMenuItem("날짜 입력");
		r.setFont(new Font("바탕", Font.PLAIN, 15));
		r.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String a = JOptionPane.showInputDialog(null, "형식 : x월 x (띄어쓰기 중요)", "날짜를 입력하세요", JOptionPane.QUESTION_MESSAGE);
				try{
					int index=0;
					while(a.charAt(index)!='월')index++;
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
					JOptionPane.showMessageDialog(null, "양식에 맞지 않습니다",null, JOptionPane.ERROR_MESSAGE);
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
		//왼쪽 오른쪽 버튼 만들기
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
		//급식을 받아오자
		Vector<String> gupsik = getGupsik(url, month, date+n);
		
		//양심없이 아침점심저녁 구분에 개행문자 세개쓰면 에러나니까 잡자
		for(int i=0; i<gupsik.size()-1; i++){
			if(gupsik.get(i).equals("") && gupsik.get(i+1).equals(""))
				gupsik.remove(i);
		}
		
		//오늘이면 타이틀이 오늘의 급식, 아니면 언젠가의 급식
		if(y==year && m==month && d ==date+n){
			this.setTitle("오늘의 급식 - "+Integer.toString(month)+"월 "+date+"일" + " (made by Jiwon Park)");
		}
		else{
			this.setTitle("언젠가의 급식 - "+Integer.toString(month)+"월 "+(date+n)+"일"+" (made by Jiwon Park)");
		}
		//급식이 없을 때는?
		try {if(gupsik.get(0).equals("int")){
			c.removeAll();
			JLabel lb = new JLabel("인터넷 연결 확인좀요",JLabel.CENTER);
			lb.setFont(new Font("바탕", Font.BOLD, 100));
			c.add(lb);
			this.setVisible(true);
			return;
		}
		}
		catch (ArrayIndexOutOfBoundsException e){
			
		}
		if(gupsik.size()<3){
			JLabel label = new JLabel("<html>이 날은 급식이 없거나 등록되지 않았습니다.</html>", JLabel.CENTER);
			label.setFont(new Font("맑은 고딕", Font.BOLD, 40));
			add(label);
			add(right);
			setVisible(true);
			return;
		}
		
		// 출력
		JPanel[] panel = new JPanel[3];
		for(int i=0; i<3; i++)
			panel[i]=new JPanel(new GridLayout(0,1, 10, 0));
		JLabel l = new JLabel("아침", JLabel.CENTER);
		l.setFont(new Font("바탕", Font.PLAIN, 30));
		l.setOpaque(true);
		l.setBackground(Color.white);
		panel[0].add(l);
		int k=0;
		//급식 쓰는 사람이 아침밥 전에 개행문자 달아놓으면 다 제거해야됨
		while(gupsik.get(k)==""){
			gupsik.remove(k);
		}
		//쉽고 빠르고 간편한 for-each
		for(String element : gupsik){
			//아침 점심 저녁은 개행문자 두개로 구분되므로 그 사이를 split하면 중간에 빈 문자열이 나오게 된다.
			//문자열을 <html></html>로 싸면 자동줄바꿈이 된다.
			if(element.equals("")){
				k++;
				if(k==1){
					JLabel label = new JLabel("<html>점심</html>", JLabel.CENTER);
					label.setFont(new Font("바탕", Font.PLAIN,30));
					label.setOpaque(true);
					label.setBackground(Color.white);
					panel[k].add(label);
				}
				else if (k==2){
					JLabel label = new JLabel("<html>저녁</html>", JLabel.CENTER);
					label.setFont(new Font("바탕", Font.PLAIN,30));
					label.setOpaque(true);
					label.setBackground(Color.white);
					panel[k].add(label);
				}
				
				
				continue;
			}
			//메뉴 추가
			JLabel label = new JLabel("<html>"+element+"</html>", JLabel.CENTER);
			label.setFont(new Font("바탕", Font.PLAIN,30));
			label.setOpaque(true);
			label.setBackground(Color.white);
			panel[k].add(label);
		}
		if(k==1){
			//저녁만 없을 때는 대표적으로 퇴사일, 아니 퇴사일밖에 없다.
			JLabel label = new JLabel ("<html>저녁은 퇴사일이라서 없음</html>", JLabel.CENTER);
			label.setFont(new Font("바탕", Font.PLAIN, 30));
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
		//프로그램의 진입점
		Gupsik g= new Gupsik();
		g.run();
		return;
	}
	
	public String mon(int month){
		//5월을 05로 바꾼다.
		if(month<10)return "0"+month;
		else return Integer.toString(month);
	}
	
	
    public Vector<String> getGupsik(String urlToRead, int month, int date) {
    	//급식을 받자
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
            			//홈페이지 양식에 따라 파싱
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
        //에러가 나면 에러 정보를 출력하고 계속
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
        //급식 메뉴 반환
        return result;
        //end of function
    }
    
    String linetrace(String a){
    	//유니코드로 깨니는 문자 ex)'' 을 되돌린다.
    	//출력이 깨지는 문자는 당신의 컴퓨터 문제
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
    			//변환
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
		//오른쪽 왼쪽 버튼 눌렀을때 Action
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
