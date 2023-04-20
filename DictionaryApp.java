import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DictionaryApp extends JFrame implements ActionListener {
    private JTextField searchField;
    private JTextArea resultArea;
    private JButton searchButton;
    private String[] words = { "apple", "banana", "cherry", "date", "elderberry", "fig", "grape" };
    private String[] meanings = {
            "a round fruit with red or green skin and white flesh",
            "a long curved fruit with yellow skin",
            "a small round fruit with red or black skin and a hard stone inside",
            "a sweet brown fruit that grows on a palm tree",
            "a small black or red fruit that grows on a bush",
            "a sweet fruit with a soft inside and a leathery skin",
            "a small round fruit with a thin skin that is usually purple or green",
            "","","",""
    };

    public DictionaryApp() {

        // Set up GUI components
        JLabel searchLabel = new JLabel("Search: ");
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);


        searchField.setFont(new Font("Serif", Font.PLAIN, 17));

//        //ResultArea properties.................
        resultArea.setFont(new Font("Serif", Font.BOLD, 17));

        // Add components to JFrame
        JPanel searchPanel = new JPanel(new FlowLayout());
//        searchPanel.setBackground(Color.blue);
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        getContentPane().add(searchPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);



        // Set up JFrame properties
        setTitle("Dictionary");
        setSize(800, 700);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == searchButton) {
            String searchTerm = searchField.getText().toLowerCase();
            int index = binarySearch(words, searchTerm);
            if (index != -1) {
                resultArea.setText("Meaning : "+meanings[index]);
            } else {
                resultArea.setText("Word not found.");
            }
        }
    }

    private int binarySearch(String[] array, String searchTerm) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int comparison = searchTerm.compareToIgnoreCase(array[mid]);
            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        new DictionaryApp();
    }
}
