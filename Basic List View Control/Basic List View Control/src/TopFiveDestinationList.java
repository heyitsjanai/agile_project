import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class TopFiveDestinationList {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	TopDestinationListFrame topDestinationListFrame = new TopDestinationListFrame();
                topDestinationListFrame.setTitle("Top 5 Destination List");
                topDestinationListFrame.setVisible(true);
            }
        });
    }
}


class TopDestinationListFrame extends JFrame {
    private DefaultListModel listModel;

    public TopDestinationListFrame() {
        super("Top Five Destination List");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900, 750);

        listModel = new DefaultListModel();


        //Janai's top 5 travel destinations. All pictures from WikiCommons images.
        addDestinationNameAndPicture("1. Paris, France: Known as the city of lights and the city of love. Wine, food, fashion all await!", new ImageIcon(getClass().getResource("/resources/paris.jpg")));
        addDestinationNameAndPicture("2. Amsterdam, Netherlands: Called the Venice of the North, Amsertdam welcomes you to come explore its canal-ridden streets!", new ImageIcon(getClass().getResource("/resources/amsterdam.jpg")));
        addDestinationNameAndPicture("3. Zermatt, Switzerland: Beauty and wonder abound in this small Swiss village. With no cars in sight, it is one of the greenest cities on Earth!", new ImageIcon(getClass().getResource("/resources/zermatt.jpg")));
        addDestinationNameAndPicture("4. Tokyo, Japan: The capital of Japan, Tokyo offers both cutting-edge fashion, food, and technology mixed with the old World culture of Japan!", new ImageIcon(getClass().getResource("/resources/tokyo.jpg")));
        addDestinationNameAndPicture("5. Barcelona, Spain: Not only is their football club world reknowned, Barcelona is a cultural hub that is sure to satisfy your cultural cravings!", new ImageIcon(getClass().getResource("/resources/barcelona.jpg")));
        
        JList list = new JList(listModel);
        
        JScrollPane scrollPane = new JScrollPane(list);
        
        //instantiated a new JLabel object to the JFrame to add my name label
        JLabel nameLabel = new JLabel("Developer: Janai Cano");

        TextAndIconListCellRenderer renderer = new TextAndIconListCellRenderer(2);
        
        //added customizations to list
        list.setBackground(Color.darkGray);
        list.setSelectionBackground(Color.pink);
        list.setCellRenderer(renderer);
        
        getContentPane().add(nameLabel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private void addDestinationNameAndPicture(String text, Icon icon) {
        TextAndIcon tai = new TextAndIcon(text, icon);
        listModel.addElement(tai);
    }
}


class TextAndIcon {
    private String text;
    private Icon icon;

    public TextAndIcon(String text, Icon icon) {
        this.text = text;
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
}


class TextAndIconListCellRenderer extends JLabel implements ListCellRenderer {
    private static final Border NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);

    private Border insideBorder;

    public TextAndIconListCellRenderer() {
        this(0, 0, 0, 0);
    }

    public TextAndIconListCellRenderer(int padding) {
        this(padding, padding, padding, padding);
    }

    public TextAndIconListCellRenderer(int topPadding, int rightPadding, int bottomPadding, int leftPadding) {
        insideBorder = BorderFactory.createEmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding);
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList list, Object value,
    int index, boolean isSelected, boolean hasFocus) {
        // The object from the combo box model MUST be a TextAndIcon.
        TextAndIcon tai = (TextAndIcon) value;

        // Sets text and icon on 'this' JLabel.
        setText(tai.getText());
        setIcon(tai.getIcon());

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        Border outsideBorder;

        if (hasFocus) {
            outsideBorder = UIManager.getBorder("List.focusCellHighlightBorder");
        } else {
            outsideBorder = NO_FOCUS_BORDER;
        }

        setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
        setComponentOrientation(list.getComponentOrientation());
        setEnabled(list.isEnabled());
        setFont(list.getFont());

        return this;
    }

    // The following methods are overridden to be empty for performance
    // reasons. If you want to understand better why, please read:
    //
    // http://java.sun.com/javase/6/docs/api/javax/swing/DefaultListCellRenderer.html#override

    public void validate() {}
    public void invalidate() {}
    public void repaint() {}
    public void revalidate() {}
    public void repaint(long tm, int x, int y, int width, int height) {}
    public void repaint(Rectangle r) {}
}