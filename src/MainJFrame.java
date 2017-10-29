import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class MainJFrame extends JFrame {
    private int screenW=Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenH=Toolkit.getDefaultToolkit().getScreenSize().height;
    private JDesktopPane jdp = new JDesktopPane();
    private JMenuBar jmb = new JMenuBar();
    private JInternalFrame jifL = new JInternalFrame();
    private JInternalFrame jifK = new JInternalFrame();
    private Container cpL;
    private Container cpK;
    private JPanel janL = new JPanel(new GridLayout(1,6,5,0));
    private JPanel janB = new JPanel(new GridLayout(1,2,5,5));
    private JPanel janK = new JPanel(new GridLayout(3,3,3,3));
    private JMenu jmf = new JMenu("File");
    private JMenu jmt = new JMenu("Tool");
    private JMenu jmh = new JMenu("Help");
    private JMenu jma = new JMenu("About");
    private JMenuItem jmiE = new JMenuItem("Exit");
    private JMenuItem jmiL = new JMenuItem("Lotto");
    private JMenuItem jmiK = new JMenuItem("Keyboard");
    private JMenuItem jmiS = new JMenuItem("Sign out");
    private JButton jbtnR = new JButton("Generate");
    private JButton jbtnC = new JButton("Close");
    private JLabel jlbs[] = new JLabel[6];
    private JButton jbtnsK[] = new JButton[10];
    private JTextField jtfK = new JTextField();
    private   String tmp = "";
    private int data[] = new int[6];
    private Random rnd = new Random(System.currentTimeMillis());

    Boolean checkjifL = true;
    Boolean checkjifK = true;


    public LoginJFrame lgjf = new LoginJFrame();
    public MainJFrame(LoginJFrame lgjf1){
        lgjf=lgjf1;
        initComp();
    }
    public void initComp(){
        setBounds(screenW/2-250,screenH/2-225,500,450);
        jifL.setBounds(0,0,200,100);
        jifK.setBounds(0,0,250,350);

        cpL=jifL.getContentPane();
        cpK=jifK.getContentPane();
        cpL.setLayout(new BorderLayout(3,3));
        cpL.add(janL,BorderLayout.CENTER);
        cpL.add(janB,BorderLayout.SOUTH);
        janB.add(jbtnR);
        janB.add(jbtnC);

        jtfK.setEditable(false);
        for(int i=0;i<6;i++) {
            jlbs[i]=new JLabel();
            janL.add(jlbs[i]);
            jlbs[i].setHorizontalAlignment((int) CENTER_ALIGNMENT);
        }
        LottoGenerate();

        for(int i=0;i<9;i++){
            jbtnsK[i]=new JButton(Integer.toString(i+1));

            String x =Integer.toString(i+1);
            jbtnsK[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tmp=tmp+x;
                    jtfK.setText(tmp);
                }
            });
        }
        Random ranK = new Random();
        int x;
        int dataK[]=new int[10];
        for(int i=0;i<9;i++){
            x=ranK.nextInt(9);
            dataK[i]=x;
            janK.add(jbtnsK[x]);
            for(int j=0;j<i;j++){
                if(dataK[j]==dataK[i]){
                    i--;
                }
            }
        }
        cpK.add(jtfK,BorderLayout.NORTH);
        cpK.add(janK,BorderLayout.CENTER);

        this.setJMenuBar(jmb);
        this.setContentPane(jdp);
        jdp.add(jifL);
        jdp.add(jifK);
        jmb.add(jmf);
        jmb.add(jmt);
        jmb.add(jmh);
        jmb.add(jma);

        jmf.add(jmiS);
        jmf.add(jmiE);

        jmt.add(jmiL);
        jmt.add(jmiK);
        jmiE.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jbtnC.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                checkjifL=true;
                jifL.setVisible(false);
            }
        });
        jbtnR.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                LottoGenerate();
            }
        });
        jmiS.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                lgjf.setVisible(true);
                dispose();
            }
        });
        jmiE.setAccelerator(KeyStroke.getKeyStroke('X',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        jmiS.setAccelerator(KeyStroke.getKeyStroke('C',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                lgjf.setVisible(true);
            }
        });

        jmiL.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkjifL==true){
                    checkjifL=false;
                    jifL.setVisible(true);
                }else{
                    checkjifL=true;
                    jifL.setVisible(false);
                }
            }
        });

        jmiK.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(checkjifK==true){
                    checkjifK=false;
                    jifK.setVisible(true);
                }else{
                    jifK.setVisible(false);
                    jtfK.setText("");
                    tmp="";
                    checkjifK=true;
                }
            }
        });
    }

    private void LottoGenerate(){
        for(int i=0;i<6;i++){
            data[i]=rnd.nextInt(42)+1;
            jlbs[i].setText(Integer.toString(data[i]));
            for(int j=0;j<i;j++){
                if(data[j]==data[i]){
                    i--;
                }
            }
        }
    }
}
