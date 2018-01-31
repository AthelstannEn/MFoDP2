package entity;
 
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
 
 
public class CryptLab2 extends JPanel {
 
    public CryptLab2() {
 
        encryptButton.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                char[] key = keyArea.getText().toLowerCase().toCharArray();
                char[] text = textArea.getText().toLowerCase().toCharArray();
 
                if (key.length >= text.length){
                    outputText = encrypt(key, text);
                    newTextArea.setText(new String(outputText));
                } else {
                    JOptionPane.showMessageDialog(CryptLab2.this, "Довжина ключа повина бути рівною довжині текста");
                }
                
            }
        });
        decryptButton.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                char[] key = keyArea.getText().toLowerCase().toCharArray();
                char[] text = textArea.getText().toLowerCase().toCharArray();
 
                if (key.length >= text.length){
                    outputText = decrypt(key, text);
                    newTextArea.setText(new String(outputText));
                } else {
                    JOptionPane.showMessageDialog(CryptLab2.this, "Довжина ключа повина бути рівною довжині текста");
                }
 
            }
        });
        add(new JLabel("Ключ:"));
        add(keyArea);
        add(new JLabel("Вхідний  текст:"));
        add(textArea);
        add(new JLabel("Результат:"));
        add(newTextArea);
        add(encryptButton);
        add(decryptButton);
 
    }
    //генератор таблицы Виженера
    private static char[][] genTable() {
        char[][] table = new char[26][26];
 
        for (int i = 0; i < 26; i++) {
            int off = i;
 
            for (int j = 0; j < 26; j++) {
 
                if(off == 26) {
                    off = 0;
                }
                table[i][j] = (char)(97 + off);
                off++;
            }
        }
        return table;
    }
    //метод шифрования
    private static  String encrypt(char[] key, char[] text) {
        char[] nText = new char[text.length];
        int k;
        int t;
        char[][] table = genTable();
 
        for (int i = 0; i < text.length; i++) {
            k = (int)key[i] - 97;
            t = (int)text[i] - 97;
            nText[i] = table[k][t];
            
        }
        return new String(nText);
    }
    //метод расшифровки
    private static  String decrypt(char[] key, char[] text) {
        char[] nText = new char[text.length];
        int k;
        int t;
        char[][] table = genTable();
 
        for (int i = 0; i < text.length; i++) {
            k = (int)key[i] - 97;
            t = (int)text[i] - 97;
            if (k > t) {
                nText[i] = table[26 + (t - k)][0];
            } else {
                nText[i] = table[t - k][0];
            }
            
 
        }
        return new String(nText);
    }
 
    private static void createAndShowGUI(){
 
        JFrame window = new JFrame("Шифровка");
        window.setSize(new Dimension(400, 210));
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(new CryptLab2());
    }
 
    public static void main(String args[]) {
        Runnable doCreateAndShowGUI = new Runnable() {
 
            public void run() {
                createAndShowGUI();
            }
        };
        SwingUtilities.invokeLater(doCreateAndShowGUI);
    }
    
    private String outputText;
    private JButton encryptButton = new JButton("En");
    private JButton decryptButton = new JButton("De");
    private JTextField keyArea = new JTextField("ABCDEFABCDEFABCDEFABCDEFABCD", 35);
    private JTextField textArea = new JTextField("csasxtitukswtgqugwyqvrkwaqjb",35);
    private JTextField newTextArea = new JTextField(35);
}