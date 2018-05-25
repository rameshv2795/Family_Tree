package familytree;
import java.io.File;
import javax.swing.JOptionPane;

/**
 * @author Vinod
 */
public class FamilyTree {

    public static void main(String[] args) {
        BaseTree treeType;
        Object[] treeValues = { "Family Tree", "Binary Tree", "Third" };
        Object selectedTree = JOptionPane.showInputDialog(null,
        "Select Tree Type", "Type",
        JOptionPane.INFORMATION_MESSAGE, null,
        treeValues, treeValues[0]);
        
        if(selectedTree == "Family Tree"){
            treeType = new Tree();
            Window frame = new Window(treeType);  
        }
    }

}
