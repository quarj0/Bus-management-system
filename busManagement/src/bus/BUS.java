package bus;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class BUS extends JFrame implements ActionListener {
    JLabel User,Pass,img;
    JButton Ulogin,signup,Admin,exit;
    JLabel l1,l2;
    JTextField UserField;
    JPasswordField PassField;
    JPanel p1;
    public BUS()
    {
        User = new JLabel("username:");
    //User.setFont(new Font("Areal",Font.BOLD,25));
    User.setBackground(Color.WHITE);
    User.setBounds(300,300,100,30);
    UserField= new JTextField("");             
    UserField.setBounds(400,300,100,30);
    l1= new JLabel();
    l1.setBounds(510,300,150,30);
    add(l1);
    Pass = new JLabel("password"); 
    //Pass.setFont(new Font("Areal",Font.BOLD,25));
    Pass.setBounds(300,350,100,30);
    PassField= new JPasswordField("");    
    PassField.setBounds(400,350,100,30);
    l2= new JLabel();
    l2.setBounds(510,350,150,30);
    add(l2);
    //PassField.setEditable(false);
    Ulogin = new JButton("login");
    Ulogin.setBounds(300,400,100,30);
    Ulogin.addActionListener(this);
    signup = new JButton("SIGNUP");                  
    signup.setBounds(420,400,100,30);
    signup.addActionListener(this);
    Admin = new JButton("Admin");
    Admin.setBounds(360,450,100,30);
    Admin.addActionListener(this);
    exit = new JButton("exit");
    exit.setBounds(750,100,100,30);
    add(exit);
    exit.addActionListener(this);
    add(User);
    add(Pass);
    add(UserField);
    add(PassField);
    add(Ulogin);
    add(signup);
    add(Admin);
    p1=new JPanel();
   p1.setBounds(270,270,420,250);
   add(p1);
    p1.setBackground(getBackground());
    //getContentPane().setBackground(Color.pink);
    JLabel   img=new JLabel(new ImageIcon("C:\\Users\\owusu\\OneDrive\\Desktop\\java-bus\\Bus-management-system\\busManagement\\res\\images\\tor-bus.jpg"));
    img.setBounds(0,0,1280,720);
     getContentPane().add(img);
        
     setSize(1280,720);
    setLayout(null);
    setVisible(true);  
    setDefaultCloseOperation(3);
    }
   
    public static void main(String[] args) {
        Connection conn =null;
        ResultSet rs = null;
        Statement pst =null;
        new BUS();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Ulogin)
       {
          
           String str4 = UserField.getText();
           if(str4.length() == 0)
           {
               l1.setText("MISSING USER NAME");
           }
           
               String str0= PassField.getText();
             if(str0.length() == 0)
           {
               l2.setText("MISSING PASSWORD");
           }  
          if(UserField.getText()!= null && PassField.getText()!=null)
           {
                    try
                    {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bus","root","quarjo")) {
                            String str1 = UserField.getText();
                            String str2= PassField.getText();
                            String str3 = "select userN,pass from users where UserN ='"+str1+"'";
                            Statement pst = conn.createStatement();
                            ResultSet rs = pst.executeQuery(str3);
                            
                            while(rs.next())
                            {
                                if(str2.equals(rs.getString("pass")))
                                {
                                    JOptionPane.showMessageDialog(null,"login successfully");
                                    new searchPAge();
                                    dispose();
                                }
                                else
                                {
                                 JOptionPane.showMessageDialog(null,"incorect details");   
                                }
                            }
                            pst.close();
                            rs.close();
                        }
                    } 
                     catch(HeadlessException | ClassNotFoundException | SQLException xy)
                     {
                         JOptionPane.showMessageDialog(null,xy);
                     }
           }
           else
           {
               JOptionPane.showMessageDialog(null,"your are missing some fields");
           }
       }
        if(e.getSource()==signup)
        {

         new Signup();
          dispose();

       }
        else if(e.getSource() == Admin)
       {
           try
           {
              new AdminLogin();
              dispose();
           }
           catch(Exception x)
           {
               System.out.println(x);
           }
       }
        else  if(e.getSource()==exit)
       {
           JOptionPane.showMessageDialog(null,"THANK YOU FOR VISITING");
           dispose();
       }
        
    }
}
  class searchPAge extends JFrame implements ActionListener{
    JLabel From,Destination,buses,id;
    JCheckBox arr[] = new JCheckBox[100];
    JTextField FromField,DestField,select;
    JButton search,Ulogout,book,user;
    JPanel JP1;
     JTable jt1;
   JComboBox FromCB,DestCB;
    int i=-1,x=0,y=400,width=200,height = 40;
    String a1[]={"source","Old site","Science Shuttle Station","SRC","Valco","KNH","Casford"
    };
     String a2[]={"destination","CoDE","Sandwich Lecture Theatre","New Lecture Theatre","SRC","Old site","Science Shuttle Station"};
    
 public  searchPAge()
        
    {
        /*JP1 = new JPanel();
        JP1.setBounds(0,400,100,40);*/
       // add(JP1);
       id = new JLabel("ID:");
       id.setBounds(350,200,100,30);
       add(id);
        book = new JButton("book");
        book.setBounds(400,250,80,40);
        add(book);
        book.addActionListener(this);
        select = new JTextField("");
        select.setBounds(400,200,100,30);
        add(select);
        
        jt1 = new JTable();
        jt1.setBounds(100,500,700,150);
        add(jt1);
        
          FromCB=new JComboBox(a1);
         FromCB.setBounds(150,180,150,30);
        add(FromCB);
        DestCB=new JComboBox(a2);
         DestCB.setBounds(150,260,150,30);
        add(DestCB);
        search = new JButton("search");
        search.setBounds(180,320,100,30);
        add(search);
        search.addActionListener(this);
        Ulogout = new JButton("logout");
        Ulogout.setBounds(750,100,100,30);
        Ulogout.addActionListener(this);
        add(Ulogout);
        Ulogout.addActionListener(this);
        buses = new JLabel("BUSES FOR SELECTED ROUTE");
        buses.setBounds(100,430,150,100);
        add(buses);
        user =  new JButton("user");
        user.setBounds(10,10,70,50);
        user.addActionListener(this);
        JLabel   img=new JLabel(new ImageIcon("C:\\Users\\owusu\\OneDrive\\Desktop\\java-bus\\Bus-management-system\\busManagement\\res\\images\\table.png"));
        img.setBounds(0,0,1280,720);
         getContentPane().add(img);
        
        setSize(1280,720);
        setLayout(null);
        setVisible(true);
        
        getContentPane().setBackground(Color.pink);
        setDefaultCloseOperation(3);
    }

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getSource() == Ulogout)
            {
                new BUS();
                dispose();
            }
        if(e.getSource()==search){
                    try
                     {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bus","root","quarjo");
                        String  from = (String) FromCB.getSelectedItem();
                        String  dest = (String) DestCB.getSelectedItem();
                        String sql = "select BUS_id,BUS_name,source,Destination,cost from buses where source = '"+from+"' and destination = '"+dest+"';";
                        PreparedStatement pst = conn.prepareStatement(sql);
                        ResultSet ra = pst.executeQuery();
                        jt1.setModel(DbUtils.resultSetToTableModel(ra));
                        jt1.setEnabled(false);
                        pst.close();
                        ra.close();
                        if(from == "source"&& dest == "destination")
                        {
                            JOptionPane.showMessageDialog(null,"select any route");
                        }
                    }
                    catch(Exception e2)
                    {
                        JOptionPane.showMessageDialog(null,e2);
                    }
                }
            /*if(e.getSource()==user)
            {
                //JOptionPane.showMessageDialog(null, "welcome");
                new users();
                dispose();
            }*/
        if(e.getSource() == book)
        {
            //new payment();
            
            int a = Integer.parseInt(select.getText());
            int[] bus = {2560,2561,2562,2563,2564,2565,2566,2567,2568,2569,2570,2222,2471,2572,1111,2571,2573,};int m=0;
            for(int i=0;i<bus.length;i++){
                int te;
            if(select.getText()==null)
            {
                JOptionPane.showMessageDialog(null, "Empty Fields","Error",3);
            }
            te=Integer.parseInt(select.getText());
              if(te==bus[i])
            {
                m=1;
                    new seats(a);
                dispose();   
            } 
              
             }
            if(m==0)
            {
                  JOptionPane.showMessageDialog(null, "NO BUSES ARE FOUND");    
            } 
        }
}
  }
        class users extends JFrame implements ActionListener
        {
            JLabel jl1;
            JButton Ulogout,uacc;
            JTable JT1;
            public users()
            {
                Ulogout =new JButton("LOGOUT");
                Ulogout.setBounds(700,750,100,150);
                Ulogout.addActionListener(this);
                add(Ulogout);
                uacc =new JButton("LOGOUT");
                uacc.setBounds(10,10,50,70);
                uacc.addActionListener(this);
                add(uacc);
                JT1 = new JTable();
                 JT1.setBounds(50,140,800,300);
                add(JT1);
                setSize(1280,720);
                setLayout(null);
                setVisible(true);
                getContentPane().setBackground(Color.pink);
                setDefaultCloseOperation(3);
            }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == Ulogout)
            {
               new BUS();
               dispose();
            }
            if(e.getSource() == uacc)
            {
                try
                {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bus","root","quarjo");
                    String str1 = "select * from buses;";
                    PreparedStatement pst = conn.prepareStatement(str1);
                    ResultSet rs = pst.executeQuery();
                    JT1.setModel(DbUtils.resultSetToTableModel(rs));
                    JT1.setEnabled(false);
                }catch(Exception x){JOptionPane.showMessageDialog(null, e);}
            }
        }
        
        }
    class Signup extends JFrame implements ActionListener 
{


       public JLabel Flabel,Llabel,phnlabel,Userlabel,passlabel;
       public JTextField Ftextfield,Ltextfield,phntextfield,usertextfiled;
       public JPasswordField passfiled;
       public  JButton cancel,save;
       
       
       
       
        /*JLabel Anamelabel,Agmailabel,Aphnolabel,seatno,bus;
        JTextField Anametf,Agmailtf,Aphnotf,Aagetf;
        JRadioButton Mrb,Frb,Orb;
        JButton Asave,Acancel,AProcced;
        ButtonGroup br;*/
   public Signup()
    {
        //JFrame JF2 = new JFrame();
        
    
        Flabel = new JLabel("First name:");
        Flabel.setBounds(300,300,100,50);
        add(Flabel);
        Ftextfield = new JTextField("");                               //textfiled1
        Ftextfield.setBounds(400,310,150,30);
        add(Ftextfield);
        Llabel = new JLabel("Last name:");
        Llabel.setBounds(300,350,100,50);
        add(Llabel);
        Ltextfield = new JTextField("");                               //textfield2
        Ltextfield.setBounds(400,360,150,30);
        add(Ltextfield);
        phnlabel = new JLabel("phone number:");
        phnlabel.setBounds(300,400,100,50);
        add(phnlabel);
        phntextfield = new JTextField("");                               //textfield3
        phntextfield.setBounds(400,410,150,30);
        add(phntextfield);
        Userlabel = new JLabel("user name:");
        Userlabel.setBounds(300,450,100,50);
        add(Userlabel);
        usertextfiled = new JTextField("");                               //textfield4
        usertextfiled.setBounds(400,460,150,30);
        add(usertextfiled);
        passlabel = new JLabel("password:");
        passlabel.setBounds(300,500,100,50);
        add(passlabel);
        passfiled = new JPasswordField("");                               //textfield4
        passfiled.setBounds(400,510,150,30);
        add(passfiled);
        cancel = new JButton("cancel");                                //JButton JB1
        cancel.setBounds(270,560,100,30);
        add(cancel);
        cancel.addActionListener(this);
        save = new JButton("save");                                  //JButton JB2
        save.setBounds(450,560,100,30);
        add(save);
        save.addActionListener(this);
        JLabel   img=new JLabel(new ImageIcon("C:\\Users\\owusu\\OneDrive\\Desktop\\java-bus\\Bus-management-system\\busManagement\\res\\images\\road.jpg"));
        img.setBounds(0,0,1280,720);
        getContentPane().add(img);
        
        setSize(1280,720);
        getContentPane().setBackground(Color.pink);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(3);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== save)
        {
            String ss = Ftextfield.getText();
         
           
            try
            {
                
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bus","root","quarjo");
                Statement st = conn.createStatement();
                PreparedStatement pst = conn.prepareStatement("insert into users values(?,?,?,?,?);");
                pst.setString(1,ss);
                pst.setString(2,Ltextfield.getText());
                pst.setString(3, phntextfield.getText());
                pst.setString(4,usertextfiled.getText());
                pst.setString(5,passfiled.getText());
                 pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"SAVED");
                String str = usertextfiled.getText();
                //st.execute("create table "+str+"(Aname varchar(30),Agmail varchar(30),Aphone int(10),AseatNo int,Abus varchar(30));");
                pst.close();
                conn.close();
            }
            catch(Exception y)
            {
                JOptionPane.showMessageDialog(null,y);
            }
        }
        if(e.getSource()==cancel)
        {
          
           new BUS();
            dispose();
        }
    }
}

class seats extends JFrame implements ActionListener{
    JCheckBox[] JC = new JCheckBox[45];
    JLabel jl;
    int a[]=new int[54];
    JButton proced,back;
    int seat = 1,id;
    public seats(int id)
    {
        this.id = id;
        int arr[] = new int[50];
        int q=0;
        try{
             Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bus","root","quarjo");
                String str = "select * from reserved where busid=?";
                
                PreparedStatement pst = conn.prepareStatement(str);
                pst.setInt(1, id);
                ResultSet rs = pst.executeQuery();
                while(rs.next()){
                    arr[q++] =rs.getInt(2);
                }
                
                
                
                pst.close();
                conn.close();
        }catch(Exception e){
            
        }
        
        
        int x=0 ,y=40 ,c = 0,i=0,j=0,seat=0;
        for(i=0;i<40;i++){
            x+=100;
            if(x>400){
                y+=40;
                x=100;
            }
            seat++;
            JC[i] = new JCheckBox(seat+"");
            JC[i].setBounds(x,y,50,30);
            for(int s=0;s<q;s++){
                       if(arr[s] == seat)
                            JC[i].setEnabled(false);
                    
                   }
            add(JC[i]);
                 
        }
        
        
            
            proced=new JButton("PROCEED");
            proced.setBounds(550,550,100,30);
            proced.addActionListener(this);
            add(proced);
            back = new JButton("back");
            back.setBounds(350,550,100,30);
            add(back);
            back.addActionListener(this);
            JLabel   img=new JLabel(new ImageIcon("C:\\Users\\owusu\\OneDrive\\Desktop\\java-bus\\Bus-management-system\\busManagement\\res\\images\\tor-bus.jpg"));
            img.setBounds(0,0,1280,720);
             getContentPane().add(img);
        setSize(1280,720);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==proced){
            dispose();
            int selected = -1;
        for(int i=0;i<40;i++){
            if(JC[i].isSelected()){
                selected=i+1;
                break;
            }
        }
        if(selected!=-1){
            new Address(selected,id);
        }
        else{
            JOptionPane.showMessageDialog(null, "Please Choose A seat","Error",3);
        }
        
        }
        else
        {
            new searchPAge();
            dispose();
        }
    }
}
 class Address extends JFrame implements ActionListener{
    JLabel Anamelabel,Agmailabel,Aphnolabel,Aage,Agender,Mlabel,Flabel,otherlabel,seatno,bus,lbus;
    JTextField Anametf,Agmailtf,Aphnotf,Aagetf;
    JRadioButton Mrb,Frb,Orb;
    JButton Asave,Acancel,AProcced;
    ButtonGroup br;
    int sel,eyed;
public Address(int selected,int id)
{   sel = selected;
    eyed = id;
    
    bus = new JLabel(""+id);
    lbus = new JLabel("BUS ID");
    seatno=new JLabel(""+selected);
    bus.setBounds(250,150,200,40);
    lbus.setBounds(150,150,200,40);
    add(bus);
    add(lbus);
    
    seatno.setBounds(300,250,150,30);
   
    Anamelabel = new JLabel("name:");
    Anamelabel.setBounds(300,300,100,50);
    add(Anamelabel);
    Anametf = new JTextField("");
    Anametf.setBounds(400,310,200,30);
    add(Anametf);
    Agmailabel = new JLabel("gmail:");
    Agmailabel.setBounds(300,350,100,50);
    add(Agmailabel); 
    Agmailtf = new JTextField("");
    Agmailtf.setBounds(400,360,200,30);
    add(Agmailtf);
    Aphnolabel = new JLabel("phone number");
    Aphnolabel.setBounds(300,400,100,50);
    add(Aphnolabel);
    Aphnotf = new JTextField("");
    Aphnotf.setBounds(400,410,200,30);
    add(Aphnotf);
    Aage = new JLabel("Age");
    Aage.setBounds(300,450,100,50);
    add(Aage);
    Aagetf = new JTextField("");
    Aagetf.setBounds(400,460,200,30);
    add(Aagetf);
    Agender = new JLabel("Gender");
    Agender.setBounds(300,500,100,50);
    add(Agender);
    Mlabel = new JLabel("M");
    Mlabel.setBounds(400,500,100,50);
    add(Mlabel);
    Mrb = new JRadioButton();
    Mrb.setBounds(400,550,20,20);
    add(Mrb);
    Mrb.addActionListener(this);
    Flabel = new JLabel("F");
    Flabel.setBounds(450,500,100,50);
    add(Flabel);
    Frb = new JRadioButton();
    Frb.setBounds(450,550,20,20);
    add(Frb);
    Frb.addActionListener(this);
    otherlabel = new JLabel("other");
    otherlabel.setBounds(500,500,100,50);
    add(otherlabel);
    Orb = new JRadioButton();
    Orb.setBounds(500,550,20,20);
    add(Orb);
    Orb.addActionListener(this);
    br=new ButtonGroup();
    br.add(Mrb);br.add(Frb);br.add(Orb);
    Asave = new JButton("save");
    Asave.setBounds(450,600,100,30);
    add(Asave);
    Asave.addActionListener(this);
    Acancel = new JButton("cancel");
    Acancel.setBounds(300,600,100,30);
    add(Acancel);
    Acancel.addActionListener(this);
    AProcced = new JButton("proceed");
    AProcced.setBounds(400,700,100,30);
   // add(AProcced);
    AProcced.addActionListener(this);
    JLabel   img=new JLabel(new ImageIcon("C:\\Users\\owusu\\OneDrive\\Desktop\\java-bus\\Bus-management-system\\busManagement\\res\\images\\table.png"));
    img.setBounds(0,0,1280,720);
     getContentPane().add(img);
    setSize(1280,720);
    getContentPane().setBackground(Color.PINK);
    setLayout(null);
    setVisible(true);
        
    setDefaultCloseOperation(3);
}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Acancel)
        {
            new seats(100);
            dispose();
        }
        if(e.getSource() == Asave)
        {
             try
            {
                
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bus","root","quarjo");
                
                PreparedStatement pst = conn.prepareStatement("insert into userData values(?,?,?,?,?);");
                pst.setString(1,Anametf.getText());
                pst.setString(2,Agmailtf.getText());
                pst.setString(3, Aphnotf.getText());
                pst.setString(4,Aagetf.getText());
                if( Mrb.isSelected() ){
                    pst.setString(5,"M");}
                if(Frb.isSelected()){
                    pst.setString(5,"F");}
                if(Orb.isSelected()){
                    pst.setString(5,"O");}
                 pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"SAVED","Message",JOptionPane.INFORMATION_MESSAGE);
                pst.close();
                conn.close();
                //-------------------------------------------------dispose();
                new payment(sel,eyed);
                dispose();
            }
            catch(Exception y)
            {
                JOptionPane.showMessageDialog(null,y);
            }
        }
       
    }
}
class payment extends JFrame implements ActionListener{
    JTextField cardnotf,choldertf,busidtf;
    JLabel cardno,cvv,Cholder,busid;
    JButton JB1,JB2;
    JPasswordField cvvpf;
    int sel,eyed;
    public payment(int selected,int id)
    {
        sel = selected;
        eyed = id;
        cardno = new JLabel("card number");
        cardno.setBounds(150,100,100,30);
        add(cardno);
        cardnotf = new JTextField("");
        cardnotf.setBounds(150,140,150,30);
        add(cardnotf);
        cvv = new JLabel("CVV");
        cvv.setBounds(150,180,100,30);
        add(cvv);
        cvvpf = new JPasswordField("");
        cvvpf.setBounds(150,220,150,30);
        add(cvvpf);
        Cholder = new JLabel("card holder");
        Cholder.setBounds(150,260,100,30);
        add(Cholder);
        choldertf = new JTextField("");
        choldertf.setBounds(150,300,150,30);
        add(choldertf);
        busid = new JLabel("BUS ID");
        busid.setBounds(150,340,100,30);
        add(busid);
        busidtf = new JTextField("");
        busidtf.setBounds(150,380,100,30);
        add(busidtf);
        JB1 = new JButton("procced");
        JB1.setBounds(150,420,100,30);
        add(JB1);
        JB1.addActionListener(this);
        JB2 = new JButton("cancel");
        JB2.setBounds(300,420,100,30);
        add(JB2);
        JLabel   img=new JLabel(new ImageIcon("C:\\Users\\owusu\\OneDrive\\Desktop\\java-bus\\Bus-management-system\\busManagement\\res\\images\\pay.jpeg"));
        img.setBounds(0,0,640,480);
         getContentPane().add(img);
        JB2.addActionListener(this);
        getContentPane().setBackground(Color.pink);
        setSize(1280,720);
        setLayout(null);
        setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==JB1)
    {
       
        try{
         
        Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bus","root","quarjo");
               // String str8 = 
                PreparedStatement pst,pst2;
                pst= conn.prepareStatement("insert into payment values(?,?,?,?)");
                pst.setString(1,cardnotf.getText());
                pst.setString(2,cvvpf.getText());//busid,seatid
                pst.setString(3, choldertf.getText());
                pst.setString(4,busidtf.getText());
                pst.executeUpdate();
                
                 JOptionPane.showMessageDialog(null,"PAYMENT SAVED");
                 String str1 = "insert into reserved values(?,?)";
                 PreparedStatement pst1 = conn.prepareStatement(str1);
                 pst1.setInt(2,sel);
                 pst1.setInt(1,eyed);
                 pst1.executeUpdate();
               
                 JOptionPane.showInternalMessageDialog(null,"THANK YOU AND HAVE A JOURNEY");
                 new searchPAge();
                 dispose();
                 pst.close();
                conn.close();
        }catch(Exception ee){
            
            JOptionPane.showMessageDialog(null, ee);
    }
       /* if(JT1.getText()==null ||JT2.getText() == null||JT3.getText() == null)
                {
                    JOptionPane.showMessageDialog(null,"YOUR ARE MISSING SOME TEXT FIELDS");
                }*/
    }
    if(e.getSource()==JB2)
    {
        JOptionPane.showMessageDialog(null,"YOUR TRANSACTION FAILED");
        dispose();
        new seats(100);
        
        dispose();
    }
    }
}
class AdminLogin extends JFrame implements ActionListener{
    JLabel AdminUser,AdminPass,Greet;
    JButton Adminlogin,Adminexit;
    JTextField AdminUserField;
    JPasswordField AdminPassField;
public AdminLogin()
{
    
    //JFrame JF = new JFrame();
    Greet = new JLabel("Welcome Admin..");
    Greet.setBounds(350,250,100,30);
    add(Greet);
    AdminUser = new JLabel("username:");           //jlabel JL2
    AdminUser.setBounds(300,300,100,30);
    AdminUserField= new JTextField("");              //JtextField F
    AdminUserField.setBounds(400,300,100,30);
    AdminPass = new JLabel("password");            //jlabel JL3
    AdminPass.setBounds(300,350,100,30);
    AdminPassField= new JPasswordField("");     //JpasswordField F1
    AdminPassField.setBounds(400,350,100,30);
    Adminlogin = new JButton("login");                      //Jbutton JB
    Adminlogin.setBounds(250,400,100,30);
    Adminlogin.addActionListener(this);
    Adminexit = new JButton("back");
    Adminexit.setBounds(450,400,100,30);
    add(Adminexit);
    Adminexit.addActionListener(this);
    add(AdminUser);
    add(AdminPass);
    add(AdminUserField);
    add(AdminPassField);
    add(Adminlogin);
    JLabel   img=new JLabel(new ImageIcon("C:\\Users\\owusu\\OneDrive\\Desktop\\java-bus\\Bus-management-system\\busManagement\\res\\images\\admin.jpg"));
    img.setBounds(0,0,1280,720);
     getContentPane().add(img);
    
     setSize(1280,720);
    setLayout(null);
    setVisible(true);  
    getContentPane().setBackground(Color.pink);
    setDefaultCloseOperation(3);
}

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==Adminlogin)
      {
          try
          {
              String str1 = AdminUserField.getText();
              String str2 = AdminPassField.getText();
              String str3 = "select userName,Passd from Adminusers where userName = '"+str1+"'";
             Class.forName("com.mysql.cj.jdbc.Driver");
               Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bus","root","quarjo");
              Statement ps = conn.createStatement();
              ResultSet rs = ps.executeQuery(str3);
              while(rs.next())
              {
                  if(str2.equals(rs.getString("Passd"))){
                  JOptionPane.showMessageDialog(null,"login successfully");
                 new Adminpage();   
                  dispose();
                  }
                  else
                  {
                      JOptionPane.showMessageDialog(null,"INCORRECT DETAILS");
                  }
              }
            ps.close();
            rs.close();
            conn.close();
          
          } catch (Exception ex) {
              JOptionPane.showMessageDialog(null,"your are missing sone fields");
          }
      }
      if(e.getSource()==Adminexit)
      {
          new BUS();
          dispose();
      }
    }
}
 class Adminpage extends JFrame implements ActionListener{
    JButton srbuses,srdriver,more,modify,Adminlogout;
    JTable JT1,JT2;
    JLabel Adminbusidl,adminbusn,buscapcity,adminfrom,admindest,admincost,admindriverid,adminbusl,admindrivern,JL10,JL11;
  public Adminpage()
  {
      JT1 = new JTable();
      JT1.setBounds(50,140,800,300);
      add(JT1);
      JT2 = new JTable();
      JT2.setBounds(50,530,800,200);
      add(JT2);      
      srbuses = new JButton("BUSES");
      srbuses.setBounds(100,50,150,30);
      add(srbuses);
      srbuses.addActionListener(this);
      Adminbusidl = new JLabel("BUS ID");
      Adminbusidl.setBounds(100,115,100,30);
      add(Adminbusidl);
      adminbusn = new JLabel("BUS NAME");
      adminbusn.setBounds(200,115,100,30);
      add(adminbusn);
      buscapcity = new JLabel("CAPCITY");
      buscapcity.setBounds(350,115,100,30);
      add(buscapcity);
      adminfrom = new JLabel("FROM");
      adminfrom.setBounds(500,115,100,30);
      add(adminfrom);
      admindest = new JLabel("DESTINTION");
      admindest.setBounds(600,115,100,30);
      add(admindest);
      admincost = new JLabel("COST");
      admincost.setBounds(750,115,100,30);
      add(admincost);
      
      srdriver = new JButton("DRIVERS");
      srdriver.setBounds(100,450,150,30);
      add(srdriver);
      srdriver.addActionListener(this);
      admindriverid = new JLabel("DRIVER ID");
      admindriverid.setBounds(100,500,100,30);
      add(admindriverid);
      adminbusl = new JLabel("BUS ID");
      adminbusl.setBounds(220,500,100,30);
      add(adminbusl);
      admindrivern = new JLabel("NAME");
      admindrivern.setBounds(400,500,100,30);
      add(admindrivern);
      JL10 = new JLabel("SALARY");
      JL10.setBounds(600,500,100,30);
      add(JL10);
      JL11 = new JLabel("GMAIL");
      JL11.setBounds(750,500,100,30);
      add(JL11);
      more = new JButton("MORE");
      more.setBounds(750,750,100,30);
      add(more);
      more.addActionListener(this);
      modify = new JButton("MODIFICATIONS");
      modify.setBounds(400,50,150,30);
      add(modify);
      modify.addActionListener(this);
      Adminlogout = new JButton("LOGOUT");
      Adminlogout.setBounds(750,50,100,30);
      add(Adminlogout);
      Adminlogout.addActionListener(this);
      JLabel   img=new JLabel(new ImageIcon("C:\\Users\\owusu\\OneDrive\\Desktop\\java-bus\\Bus-management-system\\busManagement\\res\\images\\admin.jpg"));
      img.setBounds(0,0,1280,720);
       getContentPane().add(img);
      setSize(1280,720);
      setLayout(null);
      setVisible(true);
      setDefaultCloseOperation(3);
      getContentPane().setBackground(Color.pink);
  }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == srbuses)
        {
            try
        {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bus","root","quarjo");
                String str1 = "select * from buses;";
                PreparedStatement pst = conn.prepareStatement(str1);
                ResultSet rs = pst.executeQuery();
                JT1.setModel(DbUtils.resultSetToTableModel(rs));
                JT1.setEnabled(false);
                 //scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                //JOptionPane.showMessageDialog(null,"SAVED","Message",JOptionPane.INFORMATION_MESSAGE);
                pst.close();
                conn.close();
        }catch(Exception e2)
        {
            JOptionPane.showMessageDialog(null,e2);
        }
        }
       if(e.getSource() == srdriver)
       {
           try
        {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bus","root","quarjo");
                String str2 = "select * from drivers;";
                PreparedStatement pst = conn.prepareStatement(str2);
                ResultSet rs1 = pst.executeQuery();
                JT2.setModel(DbUtils.resultSetToTableModel(rs1));
                JT2.setEnabled(false);
                
                pst.close();
                conn.close();
        }catch(Exception e2)
        {
            JOptionPane.showMessageDialog(null,e2);
        }
       }
       if(e.getSource() == more)
       {
           new Adnxtpage();
           dispose();
       }
       if(e.getSource() == modify)
       {
           new modifications();
           dispose();
       }
       if(e.getSource() == Adminlogout)
       {
          new BUS();
           dispose();
       }
    }
 }
class Adnxtpage extends JFrame implements ActionListener{
    JButton pay,backb;
    JLabel JL12,JL13,JL14,JL15,JL16,img;
    JTable JT3,JT4;

    public Adnxtpage()       
    {
//        img = new JLabel(new ImageIcon("C:\\Users\\bus\\OneDrive\\Desktop\\pro4.jpg"));
//        img.setBounds(0,0,900,900);
       // add(img);
        pay = new JButton("PAYMENT DETAILS");
        pay.setBounds(100,100,150,30);
        add(pay);
        pay.addActionListener(this);
        JT3 = new JTable();
        JT3.setBounds(50,200,800,300);
        add(JT3);
        JL12 = new JLabel("CARD NUMBER");
        JL12.setBounds(60,170,100,30);
        JL12.setForeground(Color.red);
        add(JL12);
        JL13 = new JLabel("CVV");
        JL13.setForeground(Color.red);
        JL13.setBounds(300,170,100,30);
        JL13.setForeground(Color.red);
        add(JL13);
        JL14 = new JLabel("CARD HOLDER");
        JL14.setBounds(500,170,150,30);
        JL14.setForeground(Color.red);
        add(JL14);
        JL15 = new JLabel("BUS ID");
        JL15.setBounds(670,170,150,30);
        JL12.setForeground(Color.red);
        add(JL15);
        backb = new JButton("BACK");
        backb.setBounds(100,500,100,30);
        add(backb);
        backb.addActionListener(this);
        setSize(1280,720);
        JLabel   img=new JLabel(new ImageIcon("C:\\Users\\owusu\\OneDrive\\Desktop\\java-bus\\Bus-management-system\\busManagement\\res\\images\\pay.jpeg"));
        img.setBounds(0,0,1280,720);
         getContentPane().add(img);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(3);
        getContentPane().setBackground(Color.pink);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == pay)
       {
             try
        {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bus","root","quarjo");
                String str3 = "select * from payment;";
                PreparedStatement pst = conn.prepareStatement(str3);
                ResultSet rs3 = pst.executeQuery();
                JT3.setModel(DbUtils.resultSetToTableModel(rs3));
                JT3.setEnabled(false);
                pst.close();
                conn.close();
        }catch(Exception e2)
        {
            JOptionPane.showMessageDialog(null,e2);
        }
       }
       if(e.getSource() == backb)
       {
           new Adminpage();
           dispose();
       }
    }
}
class modifications extends JFrame implements ActionListener{
    JButton Add,Remove,back;
   
    public modifications()
    {
        Add = new JButton("ADD");
        Add.setBounds(90,60,100,30);
        add(Add);
        Add.addActionListener(this);
        Remove = new JButton("REMOVE");
        Remove.setBounds(90,120,100,30);
        add(Remove);
        Remove.addActionListener(this);
        back = new JButton("back");
        back.setBounds(90,180,100,30);
        add(back);
        back.addActionListener(this);
        getContentPane().setBackground(Color.pink);
//         setTitle("");
        JLabel   img=new JLabel(new ImageIcon("C:\\Users\\owusu\\OneDrive\\Desktop\\java-bus\\Bus-management-system\\busManagement\\res\\images\\road.jpg"));
        img.setBounds(0,0,1280,720);
         getContentPane().add(img);
        setSize(1280,720);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
     if(e.getSource() == Add)
       {
            new buses_1();
            dispose();
       }
       if(e.getSource()==Remove)
       {
           new deleteBuses();
           dispose();
       }
          if(e.getSource() == back)
          {
             new Adminpage();
              dispose();
          }
    }
}
class buses_1 extends JFrame implements ActionListener {
    JTextField Bid,bname,capcity,source,Destination,cost;
    JLabel id,name,cp,src,dest,price;
    JButton can,save;
    public buses_1()
    {
        id = new JLabel("BUS ID");
        id.setBounds(250,300,100,50);
        add(id);
        Bid = new JTextField("");
        Bid.setBounds(400,310,150,30);
        add(Bid);
        name = new JLabel(" BUS NAME");
        name.setBounds(250,360,100,30);
        add(name);
        bname = new JTextField("");
        bname.setBounds(400,360,150,30);
        add(bname);
        cp = new JLabel("CAPCITY");
        cp.setBounds(250,410,150,30);
        add(cp);
        capcity = new JTextField("");
        capcity.setBounds(400,410,50,30);
        add(capcity);
        src = new JLabel("STARTING POINT");
         src.setBounds(250,460,150,30);
         add(src);
         source = new JTextField("");
         source.setBounds(400,460,100,30);
         add(source);
         dest = new JLabel("END POINT");
         dest.setBounds(250,510,150,30);
         add(dest);
         Destination = new JTextField("");
         Destination.setBounds(400,510,100,30);
         add(Destination);
         price = new JLabel("COST");
         price.setBounds(250,560,100,30);
         add(price);
         cost = new JTextField("");
         cost.setBounds(400,560,100,30);
         add(cost);
         save = new JButton("SAVE");
         save.setBounds(500,600,100,30);
         add(save);
         save.addActionListener(this);
         can = new JButton("CANCEL");
         can.setBounds(200,600,100,30);
         add(can);
         can.addActionListener(this);
         JLabel   img=new JLabel(new ImageIcon("C:\\Users\\owusu\\OneDrive\\Desktop\\java-bus\\Bus-management-system\\busManagement\\res\\images\\road.jpg"));
         img.setBounds(0,0,1280,720);
          getContentPane().add(img);
        setSize(1280,720);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         if(e.getSource()==save)
       {
            try
            {
                
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bus","root","quarjo");
                
                PreparedStatement pst = conn.prepareStatement("insert into buses values(?,?,?,?,?,?);");
                pst.setString(1,Bid.getText());
                pst.setString(2,bname.getText());
                pst.setString(3, capcity.getText());
                pst.setString(4,source.getText());
                pst.setString(5,Destination.getText());
                pst.setString(6,cost.getText());
                 pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"SAVED","Message",JOptionPane.INFORMATION_MESSAGE);
                pst.close();
                conn.close();
            }
            catch(Exception y)
            {
                JOptionPane.showMessageDialog(null,"your are missing some fields.");
            }
       }
       if(e.getSource()==can)
       {
           new modifications();
           dispose();
       }
    }
}
class deleteBuses extends JFrame implements ActionListener{
    JLabel id;
    JButton bck,del;
    JTextField box ;
    public deleteBuses()
    {
        id= new JLabel("BUS ID");
        id.setBounds(100,50,100,30);
        add(id);
        box = new JTextField("");
        box.setBounds(100,100,100,30);
        add(box);
        bck = new JButton("BACK");
        bck.setBounds(100,200,100,30);
        add(bck);
        bck.addActionListener(this);
        del = new JButton("DELETE");
        del.setBounds(100,150,100,30);
        add(del);
        del.addActionListener(this);
        getContentPane().setBackground(Color.pink);
        JLabel   img=new JLabel(new ImageIcon("C:\\Users\\owusu\\OneDrive\\Desktop\\java-bus\\Bus-management-system\\busManagement\\res\\images\\photo.gif"));
        img.setBounds(0,0,620,349);
         getContentPane().add(img);
        setTitle("deletion");
        setLayout(null);
        setSize(620,349);
        setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == del)
        {
            try
            {
               int a =Integer.parseInt(box.getText());
                String s = "delete from buses where BUS_id='"+a+"';";
                Class.forName("com.mysql.cj.jdbc.Driver");
                 java.sql.Connection c= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bus","root","quarjo");
                  Statement ps = c.createStatement();
                
                 ps.executeUpdate(s);
                JOptionPane.showMessageDialog(null,"DELETED");
                c.close();
                ps.close();
            }catch(Exception f)
            {
                JOptionPane.showMessageDialog(null,f);
            }
            
        }
        if(e.getSource() == bck)
        {
            new modifications();
        }
    }
}
  