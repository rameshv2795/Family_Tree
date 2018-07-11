package familytree;
import java.io.File;
import javax.swing.JOptionPane;

/**
 * @author Vinod
 */
public class Main {

    public static void main(String[] args) {
        BaseTree treeType;
        Object[] treeValues = { "Family Tree", "Binary Tree", "Third" };
        Object selectedTree = JOptionPane.showInputDialog(null,
        "Select Tree Type", "Type",
        JOptionPane.INFORMATION_MESSAGE, null,
        treeValues, treeValues[0]);
        
        if(selectedTree == "Family Tree"){
            treeType = new FamilyTree();
            Window frame = new Window(treeType);  
            //frame.setSize(2000,2000);
        }
        else if(selectedTree == "Binary Tree"){
            treeType = new BinaryTree();
            //Window2 frame = new Window2(treeType);
        }
    }

}
