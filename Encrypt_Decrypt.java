package  image.operation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Encrypt_Decrypt  implements  ActionListener{


    JFrame f;
    JTextField t1;
    JButton  b2, b3;
    JLabel l1;

    Encrypt_Decrypt(){

             f = new JFrame();
            f.setTitle("Image - Encryption/Decryption");
            f.setSize(400,550);
            f.setLocationRelativeTo(null); // frame will come on the centre.
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            l1 = new JLabel("Enter Secret Key Below ");
            l1.setFont(new Font("Tahoma", Font.BOLD, 25));
            f.add(l1);

            t1= new JTextField(10);
            t1.setFont(new Font("roboto", Font.BOLD, 25));
            f.add(t1);

            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("image/operation/images/Hacker.jpg"));
            Image i2 = i1.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel l1 = new JLabel(i3);
            f.add(l1);

            b2 = new JButton();
            b2.setText("Choose File & Encrypt");
            b2.setBackground(Color.BLACK);
            b2.setForeground(Color.WHITE);
            b2.setFont(new Font("Tahoma", Font.BOLD, 20));
            b2.addActionListener(this);
            f.add(b2);

            b3 = new JButton();
            b3.setText(" Choose File & Decrypt");
            b3.setBackground(Color.BLACK);
            b3.setForeground(Color.WHITE);
            b3.setFont(new Font("Tahoma", Font.BOLD, 20));
            b3.addActionListener(this);
            f.add(b3);


            f.setLayout(new FlowLayout());
            f.setVisible(true);

    }

    private void operate(int key) {

        JFileChooser fileChooser = new JFileChooser();   // JFileChooser() is an empty constructor that points to users default directory.
        fileChooser.showOpenDialog(null); // Displays the dialog box i.e. default directory
        File file = fileChooser.getSelectedFile();  // selected file from the directory will be stored in file variable.

        //To use data from file i.e. read the file
        try{
            FileInputStream fis = new FileInputStream(file); //This class reads the data from a specific file (byte by byte). It is usually used to read the contents of a file with raw bytes, such as images.
            byte[] data = new byte[fis.available()]; //  converting data into the file into bytes and storing it into byte array.
            fis.read(data);  // read till the end of the stream
            int i =0;
            for(byte b:data){
                data[i]= (byte)(b^key);
                i++;
            }
            FileOutputStream fos = new FileOutputStream(file);  // writes the data
            fos.write(data);
            fos.close();
            fis.close();

            JOptionPane.showMessageDialog(null,"Done");

        }catch (Exception e){
            e.printStackTrace();
        }

    }




    public static void main(String[] args) {
        new Encrypt_Decrypt().f.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        String text = t1.getText();
        int temp = Integer.parseInt(text);
        if(ae.getSource() == b2) {
            operate(temp);
        }


        if(ae.getSource() == b3) {
            operate(temp);
        }
    }
}

