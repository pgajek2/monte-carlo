import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.omg.PortableServer.ServantLocator;
import java.math.*;
import java.lang.Math;

public class GUI
{
	JFrame frame;
	JPanel sterowanie;
	JPanel show;
	JLabel info;
	JRadioButton moore;
	ButtonGroup sasiedztwa;
	JRadioButton periodyczne;
	JRadioButton nieperiodyczne;
	JTextField N;
	
	JTextField roz1, roz2;
	JLabel nap1;

    JButton[][] tab ;
    JButton pokaz;
	JButton reset;
	JButton ok;
    JRadioButton radio1;
	JRadioButton radio2;
	JRadioButton radio3;
	JRadioButton radio4;
	ButtonGroup group;
	Controller controller;
    int n,m;
	double k1=10,k2=10;
	int global = 0;
	int znumber;
	Map<Integer,Integer> ID = new HashMap<Integer,Integer>();
	Map<Integer,Color> ID_Color = new HashMap<Integer,Color>();

    ActionListener actioncreate = new ActionListener() {
		@Override
	public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(radio1.isSelected())
			{
				uruchom(25,25);
			}
			else if(radio2.isSelected())
			{
				uruchom(50,50);
			}
			else if(radio3.isSelected())
			{
				uruchom(100,100);
			}
			else if(radio4.isSelected())
			{
				uruchom(150,150);
			}
			else
			{
				n=Integer.parseInt(roz1.getText());
				m=Integer.parseInt(roz2.getText());;
				uruchom(n,m);
			}
			pokaz.setEnabled(false);
			show.setVisible(true);
			radio1.setEnabled(false);
			radio2.setEnabled(false);
			radio3.setEnabled(false);
			radio4.setEnabled(false);
			reset.setEnabled(true);
			ok.setEnabled(true);
		}
	};
	MouseListener clik = new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			String command = ((JButton) e.getSource()).getActionCommand();
			Integer pom = Integer.parseInt(command);
			int a=0,b=0;
			 int counter = 0;
			 int i=0;
			 int j=0;
			for(i = 0;i<n;i++)
			 {
				 for(j=0;j<m;j++)
				 {
					 if(counter == pom)
					 {
						 a=i;
						 b=j;
					 }
					 counter++;
				 }
			 }
			
			System.out.println(controller.energia[a][b]+" "+controller.moore(a, b, true));
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	};
	ActionListener actionreset = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(int i=0;i<n;i++)
				{
					for(int j=0;j<m;j++)
					{
						controller.board[i][j]=0;
						tab[i][j].setBackground(Color.WHITE);
					}
				}
				global=0;
				info.setText("-- Informations --");
			}
		};
		
	
	ActionListener actionstart = new ActionListener() {
		int a=0;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			a = Integer.parseInt(N.getText());
			for(int i=0;i<a;i++)
			{
				global++;
				ID.put(global, global);
				controller.ID.put(global, global);
				Color color = randcolor();
			    ID_Color.put(global, color);
			}
			
			Random generator = new Random();
			for(int i=0;i<n;i++)
			{
				for(int j=0;j<m;j++)
				{
					controller.board[i][j] = generator.nextInt(a)+1;
					tab[i][j].setBackground(ID_Color.get(ID.get(controller.board[i][j])));	
				}
			}
			for(int i=0;i<n;i++)
			{
				for(int j=0;j<m;j++)
				{
					//controller.energia[i][j]=controller.moore(i, j, true);
				}
			}
			
		}
	};
	
	MouseWheelListener l = new MouseWheelListener() { //krecac kólkiem spowodujemy rozrost ziaren
			@Override
				public void mouseWheelMoved(MouseWheelEvent e) {
					// TODO Auto-generated method stub
					startcount();
				}
			};
		
	GUI()//konstruktor GUI
	{
		frame = new JFrame("Rozrost ziaren");
		sterowanie = new JPanel();
		show = new JPanel();	
		moore = new JRadioButton("Moore");
		sasiedztwa = new ButtonGroup();
		periodyczne = new JRadioButton("Periodical");
		nieperiodyczne = new JRadioButton("Non-perdiodical");
		radio1 = new JRadioButton("25 x 25");
		radio2 = new JRadioButton("50 x 50");
		radio3 = new JRadioButton("100 x 100");
		radio4 = new JRadioButton("150 x 150");
		group = new ButtonGroup();
		N = new JTextField("10");
		roz1 = new JTextField("50");
		roz2 = new JTextField("50");
		nap1 = new JLabel("n:");
		info = new JLabel("Informations");
		pokaz = new JButton("Start");
		reset = new JButton("Resetuj");
		ok = new JButton("Run");
		global = 0;	
		moore.setSize(120, 20);
		moore.setLocation(50, 40);
		moore.doClick();
		sasiedztwa.add(moore);
		periodyczne.setSize(120, 20);
		periodyczne.setLocation(180, 20);
		periodyczne.doClick();
		nieperiodyczne.setSize(120,20);
		nieperiodyczne.setLocation(180, 40);
		N.setSize(60, 20);
		N.setLocation(230, 80);
		nap1.setSize(60,20);
		nap1.setLocation(200, 80);
		ok.setSize(60,20);
		ok.setLocation(215,130);
		ok.addActionListener(actionstart);
		ok.setEnabled(false);
		radio1.setSize(100, 20);
		radio1.setLocation(440, 30);	
		radio2.setSize(100, 20);
		radio2.setLocation(440, 50);	
		radio3.setSize(100, 20);
		radio3.setLocation(440, 70);
		radio4.setSize(100, 20);
		radio4.setLocation(440, 90);
		group.add(radio1);
		group.add(radio2);
		group.add(radio3);
		group.add(radio4);
		roz1.setSize(60,20);
		roz1.setLocation(440, 130);
		roz2.setSize(60,20);
		roz2.setLocation(500, 130);
		pokaz.setSize(100, 50);
		pokaz.setLocation(580, 20);
		pokaz.addActionListener(actioncreate);
		reset.setSize(100, 50);
		reset.setLocation(580, 70);
		reset.addActionListener(actionreset);
		reset.setEnabled(false);
		info.setSize(200,20);
		info.setLocation(300, 160);
		info.setFont(new Font( null, 1, 14));
		sterowanie.add(moore);
		sterowanie.add(periodyczne);
		sterowanie.add(nieperiodyczne);
		sterowanie.add(N);
		sterowanie.add(radio1);
		sterowanie.add(radio2);
		sterowanie.add(radio3);
		sterowanie.add(radio4);
		sterowanie.add(pokaz);
		sterowanie.add(reset);
		sterowanie.add(nap1);
		sterowanie.add(ok);
		sterowanie.add(info);
		sterowanie.add(roz1);
		sterowanie.add(roz2);
		sterowanie.setLayout(null);
		sterowanie.setSize(800,200);
		sterowanie.setLocation(0, 0);
		show.setLayout(null);
		show.setSize(800,800);
		show.setLocation(100, 220);
		show.addMouseWheelListener(l);
		frame.setLayout(null);
		frame.add(sterowanie);
		frame.setSize(800, 900);
		frame.setLocation(200, 50);
		frame.setVisible(true);
	}
	
	void uruchom(int a, int b)
	{
		n=a;
		m=b;
		k1=(25.0/n)*24.0;
		k2=(25.0/m)*24.0;
		tab = new JButton[n][m];
		controller = new Controller(n,m);
		controller.ID.put(global, global);
		ID.put(global, global);
		ID_Color.put(global,Color.WHITE);
		int helpvariable = 0;
		for( int i=0;i<n;i++)
		{
			for(int j=0;j<m;j++)
			{			
				tab[i][j] = new JButton();
				tab[i][j].setSize((int)k1,(int) k2);
				tab[i][j].setBackground(Color.WHITE);
				tab[i][j].setLocation((int)k1*i,(int)k2*j);
				tab[i][j].setBorder(null);
				tab[i][j].addMouseListener(clik);
				String StringCommand = Integer.toString(helpvariable);
				tab[i][j].setActionCommand(StringCommand);
				show.add(tab[i][j]);		
				helpvariable++;
			}
		}	
		show.setVisible(false);
		frame.add(show);
	}

	Color randcolor()//metoda losujaca kolor zarodka
	{
		int r=0,g=0,b=0;
		Color col;
		Random generator = new Random();
		r = generator.nextInt(254);
		g = generator.nextInt(254);
		b = generator.nextInt(254);
		col = new Color(r, g, b);
		return col;
	}
	int cout = 0;
	void startcount()
	{			
		boolean w = true;
		if(periodyczne.isSelected())
		{
		    w = true;
		}
		else if(nieperiodyczne.isSelected())
		{
			w = false;
		}
		controller.count(Integer.parseInt(N.getText()),w);
		for(int i=0;i<n;i++)
		 {
			 for(int j=0;j<m;j++)
			 {							//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				tab[i][j].setBackground(ID_Color.get(ID.get(controller.board[i][j])));	
			 }
		 }
		cout++;
		info.setText(String.valueOf(cout));

	}

}
